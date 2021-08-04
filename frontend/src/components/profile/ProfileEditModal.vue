<template>
  <!-- Modal -->
  <div class="modal fade" id="profileEditModal" tabindex="-1" aria-labelledby="profileEditLabel" aria-hidden="true">
    <div class="modal-dialog profile-modal-position">
      <div class="modal-content profile-modal-custom">
        <!-- 모달 상단 헤더 -->
        <div class="modal-header d-flex justify-content-center mt-2"  style="color:#420909; border-bottom: 2px solid #EDE0C6;">
          <h5 class="modal-title" id="exampleModalLabel" style="color:#420909; font-size:40px">프로필 편집</h5>
        </div>  
      
        <!-- 프로필 정보 폼 -->
        <div class="modal-body">
          <!-- 상위의 정보입력 부분 -->
          <div class="profile-edit-upper-container d-flex justify-content-between">
            <div style="display: inline-block;">
              <ProfileImage class="profile-edit-img-box" />
              <div>
                <button type="button" class="btn profile-img-edit-btn">사진 업로드</button>
              </div>
            </div>
            <!-- 상위의 정보입력 타이틀 -->
            <div style="width:180px; margin-left:40px;">
              <p class="mt-2" style="font-size:20px">MBTI </p>
              <p style="font-size:18px; margin-top:28px">개발스타일 </p>
              <p style="font-size:18px; margin-top:33px">관심분야 </p>
              <p style="font-size:18px; margin-top:36px">기술스택 </p>
            </div>
            <!-- 상위의 정보입력창 -->
            <div style="display: inline-block;">
              <div class="d-flex">
                <input class="profile-intro-input-top editMbti" type="text" v-model.trim="userInfo.userMbti" placeholder="">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editDevstyle" type="text" v-model.trim="userInfo.userDevstyle" placeholder="ex) 아침형, 몰입형">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editWishfield" type="text" v-model.trim="userInfo.userWishfield" placeholder="ex) 프론트엔드, 인공지능">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editTechstack " type="text" v-model.trim="userInfo.userTechstack" placeholder="ex) python 상, vue 중">
              </div> 
              <div style="color:red; font-weight:600; margin-top:20px; margin-left:50px;">* 모든 항목은 12자 이내로 작성해주세요.</div>
            </div>
          </div>
          <!-- 하위의 정보입력 부분 -->
          <div class="profile-edit-lower-container d-flex justify-content-between">
            <!-- 하위의 정보입력 타이틀 -->
            <div style="width:180px; margin-left:40px;">
              <p style="font-size:20px"><i class="fa fa-git-square fa-2x" style="color:black" type="button"></i></p>
              <p style="font-size:20px; margin-top:20px"><i class="fab fa-blogger fa-2x" style="color:orange" type="button"></i></p>
              <p style="font-size:18px; margin-top:33px">소개 </p>
            </div>
            <!-- 하위의 정보입력창 -->
            <div style="display: inline-block;">
              <div class="d-flex">
                <input class="profile-long-input editGit" type="text" v-model.trim="userInfo.userGit" placeholder="ex) 개인 깃헙 주소를 입력해주세요.">
              </div>
              <div class="d-flex">
                <input class="profile-long-input editBlog" type="text" v-model.trim="userInfo.userBlog" placeholder="ex) 개인 블로그 주소를 입력해주세요.">
              </div>
              <div class="d-flex">
                <input class="profile-textarea-input editIntroduce" type="text" v-model.trim="userInfo.userIntroduce" placeholder="ex) 각오, 소개 등 친구들에게 하고싶은 말을 자유롭게 써주세요.">
              </div> 
              <div style="color:red; font-weight:600; margin-top:20px; margin-right:220px;">* 소개글은 70자 이내로 작성해주세요.</div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger mb-4 me-4" style="width:70px" data-bs-dismiss="modal">취소</button>
          <button @click="editSubmit" type="button" class="btn profile-submit-btn me-5 mb-4" data-bs-dismiss="modal" style="width:70px; font-weight:100">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"
import axios from '@/util/http-common.js';
// import "@/css/profilemodal.css";

