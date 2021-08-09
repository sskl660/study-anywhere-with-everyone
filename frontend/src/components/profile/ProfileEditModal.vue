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
              <!-- 내프로필 이미지 떠있는 곳 -->
              <img id="modalimage" class="profile-img-default apple" src="" alt="" >
              <!-- <img class="profile-img-default apple" src="@/assets/ssazip.png" alt="" > -->
              <!-- <ProfileImage class="profile-edit-img-box" /> -->
              <!-- 프로필 미리보기 -->
              <!-- class="item-file-image" -->
              
              <!-- 프로필 편집 -->
              <div>
                <form @submit.prevent="submitForm" class="form" enctype="multipart/form-data">
                  <div class="itemFileBox" ref="itemFileBox">
                    <input
                      type="file"                     
                      id="uploadItemFile"
                      ref="uploadItemFile"
                      @change="onFileSelected"
                      accept="image/*"
                      style="display:none"
                    />
                    <div>
                      <label for="uploadItemFile">
                        <div class="wrapper-image preview">
                          <img ref="uploadItemImage" class="profile-img-default"/>
                        </div>
                      </label>
                    </div>
                    <!-- <button v-on:click="getImage()">사나이 유희왕 할수있다</button> -->
                    <!-- <label for="ItemFile" >
                      <div class="wrapper-image">
                        <img style="display:none" class="profile-img-default" id="image" src="" />
                      </div>
                    </label> -->
                  </div>
                  <!-- <div>

                    <input style="display:none" type="file" ref="taskimg" name="taskimg" id="taskimg"/>
                  </div> -->
                  <!-- <button @click="profileShow()" class="profile-img-btn" id="shownbtn">사진 업로드</button> -->
                  <button style="display:none" type="submit" id="uploadSubmit">Submit</button>
                </form>
                <!-- 사진업로드를 클릭하면 함수를 실행하여 taskimg가 눌려서 사진업로드 창이 뜬다. -->
                <button @click="profileShow()" class="btn profile-img-edit-btn" id="shownbtn">사진 업로드</button>
                <!-- <button type="button" class="btn profile-img-edit-btn" id="uploadProfile">사진 업로드</button> -->
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
                <input class="profile-intro-input-top editMbti" maxlength="4" type="text" v-model="userInfo.userMbti" placeholder="">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editDevstyle" maxlength="12" type="text" v-model="userInfo.userDevstyle" placeholder="ex) 아침형, 몰입형">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editWishfield" maxlength="12" type="text" v-model="userInfo.userWishfield" placeholder="ex) 프론트엔드, 인공지능">
              </div>
              <div class="d-flex">
                <input class="profile-intro-input editTechstack " maxlength="12" type="text" v-model="userInfo.userTechstack" placeholder="ex) python 상, vue 중">
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
                <textarea class="profile-textarea-input editIntroduce" type="text" maxlength="250" v-model.trim="userInfo.userIntroduce" placeholder="ex) 각오, 소개 등 친구들에게 하고싶은 말을 자유롭게 써주세요.">
                </textarea>
              </div> 
              <div style="color:red; font-weight:600; margin-top:20px; margin-right:220px;">* 소개글은 250자 이내로 작성해주세요.</div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <!-- <button type="button" class="btn btn-danger mb-4 me-4" style="width:70px" data-bs-dismiss="modal">취소</button> -->
          <button @click="editSubmit" type="submit" class="btn profile-submit-btn me-5 mb-4" data-bs-dismiss="modal" style="width:70px; font-weight:100">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"
import axios from '@/util/http-common.js';
import http from "@/util/http-common.js";
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
      },
      default: true,
      preview: false,
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
      document.getElementById("uploadSubmit").click();
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

    },
    getImage: function(e) {
      //주의: BLOB 파일 용량 제한은 64kb까지임.. ->  ㅡ
      console.log('모달')
      console.log(this.userInfo)
      http.get(`/viewimage/${this.userInfo.userEmail}`).then((response) => {
        console.log('이미지성공')
        console.log(response.data);
        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
        document.getElementById("modalimage").src = imgsrc;
        this.imgData=imgsrc;
      })
      .catch((error) => {
        console.log("이미지없음")
        console.log(this.imgsrc);
        if (this.imgsrc == null) {
          console.log("호이")
          document.getElementById("modalimage").src = "/img/ssazip.43ffb363.png"
        }
      });
    },
    profileShow: function ( ){
      document.getElementById("uploadItemFile").click();
      // document.getElementById("uploadItemFile").click();
    },
    submitForm() {
      var frm = new FormData();
      var timg = document.getElementById("uploadItemFile");
      frm.append("file", timg.files[0]);
      frm.append("useremail", this.userInfo.userEmail);
      console.log('이미지!!')
      console.log(this.userInfo.userEmail)
      http
        .post(`/profile/upload/${this.userInfo.userEmail}`, frm, {
          header: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log(response);
          this.$router.go()
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onFileSelected(event) {
      this.default=false // 미리보기가 뜨면 기존의 프사이미지는 안뜸
      console.log(this.preview)
      this.preview=true
      console.log(this.preview)
      let image = event.target;
      if (image.files[0]) {
        let itemImage = this.$refs.uploadItemImage; //img dom 접근
        itemImage.src = window.URL.createObjectURL(image.files[0]); //img src에 blob주소 변환
        this.itemImageInfo.uploadImages = itemImage.src; //이미지 주소 data 변수에 바인딩해서 나타내게 처리
        itemImage.width = '100'; // 이미지 넓이
        itemImage.onload = () => {
          window.URL.revokeObjectURL(this.src); //나중에 반드시 해제해주어야 메모리 누수가 안생김.      
        };
      }
    },
  },
  updated: function () {
    this.getImage();
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
.apple {
  position: absolute;
  left:80px;
  top: -3px;
}
</style>