<template>
  <div>
    <Title style="display:inline-block"/>
    <div class="id-container">
      <div>idcard</div>
    </div>
  </div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import './css/profile.css'

export default {
  name: 'Profile',
  components: {
    Title,
  },
}
</script>

<style>
.id-container {
  top: 300px;
  display: inline-block;
}
</style>