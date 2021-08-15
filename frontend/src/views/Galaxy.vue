<!-- Galaxy 템플릿에 맞게 배치-->
<!-- Galaxy CSS 적용-->
<template>
  <div id="ds" class="">
    <SSazip id="asd"/>
    <SSazipRank id="galaxyRank"/>
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
import SSazip from '@/components/galaxy/SSazip.vue';
import SSazipRank from '@/components/galaxy/SSazipRank.vue';

export default {
  name: 'Galaxy',
  components: {
    Chat,
    ChatTab,
    SSazip,
    SSazipRank,
  },
      beforeRouteLeave(to, from, next) {
        if(document.getElementById('newDivSpace'))
          document.getElementById('newDivSpace').remove();
        //document.getElementById('newDivSpace').remove();
        next();
    },
  // methods:{
  //   createSSazipContainer: function () {
  //     const SSazipCon = document.getElementById('newDivSpace')
  //     SSazipCon.style = "width:500px;"
  //   }
  // }
  // updated: function() {
  //   this.$router.go()
  // }
  // connected: function(){
  //   this.$router.go()
  // }
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
