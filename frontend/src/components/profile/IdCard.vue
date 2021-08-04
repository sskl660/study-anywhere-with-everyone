<template>
  <div class="idcard" style="d-flex justify-content-center display: inline-block;">
    <!-- 프로필카드 좌측 컨테이너 -->
    <div class="idcard-left" style="display: inline-block;">
      <!-- 프로필카드 이미지 -->
      <ProfileImage class="profile-img-box" />
      <!-- 깃헙 아이콘. 블로그 아이콘 클릭시 해당 링크로 이동 -->
      <p>
        <a :href="userInfo.userGit"><i class="fa fa-git-square fa-2x" style="color:black" type="button"></i></a>
        <a :href="userInfo.userBlog"><i class="fab fa-blogger fa-2x ms-5" style="color:orange" type="button"></i></a>
      </p>
    </div>
  <!-- 프로필카드 우측 컨테이너  -->
    <div class="card-intro" style="display: inline-block;">
      <p style="font-size:25px">
        <!-- 몇기 이름 -->
        {{userInfo.userTerm}}기 {{userInfo.userName}}
        <!-- user가 누군지에 따라서 프로필편집, 팔로우, 팔로우 취소로 보이게 하기 -->
        <!-- <button type="button" class="btn profile-edit-btn">프로필편집</button> -->
        <!-- Button trigger modal -->
        <button type="button" class="btn profile-edit-btn" data-bs-toggle="modal" data-bs-target="#profileEditModal">
          프로필편집
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