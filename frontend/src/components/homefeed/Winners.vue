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
    background-color: white;
    width: 600px;
    height: 100vh;
    right: 0px;
  }

  .winners-title {
    font-family: "Noto Sans KR", sans-serif;
    margin-top: 100px;
    font-weight: bold;
  }

  .trophy {
    width: 50px;
    height: 50px;
  }
</style>