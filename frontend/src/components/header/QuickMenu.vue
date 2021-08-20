<template>
	<div class="quick-menu" ref="quickMenu" :style="quickMenuStyle">

    <div v-for="(n, key) in menuCount" class="sub-menu" :key="n" :style="getSubMenu(n-1)">

      <div v-if="menuUrlList[n-1].url === ''">
        <div class="sub-sub-menu" @click="galaxyEntranceModal()">
          <router-link v-if="menuUrlList[n-1].isLink" :to="menuUrlList[n-1].url" :target="openNewTab" :style="subMenuStyle" @mouseover.stop="mouseEnterSubMenu" @mouseout.stop="mouseOutSubMenu">
            <i :class="iconClass[n-1]" ref="icon"></i>
            <figcaption class="menu-name-galaxy">갤럭시</figcaption> 
          </router-link>
          <a v-else :style="subMenuStyle" @mouseover.stop="mouseEnterSubMenu" @mouseout.stop="mouseOutSubMenu" @click="processCallback(key)">
            <i :class="iconClass[n-1]" ref="icon"></i>
          </a>
        </div>
      </div>

      <div v-else>
        <div class="sub-sub-menu">
          <router-link v-if="menuUrlList[n-1].isLink" :to="menuUrlList[n-1].url" :target="openNewTab" :style="subMenuStyle" @mouseover.stop="mouseEnterSubMenu" @mouseout.stop="mouseOutSubMenu">
            <i :class="iconClass[n-1]" ref="icon"></i>
            <figcaption v-if="menuUrlList[n-1].url === '/homefeed'" class="menu-name-home">
              홈
            </figcaption>
            <figcaption v-else-if="menuUrlList[n-1].url === '/challenges'" class="menu-name-challenge">
              챌린지
            </figcaption>
            <figcaption v-else class="menu-name-profile">
              프로필
            </figcaption>
          </router-link>
          <a v-else :style="subMenuStyle" @mouseover.stop="mouseEnterSubMenu" @mouseout.stop="mouseOutSubMenu" @click="processCallback(key)">
            <i :class="iconClass[n-1]" ref="icon"></i>
          </a>
        </div>
      </div>
        
    </div>

    <div class='menu' :style="menuStyle">
      <div class='core-menu' @click="toggleMenu">
        <div class='bar'></div>
      </div>
    </div>

  </div>
</template>

<script>
import swal from 'sweetalert';
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex'

