<!-- 메세지 입력창 하단으로 배치-->
<!-- 채팅창 CSS 입히기-->
<template>
  <div>
    <div class="participantList" id="participantbox">

        <span class="dropdown">
              <div class="drop-list-title" style="">참여자 목록</div>
              <div class="drop-list">
                <div v-for="(user,indexx) in this.participants" :key="indexx">
                  <div class="image-box" @click="toProfile(user.partEmail)">
                    <div class="content">{{ user.partTerm }} 기 {{ user.partName }}</div>
                  </div>
                </div>
              </div>
          </span>

      </div>
        <div>
            <div v-if="chatType == 'algo'">
                <!-- <div class="dropdown">
            <button class="btn pt-0 pb-0" style="height:30px; text-align:center; font-weight:bold;" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              {{userInfo.userFollower}}
            </button>
            <ul class="dropdown-menu text-center" style="background-color: #E1AF4E; border-radius: 1rem" aria-labelledby="dropdownMenuButton1">
              <div class="drop-list-title" style="">follower</div>
              <div class="drop-list">
                <div v-for="follower in followers" :key="follower">
                  <li>
                    <router-link class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" :to="{ path: '/profile', query: { user: follower[0]  }}">
                      {{follower[1]}}
                    </router-link>
                  </li>
                  
                </div>
              </div>              
            </ul>
          </div> -->
                <div id="roomNameAlgo">
                    <h3 style="color:white">Algo 채팅방 입니다.</h3>
                    <span id="participant">{{ this.participants.length }} 명 참여 중</span>
                    <!-- <span class="dropdown" id="participant">
            <button
              class="btn pt-0 pb-0"
              style="height:30px; text-align:center; font-weight:bold; color:white; "
              type="button"
              id="dropdownMenuButton2"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              {{ this.participants.length }} 명 참여 중
            </button> -->
                    <!-- <ul
              class="dropdown-menu text-center"
              style=" background-color: rgba(255,255,255,0.5);     border-radius: 15px;
"
              aria-labelledby="dropdownMenuButton2"
            >
              <div class="drop-list-title" style="">참여자 목록</div>
              <div class="drop-list">
                <div v-for="user in this.participants" :key="user">
                  <li class="image-box" @click="toProfile(user.partEmail)">
                    <div class="content">{{ user.partTerm }} 기 {{ user.partName }}</div>
                  </li>
                </div>
              </div>
            </ul> -->
                    <!-- </span> -->
                </div>

                <div id="roomBox">
                    <div v-for="(obj, index) in receivedMessagesAlgo" :key="index">
                        <!-- 입퇴장 -->
                        <div v-if="obj.senderId === null" id="chatboxcome">
                            <div id="Ccome">{{ obj.content }}</div>
                        </div>
                        <!-- 남이 보낸거 -->
                        <div v-else-if="obj.senderId != userEmail" id="chatboxleft">
                            <div id="Cname">
                                <a class="link-1"
                                    ><strong @click="toProfile(obj.senderId)">{{ obj.sender }}</strong>
                                </a>
                            </div>
                            <div id="Ctext">{{ obj.content }}</div>
                        </div>

                        <!-- 내가 보낸거 -->
                        <div v-else id="chatboxright">
                            <div id="CMname">
                                <strong>{{ obj.sender }}</strong>
                            </div>
                            <div id="CMtext">{{ obj.content }}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div v-else-if="chatType == 'cs'">
                <div id="roomNameCS">
                    <h3 style="color:white">CS 채팅방 입니다.</h3>
                    <span id="participant">{{ this.participants.length }} 명 참여 중</span>
                </div>
                <div id="roomBox">
                    <div v-for="(obj, index) in receivedMessagesCS" :key="index">
                        <div v-if="obj.senderId === null" id="chatboxcome">
                            <div id="Ccome">{{ obj.content }}</div>
                        </div>
                        <div v-else-if="obj.senderId != userEmail" id="chatboxleft">
                            <div id="Cname">
                                <a class="link-1">
                                    <strong @click="toProfile(obj.senderId)">{{ obj.sender }}</strong></a
                                >
                            </div>
                            <div id="Ctext">{{ obj.content }}</div>
                        </div>
                        <div v-else id="chatboxright">
                            <div id="CMname">
                                <strong>{{ obj.sender }}</strong>
                            </div>
                            <div id="CMtext">{{ obj.content }}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div v-else>
                <div id="roomNameJob">
                    <h3 style="color:white">Job 채팅방 입니다.</h3>
                    <span id="participant">{{ this.participants.length }} 명 참여 중</span>
                </div>
                <div id="roomBox">
                    <div v-for="(obj, index) in receivedMessagesJob" :key="index">
                        <div v-if="obj.senderId === null" id="chatboxcome">
                            <div id="Ccome">{{ obj.content }}</div>
                        </div>
                        <div v-else-if="obj.senderId != userEmail" id="chatboxleft">
                            <div id="Cname">
                                <a class="link-1">
                                    <strong @click="toProfile(obj.senderId)">{{ obj.sender }}</strong></a
                                >
                            </div>
                            <div id="Ctext">{{ obj.content }}</div>
                        </div>
                        <div v-else id="chatboxright">
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
        <SSazipRank id="galaxyRank" :ranker="ranker" />
    </div>
