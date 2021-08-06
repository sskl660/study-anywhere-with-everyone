<template>
  <div class="d-flex justify-content-between">
    <!-- 컴포넌트 위치가 겹치더라도 제일 앞에 위치하도록하여 네비게이션바가 작동되도록 한다. -->
    <div>
      <Logout/>
    </div>
    <div class="d-flex" style="position:relative; z-index:2; width:1750px">
      <div class="greeting" style="display:inline-block;">{{this.userName}}님 환영합니다</div>
      <Navbar style="display:inline-block"/>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/header/Navbar.vue'
import Logout from '@/components/header/Logout.vue'
import { mapState } from 'vuex'

export default {
  name: 'Header',
  components: {
    Navbar,
    Logout,
  },
  computed: {
    ...mapState([
      'userName',
    ])
  },
}
</script>

<style>
.greeting {
  position:absolute;
  top: 40px;
  width: 260px;
  height: 45px;
  background-color: rgb(31, 66, 86, 0.7);
  color:white; 
  font-size:25px;
  line-height:50px;
  border-radius: 1rem;
}
</style>