export default{
  name:'quickMenu',
  props:{
    menuCount:{
      type: Number,
      required: true,
      default:4
    },
    iconClass:{
      type:Array,
      required: true
    },
    menuUrlList:{
      type:Array,
      required: true
    },
    backgroundColor:{
      type:String,
      default:'#20babb'
    },
    color:{
      type:String,
      default:'#fff'
    },
    isOpenNewTab:{
      type:Boolean,
      default:false
    },
    position:{
      type:String,
      default:'top-left'
    }
  },
  computed:{
    openNewTab(){
      return this.isOpenNewTab?'_blank':'_self'
    },
    quickMenuStyle(){
      const topPosition = {top:'30px'}, 
      bottomPosition={bottom:'30px'},
      leftPosition = {left:'30px'},
      rightPosition = {right:'30px'}
      let style = this.isTop?topPosition:bottomPosition
      Object.assign(style, this.isLeft?leftPosition:rightPosition)
      Object.assign(style,{transform: this.isLeft?"rotate(-180deg)":"rotate(180deg)"})
      return style
    },
    menuStyle(){
      return {
        backgroundColor: this.backgroundColor,
        color: this.color
      }
    },
    subMenuStyle(){
      const style = {
        backgroundColor: this.backgroundColor,
        color: this.color
      }
      return style
    },
    isTop(){
      return !!~this.position.toLowerCase().indexOf('top')
    },
    isLeft(){
      return !!~this.position.toLowerCase().indexOf('left')
    },
    ...mapGetters([
      'userEmail',
    ])
  },
  data(){
    return{
      menuSize:60,
      subMenu4:[[["0","-160"],["-80","-138.6"],["-138.6","-80"],["-160","0"]],[["0","-160"],["80", "-138.6"],["138.6","-80"],["160","0"]],[["0","160"],["138.6","80"],["80","138.6"],["160","0"]],[["-160","0"],["-138.6","80"],["-80","138.6"],["0","160"]]],
      subMenu3:[[["-160","0"],["-113","-113"],["0","-160"]],[["0","-160"],["113","-113"],["160","0"]],[["0","160"],["113","113"],["160","0"]],[["-160","0"],["-113","113"],["0","160"]]],
      subMenu2:[[["-160","0"],["0","-160"]],[["0","-160"],["160","0"]],[["0","160"],["160","0"]],[["-160","0"],["0","160"]]]
    }
  },
  methods:{
    getSubMenu(n){
      let menuPosition = this.menuCount===4?this.subMenu4:this.menuCount===3?this.subMenu3:this.subMenu2
      menuPosition = this.isTop&&this.isLeft?menuPosition[2]:this.isTop&&!this.isLeft?menuPosition[1]:!this.isTop&&this.isLeft?menuPosition[3]:menuPosition[0]
      return {top:menuPosition[n][0]+'px',left:menuPosition[n][1]+'px'}
    },
    toggleMenu(e){
      let menuEl = this.$refs.quickMenu
      let menuIconEl = this.$refs.icon
      if(!~menuEl.className.indexOf(' active')){
        menuEl.className += ' active';
        menuIconEl.forEach( function(element, index) {
          element.className += ' menu-animate';
        });
      } else {
        menuEl.className = menuEl.className.replace(' active','')
        menuIconEl.forEach( function(element, index) {
        element.className = element.className.replace(' menu-animate','')
        });
      }
      
    },
    processCallback(key){
      console.log(key)
      this.$emit('process',key)
    },
    mouseEnterSubMenu(e){
      if(e.target.tagName==='A'){
        e.target.style.backgroundColor = this.lightenColor(this.backgroundColor, 20)
        e.target.firstElementChild.style.backgroundColor = this.lightenColor(this.backgroundColor, 20)
      } else if(e.target.tagName==='I'){
        e.target.parentElement.style.backgroundColor = this.lightenColor(this.backgroundColor, 20)
        e.target.style.backgroundColor = this.lightenColor(this.backgroundColor, 20)
      }
      
    },
    mouseOutSubMenu(e){
      if(e.target.tagName==='A'){
        e.target.style.backgroundColor = this.backgroundColor
        e.target.firstElementChild.style.backgroundColor = this.backgroundColor
      }else if(e.target.tagName==='I'){
        e.target.parentElement.style.backgroundColor = this.backgroundColor
        e.target.style.backgroundColor = this.backgroundColor
      }
      
    },
    lightenColor (hex, amt) {
      var usePound = false
      if (hex[0] === '#') {
        hex = hex.slice(1)
        usePound = true
      }
      var num = parseInt(hex, 16)
      var r = (num >> 16) + amt
      if (r > 255) r = 255
      else if (r < 0) r = 0
          var b = ((num >> 8) & 0x00FF) + amt
      if (b > 255) b = 255
      else if (b < 0) b = 0
      var g = (num & 0x0000FF) + amt
      if (g > 255) g = 255
      else if (g < 0) g = 0
      return (usePound ? '#' : '') + (g | (b << 8) | (r << 16)).toString(16)
    },
    galaxyEntranceModal () {
      if (window.location.pathname != '/Galaxy') {
        if (window.location.pathname == '/profile' || window.location.pathname == '/challengeRoom' || window.location.pathname == '/postDetailAfter') {
          this.$router.go(-1)
        }
        swal({
          title: '갤럭시에 오신 것을 환영합니다!',
          text: '상태메시지를 입력해주세요.',
          icon: "/img/star.5dee8d8d.png",
          content: {
            element: "input",
            attributes: {
              placeholder: '메시지는 20자 이내로 작성해주세요.'
            }
          },
          button: {
            text: '입장',
            closeModal: false
          }
        })
          .then(message => {
            if (!message) {
              return swal({
                text: '메시지는 필수 입력사항입니다!',
                button: {
                  text: '확인',
                }
              })
            }

            if (20 < message.length) {
              return swal({
                text: '메시지는 20자 이내여야 합니다!',
                button: {
                  text: '확인',
                }
              })
            }

            const userInfo = {
              message: message,
              userEmail: this.userEmail
            } 

            axios({
              method: 'post',
              url: '/galaxy/entry',
              data: userInfo
            })
              .then(res => {
                this.getMessage(message)
                this.$router.push({path: '/Galaxy'})
              })
              .catch(err => {
                console.log(err)
              })

            swal.close()
          })
        .catch(err => {
          console.log(err)
        })
      }
    },
    ...mapActions([
      'getMessage',
    ])
  },
}
</script>

<style scoped>
.sub-sub-menu {
  height: 60px;
}

.menu-name-galaxy {
  opacity: 0;
  position: absolute;
  left: 5px;
  bottom: 15px;
  font-size: 18px;
  color: white;
}

a:hover .menu-name-galaxy{
  opacity: 1
}

.menu-name-home {
  opacity: 0;
  position: absolute;
  left: 22px;
  bottom: 15px;
  font-size: 18px;
  color: white;
}

a:hover .menu-name-home {
  opacity: 1
}

.menu-name-challenge {
  opacity: 0;
  position: absolute;
  left: 7px;
  bottom: 15px;
  font-size: 18px;
  color: white;
}

a:hover .menu-name-challenge {
  opacity: 1
}

.menu-name-profile {
  opacity: 0;
  position: absolute;
  left: 6px;
  bottom: 15px;
  font-size: 18px;
  color: white;
}

a:hover .menu-name-profile {
  opacity: 1
}
</style>

