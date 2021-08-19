<!-- Galaxy 템플릿에 맞게 배치-->
<!-- Galaxy CSS 적용-->
<template>
  <div id="ds" class="">
    <SSazip id="asd" />
    <div class="">
      <div><ChatTab /></div>
    </div>
    <div class="GalaxyChat">
      <div><Chat /></div>
    </div>
    <div class="galaxy-exit-button">
      <img class="exit" src="../assets/exit-icon.png" alt="exit" @click="exit()" />
    </div>

    <!-- Timer 설정 -->
    <!-- <span id="counter"></span><input type="button" value="연장" @click="counter_reset()"> -->

    <div class="popup-background" style="display:none">
      <!-- Layer Popup 설정 시작 -->
      <div id="popup" style="display:none">
        <!-- 초기화면에서는 보여줄 필요가 없으니 아예 생성도 하지 않도록 한다. -->
        <div class="popup-body">
          <!-- 팝업내용 -->
          <img class="star" src="../assets/star.png" alt="star" />
          <div class="exit-message">벌써 한 시간이나 공부하셨네요~!</div>
          <div class="countdown-message"><span id="ncounter"></span> 후 자동 퇴실합니다.</div>
          <div class="d-flex justify-content-end exit-modal-footer">
            <input
              class="btn btn-success"
              type="button"
              value="더 할래요~"
              @click="counter_reset(), close_window()"
            />
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="exit()">
              그만 할래요
            </button>
          </div>
        </div>
      </div>
      <!-- Layer Popup 설정 끝 -->
    </div>
  </div>
</template>

<script>
import Chat from '@/components/galaxy/Chat.vue';
import ChatTab from '@/components/galaxy/ChatTab.vue';
import SSazip from '@/components/galaxy/SSazip.vue';
import swal from 'sweetalert';
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'Galaxy',
  components: {
    Chat,
    ChatTab,
    SSazip,
  },
  data: function() {
    return {
      startTime: null,
      tid: null,
      cnt: null,
      mainTimeLimit: 3600, // 공부 시간 : 3600 (1시간)
      popupTimeLimit: 180, // 모달 유지 시간 : 180 (3분)
      isIntendedExit: true,
    };
  },
  methods: {
    ...mapActions([
      'getMessage',
      'getUserInfo'
    ]),

    getStartTime: function() {
      var date = new Date();
      var year = date.getFullYear().toString();

      var month = date.getMonth() + 1;
      month = month < 10 ? '0' + month.toString() : month.toString();

      var day = date.getDate();
      day = day < 10 ? '0' + day.toString() : day.toString();

      var hour = date.getHours();
      hour = hour < 10 ? '0' + hour.toString() : hour.toString();

      var minites = date.getMinutes();
      minites = minites < 10 ? '0' + minites.toString() : minites.toString();

      var seconds = date.getSeconds();
      seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

      this.startTime = year + '-' + month + '-' + day + 'T' + hour + ':' + minites + ':' + seconds;
    },

    //Layer Popup 설정 시작
    open_window: function() {
      var popup = document.getElementById('popup');
      var popupBackground = document.querySelector('.popup-background');
      popup.style.display = 'block'; // block을 생성해서 보이게한다.
      popupBackground.style.display = 'block';
    },

    close_window: function() {
      var popup = document.getElementById('popup');
      var popupBackground = document.querySelector('.popup-background');
      popup.style.display = 'none'; // 해당 영역을 지워서 않보이게 한다/
      popupBackground.style.display = 'none';
    },
    //Layer Popup 설정 끝

    counter_init: function() {
      // 메인화면 세션 카운트 실행
      this.cnt = parseInt(this.mainTimeLimit);
      this.tid = setInterval(this.counter_run, 1000);
    },

    counter_run: function() {
      // 메인화면 세션 카운트
      // document.getElementById("counter").innerText = this.time_format(this.cnt);
      this.cnt--;
      if (this.cnt < 0) {
        clearInterval(this.tid);
        /* 세션연장 여부를 질의하는 팝업을 띄운다. */
        this.open_window();
        /* 팝업에서 다시 카운트 시작 */
        this.cnt = parseInt(this.popupTimeLimit); //카운트 초기화(초단위)
        this.nCounter_init();
      }
    },

    nCounter_init: function() {
      // 팝업화면 추가 세션 카운트 실행
      this.isIntendedExit = false;
      this.tid = setInterval(this.nCounter_run, 1000);
    },

    nCounter_run: function() {
      // 팝업화면 추가 세션 카운트
      document.getElementById('ncounter').innerText = this.time_format(this.cnt);
      this.cnt--;
      if (this.cnt < 0) {
        this.exit();
      }
    },

    counter_reset: function() {
      // 메인화면 카운트 재시작 및 서버 세션 연장
      //(1) 세션 카운트 초기화
      this.isIntendedExit = true;
      clearInterval(this.tid);
      this.counter_init();

      //(2) 팝업화면 추가 세션 카운트도 초기화
      document.getElementById('ncounter').innerText = '';
    },

    time_format: function(s) {
      var nHour = 0;
      var nMin = 0;
      var nSec = 0;
      if (s > 0) {
        nMin = parseInt(s / 60);
        nSec = s % 60;

        if (nMin > 60) {
          nHour = parseInt(nMin / 60);
          nMin = nMin % 60;
        }
      }
      if (nSec < 10) nSec = '0' + nSec;
      if (nMin < 10) nMin = '0' + nMin;

      return nMin + '분 ' + nSec + '초';
    },

    exit: function() {
      clearInterval(this.tid);
      this.getMessage(null);
      this.cnt = parseInt(this.mainTimeLimit);
      this.$router.push({ name: 'HomeFeed' });
    },
  },
  created: function() {
    this.getStartTime();
    this.counter_init();
  },
  computed: {
  ...mapGetters([
    'userEmail', 
    'isLogin']),
  },
  beforeRouteLeave(to, from, next) {
    if (document.getElementById('newDivSpace')) {
      document.getElementById('newDivSpace').remove();
    }

    clearInterval(this.tid);

    const userInfo = {
      startTime: this.startTime,
      userEmail: this.userEmail,
    };

    this.getUserInfo(userInfo)

    if (!this.isLogin) {
      next()
    } else if (this.isIntendedExit) {
      swal({
        title: '정말 갤럭시 방을 나가시겠어요?',
        icon: '/img/star.5dee8d8d.png',
        buttons: {
          text: '네 나갈래요!',
          cancel: '아니요!',
        },
      }).then((value) => {
        if (value) {
          axios({
            method: 'post',
            url: '/galaxy/exit',
            data: userInfo,
          })
            .then((res) => {
              this.getMessage(null);
              next();
            })
            .catch((err) => {
              console.log(err);
            });
        }

        swal.close();
      });
    } else {
      axios({
        method: 'post',
        url: '/galaxy/exit',
        data: userInfo,
      })
        .then((res) => {
          this.getMessage(null);
          next();
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
};
</script>

<style>
.GalaxyChat {
  background-color: #b7beda;
  width: 460px;
  height: 780px;
  border-radius: 20px;
  position: fixed;
  bottom: 15px;
  right: 30px;
  padding-top: 10px;
}
</style>

<style scoped>
.exit {
  width: 40px;
  height: 40px;
  margin-left: 3px;
}

.galaxy-exit-button {
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 60px;
  border-radius: 75px;
  bottom: 25px;
  left: 30px;
  background-color: #99b7ff;
  cursor: pointer;
  font-size: 40px;
  font-weight: bold;
  z-index: 5;
}

.popup-background {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.4);
  z-index: 1100;
}

#popup {
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -250px;
  margin-top: -100px;
  background-color: #99b7ff;
  border-radius: 1.5rem;
  width: 500px;
  height: 180px;
  padding-top: 10px;
}

.popup-body {
  position: relative;
}

.exit-message {
  position: absolute;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 27px;
  font-weight: bold;
  top: 15px;
  left: 63px;
}

.countdown-message {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 18px;
  font-weight: bold;
  color: #ff3d3d;
  margin-bottom: 8px;
}

#counter {
  color: white;
}

