<template>
    <div>
        <!-- 배경우주영상 -->
        <video muted autoplay loop style="width:100%;">
            <source src="@/assets/space.mp4" type="video/mp4">
        </video>
        <!-- 웰컴페이지 구현 -->
        <div>    
            <div class="stage neonSSAZIP" style="width:300px; height:350px; position: absolute; top:200px; left:600px;">
                SSA.zip
            </div>
            <!-- SSAZIP 구현 -->
            <div class="stage" style="position: absolute;top:40px;right:790px;">
                <img class="ssazip box bounce-7" src="../assets/ssazip.png" alt="ssazip"/>
            </div>
            <!-- 로그인, 회원가입 버튼 -->
            <div class="neonLogin login-btn" data-bs-toggle="modal"
                data-bs-target="#loginModal"
                data-bs-whatever="@mdo"
                style="width:150px; position: absolute; top:550px; left:750px; height:75px; line-height:40px; cursor:pointer;">
                Login
            </div>
            <LoginModal/>
            <router-link class="neonJoin join-btn" style="text-decoration:none; width:150px; height:75px; position: absolute; top:550px; right:750px;line-height:40px;" to="/join">Join</router-link>
        </div>  
    </div>
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound';
import LoginModal from '@/components/welcome/LoginModal';
import '@/views/css/welcome.css';
export default {
    name: 'Welcome',
    components: {
        ButtonRound, // 둥근 버튼을 가져옴
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
    }
};
</script>

<style scoped>
.ssazip {
  width: 201px;
  height: 260px;
  margin-bottom: 20px;
  /* position: absolute;
  top:300px;
  left:800px; */
  animation: bounce .1s ease infinite alternate;
}
/* 로그인과 회원가입 버튼을 감싸는 부분 */
.menu {
    padding-right: 0px;
}

/* 회원가입 버튼 */
.join-btn .btn-light {
    color: #ffffff;
    background-color: #1c84c4;
    border-color: #1c84c4;
    font-size: 25px;
    font-weight: bold;
    width: 180px;
    height: 60px;
    margin-left: 90px;
}

/* 회원가입 버튼 색 변경 방지 */
.join-btn .btn-light:hover {
    color: #ffffff;
    background-color: #1c84c4;
    border-color: #1c84c4;
}

.btn-check:checked + .btn-light,
.btn-check:active + .btn-light,
.btn-light:active,
.btn-light.active,
.show > .btn-light.dropdown-toggle {
    color: #ffffff;
    background-color: #1c84c4;
    border-color: #1c84c4;
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
  padding: 0.4em;
  box-shadow: 0 0 .2rem #fff,
            0 0 .2rem #fff,
            0 0 2rem #fee613,

            inset 0 0 1.3rem #fee613; 
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
  padding: 0.4em;
  box-shadow: 0 0 .2rem #fff,
            0 0 .2rem #fff,
            0 0 2rem #f44cfa,
            inset 0 0 1.3rem #f44cfa; 
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
