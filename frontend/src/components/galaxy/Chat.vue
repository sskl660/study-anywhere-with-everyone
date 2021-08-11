<!-- 메세지 입력창 하단으로 배치-->
<!-- 채팅창 CSS 입히기-->
<template>
  <div>
    <div>
      <div v-if="chatType == 1">
        <div id="roomNameAlgo"><h3 style="color:white">Algo 채팅방 입니다.</h3></div>
        <div id="roomBox">

          <div v-for="(obj, index) in receivedMessagesAlgo" :key="index">
            <div v-if="!Me">
              <div id="Cname"><strong>{{ obj.sender }}</strong></div> 
              <div id="Ctext">{{ obj.content }}</div>
            </div>

            <div v-else>
              <div id="CMname"><strong>{{ obj.sender }}</strong></div> 
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>

        </div>
      </div>

      <div v-else-if="chatType == 2">
        <div id="roomNameCS"><h3 style="color:white">CS 채팅방 입니다.</h3></div>
        <div id="roomBox">

          <div v-for="(obj, index) in receivedMessagesCS" :key="index">
            <div v-if="!Me">
              <div id="Cname"><strong>{{ obj.sender }}</strong></div> 
              <div id="Ctext">{{ obj.content }}</div>
            </div>
            <div v-else>
              <div id="CMname"><strong>{{ obj.sender }}</strong></div> 
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>

        </div>
      </div>
      <div v-else>
        <div id="roomNameJob"><h3 style="color:white">Job 채팅방 입니다.</h3></div>
        <div id="roomBox">

          <div v-for="(obj, index) in receivedMessagesJob" :key="index">
            <div v-if="!Me">
              <div id="Cname"><strong>{{ obj.sender }}</strong></div>
              <div id="Ctext">{{ obj.content }}</div>
            </div>

            <div v-else>
              <div id="CMname"><strong>{{ obj.sender }}</strong></div>
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>

        </div>
      </div>
      <div>
        <input type="text" v-model="message" class="blocked mr-2 sendChat" ref="user-name-input" id="send_chat" placeholder="  메세지를 입력하세요!" @keyup.enter="sendMessage"/>&nbsp;
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
      Me: true,
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
          sender: this.userTerm + '기 ' + this.userName,
          content: this.message.trim(),
        };

        // 해당 Endpoint로 메세지를 전송한다.
        // Destination, header, body로 구성된다.
        // 채팅 Type에 따라서 다르게 보낸다.
        if (this.chatType == 1) {
          this.stompClient.send('/galaxy/chat/send/algo', {}, JSON.stringify(sendMessage));
        } else if (this.chatType == 2) {
          this.stompClient.send('/galaxy/chat/send/cs', {}, JSON.stringify(sendMessage));
        } else if (this.chatType == 3) {
          this.stompClient.send('/galaxy/chat/send/job', {}, JSON.stringify(sendMessage));
        }
        // 메세지를 전송하였으므로 변수를 초기화 시켜준다.
        this.message = '';
      }

      // 사용자 이메일 비교해서 나 인지 구분하기
      if(this.receivedMessages.sender.userName == this.userName){
        this.Me = true;
      }
      else{
        this.Me = false
      }
    },

    // 메세지를 받는 함수.
    onMessageReceived(payload) {
      // String 객체를 JSON으로 변환한다.
      const receiveMessage = JSON.parse(payload.body);

      this.idx = Math.floor(Math.random() * 3);
      // 채팅 입장, 퇴장에 따라서 메세지를 다르게 파싱하여 전송한다.
      if (receiveMessage.type === 'JOIN') {
        receiveMessage.content = receiveMessage.sender + this.enterMessage[this.idx];
      } else if (receiveMessage.type === 'LEAVE') {
        receiveMessage.content = receiveMessage.sender + this.exitMessage[this.idx];
      }
      console.log(receiveMessage.room + 'this');

      if (this.chatType == 1) {
        this.receivedMessagesAlgo.push(receiveMessage);
      } else if (this.chatType == 2) {
        this.receivedMessagesCS.push(receiveMessage);
      } else if (this.chatType == 3) {
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
      if (this.chatType == 1) {
        this.stompClient.subscribe('/topic/chat/algo', this.onMessageReceived);
      } else if (this.chatType == 2) {
        console.log('sub2');
        this.stompClient.subscribe('/topic/chat/cs', this.onMessageReceived);
      } else {
        this.stompClient.subscribe('/topic/chat/job', this.onMessageReceived);
      }
    },
  },

  computed: {
    ...mapGetters(['userName', 'userTerm', 'chatType']),
  },
  watch: {
    chatType: function() {
      this.stompClient.disconnect();
      this.socketConnect();
    },
  },
};
</script>

<style scoped>
#roomNameAlgo{
  position: fixed;
  background-color: #CC922C;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomNameCS{
  position: fixed;
  background-color: #C52E1E;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomNameJob{
  position: fixed;
  background-color: #32479C;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomBox{
  width: 400px;
  height: 610px;
  margin-left: 6%;
  overflow: scroll;
  position: absolute;
  margin-top: 72px;
}

#send_chat{
  width: 430px;
  height: 55px;
  border-radius: 20px;
  margin-top: 10px;
  position: fixed;
  bottom: 28px;
  right: 45px;
}

input {
  border: 3px solid #1C84C4;
}

input:focus {
  outline: none;
  box-shadow: 0 0 3px 2px #1C84C4;
}

#Cname{
  text-align: left;
  color: black;
  font-size: 1.1rem;
  margin-top: 5px;
}

#Ctext{
  text-align: left;
  color: black;
  background-color: white;
  margin-top: 5px;
  margin-bottom: 15px;
  padding-top: 2%;
  padding-bottom: 2%;
  border-radius: 10px;
  width: 70%;
  padding-left: 5%;
  padding-right: 5%;
  margin-left: 10px;
}

#CMname{
  text-align: right;
  color: black;
  font-size: 1.1rem;
  margin-top: 5px;
}

#CMtext{
  text-align: left;
  color: black;
  background-color: #F1C069;
  margin-top: 5px;
  margin-bottom: 15px;
  padding-top: 2%;
  padding-bottom: 2%;
  border-radius: 10px;
  width: 70%;
  padding-left: 5%;
  padding-right: 5%;
  margin-left: 110px;
}
</style>
