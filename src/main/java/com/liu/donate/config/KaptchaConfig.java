package com.liu.donate.config;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 14:16
 * 验证码配置类
 */

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("Kaptcha.border","no");
        properties.put("Kaptcha.textproducer.font.color","black");
        properties.put("Kaptcha.textproducer.char.space","5");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
