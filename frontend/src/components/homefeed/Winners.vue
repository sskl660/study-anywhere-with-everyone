<template>
  <div class="display-6 winners">
    <div class="winners-title d-flex justify-content-center">
      <img class="trophy" src="../../assets/trophy.png" alt="trophy"/>
       &nbsp;이번 주 명예의 전당&nbsp;
      <img class="trophy" src="../../assets/trophy.png" alt="trophy"/>
    </div>
    <WinnerList/>
  </div>
</template>

<script>
import WinnerList from '@/components/homefeed/WinnerList'

export default {
  name: 'Winners',
  components: {
    WinnerList
  }
}
</script>

<style scoped>
  .winners {
    display: inline-block;
    /* background-color:rgba(255,255,255, 0.2); */
    box-shadow:
    0 0 20px 3px #fff,  
    0 0 20px 15px rgb(246, 248, 92); 
    /* 0 0 140px 90px #0ff;  */
    width: 550px;
    height: 840px;
    margin-top: 60px;
    margin-right:160px;
    border-radius: 30px;
    background-color:rgba(255,255,255, 0.13);
  }

  .winners-title {
    font-family: "Noto Sans KR", sans-serif;
    /* margin-top: 100px; */
    font-weight: 400;
    color:#fff;
    margin-top: 30px;
  }

  .trophy {
    width: 50px;
    height: 50px;
  }
</style>