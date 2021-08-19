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
            <h1>{{ userName }}의 {{ Number(idx) + 1 }}번째 과제</h1>
          </div>
          <div class="col Dleft flex-item">
            <form @submit.prevent="submitForm" class="form" enctype="multipart/form-data">
              <div id="uploadimg">
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="마우스로 과제 인증 사진을 끌어오세요"
                    v-model="taskimg"
                    @dragover.prevent
                    @dragenter.prevent
                    @drop.prevent="onDrop"
                  />
                  <!-- accept=".png"
                                    @change="onFileChange" -->
                  <div class="input-group-append">
                    <span class="input-group-text" @click="onClickFile"
                      ><i class="fa fa-paperclip"> </i
                    ></span>
                  </div>
                  <input
                    type="file"
                    class="file-input"
                    accept="image/*"
                    ref="fileInput"
                    id="taskimgId"
                    @change="onFileChange"
                  />
                </div>
                <div v-show="taskimg" class="upload-image">
                  <img :src="taskimg" />
                </div>
              </div>
              <div id="post">
                <div id="divCKEditor" class="writepost">
                  <html
                    placeholder="<h3>과제 내용을 작성 해 주세요.&nbsp;</h3>
                                    <ul><li>친구들에게 보일 과제 설명입니다.
                                    </li><li>이미지를 위에 올리면 바로 볼 수 있습니다.</li>
                                    <li>이미지가 아니라면 아래 ‘파일 선택’ 이용해 주세요.</li></ul>"
                  >
                    <!-- <h3>과제 내용을 작성 해 주세요.&nbsp;</h3>
                                    <ul><li>친구들에게 보일 과제 설명입니다.
                                    </li><li>이미지를 위에 올리면 바로 볼 수 있습니다.</li>
                                    <li>이미지가 아니라면 아래 ‘파일 선택’ 이용해 주세요.</li></ul> -->
                  </html>
                </div>
              </div>
              <div>
                <p>
                  <input type="file" id="taskfileId" class="inputfile" v-on:change="upload" />
                  <label for="taskfileId" class="input-plus">+</label>
                </p>
              </div>
              <button style="display:none" type="submit" id="submitId"></button>
            </form>
          </div>
        </div>

        <!-- 오른쪽 - 댓글창 -->
        <div class="right flex-item">
          <!-- 댓글창 맨 위 개인 프로필 -->
          <div id="infowriter">
            <!-- <ProfileImage class="comment-img-box" /> -->
            <div class="comment-text-box">
              <h3>
                🔥 <strong>{{ userTerm }}기 {{ userName }}</strong> 오늘도 화이팅!!! 🔥
              </h3>
            </div>
            <h3></h3>
            <hr id="line" />
            "해당 과제는 {{ chall_info.challengeTaskdeadlines[idx] }} 까지입니다"
            <br />
            <br />
            <hr id="line" />
            <div
              style="margin-left:30px; margin-right:25px; text-align:left"
              v-html="handleNewLine(chall_info.challengeDesc)"
            ></div>
            <br />
          </div>
          <div></div>
        </div>
      </div>

      <div style="margin-left:907px; margin-top:7px;" class="d-flex">
        <button class="btn btn-danger d-flex align-items-center" @click="goBack()">
          <!-- <router-link to="/ChallengeRoom" style="text-decoration: none; color: #ffffff"> -->
          <div style="text-decoration: none; color: #ffffff">취소</div>
          <!-- </router-link> --></button
        >&nbsp;
        <button class="btn btn-primary d-flex align-items-center" @click="clickerSubmit()">
          <div>생성</div>
        </button>
      </div>

      <!-- <div class="btn-footer">
                <router-link to="/ChallengeRoom"><button type="button" class="btn btn-danger Pcancel_btn">취소</button></router-link>&nbsp;
                <button type="button" class="btn btn-primary Pjoin_btn" @click="sendPost">생성</button>
            </div> -->

      <!-- <div class="Pjoin_btn"><ButtonSquare :text="생성" @click="sendPost"/></div>
            <div class="Pcancel_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="취소"/></router-link></div> -->
      <!-- <div class="Pback_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="뒤로"/></router-link></div> -->
    </div>
  </div>
</template>

<script>
import swal from 'sweetalert';
import '@/components/css/PostDetail.css';
// import ButtonSquare from '@/components/common/ButtonSquare.vue'
import ProfileImage from '@/components/common/ProfileImage.vue';
// import CommentBox from "@/components/challengeroom/CommentBox.vue"
import axios from '@/util/http-common.js';
import { mapGetters } from 'vuex';
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

Vue.use(CKEditor);

