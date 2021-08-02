<template>
  <div>
    <h1 class="display-4 message">Coming Soon...</h1>
    <!-- flexbox를 이용해 위치 조정 -->
    <div class="d-flex justify-content-center welcome-container">
      <div>
        <!-- 별 구현 -->
        <div class="star1-box">
          <img class="star1" src="../assets/star.png" alt="star">
        </div>
        <div class="star2-box">
          <img class="star2" src="../assets/star.png" alt="star">
        </div>
        <div class="star3-box">
          <img class="star3" src="../assets/star.png" alt="star">
        </div>
        <!-- SSAZIP 구현 -->
        <img class="ssazip" src="../assets/ssazip.png" alt="ssazip">
        <!-- 로그인, 회원가입 버튼 -->
        <div class="d-flex menu">
          <div class="login-btn"><LoginModal text="Login"/></div>
          <div class="join-btn"><ButtonRound text="Join"/></div>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound'
import LoginModal from '@/components/LoginModal'
import "@/views/css/welcome.css";

export default {
  name: 'Welcome',
  components: {
    ButtonRound, // 둥근 버튼을 가져옴
    LoginModal,  // 로그인 모달을 가져옴
  },
}
</script>

<style scoped>
  /* 로그인과 회원가입 버튼을 감싸는 부분 */
  .menu {
    padding-right: 0px;
  }

  /* 회원가입 버튼 */
  .join-btn .btn-light {
    color: #FFFFFF;
    background-color: #1C84C4;
    border-color: #1C84C4;
    font-size: 25px;
    font-weight: bold;
    width: 180px;
    height: 60px;
    margin-left: 90px;
  }

  /* 회원가입 버튼 색 변경 방지 */
  .join-btn .btn-light:hover {
    color: #FFFFFF;
    background-color: #1C84C4;
    border-color: #1C84C4;
  }

  .btn-check:checked + .btn-light, .btn-check:active + .btn-light, .btn-light:active, .btn-light.active, .show > .btn-light.dropdown-toggle {
    color: #FFFFFF;
    background-color: #1C84C4;
    border-color: #1C84C4;
  }
</style>