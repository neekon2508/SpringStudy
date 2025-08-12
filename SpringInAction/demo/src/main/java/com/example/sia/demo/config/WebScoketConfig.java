package com.example.sia.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebScoketConfig implements WebSocketMessageBrokerConfigurer{
    //Dang ky mot websocket endpoint ma cac client se se dung de ket noi toi may chu websocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    //cau hinh nha moi moi gioi tin nhan duoc su dung de dinh tuyen thu tu mot khach hang nay den ung dung khach khac
    //1st line: dinh tuyen den cac phuong thuc xu ly tin nhan
    //2nd line: dinh tuyen toi nha moi gioi tin nhan. Nha moi gioi tin nhan se phat cac tin nhan den tat ca khach hang duoc ket noi da ang ky mot chu de cu the
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
