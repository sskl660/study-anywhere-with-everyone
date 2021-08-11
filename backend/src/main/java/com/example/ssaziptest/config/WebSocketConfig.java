package com.example.ssaziptest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP websocket 활성화
// 메세지를 중개 및 라우팅하는 브로커를 설정한다.
// registerStompEndPoints(Client 등록), configureMessageBroker(메세지 브로드캐스팅) 두 가지를 오버라이딩 해야한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 웹소켓 엔드포인트 정의
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /ws라는 경로로 들어오는 사용자를 Endpoint에 저장(StompEndpointRegistry 객체)해둔다.
        // setAlloewdOrigins를 이용하여 CORS를 예방한다.
        // SockJS를 활용한다.
        // withSockJS를 사용하면 WebSocket의 형태로 연결이 지속되기 어려운 경우, HTTP를 사용해서 연결을 지속 시켜준다.
        // 요청을 받을 경로 : export const chatURL = 'http://13.125.119.76:8080/ssazip'; // 백엔드 서버를 프론트에서
        registry.addEndpoint("/ssazip").setAllowedOriginPatterns("*").withSockJS();//sockJs 라이브러리 연결 매끄럽게
    }


    // 메시지 브로커 구성 (메시지를 브로드캐스팅)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // setApplicationDestinationPrefixes는 브로드 캐스팅의 시작 주소(접두사)를 설정한다.
        registry.setApplicationDestinationPrefixes("/galaxy");//대화 보낼 방 선택 연결
        // enableSimpleBroker는 해당 메세지 주솟값을 담당하여 처리하는 Broker를 활성화 시키는 함수이다(여기서는 /topic).
        registry.enableSimpleBroker("/topic");//브로드 캐스 할 주소! 클라이언트에서 구독하면 된다
    }
}