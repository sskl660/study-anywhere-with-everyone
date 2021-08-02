<template>
  <div style="d-flex ">
    <!-- id 카드 -->
    <IdCard style="display:inline-block"/>
    <!-- 카드 우측의 컨테이너 -->
    <div class="profile-info" style="display:inline-block">
      <!-- 상단의 챌린지수, 팔로워수, 팔로잉수 -->
      <ProfileStatus />
      <!-- 하단의 각오 및 소개글 -->
      <ProfileIntro style="display:inline-block"/>
    </div>
  </div>
</template>

<script>
import IdCard from "@/components/profile/IdCard.vue"
import ProfileStatus from "@/components/profile/ProfileStatus.vue"
import ProfileIntro from "@/components/profile/ProfileIntro.vue"

export default {
  name: 'profileinfo',
  components: {
    IdCard,
    ProfileStatus,
    ProfileIntro,
  }
}
</script>

<style>
.profile-info {
  margin-left:70px;
  width:580px;
}
</style>