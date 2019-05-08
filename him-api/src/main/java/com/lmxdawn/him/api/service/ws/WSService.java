package com.lmxdawn.him.api.service.ws;

import com.lmxdawn.him.api.vo.req.WSMessageReqVO;
import com.lmxdawn.him.common.protobuf.WSMessageProtoOuterClass;
import com.lmxdawn.him.common.protobuf.WSMessageReqProtoOuterClass;
import com.lmxdawn.him.common.protobuf.WSMessageResProtoOuterClass;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Date;

@Component
@Slf4j
public class WSService {

    @Value("${ws.port}")
    private int wsPort;

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    /**
     * 启动 ws server
     *
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(wsPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new WSServerInitializer());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("启动 ws server 成功");
        }
    }

    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        log.info("关闭 ws server 成功");
    }

    /**
     * 发送 Google Protocol 编码消息
     *
     * @param wsMessageReqVO 消息
     */
    public Boolean sendMsg(Long uid, WSMessageReqVO wsMessageReqVO) {
        Channel channel = SessionSocketHolder.get(uid);

        if (null == channel) {
            log.info("客户端[" + wsMessageReqVO.getReceiveId() + "]不在线！");
            return false;
        }

        WSMessageProtoOuterClass.WSMessageProto wsMessageProto = WSMessageProtoOuterClass.WSMessageProto.newBuilder()
                .setSenderId(wsMessageReqVO.getSenderId())
                .setReceiveId(wsMessageReqVO.getReceiveId())
                .setMsgType(wsMessageReqVO.getType())
                .setMsgContent(wsMessageReqVO.getMsgContent())
                .setSenderId(wsMessageReqVO.getSenderId())
                .build();

        WSMessageResProtoOuterClass.WSMessageResProto wsMessageResProto = WSMessageResProtoOuterClass.WSMessageResProto.newBuilder()
                .setType(wsMessageReqVO.getType())
                .setMessage(wsMessageProto)
                .setCreateTime(new Date().toString())
                .build();

        channel.writeAndFlush(wsMessageResProto);
        return true;
    }

}