export default {
  name: 'ProfileEditModal',
  components: {
    ProfileImage,
  },
  props: {
    userInfo: {
      type: Object
    }
  },
  data: function() {
    return {
      editProfileData: {
        "userBlog": "",
        "userDevstyle": "",
        "userEmail": "",
        "userGit": "",
        "userImage": "",
        "userIntroduce": "",
        "userMbti": "",
        "userTechstack": "",
        "userWishfield": "",
      }
    }
  },
  methods: {
    editSubmit: function() {
      this.editProfileData.userBlog = document.querySelector('.editBlog').value
      this.editProfileData.userDevstyle = document.querySelector('.editDevstyle').value
      this.editProfileData.userEmail = this.userInfo.userEmail
      this.editProfileData.userGit = document.querySelector('.editGit').value
      this.editProfileData.userImage = this.userInfo.userImage
      this.editProfileData.userIntroduce = document.querySelector('.editIntroduce').value
      this.editProfileData.userMbti = document.querySelector('.editMbti').value
      this.editProfileData.userTechstack = document.querySelector('.editTechstack').value
      this.editProfileData.userWishfield = document.querySelector('.editWishfield').value
      this.editProfile()
      console.log('check')
      console.log(this.editProfileData)
    },
    editProfile: function () {
      axios({
      method: 'put',
      url: "/profile/update",
      data: this.editProfileData
      })
        .then((res) => {
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err)
        })

    }
  }
}
</script>

<style scoped>
/* 모달창 커스텀 */
.profile-modal-custom {
  width:800px;
  height: 850px;
  background-color: #EDE0C6;
  border-radius: 2rem;
}
/* 모달창 중앙위치하도록 조정 */
.profile-modal-position {
  margin-right: 850px;
}
/* 편집등록 버튼 */
.profile-submit-btn {
  background-color: #1C84C4 ;
  border-color: #1C84C4 ;
  color: #fff ;
  font-weight:bold ;
  margin-left: 10px;
}

/* 프로필 편집 모달 상위 영역 컨테이너 */
.profile-edit-upper-container {
  width: 650px;
  margin-left:60px;
}
/* 동그란 프로필 사진 부분 */
.profile-edit-img-box {
  width: 200px;
  height: 200px;
  margin-top: 5px;
}
/* 사진 업로드 버튼 */
.profile-img-edit-btn {
  background-color: #420909;
  border-color: #420909;
  color: #fff;
  margin-left: 5px;
  margin-top: 20px;
}
/* 프로필 편집 모달의 상위파트 input창-제일 위 */
.profile-intro-input-top {
  width: 300px;
  height: 40px;
  padding-left: 15px;
  border-radius: 0.5rem;
  border-style: none;
  margin-left:10px;
}
.profile-intro-input-top:focus {
  outline: none;
}
/* 프로필 편집 모달의 상위파트 input창-나머지 3개 */
.profile-intro-input {
  width: 300px;
  height: 40px;
  padding-left: 15px;
  border-radius: 0.5rem;
  border-style: none;
  margin-left:10px;
  margin-top: 20px;
}
.profile-intro-input::placeholder {
  color: #b3b3b3;
}
.profile-intro-input:focus {
  outline: none;
}

/* 하위 영역 컨테이너*/
.profile-edit-lower-container {
  width: 650px;
  margin-left:60px;
  margin-top: 35px;
}
/* 하위 긴 입력창 2개 */
.profile-long-input {
  width: 500px;
  height: 40px;
  padding-left: 15px;
  border-radius: 0.5rem;
  border-style: none;
  margin-left:10px;
  margin-bottom: 20px;
}
.profile-long-input::placeholder {
  color: #b3b3b3;
}
.profile-long-input:focus {
  outline: none;
}
/* 하위 소개글 입력창 */
.profile-textarea-input {
  width: 500px;
  height: 180px;
  padding-left: 15px;
  border-radius: 0.5rem;
  border-style: none;
  margin-left:10px;
}
.profile-textarea-input::placeholder {
  color: #b3b3b3;
}
.profile-textarea-input:focus {
  outline: none;
}
</style>