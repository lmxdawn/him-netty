package com.lmxdawn.him.api.service.ws;

import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.common.protobuf.WSBaseReqProtoOuterClass;
import com.lmxdawn.him.common.protobuf.WSBaseResProtoOuterClass;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

@ChannelHandler.Sharable
@Slf4j
public class WSServerHandler extends SimpleChannelInboundHandler<WSBaseReqProtoOuterClass.WSBaseReqProto> {

    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线!");
        // userOffLine(ctx);
    }

    /**
     * 心跳检查
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 关闭用户的连接
                userOffLine(ctx);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 用户下线
     */
    private void userOffLine(ChannelHandlerContext ctx) throws IOException {
        SessionSocketHolder.remove(ctx.channel());
        ctx.channel().close();
    }

    /**
     * 读到客户端的内容 （这里只做心跳检查）
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WSBaseReqProtoOuterClass.WSBaseReqProto msg) throws Exception {

        String sid = msg.getSid();
        long uid = msg.getUid();
        if (!UserLoginUtils.checkToken(uid, sid)) {
            log.info("非法登录");
            userOffLine(ctx);
            return;
        }

        log.info("客户端心跳连接");
        WSBaseResProtoOuterClass.WSBaseResProto wsBaseResProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                .setType(0)
                .setCreateTime(new Date().toString())
                .build();
        ctx.channel().writeAndFlush(wsBaseResProto);

        // 加入 在线 map 中
        SessionSocketHolder.put(uid, ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if ("Connection reset by peer".equals(cause.getMessage())) {
            log.error("连接出现问题");
            return;
        }

        log.error(cause.getMessage(), cause);
    }

}
