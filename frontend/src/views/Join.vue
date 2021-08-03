<template>
  <div class="d-flex justify-content-center">
     <div class="join-container">
      <h2>계정 등록</h2>
      <div>
        <label for="email">이메일</label>
        <input type="text" id="email">
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="text" id="password">
      </div>
      <div>
        <label for="password-confirm">비밀번호 확인</label>
        <input type="text" id="password-confirm">
      </div>
      <br>
      
      <h2>정보 등록</h2>
      <div class="d-flex justify-content-center">
        <div>
          <label for="name">이름</label>
          <input type="text" id="name">
        </div>
        <div>
          <label for="phone">전화번호</label>
          <input type="text" id="phone">
        </div>
      </div>
      
      <div class="d-flex justify-content-center">
        <div>
          <label for="grade">기수</label>
          <input type="text" id="grade">
        </div>
        <div>
          <label for="code">참여코드</label>
          <input type="text" id="code">
        </div>
      </div>
      <br>
      <ButtonRound text="가입하기"/>
    </div>
  </div>
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound'
export default {
  name: 'Join',
  components: {
    ButtonRound, // 둥근 버튼을 가져옴
  },
}
</script>

<style scoped>
  * {
    color: white;
  }

  h2 {
    text-align: left;
  }

  .join-container {
    background-color: rgba(255,255,255, 0.2);
    width: 1000px;
    height: 700px;
    margin-top: 200px;
    border-radius: 2rem;
  }
</style>