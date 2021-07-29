<template>
  <div>
    <div style="background:#1F4256;">
      <quick-menu :menu-count=getCount 
      :icon-class=icons 
      :menu-url-list=list 
      :background-color=backgroundColor 
      :color=color :position=position 
      :is-open-new-tab=getIsOpenNewTab 
      @process=print>
      </quick-menu>

    </div>

  </div>
</template>

<script>
import "@/components/css/Navbar.css";

import quickMenu from 'vue-quick-menu'


export default {
  name: 'Navbar',
  data () {
    return {
      count:4,
      icons:["fa fa-github","fa fa-comment","fa fa-code","fa fa-envelope"],
      list:[{'isLink':false},{'isLink':true,url:"/doo"},{'isLink':true,url:"/foo"},{'isLink':false}],
      backgroundColor:'#F9D479',
      color:'#EE4748',
      position:'top-right',
      isOpenNewTab:false,
      
    }
  },
  components:{
  'quickMenu':quickMenu
}}
</script>

<style>

</style>