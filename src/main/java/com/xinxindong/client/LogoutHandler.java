package com.xinxindong.client;

import com.alibaba.fastjson.JSONObject;
import com.xinxindong.client.util.Constant;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.Consumer;

/**
 * Created by xingdu on 2016/9/24.
 */
public class LogoutHandler extends ChannelHandlerAdapter {
    private Consumer callback;//回调

    public LogoutHandler(Consumer callback) {

        this.callback = callback;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        JSONObject nettyMsg = (JSONObject) msg;
        if (nettyMsg != null) {

            int type = nettyMsg.getIntValue("type");
            if (type == Constant.LOGOUT) {  //用户异地登陆，关闭相关操作
                if (callback != null)
                    callback.accept(msg);

            } else

                ctx.fireChannelRead(msg);
        } else
            ctx.fireChannelRead(msg);
    }


    public static void handle(ChannelHandlerContext ctx, JSONObject
            msg, Consumer callback) throws Exception {

//        logger.info("------------client logout ------------" + msg.getString("alias"));

    }
}
