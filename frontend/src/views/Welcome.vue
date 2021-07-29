<template>
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
        <div class="login-btn"><ButtonRound :text="login"/></div>
        <div class="join-btn"><ButtonRound :text="join"/></div>
      </div>

    </div>
  </div>
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound'
import "@/views/css/welcome.css";

export default {
  name: 'Welcome',
  components: {
    ButtonRound, // 둥근 버튼을 가져옴
  },
  data: function () {
    return {
      // 버튼에 들어갈 문구들
      login: 'Login',
      join: 'Join',
    }
  }
}
</script>

<style>

</style>