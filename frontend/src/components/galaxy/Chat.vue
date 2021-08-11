<!-- 메세지 입력창 하단으로 배치-->
<!-- 채팅창 CSS 입히기-->
<template>
  <div>
    <div>
      <div v-if="chatType == 'algo'">
        <h1 style="color:orange">Algo 채팅방 입니다.</h1>
        <p v-for="(obj, index) in receivedMessagesAlgo" :key="index" style="color:white">
          {{ obj.senderId }}
          {{ obj.sender }} : {{ obj.content }}
        </p>
      </div>
      <div v-else-if="chatType == 'cs'">
        <h1 style="color:orange">CS 채팅방 입니다.</h1>
        <p v-for="(obj, index) in receivedMessagesCS" :key="index" style="color:white">
          {{ obj.sender }} : {{ obj.content }}
        </p>
      </div>
      <div v-else>
        <h1 style="color:orange">Job 채팅방 입니다.</h1>
        <p v-for="(obj, index) in receivedMessagesJob" :key="index" style="color:white">
          <!-- <b-avatar variant="info"></b-avatar> -->
          {{ obj.sender }}
          <b-alert show variant="light">{{ obj.content }}</b-alert>
        </p>
      </div>
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
    </div>
  </div>
</template>

<script>
// 해당 모듈들을 포함한다.
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { mapGetters } from 'vuex';
import { chatURL } from '@/util/http-common.js';

export default {
  name: 'App',
  data() {
    return {
      enterMessage: [],
      exitMessage: [],
      idx: '',
      message: '',
      stompClient: null,
      receivedMessagesAlgo: [],
      receivedMessagesCS: [],
      receivedMessagesJob: [],
    };
  },
  mounted() {
    this.socketConnect();
  },
  destroyed() {
    this.socketDisconnect();
  },
  created() {
    this.enterMessage.push('님이 입장하셨습니다!');
    this.enterMessage.push('님이 공부하러 오셨어요!');
    this.enterMessage.push('님 오늘도 화이팅!');
    this.exitMessage.push('님이 퇴장하셨습니다!');
    this.exitMessage.push('님 다음에 또 만나요!');
    this.exitMessage.push('님 다음에도 화이팅!');
  },

  methods: {
    // 메세지 전송하는 함수.
    sendMessage() {
      // 메세지가 존재하고, 연결 정보가 유지되는 경우
      if (this.message.trim() && this.stompClient) {
        // 메세지 형식을 정의한다(JSON).
        const sendMessage = {
          senderId: this.userEmail,
          sender: this.userTerm + '기 ' + this.userName,
          content: this.message.trim(),
        };

        // 해당 Endpoint로 메세지를 전송한다.
        // Destination, header, body로 구성된다.
        // 채팅 Type에 따라서 다르게 보낸다.
        this.stompClient.send(
          '/galaxy/chat/send/' + this.chatType,
          {},
          JSON.stringify(sendMessage)
        );
        // 메세지를 전송하였으므로 변수를 초기화 시켜준다.
        this.message = '';
      }
    },

    // 메세지를 받는 함수.
    onMessageReceived(payload) {
      // String 객체를 JSON으로 변환한다.
      const receiveMessage = JSON.parse(payload.body);

      // this.idx = Math.floor(Math.random() * 3);
      // 채팅 입장, 퇴장에 따라서 메세지를 다르게 파싱하여 전송한다.
      // if (receiveMessage.type === 'JOIN') {
      //   receiveMessage.content = receiveMessage.sender + this.enterMessage[this.idx];
      // } else if (receiveMessage.type === 'LEAVE') {
      //   receiveMessage.content = receiveMessage.sender + this.exitMessage[this.idx];
      // }
      // console.log(receiveMessage.room + 'this');

      if (this.chatType == 'algo') {
        this.receivedMessagesAlgo.push(receiveMessage);
      } else if (this.chatType == 'cs') {
        this.receivedMessagesCS.push(receiveMessage);
      } else if (this.chatType == 'job') {
        this.receivedMessagesJob.push(receiveMessage);
      }
    },

    onError(error) {
      console.log(error);
    },

    // 소켓 연결
    socketConnect() {
      // 소켓을 이용하여 Server와 연결한다.
      var socket = new SockJS(chatURL);
      // 소켓 정보를 stompClient 변수에 할당한다.
      this.stompClient = Stomp.over(socket);
      // header, connectCallback, errorCallback을 connect 메서드에 입력한다.
      this.stompClient.connect('', this.onConnected, this.onError);
    },

    // 소켓 연결하며 대화 채널 구독
    onConnected() {
      // 해당 브로커가 중개하는 채널(/topic/public)로 연결(구독)한다.
      // destination, 보내고자하는 메세지(call back 함수)를 넣어줄 수 있다.
      this.stompClient.subscribe('/topic/chat/' + this.chatType, this.onMessageReceived);
    },
  },

  computed: {
    ...mapGetters(['userEmail', 'userName', 'userTerm', 'chatType']),
  },
  watch: {
    chatType: function() {
      this.stompClient.disconnect();
      this.socketConnect();
    },
  },
};
</script>

<style></style>
