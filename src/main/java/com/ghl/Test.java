package com.ghl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;


public class Test {

    public static void main(String[] args) {

        //获取机器人信息
        String url = "https://api.telegram.org/xxxxx:XXXXXXXXXXXXXXXXXXXXXXXXXXXX/getMe";
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxyPort", "7890");
        String result = HttpUtil.get(url);
        System.out.println(JSON.toJSONString(JSON.parseObject(result), true));

    }

}
