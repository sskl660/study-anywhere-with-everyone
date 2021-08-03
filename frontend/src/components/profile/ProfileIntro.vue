<template>
  <div class="profile-intro" style="display:inline-block">
    <div class="profile-intro-content" style="display:inline-block">
      {{userIntroduce}}
      
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileIntro",
  props: {
    userIntroduce: {
      type: Object
    }
  }
}
</script>

<style>
.profile-intro {
  width: 550px;
  height: 220px;
  background-color:rgba(169, 218, 246, 0.2);
  border-radius: 1rem;
  margin-top: 50px;
}
.profile-intro-content {
  position: relative;
  top: 30px;
  width: 460px;
  height: 300px;
  color: white;
}

</style>