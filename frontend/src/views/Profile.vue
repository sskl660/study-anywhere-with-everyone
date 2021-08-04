<template>
  <div>
    <!-- <p>{{this.user_info}}</p> -->
    <div class="profile-info-continer">
      <!-- 타이틀도 추후에 bg-img로 바꿔주기 -->
      <Title style="display:inline-block" :text="user_info.userName"/>
      <!-- 프로필 상단의 카드와 팔로워, 각오 소개글 -->
      <ProfileInfo class="id-container" :userInfo="user_info"/>
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
import axios from '@/util/http-common.js';

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
        // 유저 이메일 정보 받아와서 넣기
        useremail: 'bbb@bbb.com',
        user_info: {
          "userBlog": "string",
          "userDevstyle": "string",
          "userEmail": "string",
          "userFollower": 0,
          "userFollowing": 0,
          "userGit": "string",
          "userGraduated": true,
          "userImage": "string",
          "userIntroduce": "string",
          "userMbti": "string",
          "userName": "string",
          "userNumber": "string",
          "userTechstack": "string",
          "userTerm": 0,
          "userTotalcomplete": 0,
          "userTotaltime": 0,
          "userWeekcomplete": 0,
          "userWeektime": 0,
          "userWishfield": "string"
        }
    }
  },
  methods: {
    getUserInfo: function () {
      axios({
      method: 'get',
      url: `/profile/info/${this.useremail}`,
      })
        .then((res) => {
          console.log(res.data)
          this.user_info = res.data
        })
        .catch((err) => {
          console.log(err)
        })
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