</template>

<script>
// 해당 모듈들을 포함한다.
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { mapActions, mapGetters } from 'vuex';
import { chatURL } from '@/util/http-common.js';
import SSazipRank from '@/components/galaxy/SSazipRank.vue';

export default {
  name: 'App',
  components:{
    SSazipRank
  },
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
      participants: [], // length. 참여자수, 이메일 userEmail
      // 참여자 정보 전달 채널
      partChannel: null,
      // 대화 채널
      channel: null,
      // 상위 5명
      ranker: [],
    };
  },
  mounted() {
    this.socketConnect();
  },
  destroyed() {
    this.socketDisconnect();
  },
  updated() {
    this.scrollDown();
  },
  methods: {
    ...mapActions(['setPart']),
    // 자동 스크롤
    scrollDown() {
      var scrollbox = document.getElementById('roomBox');
      scrollbox.scrollTop = scrollbox.scrollHeight;
    //   console.log(this.participants.length);
    },
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
                this.stompClient.send('/galaxy/chat/send/' + this.chatType, {}, JSON.stringify(sendMessage));
                // 메세지를 전송하였으므로 변수를 초기화 시켜준다.
                this.message = '';
            }
        },

        // 메세지를 받는 함수.
        onMessageReceived(payload) {
            // String 객체를 JSON으로 변환한다.
            let receiveMessage = JSON.parse(payload.body);

            // 참가자라면 참여 메세지만 출력하기
            if (receiveMessage.constructor.name == 'Array') {
                // 입장 시간을 기준으로 랭킹 정렬(5명만).
                this.ranker = JSON.parse(
                    JSON.stringify(
                        receiveMessage.sort(function(a, b) {
                            // console.log(a.getTime);
                            // return a.getTime() - b.getTime();
                            return new Date(a.enterTime) - new Date(b.enterTime);
                        })
                    )
                );
                // console.log(this.participants);
                // console.log(this.ranker);

                // 이름 순으로 참여자 정렬
                receiveMessage.sort(function(a, b) {
                    return a.partName > b.partName ? 1 : -1;
                });
                this.participants = receiveMessage;
                // console.log(this.participants);
                this.setPart(this.participants);

                /////////////////////////////////////////////////////////
                // 여기까지가 참여자 목록이 갱신된 지점입니다.////////////
                ////////////////////////////////////////////////////////

                return;
            }

            if (receiveMessage.sender == '') {
                this.receivedMessagesAlgo.push(receiveMessage);
                this.receivedMessagesCS.push(receiveMessage);
                this.receivedMessagesJob.push(receiveMessage);
                return;
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
            // console.log(error);
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
            this.channel = this.stompClient.subscribe('/topic/chat/' + this.chatType, this.onMessageReceived);

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
        toProfile(senderId) {
            // this.$router.push({name: 'routeName', query: {user: "senderId"}, target="_blank"});
            let routeData = this.$router.resolve({ name: 'Profile', query: { user: senderId } });
            window.open(routeData.href, '_blank');
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
            this.channel = this.stompClient.subscribe('/topic/chat/' + this.chatType, this.onMessageReceived);
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

#chatboxcome {
    padding-top: 1%;
    padding-bottom: 1%;
    text-align: center;
}
#chatboxright {
    padding-top: 1%;
    padding-bottom: 1%;
    text-align: right;
}

#chatboxleft {
    padding-top: 1%;
    padding-bottom: 1%;
    text-align: left;
}

#Cname {
    text-align: left;
    color: black;
    font-size: 1.1rem;
    margin-top: 5px;
}

#Ctext {
    display: inline-block;
    word-break: break-all;
    max-width: 75%;
    padding: 10px;
    border-radius: 5px;
    background-color: white;
    color: black;
    margin-top: 5px;
    margin-bottom: 5px;
    padding-top: 2%;
    padding-bottom: 2%;
    margin-left: 2%;
    font-family: 'BBTreeGB';
}

#CMname {
    text-align: right;
    color: black;
    font-size: 1.1rem;
    margin-top: 5px;
}

