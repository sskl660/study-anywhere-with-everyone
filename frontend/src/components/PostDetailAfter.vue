<template>
  <div class="flex_container">
    <div class="stars-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar" />
    </div>

    <div class="mainbody">
      <div class="d-flex justify-content-center">
        <!-- 왼쪽 - 과제 설명란 -->
        <div class="left flex-item">
          <div class="col Tleft flex-item">
            <h1>
              {{ task_info.userName + " 과제" + (task_info.taskIndex + 1) }}
            </h1>
            <!-- 작성자가 보이는 부분 -->
          </div>
          <div class="col Dleft flex-item">
            <div class="Ddleft">
              <div v-show="imgFlag" id="uploadimg">
                <!-- 업로드 된 사진이 보이는 부분 -->
                <div class="upload-image">
                  <img id="image" src="" />
                  <!-- <div v-show="imageSrc" class="upload-image">
                                    <img :src="imageSrc" /> -->
                </div>
              </div>
              <div
                id="post"
                style="text-align: left; padding: 30px"
                v-html="task_info.taskContent"
              ></div>
            </div>
          </div>
          <div id="whoru" style="backgoround-color:red margin-top : 8px">
            <div class="like-box">
              <i
                v-show="!heart"
                class="fas fa-heart like-img"
                @click="sendLike(like)"
                style="cursor: pointer"
              ></i>
              <i
                v-show="heart"
                class="fas fa-heart like-img"
                @click="sendUnLike(like)"
                style="cursor: pointer; color: red"
              ></i>
            </div>

            <div class="like-num">
              <h6 style="margin-top: 10px">
                <strong>{{ task_info.likes + " 명" }}</strong
                >이 좋아합니다
              </h6>
            </div>
          </div>
          <div v-if="task_info.taskFile" id="filedownload">
            <a
              href="javascript:void(0);"
              v-on:click="download()"
              style="text-decoration: none; color: #blue"
              ><strong>첨부파일 다운로드</strong></a
            >
          </div>
        </div>

        <!-- 오른쪽 - 댓글창 -->
        <div class="right flex-item">
          <!-- 댓글창 맨 위 개인 프로필 -->
          <div id="infowriter">
            <img id="profileimage" class="comment-img-box" src="" alt="" />
            <h4 id="writername" style="color: black">
              {{ task_info.userTerm }} 기 {{ task_info.userName }}
            </h4>
            <hr id="line" />
          </div>
          <div>
            <!-- 댓글 -->
            <div>
              <CommentBox
                style="d-flex justify-content-center"
                :taskInfo="task_info"
              />
            </div>
          </div>
        </div>
      </div>

      <div style="margin-left: 876px; margin-top: 7px" class="d-flex">
        <button
          v-if="checkUser()"
          class="btn btn-danger d-flex align-items-center"
          @click="erazeTask()"
        >
          <div>삭제</div></button
        >&nbsp;
        <button
          class="btn btn-warning d-flex align-items-center"
          @click="goBack()"
        >
          <div style="text-decoration: none; color: #ffffff">뒤로가기</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/postdetailafter.css";
import ButtonSquare from "@/components/common/ButtonSquare.vue";
import CommentBox from "@/components/challengeroom/CommentBox.vue";
import axios from "@/util/http-common.js";
import http from "@/util/http-common.js";
import { mapActions, mapState } from "vuex";

// import Vue from 'vue';
// import CKEditor from '@ckeditor/ckeditor5-vue2';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

// Vue.use(CKEditor);

// import router from '../router/index.js'

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// Vue.use(VueAlertify);

