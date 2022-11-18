package com.test.demo.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;

@EnableConfigurationProperties
@EnableRabbit
@EnableJms
public class Config {
}
