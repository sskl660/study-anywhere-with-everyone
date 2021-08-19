<template>
    <div>
        <!-- 배경우주영상 -->
        <video muted autoplay loop style="width:100%;">
            <source src="@/assets/space.mp4" type="video/mp4">
        </video>
        <!-- 웰컴페이지 구현 -->
        <div class="welcome-container"  >   
            <div class="stage neonSSAZIP" >
                SSA.zip
            </div>
            <!-- SSAZIP 구현 -->
            <div class="stage" >
                <img class="ssazip box bounce-7" src="../assets/ssazip.png" alt="ssazip"/>
            </div>
            <!-- 로그인, 회원가입 버튼 -->
            <div class="neonLogin login-btn" data-bs-toggle="modal"
                data-bs-target="#loginModal"
                data-bs-whatever="@mdo"
                style="line-height:40px; cursor:pointer;">
                Login
            </div>
            <router-link class="neonJoin join-btn" style="text-decoration:none;" to="/join">Join</router-link>
        </div>  
        <LoginModal/>
    </div>
</template>

<script>
import LoginModal from '@/components/welcome/LoginModal';
import { mapGetters } from 'vuex';

export default {
    name: 'Welcome',
    components: {
        LoginModal, // 로그인 모달을 가져옴
    },
    methods: {
        //로그인 상태라면 챌린지(임시) 페이지로 이동
        logined: function() {
            if (this.isLogin) {
                this.$router.push({ path: '/homefeed' });
            }
        },
    },
    created: function() {
        this.logined();
    },
    beforeRouteLeave(to, from, next) {
        let modalBackdrops = document.querySelectorAll(".modal-backdrop")
        modalBackdrops.forEach(modalBackdrop => {
            modalBackdrop.style.display = "none"
        })
        next()
    },
    computed: {
        ...mapGetters(['isLogin']),
    }
};
</script>

<style scoped>
.welcome-container {
  position: absolute;
  top: 60%;
  left:50%;
  transform: translate(-50%, -50%)
}
.ssazip {
  width: 201px;
  height: 260px;
  position:absolute;
  top: 6.5%;
  left: 57%;
  animation: bounce .1s ease infinite alternate;
}

/* 뛰어다니는 싸집이 */
.stage {
    display: flex;
    height: 330px;
}

/* 싸집이 크기 */
.box {
    align-self: flex-end;
    animation-duration: 2s;
    animation-iteration-count: infinite;
    height: 120px;
    margin: 0 auto 0 auto;
    transform-origin: bottom;
    width: 120px;
}
.bounce-7 {
    animation-name: bounce-7;
    animation-timing-function: cubic-bezier(0.280, 0.840, 0.420, 1);
}
@keyframes bounce-7 {
    0%   { transform: scale(1,1)      translateY(0); }
    10%  { transform: scale(1.1,.9)   translateY(0); }
    30%  { transform: scale(.9,1.1)   translateY(-100px); }
    50%  { transform: scale(1.05,.95) translateY(0); }
    57%  { transform: scale(1,1)      translateY(-7px); }
    64%  { transform: scale(1,1)      translateY(0); }
    100% { transform: scale(1,1)      translateY(0); }
}

/* 로그인버튼 */
.neonLogin {
  color: #fff;
  text-shadow:
    0 0 7px #fff,
    0 0 10px #fff,
    0 0 21px #fff,
    0 0 42px #fee613,
    0 0 82px #fee613;
  font-size: 2.2rem;
  animation: pulsate 1.5s infinite alternate;  
  border: 0.2rem solid #fff;
  border-radius: 2rem;
  /* padding: 0.4em; */
  box-shadow: 0 0 .2rem #fff,
            0 0 .2rem #fff,
            0 0 2rem #fee613,
            inset 0 0 1.3rem #fee613; 
  position:absolute;
  top: 48%;
  left: 20%;
  width:140px;
  height:60px;
  padding-top:5px;
}
/* 회원가입 버튼 */
.neonJoin {
  color: #fff;
  text-shadow:
    0 0 7px #fff,
    0 0 10px #fff,
    0 0 21px #fff,
    0 0 42px #f44cfa,
    0 0 82px #f44cfa;
  font-size: 2.2rem;
  animation: pulsate 1.5s infinite alternate;  
  border: 0.2rem solid #fff;
  border-radius: 2rem;
  /* padding: 0.4em; */
  box-shadow: 0 0 .2rem #fff,
            0 0 .2rem #fff,
            0 0 2rem #f44cfa,
            inset 0 0 1.3rem #f44cfa; 
  position:absolute;
  top: 48%;
  left: 60%;
  width:140px;
  height:60px;
}
/* 싸집글자부분 */
.neonSSAZIP {
  color: #fff;
  text-shadow:
    0 0 10px #fee613;
  font-size: 10.2rem;
  animation: pulsate 1.5s infinite alternate;  
  padding: 0.4em;
}
</style>
