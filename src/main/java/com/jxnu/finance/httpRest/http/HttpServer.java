package com.jxnu.finance.httpRest.http;

import com.google.common.eventbus.EventBus;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class HttpServer {
    private final static Logger logger = LoggerFactory.getLogger(HttpServer.class);
    @Value("${http.ip}")
    private String ip;
    @Value("${http.port}")
    private Integer port;
    @Value("${http.boosThreadNum}")
    private Integer boosThreadNum;
    @Value("${http.workThreadNum}")
    private Integer workThreadNum;
    @Resource
    private EventBus eventBus;

    @PostConstruct
    public void init() {
        Integer proNum = Runtime.getRuntime().availableProcessors();
        EventLoopGroup bossGroup = new NioEventLoopGroup(proNum / 2);
        EventLoopGroup workGroup = new NioEventLoopGroup(proNum);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)//
                    .channel(NioServerSocketChannel.class)//
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 这几个都是框架需要，完成HTTP协议的编解码所用
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpHandler(eventBus));
                        }
                    });
            // 开始真正绑定端口进行监听
            ChannelFuture future = bootstrap.bind(ip, port).sync();
            future.addListener(new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    if (future.isDone()) {
                        logger.info("bind port:{} success...", port);
                    }
                }
            });
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("error:{}", ExceptionUtils.getMessage(e));
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            logger.info("http server exit...");
        }
    }
}
