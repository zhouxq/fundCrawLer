package com.jxnu.finance.utils;

import com.jxnu.finance.httpRest.model.http.HttpPropers;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;


/**
 * @author shoumiao_yao
 * @date 2016-07-05
 */
public class ResponseUtils {

    private static final String CHANNEL_KEY = "channel";
    private static final String KEEP_ALIVE_KEY = "keepAlive";

    public static void response(HttpPropers req, Object jsonObject) {
        response(req, JsonUtils.ToJson(jsonObject));
    }

    public static void response(HttpPropers req, String resp) {
        Channel channel = (Channel) req.getProperty(CHANNEL_KEY);
        Boolean keepAlive = (Boolean) req.getProperty(KEEP_ALIVE_KEY);
        FullHttpResponse response;
        if (StringUtils.isNotEmpty(resp)) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(resp, CharsetUtil.UTF_8)));
        } else {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        }
        if (null != response) {
            response.headers().set("Content-Type", "application/json; charset=UTF-8");
            response.headers().set("Access-Control-Allow-Origin", "*");
            response.headers().set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.headers().set("Access-Control-Max-Age", "3600");
            response.headers().set("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
            response.headers().set("Access-Control-Allow-Credentials", "true");
            if (keepAlive.booleanValue()) {
                response.headers().set("Content-Length", Integer.valueOf(response.content().readableBytes()));
                response.headers().set("Connection", "keep-alive");
            }
            ChannelFuture channelFuture = channel.writeAndFlush(response);
            if (!keepAlive.booleanValue()) {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }
}
