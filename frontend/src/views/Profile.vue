<template>
  <div>
    <div class="profile-info-continer">
      <!-- 타이틀도 추후에 bg-img로 바꿔주기 -->
      <Title style="display:inline-block" :text="profile_title"/>
      <!-- 프로필 상단의 카드와 팔로워, 각오 소개글 -->
      <ProfileInfo class="id-container"/>
    </div>
    <!-- 프로필 티켓 -->
    <div>
      <ProfileTicket style="d-flex justify-content-center" />   
    </div>
  </div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import ProfileInfo from '@/components/profile/ProfileInfo.vue'
import ProfileTicket from '@/components/profile/ProfileTicket.vue'
import './css/profile.css'

export default {
  name: 'Profile',
  components: {
    Title,
    ProfileInfo,
    ProfileTicket
  },
  data: function(){
    return{
        profile_title: '땡땡땡의 프로필',
    }
  }
}
</script>

<style>
/* 티켓상단의 개인정보부분 영역 컨테이너*/
.id-container {
  height: 350px;
  /* margin-top:60px;
  margin-left: 310px;
  margin-right: 17%; */
  position: relative;
  top: 60px;
}
.profile-info-continer {
  height: 570px;
}
</style>