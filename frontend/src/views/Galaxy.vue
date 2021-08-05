<!-- Galaxy 템플릿에 맞게 배치-->
<!-- Galaxy CSS 적용-->
<template>
  <div>
    <div><ChatTab /></div>
    <div><Chat /></div>
  </div>
</template>

<script>
import Chat from '@/components/galaxy/Chat.vue';
import ChatTab from '@/components/galaxy/ChatTab.vue';

export default {
  name: 'Galaxy',
  components: {
    Chat,
    ChatTab,
  },
};
</script>

<style></style>
