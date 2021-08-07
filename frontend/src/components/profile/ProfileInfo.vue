<template>
  <div class="d-flex justify-content-center">
    <!-- id 카드 -->
    <!-- <div>{{userInfo.userName}}</div> -->
    
    <IdCard style="display:inline-block" :userInfo="userInfo" :followers="followers" :followings="followings"/>
    <!-- 프로필 정보 우측의 컨테이너 -->
    <div class="profile-info" style="display:inline-block" >
      <!-- 상단의 챌린지수, 팔로워수, 팔로잉수 -->
      <ProfileStatus :userInfo="userInfo" :followers="followers" :followings="followings"/>
      <!-- 하단의 각오 및 소개글 -->
      <ProfileIntro style="display:inline-block" :userIntroduce="userInfo.userIntroduce"/>
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
  },
  props: {
    userInfo: {
      type: Object
    },
    followers: {
      type: Array
    },
    followings: {
      type: Array
    }
  }
}
</script>

<style>
.profile-info {
  margin-left:70px;
  width:580px;
}
</style>