export default {
  name: 'PostDetail',
  data: function() {
    return {
      CKEditor: '',
      taskimg: '',
      attachFile: false,
      newImgSrc: '',
      idx: 0,
      challengeno: 0,
      taskcontent: '',
      taskdesc: '',
      taskindex: 0,
      chall_info: {
        challengeCapacity: 0,
        challengeCategory: 'string',
        challengeDesc: 'string',
        challengeEnddate: 'string',
        challengeGroup: [['string']],
        challengeLevel: 0,
        challengeName: 'string',
        challengeNo: 0,
        challengeStartdate: 'string',
        challengeTaskCnt: 0,
        challengeTaskdeadlines: ['string'],
      },
    };
  },
  methods: {
    // 개행 처리
    handleNewLine(text) {
      return String(text).replace(/(?:\r\n|\r|\n)/g, '</br>');
    },
    submitForm() {
      let message = this.CKEditor.getData();
      if (message == '' || message == null) {
        swal('글을 작성해주세요');
        return;
      }
      if (message.length > 2000) {
        swal('내용은 2000자를 초과할 수 없습니다. 현재 글자 수 ' + message.length + '자');
        return;
      }
      var frm = new FormData();
      var timg = document.getElementById('taskimgId');
      var tfile = document.getElementById('taskfileId');
      //파일 첨부 여부와 사이즈 검색
      if (timg.files.length != 0) {
        if (timg.files[0].size > 1024 * 100) {
          // 용량 초과시 경고후 해당 파일의 용량도 보여줌
          swal(
            '100kb 이하 이미지만 등록할 수 있습니다.\n\n' +
              '현재 이미지 크기 : ' +
              Math.round((timg.files[0].size / 1024) * 100) / 100 +
              'KB'
          );
          return;
        } else {
          // console.log('이미지 탑승');
          frm.append('img', timg.files[0]);
        }
      }
      if (tfile.files.length != 0) {
        // console.log('파일 탑승');
        if (tfile.files[0].size > 1024 * 2000) {
          swal(
            '2 mb 이하 첨부파일만 등록할 수 있습니다.\n\n' +
              '현재 파일 크기 : ' +
              Math.round((tfile.files[0].size / 1024) * 100) / 100 +
              'KB'
          );
          return;
        } else {
          frm.append('file', tfile.files[0]);
        }
      }
      frm.append('taskContent', message);
      frm.append('challengeNo', this.challengeno);
      frm.append('taskIndex', this.idx);
      frm.append('userEmail', this.userEmail);
      axios
        .post('/challenge/task', frm, {
          header: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          // console.log('과제 제출 성공!');
          // swal('과제 제출이 성공하였습니다.');
          this.$router.push({ path: '/challengeRoom', query: { cn: this.challengeno } });
        })
        .catch((error) => {
          // console.log('과제 제출 실패!');
          // console.log(error);
        });
    },

    // 여기서부터 사진 업로드
    onDrop(event) {
      this.inputImageFile(event.dataTransfer.files);
    },
    inputImageFile(files) {
      if (files.length) {
        let file = files[0];
        if (!/^image\//.test(file.type)) {
          swal('이미지 파일만 등록이 가능합니다');
          return false;
        }

        if (file.size > 1024 * 100) {
          // 용량 초과시 경고후 해당 파일의 용량도 보여줌
          swal(
            '100kb 이하 이미지만 등록할 수 있습니다.\n\n' +
               '현재 이미지 크기 : '  +
              Math.round((file.size / 1024) * 100) / 100 +
              'KB'
          );
          return;
        }

        this.taskimg = file.name;
        this.preview(file);
      }
    },
    onClickFile(event) {
      this.$refs.fileInput.click(event);
    },
    onFileChange(event) {
      this.inputImageFile(event.target.files);
    },
    onClickUpload() {
      this.preview(this.taskimg);
    },
    preview(file) {
      if (typeof file === 'string') {
        this.taskimg = file;
      } else {
        let vm = this;
        let reader = new FileReader();
        reader.onload = () => {
          vm.taskimg = reader.result;
        };
        reader.readAsDataURL(file);
      }
    },
    // 여기서부터 파일업로드
    // + 를 눌렀을 때 함수
    upload(e) {
      let file = e.target.files;
      let reader = new FileReader();

      reader.readAsDataURL(file[0]);
      reader.onload = (e) => {
        this.newImgSrc = e.target.result; // 로컬에서의 이미지 경로
      };
    },
    goBack: function() {
      // swal('goBack function')
      this.$router.go(-1);
    },
    getChallInfo: function() {
      axios({
        methods: 'get',
        url: `/challenge/info/${this.challengeno}`,
      })
        .then((res) => {
          this.chall_info = res.data;
          // console.log('페이지 로드 성공');
          // console.log(res);
        })
        .catch((err) => {
          // swal('정보부르기 실패');
          // console.log(err);
        });
    },
    clickerSubmit: function() {
      document.getElementById('submitId').click();
    },
  },
  mounted() {
    ClassicEditor.create(document.querySelector('#divCKEditor'), {
      placeholder: '-여기에 과제에 대한 글을 작성하고 친구들에게 설명 해 주세요. -이미지(64kb)와 첨부파일(2Mb)을 올릴 수 있습니다.',
    })
      .then((editor) => {
        this.CKEditor = editor;
      })
      .catch((err) => {
        // console.log(err);
      });
  },
  created: function() {
    this.challengeno = this.$route.query.cn;
    this.idx = Number(this.$route.query.idx);
    this.getChallInfo();
  },
  computed: {
    ...mapGetters(['userName', 'userTerm', 'userEmail']),
  },
};
</script>

<style>
/* CKEditor 는 vue 와 별개로 rendering 되어서 scope 를 넣으면 반영되지 않는다. */
.ck-editor__editable {
  height: 370px !important;
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

.comment-text-box {
  margin-top: 15px;
  width: 90%;
  height: 55px;
  margin-left: 22px;
  position: relative;
  background-color: lightsteelblue;
  border-radius: 33px;
}
.btn-footer {
  display: flex;
  justify-content: right;
  padding-top: 2px;
  border-style: none;
  margin-left: 930px;
}
.inputfile {
  overflow: hidden;
  margin-top: 10px;
  margin-right: 80px;
}

.btn-primary {
  color: #ffffff;
  background-color: #4c67aa;
  border-color: #4c67aa;
  height: 80%;
  font-size: 8px;
}

.input-group-text {
  font-size: 1.5rem;
}

h3 {
  padding-top: 11px;
}
</style>