#CMtext {
    display: inline-block;
    word-break: break-all;
    max-width: 75%;
    padding: 10px;
    border-radius: 5px;
    background-color: #f6d77f;
    color: black;
    margin-top: 5px;
    margin-bottom: 5px;
    padding-top: 2%;
    padding-bottom: 2%;
    margin-right: 2%;
    text-align: left;
    font-family: 'BBTreeGB';
}

#Ccome {
    display: inline-block;
    word-break: break-all;
    max-width: 75%;
    padding: 10px;
    border-radius: 5px;
    background-color: white;
    background-color: rgba(255, 255, 255, 0.1);
    color: #555;
    margin-top: 5px;
    margin-bottom: 5px;
    padding-top: 2%;
    padding-bottom: 2%;
    font-size: 14px;
}

#participant {
    font-size: 18px;
    position: absolute;
    margin-top: -99px;
    margin-left: 121px;
    position: fixed;
    color: white;
    font-weight: bold;
}

/* 목록호버링을 위한 */
.image-box {
    width: 100%;
    height: 100%;
    position: relative;
    z-index: 2;
    background: none;
    font-weight: bold;
    
    padding: 3%;
    cursor: pointer;
}
.image-box .content::after,
.image-box .content::before {
    /* content: '';
  display: block;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 0%;
  height: 0%; */
    border-color: rgb(46, 45, 100);
    /* border-width: 1px;
  transition: 0.5s; */
}
.image-box .content::after {
    height: 100%;
    border-style: solid none;
}
.image-box .content::before {
    width: 100%;
    border-style: none solid;
}
.image-box:hover::after {
    opacity: 1;
}
.image-box:hover .content::after,
.image-box:hover .content::before {
    width: 100%;
    height: 100%;
    border-radius: 5px;
}

/* 이름 호버링 */

/* linkseven */
/* linkone */
a {
    text-transform: uppercase;
    color: rgb(15, 14, 14);
    text-decoration: none;
    position: relative;
    display: inline-block;
    cursor: pointer;
}
.link-1 a:before,
.link-1 a:after {
    content: '';
    border-bottom: solid 1px white;
    position: absolute;
    bottom: 0;
    width: 0;
}

.link-1 a:before {
    left: 0;
}
.link-1 a:after {
    right: 0;
}

.link-1 a:hover:before,
.link-1 a:hover:after {
    width: 50%;
}

.link-1 a:before,
.link-1 a:after {
    -webkit-transition: all 0.2s ease;
    transition: all 0.2s ease;
}

.participantList {
    position: absolute;
    margin-left: -170px;
    margin-top: -13px;
}

#participantbox {
    background-color: rgba(255, 255, 255, 0.2);
    padding-right: 17px;
    padding-left: 17px;
    padding-top: 15px;
    padding-bottom: 15px;
    border-radius: 20px;
    max-height: 770px;
}
.drop-list-title {
    background-color: #f1c069;
    border-radius: 33px;
    padding-top: 8px;
    padding-bottom: 8px;
    margin-bottom: 8px;
    font-size: 18px;
}

.drop-list {
    border-radius: 10px;
    padding-top: 8px;
    padding-bottom: 8px;
    margin-left: 0px;
    max-height: 700px;
    overflow: scroll;
}
/* .link-6 a:hover {
  -webkit-transform: scale(1.05);
     -moz-transform: scale(1.05);
      -ms-transform: scale(1.05);
       -o-transform: scale(1.05);
          transform: scale(1.05,-1.05);  
          
}
.link-6 a {
  -webkit-transition: all 0.1s linear;
          transition: all 0.2s linear;
} */

/* @-webkit-keyframes heartbeat {
  0% { -webkit-transform: scale(1); }
  10% { -webkit-transform: scale(1.1); }
  20% { -webkit-transform: scale(1); }
  30% { -webkit-transform: scale(1.1); }
  40% { -webkit-transform: scale(1); }
}

@-webkit-keyframes heartbeat-x {
  0% { -webkit-transform: scaleX(0); }
  10% { -webkit-transform: scaleX(1); }
  20% { -webkit-transform: scaleX(0); }
  30% { -webkit-transform: scaleX(1); }
  40% { -webkit-transform: scaleX(0); }
}

@keyframes heartbeat {
  0% { transform: scale(1); }
  10% { transform: scale(1.1); }
  20% { transform: scale(1); }
  30% { transform: scale(1.1); }
  40% { transform: scale(1); }
}

@keyframes heartbeat-x {
  0% { transform: scaleX(0); }
  10% { transform: scaleX(1); }
  20% { transform: scaleX(0); }
  30% { transform: scaleX(1); }
  40% { transform: scaleX(0); }
} */
</style>