export default {
  name: "PostDetailAfter",
  components: {
    // Title,          // 타이틀 가져오기
    ButtonSquare, // 둥근 버튼 가져오기
    CommentBox, // 댓글 구현하기
  },
  // props: { //여기를 this. router. query. url에 있는 key값 바꿔줘야한다
  //         forwardTaskNo: {
  //             type: String,
  //             default : ''
  //         },
  //     },
  data: function () {
    return {
      뒤로: "돌아가기",
      task_info: {
        likemembers: ["string"],
        likes: 0,
        taskContent: "string",
        taskDesc: "string",
        taskFile: "string",
        taskFileName: "string",
        taskFileType: "string",
        taskImage: "string",
        taskIndex: 0,
        taskNo: 0,
        userEmail: "string",
        userName: "string",
        userTerm: 0,
      },
      like: {
        taskNo: 0,
        userEmail: "string",
      },
      unlike: {
        taskNo: 0,
        userEmail: "string",
      },
      task_No: 0,
      heart: false,
      chall_no: 0,
      imgFlag: false,
      // CKEditor : '',
      // filename: '',
      // imageSrc: '',
      // attachFile: false,
    };
  },
  computed: {
    ...mapState(["userEmail"]),
  },
  methods: {
    getTaskInfo: function () {
      axios({
        methods: "get",
        url: `/challenge/task/${this.$route.query.taskNo}`,
      })
        .then((res) => {
          // alert("과제 상세 정보가 들어왔습니다.");
          console.log("getTaskInfo res로그");
          console.log(res.data);
          this.task_info = res.data;
          console.log(this.task_info);
          if (this.task_info.taskNo == 0) {
            alert("없는 과제 입니다.");
            this.$router.push({ path: "/homefeed" });
          }
        })
        .catch((err) => {
          console.log("getTaskInfo err로그");
          console.log(err);
        });
    },
    ...mapActions(["presslike", "pressunlike"]),
    checkUser: function () {
      if (this.userEmail === this.task_info.userEmail) {
        // alert('true');
        return true;
      } else {
        // alert('false');
        return false;
      }
    },
    // 좋아요
    sendLike: function (like) {
      document.querySelector(".like-img").style.color = "red";
      this.like.userEmail = this.userEmail;
      this.like.taskNo = this.task_info.taskNo;
      this.presslike(like);
      this.task_info.likes++;
      //alert('좋아요');
      this.heart = true;
      console.log(like);
    },
    // 좋아요 취소
    sendUnLike: function (like) {
      document.querySelector(".like-img").style.color = "black";
      this.like.userEmail = this.userEmail;
      this.like.taskNo = this.task_info.taskNo;
      this.pressunlike(like);
      this.task_info.likes--;
      // alert('좋아요 취소');
      this.heart = false;
      console.log(like);
    },
    // 뒤로가기
    goBack: function () {
      // alert('goBack function')
      this.$router.go(-1);
    },
    getLikeInfo: function () {
      axios({
        method: "get",
        url: `challenge/task/like/${this.userEmail}/${this.$route.query.taskNo}`,
      })
        .then((res) => {
          console.log("getLikeInfo res로그");
          console.log(res.data.userLikeFlag);
          this.heart = res.data.userLikeFlag;
        })
        .catch((err) => {
          console.log("getLikeInfo err로그");
          console.log(err);
        });
    },
    erazeTask: function () {
      if (confirm("정말 삭제하시겠습니까??") == true) {
        //확인
        // document.form.submit();
        axios({
          method: "delete",
          url: `/challenge/task/${this.task_No}`,
        })
          .then((res) => {
            console.log("erazeTask res로그 삭제성공");
            console.log(this.$route.query.taskNo + "번호 삭제");
            this.$router.go(-1);
            //                        this.$router.push({ path: '/challengeRoom', query: { cn: this.chall_no } });
          })
          .catch((err) => {
            console.log("erazeTask err로그");
            console.log(this.chall_no + "삭제시도");
            console.log(this.$route.query.taskNo + "번호 삭제 시도");
            console.log(err);
          });
      } else {
        //취소
        return;
      }
    },

    // 이미지 가져오기
    getProfileImage: function () {
      console.log("프로필 사진 가져오기 여기!!");
      http
        .get(`/viewimage/${this.task_info.userEmail}`)
        .then((response) => {
        //   console.log("과제 창 이미지성공");
          var imgsrc =
            "data:image/png;base64," +
            btoa(
              String.fromCharCode.apply(null, new Uint8Array(response.data))
            );
          document.getElementById("profileimage").src = imgsrc;
          // this.comment.userImage = imgsrc;
        })
        .catch((error) => {
          // console.log("이미지없음")
          // console.log(this.imgsrc);
        //   if (this.imgsrc == null) {
        //     document.getElementById("profileimage").src =
        //       "/img/ssazip.43ffb363.png";
        //   }
        });
    },
    getTaskImg: function () {
      console.log("getTaskImg 요청됨" + this.task_No);
      //주의: BLOB 파일 용량 제한은 64kb까지임.. ->  ㅡ
      axios
        .get(`/challenge/task/img/${this.task_No}`)
        .then((response) => {
          console.log("이미지소환성공");
          console.log(response.data);
          if(response.data == 'noImage'){
              console.log('여기 확인!!')
              return // 이미지가 없으면 이미지 불러오기 중단시키기
          }
          var imgsrc =
            "data:image/png;base64," +
            btoa(
              String.fromCharCode.apply(null, new Uint8Array(response.data))
            );
          document.getElementById("image").src = imgsrc;
          this.imgFlag = true;
          // this.imgData = imgsrc;
        //   console.log('여기보세요!!!!!!!!!!')
        //   console.log(imgsrc);
          
        })
        // .catch((error) => {
          // console.log("이미지없음")
          // console.log(this.imgsrc);
        //   if (this.imgsrc == null) {
            // console.log("얍")
            // this.imgFlag = false;
            //  document.getElementById('image') = null;
            //  document.getElementById('image').src = '/img/ssazip.43ffb363.png';
        //   }
        // });
    },
    download() {
      console.log(this.task_No + "번호 첨부파일 다운 요청");
      axios
        .get(`/challenge/task/file/${this.task_No}`, { responseType: "blob" })
        .then(({ data }) => {
          console.log("다운로드 통신 성공");
          const url = window.URL.createObjectURL(
            new Blob([data], { type: this.task_info.taskFileType })
          );
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", this.task_info.taskFileName);
          document.body.appendChild(link);
          link.click();
        })
        .catch((err) => {
          console.log("다운로드 통신 실패");
          console.log(err);
        });
    },
  },
  created: function () {
    // alert(this.forwardTaskNo);
    this.task_No = this.$route.query.taskNo;
    this.chall_no = this.$route.query.cn;
    this.getProfileImage();
    this.getTaskInfo();
    this.getTaskImg();
  },
  updated: function () {
    // 랜더링이 다 끝난 뒤에 들어오는 것
    this.getLikeInfo();
    this.getProfileImage();
  },
};
</script>

<style>
/* CKEditor 는 vue 와 별개로 rendering 되어서 scope 를 넣으면 반영되지 않는다. */
.ck-editor__editable {
  height: 380px !important;
}

.ck.ck-reset.ck-editor.ck-rounded-corners {
  /* margin-top: 200px; */
  background-color: #e7eeff;
  width: 90%;
  text-align: center;
  margin-left: 5%;
}

.thumbnail-wrapper {
  margin-top: 5px;
}

.thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}

.comment-img-box {
  width: 75px;
  height: 75px;
  position: relative;
  top: 17px;
  left: -160px;
  border-radius: 30%;
  border: 3px outset #99b7ff;
}

.btn-warning {
  color: #ffffff;
  background-color: #f0b756;
  border-color: #f0b756;
  height: 80%;
  font-size: 8px;
}

.btn-warning:hover {
  color: #ffffff;
  background-color: #f0b756;
  border-color: #f0b756;
}

.btn-danger {
  height: 80%;
  font-size: 10px;
  font-size: 8px;
}

i.fa.fa-paperclip {
  font-size: 24px;
}

#filedownload {
  position: absolute;
  margin-top: -42px;
  margin-left: 32%;
}

element.style {
  margin-top: 10px;
}
</style>
