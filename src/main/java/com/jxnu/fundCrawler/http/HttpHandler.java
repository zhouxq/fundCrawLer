package com.jxnu.fundCrawler.http;

import com.google.common.eventbus.EventBus;
import com.jxnu.fundCrawler.http.codec.HttpCodec;
import com.jxnu.fundCrawler.http.httpCache.UrlCache;
import com.jxnu.fundCrawler.http.codec.KvCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);
    private EventBus eventBus;
    private String url;

    public HttpHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            QueryStringDecoder decoder = new QueryStringDecoder(req.getUri());
            url = decoder.path();
            if (req.getMethod() == HttpMethod.GET) {
                Map<String, List<String>> map = decoder.parameters();
                HttpCodec httpCodec = UrlCache.getEncodeMap().get(url);
                Class clazz = UrlCache.getClassMap().get(url);
                Object object = httpCodec.decode(map, clazz);
                eventBus.post(object);
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf byteBuf = httpContent.content();
            if (byteBuf.capacity() == 0) return;
            byte[] bytes = byteBuf.array();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("netty exceptionCaught error:{}", ExceptionUtils.getMessage(cause));
        ctx.close();
    }
}
