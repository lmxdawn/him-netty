package com.lmxdawn.him.api.ws;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 操作 在线用户的 Channel
 */
public class WSSocketHolder {

    private static final Map<Long, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    public static void put(Long id, Channel channel) {
        CHANNEL_MAP.put(id, channel);
    }

    public static Channel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

    public static Map<Long, Channel> getMAP() {
        return CHANNEL_MAP;
    }

    public static void remove(Channel channel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == channel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }

}
