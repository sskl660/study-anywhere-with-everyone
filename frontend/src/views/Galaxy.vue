<!-- Galaxy 템플릿에 맞게 배치-->
<!-- Galaxy CSS 적용-->
<template>
  <div class="">
    <div class="">
      <div><ChatTab /></div>
    </div>
    <div class="GalaxyChat">
      <div><Chat /></div>
    </div>
    
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

<style>

.GalaxyChat{
  background-color: #B7BEDA;
  width: 460px;
  height: 780px;
  border-radius: 20px;

  position: fixed;
  /* justify-content: center;
  align-items: center; */
  bottom: 15px;
  right: 30px;
  padding-top: 10px;
}
</style>
