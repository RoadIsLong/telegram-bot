package com.ghl;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TestCase01 {

    //代理ip
    private static final String PROXY_HOST = "127.0.0.1";

    //代端口
    private static final Integer PROXY_PORT = 7890;

    public static void main(String[] args) {

        // 初始化Api上下文
        ApiContextInitializer.init();

        // 实例化Telegram Bots API
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        //设置代理参数
        DefaultBotOptions defaultBotOptions = new DefaultBotOptions();
        defaultBotOptions.setProxyHost(PROXY_HOST);
        defaultBotOptions.setProxyPort(PROXY_PORT);
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        defaultBotOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        TelegramLongPollingBot telegramLongPollingBot = new TelegramLongPollingBot(defaultBotOptions) {

            //设置bot的username
            @Override
            public String getBotUsername() {
                //你的机器人名称
                return "xxxxx_bot";
            }

            //设置bot的token
            @Override
            public String getBotToken() {
                //你的机器人token
                return "XXXXXXX:XXXXXXXXXXXXXXXXXXXXXXXX";
            }


            //接收消息并回复
            @Override
            public void onUpdateReceived(Update update) {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    Message message = update.getMessage();
                    SendMessage sendMsg = new SendMessage()
                            .setChatId(message.getChatId())
                            .setText(message.getText());
                    try {
                        execute(sendMsg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        try {
            System.out.println("开始注册机器人");
            telegramBotsApi.registerBot(telegramLongPollingBot);
            System.out.println("注册机器人成功");
        } catch (TelegramApiException e) {
            System.out.println("注册机器人失败");
            e.printStackTrace();
        }
    }

}