#ncounter {
  color: #ff3d3d;
  font-family: 'Noto Sans KR', sans-serif;
}

.star {
  top: -10px;
  width: 65px;
  height: 65px;
  margin-right: 360px;
}

.btn-success {
  font-family: 'Noto Sans KR', sans-serif;
  color: #fff;
  background-color: #1c84c4;
  border-style: none;
  font-weight: bold;
  margin-right: 10px;
  width: 120px;
  height: 40px;
}

.btn-check:focus + .btn-success,
.btn-success:focus {
  color: #fff;
  background-color: #1c84c4;
  border-style: none;
  box-shadow: none;
}

.btn-check:checked + .btn-success,
.btn-check:active + .btn-success,
.btn-success:active,
.btn-success.active,
.show > .btn-success.dropdown-toggle {
  color: #fff;
  background-color: #1c84c4;
  border-color: #1c84c4;
  border-style: none;
}

.btn-check:checked + .btn-success:focus,
.btn-check:active + .btn-success:focus,
.btn-success:active:focus,
.btn-success.active:focus,
.show > .btn-success.dropdown-toggle:focus {
  box-shadow: none;
}

.btn-success:hover {
  color: #fff;
  background-color: #1c84c4;
  border-color: #1c84c4;
  border-style: none;
}

.btn-danger {
  font-family: 'Noto Sans KR', sans-serif;
  color: #fff;
  background-color: #f53535;
  border-color: #f53535;
  border-style: none;
  font-weight: bold;
  width: 120px;
  height: 40px;
}

.btn-danger:hover {
  color: #fff;
  background-color: #f53535;
  border-color: #f53535;
}

.btn-check:checked + .btn-danger,
.btn-check:active + .btn-danger,
.btn-danger:active,
.btn-danger.active,
.show > .btn-danger.dropdown-toggle {
  color: #fff;
  background-color: #f53535;
  border-style: none;
}

.btn-check:focus + .btn-danger,
.btn-danger:focus {
  color: #fff;
  background-color: #f53535;
  border-style: none;
  box-shadow: none;
}

.btn-check:checked + .btn-danger:focus,
.btn-check:active + .btn-danger:focus,
.btn-danger:active:focus,
.btn-danger.active:focus,
.show > .btn-danger.dropdown-toggle:focus {
  box-shadow: none;
}

.exit-modal-footer {
  margin-top: 20px;
  margin-right: 30px;
}
</style>
