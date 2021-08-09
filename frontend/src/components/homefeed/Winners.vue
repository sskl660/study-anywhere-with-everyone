<template>
  <div class="display-6 winners">
    <div class="winners-title">🏆 이번 주 명예의 전당 🏆</div>
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
    background-color: white;
    width: 600px;
    height: 960px;
    right: 0px;
  }

  .winners-title {
    margin-top: 130px;
  }
</style>