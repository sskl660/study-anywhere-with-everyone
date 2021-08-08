<template>
  <div class="idcard" style="d-flex justify-content-center display: inline-block;">
    <!-- 프로필카드 좌측 컨테이너 -->
    <div class="idcard-left" style="display: inline-block;">
      <!-- 프로필카드 이미지 -->
      <ProfileImage class="profile-img-box" />
      <!-- 깃헙 아이콘. 블로그 아이콘 클릭시 해당 링크로 이동 -->
      <p>
        <a :href="userInfo.userGit" target="_blank"><i class="fa fa-git-square fa-2x" style="color:black" type="button"></i></a>
        <a :href="userInfo.userBlog" target="_blank"><i class="fab fa-blogger fa-2x ms-5" style="color:orange" type="button"></i></a>
      </p>
    </div>
  <!-- 프로필카드 우측 컨테이너  -->
    <div class="card-intro" style="display: inline-block;">
      <p style="font-size:25px">
        <!-- 몇기 이름 -->
        {{userInfo.userTerm}}기 {{userInfo.userName}}
        <!-- 계정 주인이면 프로필 편집 모달 뜸 -->
        <button v-if="this.checkOwner()" type="button" class="btn profile-edit-btn" data-bs-toggle="modal" data-bs-target="#profileEditModal">
          프로필편집
        </button>
        <!-- 계정주인이 아니고 팔로워 중에 한명이면 -->
        <button v-else-if="this.checkFollow()" @click="cancelFollow()" type="button" class="btn profile-edit-btn" >
          언팔로우
        </button>
        <!-- 계정주인이 아니고 팔로워 중에 한명도 아니면 -->
        <button v-else @click="makeFollow()" type="button" class="btn profile-edit-btn" >
          팔로우
        </button>
      </p>
      <!-- 유저 부가정보 항목-->
      <div align="right" style="display: inline-block;">
        <p style="color: #1F4256">MBTI</p>
        <p style="color: #1F4256">개발 스타일</p>
        <p style="color: #1F4256">관심분야</p>
        <p style="color: #1F4256">기술스택</p>  
      </div>
      <!-- 유저정보 -->
      <div align="left" style="display: inline-block; margin-left:30px;">
        <p>{{userInfo.userMbti}}</p>
        <p>{{userInfo.userDevstyle}}</p>
        <p>{{userInfo.userWishfield}}</p>
        <p>{{userInfo.userTechstack}}</p>  
      </div>
 
    </div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"
import { mapState } from 'vuex'
import axios from "@/util/http-common.js";
// import ButtonSquare from '@/components/common/ButtonSquare.vue'
export default {
  name: 'IdCard',
  components: {
    ProfileImage,
    // ButtonSquare,
  },
  data: function () {
    return {
      followers: [],
      follow: {
        "followFollower": "",
        "userEmail": "",
      }
    }
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
  },
  methods: {
    // 로그인한 사용자가 계정주인이면 true
    checkOwner: function () {
      // console.log(this.userInfo.userEmail) // 팔로우 당하는 사람
      // console.log(this.userEmail) // 팔로우 요청하는 사람
      if (this.userEmail == this.userInfo.userEmail) return true;
      else false;
    },
    // 로그인한 사용자가 계정주인이 아닌데 팔로워 중에 한명이라면 true
    checkFollow: function () {
      for (var i in this.followers) {
        if (this.userEmail == this.followers[i][0]) return true;
        else false;
      }
    },
    // 팔로우 요청시 필요한 데이터 넣어주는 함수
    doFollow: function() {
      console.log('왜 안되냐고')
      this.follow.followFollower = this.userEmail; //팔로우 요청하는 사람
      this.follow.userEmail = this.userInfo.userEmail;  // 팔로우 당하는 사람
      console.log('1'+this.userInfo.userEmail)
      console.log('2'+this.userEmail) 
      console.log(this.follow)
    },
    makeFollow: function () {
      axios({
          method: 'post',
          url: '/follow',
          data: this.follow,
        })
          .then((res) => {
              console.log(res.data)
              this.$router.go()
          })
          .catch((err) => {
              console.log(err);
          });
    },
    cancelFollow: function () {
      axios({
          method: 'delete',
          // 프로필계정 주인: 팔로우하는 사람
          url: `/follow/${this.userInfo.userEmail}/${this.userEmail}`,
        })
          .then((res) => {
              console.log('유저팔로우!!');
              console.log(res.data)
              this.$router.go()
          })
          .catch((err) => {
              console.log(err);
          });
    }
  },
  computed: {
    ...mapState([
      'userEmail',
    ]),
  },
  created:function() {
    this.checkOwner();
  },
  updated: function () {
    this.doFollow();
  }
}
</script>

<style>
/* id카드의 우측 정보 컨테이너 */
.card-intro {
  width: 300px;
  position: relative;
  top: 60px;
}
/* id카드 크기와 배경 */
.idcard{
  width:600px;
  height:350px;
  /* position:relative; */
  background-image: url(~@/assets/card2.png);
}
/* id카드 프로필 이미지 */
.profile-img-box {
  width: 170px;
  height: 170px;
  position: relative;
  top: -20px;
  left: 5px;
}
p {
  color: #420909;
  font-weight: bold;
}
/* 프로필 편집버튼 */
.profile-edit-btn {
  background-color: #E1AF4E !important;
  border-color: #E1AF4E !important;
  color: #420909 !important;
  font-weight:bold !important;
  margin-left: 10px;
}
/* .git-btn {
  width: 500px;
} */

.idcard-left {
  position: relative;
  top: 70px;
}
</style>