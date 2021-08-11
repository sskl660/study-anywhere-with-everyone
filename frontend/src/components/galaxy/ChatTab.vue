<!-- 채팅 Tab CSS 수정-->
<template>
  <div class="GalaxyTab">
    <img
      src="@/assets/algo.png"
      style="width:80px; cursor: pointer;"
      @click="changeChatType(1)"
    >
    &nbsp;
    <img
      src="@/assets/cs.png"
      style="width:80px; cursor: pointer;"
      @click="changeChatType(2)"
    >
      <!-- <h4>CS</h4> -->
      &nbsp;
    <img
      src="@/assets/job.png"
      style="width: 80px; cursor: pointer;"
      @click="changeChatType(3)"
    >
      <!-- <h4>Job</h4> -->
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  data() {
    return {
      chatType: null,
    };
  },
  methods: {
    ...mapActions(['changeChatType']),
  },
};
</script>

<style>
h4{
  color: #ffffff;
}

.GalaxyTab{
  padding-top: 48px;
  width: 20%;
  margin-left: 72%;
}
</style>
