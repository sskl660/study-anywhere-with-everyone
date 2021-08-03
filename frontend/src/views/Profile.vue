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
    <div>
      <ProfileTicket style="d-flex justify-content-center" />   
    </div>
    <ProfileEditModal/>
  </div>
</template>

<script>
import Title from '@/components/common/Title.vue'
import ProfileInfo from '@/components/profile/ProfileInfo.vue'
import ProfileTicket from '@/components/profile/ProfileTicket.vue'
import ProfileEditModal from '@/components/profile/ProfileEditModal.vue'
import './css/profile.css'
// import axios from 'axios'

export default {
  name: 'Profile',
  components: {
    Title,
    ProfileInfo,
    ProfileTicket,
    ProfileEditModal
  },
  data: function(){
    return{
        profile_title: '땡땡땡의 프로필',
        user_email: 'bbb@bbb.com',
        user_info: {
          user_term : 0, 
          user_graduated : Boolean,
          user_image : '',
          user_git : String,
          user_blog : String,
          user_devstyle : String,
          user_mbti : String,
          user_wishfield : String,
          user_introduce : String,
          user_techstack : String,
        }
    }
  },
  methods: {
    getUserInfo: function () {
      // axios({
      // metthod: 'get',
      // url: `http://localhost:8080/profile/info/${this.user_email}/`,
      // })
      //   .then((res) => {
      //     console.log(res)
      //     this.user_info = res.data
      //   })
    }

  },
  created: function () {
    this.getUserInfo()
  }
}
</script>

<style scoped>
/* 티켓상단의 개인정보부분 영역 컨테이너*/
.id-container {
  height: 350px;
  position: relative;
  top: 60px;
}
.profile-info-continer {
  height: 570px;
}
.profile-edit-btn {
  background-color: #E1AF4E !important;
  border-color: #E1AF4E !important;
  color: #420909 !important;
  font-weight:bold !important;
  margin-left: 10px;
}
</style>