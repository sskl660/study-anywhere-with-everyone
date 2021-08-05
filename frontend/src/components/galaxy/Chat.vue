<!-- 메세지 입력창 하단으로 배치-->
<!-- 채팅창 CSS 입히기-->
<template>
  <div>
    <div class="chat-room-wrap" v-if="stompClient">
      <input
        type="text"
        v-model="message"
        class="blocked mr-2"
        ref="user-name-input"
        @keyup.enter="sendMessage"
        placeholder="메세지를 입력하세요!"
      />
    </div>
    <div>
      <p
        style="padding: 0; margin: 0; color: white;"
        v-for="(obj, index) in receivedMessages"
        :key="index"
      >
        {{ obj.sender }} : {{ obj.content }}
      </p>
    </div>
  </div>
</template>

<script>
// 해당 모듈들을 포함한다.
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export default {
  name: 'App',
  data() {
    return {
      userName: [],
      idx: '',
      message: '',
      stompClient: null,
      receivedMessages: [],
    };
  },
  mounted() {
    this.socketConnect();
  },
  destroyed() {
    this.socketDisconnect();
  },
  created() {
    this.userName.push('정의감 넘치는 원숭이');
    this.userName.push('아름다운 돌고래');
    this.userName.push('기뻐 춤을 추는 피카치노');
    this.userName.push('포브스 선정 올해 최고의 팀 서울 3반 7팀');
    this.userName.push('커피 한 잔 하고 싶은 태현이');
    this.userName.push('오토에버 합격한 희원이');
    this.userName.push('넥슨 합격한 희은이');
    this.userName.push('삼성전자 근무중인 장섭이');
    this.userName.push('세계 일주를 밥먹듯이 하는 은채');
    this.userName.push('최고의 기타리스트 준형이');
    this.idx = Math.floor(Math.random() * 10);
    console.log(this.idx);
  },

  methods: {
    // 메세지 전송.
    sendMessage() {
      // 메세지가 존재하고, 연결 정보가 유지되는 경우
      if (this.message.trim() && this.stompClient) {
        // 메세지 형식을 정의한다.
        const sendMessage = {
          //   sender: this.userName,
          sender: this.userName[this.idx],
          content: this.message.trim(),
        };
        // 해당 Endpoint로 메세지를 전송한다.
        // Destination, header, body로 구성된다.
        this.stompClient.send('/galaxy/chat.send', {}, JSON.stringify(sendMessage));
        // 메세지를 전송하였으므로 변수를 초기화 시켜준다.
        this.message = '';
      }
    },

    onMessageReceived(payload) {
      // String 객체를 JSON으로 변환한다.
      const receiveMessage = JSON.parse(payload.body);

      // 채팅 입장, 퇴장에 따라서 메세지를 다르게 파싱하여 전송한다.
      if (receiveMessage.type === 'JOIN') {
        // receiveMessage.content = receiveMessage.sender + ' joined!';
        receiveMessage.content = receiveMessage.sender + ' joined!';
      } else if (receiveMessage.type === 'LEAVE') {
        receiveMessage.content = receiveMessage.sender + ' left!';
      }

      this.receivedMessages.push(receiveMessage);
    },

    onConnected() {
      // 해당 브로커가 중개하는 채널(/topic/public)로 연결(구독)한다.
      // destination, 보내고자하는 메세지(call back 함수)를 넣어줄 수 있다.
      this.stompClient.subscribe('/topic/public', this.onMessageReceived);
      // 메세지를 해당 경로로 전송한다.
      this.stompClient.send(
        '/galaxy/chat.addUser',
        {},
        // JSON 형식을 String 객체로 변환한다.
        // JSON.stringify({ content: '', sender: this.userName, type: 'JOIN' })
        JSON.stringify({ content: '', sender: this.userName[this.idx], type: 'JOIN' })
      );
    },

    onError(error) {
      console.log(error);
    },

    onDisconnected() {
      // 연결 정보를 삭제한다(null).
      // 받은 메세지를 삭제한다.
      this.stompClient = null;
      this.receivedMessages = [];
    },

    // 소켓 연결
    socketConnect() {
      // 입력이 없는 경우 경고 메세지를 표시한다.
      //   if (!this.userName.trim()) {
      //     alert('대화명을 입력해주세요!');
      //     this.$refs['user-name-input'].focus();
      //     return false;
      //   }

      // 요청 서버 URL을 작성한다.
      //   console.log(this.userName);
      console.log(this.userName[this.idx]);
      //   const serverURL = 'http://localhost:8080/ssazip';
      const serverURL = 'http://13.125.119.76:8080/ssazip';
      // 소켓을 이용하여 Server와 연결한다.
      let socket = new SockJS(serverURL);
      // 소켓 정보를 stompClient 변수에 할당한다.
      this.stompClient = Stomp.over(socket);
      // header, connectCallback, errorCallback을 connect 메서드에 입력한다.
      this.stompClient.connect('', this.onConnected, this.onError);
    },

    // 소켓 연결 해제
    socketDisconnect() {
      // 서버의 엔드포인트로 해당 메세지를 전송한다.
      this.stompClient.send(
        '/galaxy/chat.send',
        {},
        JSON.stringify({
          content: '',
          //   sender: this.userName,
          sender: this.userName[this.idx],
          type: 'LEAVE',
        })
      );
      // 연결을 종료한다.
      this.stompClient.disconnect(this.onDisconnected);
    },
  },
};
</script>

<style></style>
