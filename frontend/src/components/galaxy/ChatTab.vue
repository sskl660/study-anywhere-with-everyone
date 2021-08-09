<!-- 채팅 Tab CSS 수정-->
<template>
  <div>
    <button
      type="button"
      class="btn btn-danger mb-4 me-4"
      style="width:100px"
      @click="changeChatType(1)"
    >
      알고리즘
    </button>
    <button
      type="button"
      class="btn btn-danger mb-4 me-4"
      style="width:100px"
      @click="changeChatType(2)"
    >
      CS
    </button>
    <button
      type="button"
      class="btn btn-danger mb-4 me-4"
      style="width: 100px"
      @click="changeChatType(3)"
    >
      취업
    </button>
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

<style></style>
