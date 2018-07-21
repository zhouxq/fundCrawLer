package com.jxnu.finance.httpRest.http;

import com.google.common.eventbus.EventBus;
import com.jxnu.finance.httpRest.http.codec.HttpCodec;
import com.jxnu.finance.httpRest.http.httpCache.UrlCache;
import com.jxnu.finance.httpRest.model.http.HttpPropers;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);
    private EventBus eventBus;
    private HttpRequest httpRequest;
    private String IP_KEY = "ip";
    private String CHANNEL_KEY = "channel";
    private String KEEP_ALIVE_KEY = "keepAlive";

    public HttpHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            httpRequest = (HttpRequest) msg;
            if (HttpHeaders.is100ContinueExpected(httpRequest)) {
                send100Continue(ctx);
            }
            if (httpRequest.getMethod() != HttpMethod.GET && httpRequest.getMethod() != HttpMethod.POST) {
                logger.error("unsupported request method:{} request.", httpRequest.getUri());
                ctx.channel().close();
                return;
            }
        }
        if (msg instanceof HttpContent) {
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(this.httpRequest.getUri());
            String url = queryStringDecoder.path();
            HttpCodec httpCodec = UrlCache.getEncodeMap().get(url);
            Class clazz = UrlCache.getClassMap().get(url);
            Object object = null;
            if (httpRequest.getMethod() == HttpMethod.GET) {
                Map<String, List<String>> map = queryStringDecoder.parameters();
                object = httpCodec.decode(map, clazz);
            } else {
                HttpContent httpContent = (HttpContent) msg;
                ByteBuf byteBuf = httpContent.content();
                if (byteBuf.capacity() == 0) return;
                String content = byteBuf.toString(CharsetUtil.UTF_8);
                object = httpCodec.decode(content, clazz);
            }
            if (object instanceof HttpPropers) {
                HttpPropers httpPropers = (HttpPropers) object;
                InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                httpPropers.setProperty(this.IP_KEY, remoteAddress.getAddress().getHostAddress());
                httpPropers.setProperty(this.CHANNEL_KEY, ctx.channel());
                httpPropers.setProperty(this.KEEP_ALIVE_KEY, Boolean.valueOf(HttpHeaders.isKeepAlive(this.httpRequest)));
            }
            if (object != null) {
                eventBus.post(object);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("netty exceptionCaught error:{}", ExceptionUtils.getMessage(cause));
        ctx.close();
    }


    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.write(response);
    }
}
