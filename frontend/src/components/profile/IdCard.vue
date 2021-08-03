<template>
  <div class="idcard" style="d-flex justify-content-center display: inline-block;">
    <div class="idcard-left" style="display: inline-block;">
      <ProfileImage class="profile-img-box" />
      <p>
        <i class="fa fa-git-square fa-2x" style="color:black" type="button"></i>
        <i class="fab fa-blogger fa-2x ms-5" style="color:orange" type="button"></i>
      </p>
    </div>

    <div class="card-intro" style="display: inline-block;">
      <p style="font-size:25px">
        <!-- <p >{{ userTerm }}기 {{userName}}</p> -->
        <!-- pp{{user_info.userTerm}}기 {{user_info.userName}} -->
        {{userInfo.userTerm}}기 {{userInfo.userName}}
        <!-- user가 누군지에 따라서 프로필편집, 팔로우, 팔로우 취소로 보이게 하기 -->
        <!-- <button type="button" class="btn profile-edit-btn">프로필편집</button> -->
        <!-- Button trigger modal -->
        <button type="button" class="btn profile-edit-btn" data-bs-toggle="modal" data-bs-target="#profileEditModal">
          프로필편집
        </button>
      </p>
      <p>{{userInfo.userMbti}}</p>
      <p>{{userInfo.userDevstyle}}</p>
      <p>{{userInfo.userWishfield}}</p>
      <p>{{userInfo.userTechstack}}</p>    
    </div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"
// import ButtonSquare from '@/components/common/ButtonSquare.vue'
export default {
  name: 'IdCard',
  components: {
    ProfileImage,
    // ButtonSquare,
  },
  data: function () {
    return {
      // 버튼에 들어갈 문구들
      profileEdit: '프로필 편집',
      follow: '팔로우',
      unfollow: '팔로우 취소',
    }
  },
  props: {
    userInfo: {
      type: Object
    }
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