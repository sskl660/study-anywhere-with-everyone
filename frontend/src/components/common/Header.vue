<template>
  <div>
    <!-- 컴포넌트 위치가 겹치더라도 제일 앞에 위치하도록하여 네비게이션바가 작동되도록 한다. -->
    <div style="position:relative; z-index:2;">
      <Navbar/>
    </div>
    <div style="position:relative; z-index:2;">
      <Logout/>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/header/Navbar.vue'

export default {
  name: 'Header',
  components: {
    Navbar,
  }
}
</script>

<style>

</style>