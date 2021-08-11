<!-- 메세지 입력창 하단으로 배치-->
<!-- 채팅창 CSS 입히기-->
<template>
  <div>
    <div>
      <div v-if="chatType == 'algo'">
        <div>{{ participants.length }}</div>
        <div v-for="(obj, index) in participants" :key="index">
          {{ obj.partTerm }} {{ obj.partName }} : {{ obj.partEmail }}
        </div>
        <div id="roomNameAlgo"><h3 style="color:white">Algo 채팅방 입니다.</h3></div>
        <div id="roomBox">
          <div v-for="(obj, index) in receivedMessagesAlgo" :key="index">
            <div v-if="obj.senderId != userEmail">
              <div id="Cname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="Ctext">{{ obj.content }}</div>
            </div>

            <div v-else>
              <div id="CMname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>
        </div>
      </div>

      <div v-else-if="chatType == 'cs'">
        <div id="roomNameCS"><h3 style="color:white">CS 채팅방 입니다.</h3></div>
        <div id="roomBox">
          <div v-for="(obj, index) in receivedMessagesCS" :key="index">
            <div v-if="obj.senderId != userEmail">
              <div id="Cname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="Ctext">{{ obj.content }}</div>
            </div>
            <div v-else>
              <div id="CMname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <div id="roomNameJob"><h3 style="color:white">Job 채팅방 입니다.</h3></div>
        <div id="roomBox">
          <div v-for="(obj, index) in receivedMessagesJob" :key="index">
            <div v-if="obj.senderId != userEmail">
              <div id="Cname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="Ctext">{{ obj.content }}</div>
            </div>
            <div v-else>
              <div id="CMname">
                <strong>{{ obj.sender }}</strong>
              </div>
              <div id="CMtext">{{ obj.content }}</div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <input
          type="text"
          v-model="message"
          class="blocked mr-2 sendChat"
          ref="user-name-input"
          id="send_chat"
          placeholder="  메세지를 입력하세요!"
          @keyup.enter="sendMessage"
        />&nbsp;
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
      // 소켓 연결
      stompClient: null,
      // 메세지 내용 저장
      receivedMessagesAlgo: [],
      receivedMessagesCS: [],
      receivedMessagesJob: [],
      participants: [],
      // 참여자 정보 전달 채널
      partChannel: null,
      // 대화 채널
      channel: null,
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
      // console.log(receiveMessage);

      // this.idx = Math.floor(Math.random() * 3);
      // 채팅 입장, 퇴장에 따라서 메세지를 다르게 파싱하여 전송한다.
      // if (receiveMessage.type === 'JOIN') {
      //   receiveMessage.content = receiveMessage.sender + this.enterMessage[this.idx];
      // } else if (receiveMessage.type === 'LEAVE') {
      //   receiveMessage.content = receiveMessage.sender + this.exitMessage[this.idx];
      // }
      // console.log(receiveMessage.room + 'this');
      if (receiveMessage[0].partEmail != null) {
        this.participants = receiveMessage;
        console.log(this.participants);
      }

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
      // 참여자 정보를 알려줄 채널
      this.partChannel = this.stompClient.subscribe('/topic/part', this.onMessageReceived);

      // 대화방 입장 시 기본 채널은 Algo 채팅방으로 설정된다.
      this.channel = this.stompClient.subscribe(
        '/topic/chat/' + this.chatType,
        this.onMessageReceived
      );
      console.log(this.channel + 'here');

      this.stompClient.send(
        '/galaxy/chat/enter',
        {},
        JSON.stringify({
          partEmail: this.userEmail,
          partTerm: this.userTerm,
          partName: this.userName,
        })
      );
    },

    // 소켓 연결 해제, 대화 채널 이탈.
    socketDisconnect() {
      console.log('dis!!!!');
      this.stompClient.send(
        '/galaxy/chat/exit',
        {},
        JSON.stringify({
          partEmail: this.userEmail,
          partTerm: this.userTerm,
          partName: this.userName,
        })
      );

      this.stompClient.disconnect();
    },
  },

  computed: {
    ...mapGetters(['userEmail', 'userName', 'userTerm', 'chatType']),
  },
  watch: {
    // 대화방을 바꿀때! 연결을 한번 끊고 (구독만 끊기는 안되더라..) 재연결
    chatType: function() {
      // 구독 취소
      this.channel.unsubscribe();
      // 재 구독
      this.channel = this.stompClient.subscribe(
        '/topic/chat/' + this.chatType,
        this.onMessageReceived
      );
      // this.stompClient.disconnect();
      // this.socketConnect();
    },
  },
};
</script>

<style scoped>
#roomNameAlgo {
  position: fixed;
  background-color: #cc922c;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomNameCS {
  position: fixed;
  background-color: #c52e1e;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomNameJob {
  position: fixed;
  background-color: #32479c;
  width: 430px;
  border-radius: 20px;
  margin-left: 3%;
  padding-bottom: 4px;
  padding-top: 3px;
  right: 45px;
}

#roomBox {
  width: 400px;
  height: 610px;
  margin-left: 6%;
  overflow: scroll;
  position: absolute;
  margin-top: 72px;
}

#send_chat {
  width: 430px;
  height: 55px;
  border-radius: 20px;
  margin-top: 10px;
  position: fixed;
  bottom: 28px;
  right: 45px;
}

input {
  border: 3px solid #1c84c4;
}

input:focus {
  outline: none;
  box-shadow: 0 0 3px 2px #1c84c4;
}

#Cname {
  text-align: left;
  color: black;
  font-size: 1.1rem;
  margin-top: 5px;
}

#Ctext {
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

#CMname {
  text-align: right;
  color: black;
  font-size: 1.1rem;
  margin-top: 5px;
}

#CMtext {
  text-align: right;
  color: black;
  background-color: #f1c069;
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
