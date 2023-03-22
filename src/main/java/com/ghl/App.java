package com.ghl;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class App {

    private static final String PROXY_HOST = "127.0.0.1";
    private static final Integer PROXY_PORT = 7890;

    public static void main(String[] args) {
        // 初始化Api上下文
        ApiContextInitializer.init();
        // 实例化Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            System.err.println("开始注册我的机器人");
            DefaultBotOptions botOptions = new DefaultBotOptions();
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            botsApi.registerBot(new MyBot(botOptions));
            System.err.println("注册我的机器人成功");
        } catch (TelegramApiException e) {
            System.err.println("注册我的机器人失败");
            e.printStackTrace();
        }
    }
}

