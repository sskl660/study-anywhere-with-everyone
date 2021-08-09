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
                        <h1>{{ userName }}의 {{ idx }}번째 과제</h1>
                    </div>
                    <div class="col Dleft flex-item">
                        <div id="uploadimg">
                            <div class="input-group">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="마우스로 과제 인증 사진을 끌어오세요"
                                    v-model="filename"
                                    @dragover.prevent
                                    @dragenter.prevent
                                    @drop.prevent="onDrop"

                                    @change="onFileSelected"

                                />
                                <div class="input-group-append">
                                    <span class="input-group-text" @click="onClickFile"><i class="fa fa-paperclip"> </i></span>
                                    <!-- <button class="btn btn-outline-info" @click="onClickUpload">Upload</button> -->
                                </div>
                                <input type="file" class="file-input" accept="image/*" ref="fileInput" @change="onFileChange" />
                            </div>
                            <div v-show="imageSrc" class="upload-image">
                                <img :src="imageSrc" />
                            </div>
                        </div>
                        <div id="post">
                            <div id="divCKEditor" class="writepost"></div>
                        </div>
                        <!-- 여기서부터 파일업로드 -->
                        <!-- <div>
                            <div class="form-check mb-3">
                                <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert">
                                <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
                            </div>
                            <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
                                <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
                                    <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index">
                                </div>
                            </div>
                        </div> -->
                        <div>
                            <p>
                                <input type="file" id="file" class="inputfile" v-on:change="upload" />
                                <label for="file" class="input-plus">+</label>
                            </p>
                            <!-- 이미지 여기서는 안보여줘도 되겠지 -->
                            <!-- <div>
                                <p><img v-bind:src="newImgSrc"></p>
                            </div> -->
                        </div>
                    </div>

                    <!-- <div class="like-box">
                        <img class="like" src="../assets/grayheart.png" alt="likeU">
                    </div> -->
                </div>

                <!-- 오른쪽 - 댓글창 -->
                <div class="right flex-item">
                    <!-- 댓글창 맨 위 개인 프로필 -->
                    <div id="infowriter">
                        <!-- <ProfileImage class="comment-img-box" /> -->
                        <div class="comment-text-box">
                            <h3>🔥 <strong>{{ userName }}</strong> 오늘도 화이팅!!! 🔥</h3>
                        </div>
                        <!-- <h4 id="writername" class="comment-img-box">{{ userTerm }}기 {{ userName }}</h4> -->
                        <hr id="line" style="margin-top: 12px"/>
                    </div>
                    <div>
                        <!-- 댓글 -->
                        <!-- <div>
                            <CommentBox style="d-flex justify-content-center" />
                        </div> -->
                        <!-- <div class="writecomment">  
                            <input type="text" id="send_comment" placeholder="  댓글 달기" name="send_comment" value="" onKeypress="javascript:if(event.keyCode==13) {search_onclick_submit}"/>
                        </div> -->
                    </div>
                </div>
            </div>

            <div style="margin-left:907px; margin-top:7px;" class="d-flex">
                <button class="btn btn-danger d-flex align-items-center" @click="goBack()">
                    <!-- <router-link to="/ChallengeRoom" style="text-decoration: none; color: #ffffff"> -->
                    <div style="text-decoration: none; color: #ffffff">취소</div>
                    <!-- </router-link> -->
                </button>&nbsp;
                <button class="btn btn-primary d-flex align-items-center" @click="sendPost">
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
import '@/components/css/PostDetail.css';
// import ButtonSquare from '@/components/common/ButtonSquare.vue'
import ProfileImage from '@/components/common/ProfileImage.vue';
// import CommentBox from "@/components/challengeroom/CommentBox.vue"
// import axios from "@/util/http-common.js";
import { mapGetters } from 'vuex';
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

Vue.use(CKEditor);

// import InsertModal from '@/components/modals/InsertModal.vue'
// import DetailModal from '@/components/modals/DetailModal.vue';
// import router from '../router/index.js'

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// Vue.use(VueAlertify);

export default {
    name: 'PostDetail',
    components: {
        // Title,          // 타이틀 가져오기
        // ButtonSquare,    // 둥근 버튼 가져오기
        ProfileImage,
        // CommentBox,
    },
    data: function() {
        return {
            // 버튼에 들어갈 문구들
            // 생성: '생성',
            // 취소: '취소',
            뒤로: '돌아가기',
            CKEditor: '',
            filename: '',
            imageSrc: '',
            attachFile: false,
            newImgSrc: '',
            idx: '',

            challengeno: 0,
      taskfile: new File(),
      taskimg: new File(),
      taskcontent: '',
      taskdesc: '',
      taskindex: 0,
      useremail: '',
        };
    },
    methods: {
        sendPost() {
            let message = this.CKEditor.getData();
            alert(message);
        },

        // 여기서부터 사진 업로드
        onDrop(event) {
            this.inputImageFile(event.dataTransfer.files);
        },
        onClickFile(event) {
            this.$refs.fileInput.click(event);
        },
        onFileChange(event) {
            this.inputImageFile(event.target.files);
        },
        inputImageFile(files) {
            if (files.length) {
                let file = files[0];
                if (!/^image\//.test(file.type)) {
                    alert('이미지 파일만 등록이 가능합니다');
                    return false;
                }
                this.filename = file.name;
                this.preview(file);
            }
        },
        onClickUpload() {
            this.preview(this.filename);
        },
        preview(file) {
            if (typeof file === 'string') {
                this.imageSrc = file;
            } else {
                let vm = this;
                let reader = new FileReader();
                reader.onload = () => {
                    vm.imageSrc = reader.result;
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
        goBack: function(){
            // alert('goBack function')
            this.$router.go(-1);
        },
    },
    mounted() {
        ClassicEditor.create(document.querySelector('#divCKEditor'))
            .then((editor) => {
                this.CKEditor = editor;
            })
            .catch((err) => {
                console.log(err);
            });
    },
    created: function() {
        this.challengeNo = this.$route.query.cn;
        this.idx = (Number)(this.$route.query.idx)+1;
    },
    computed: {
        ...mapGetters(['userName', 'userTerm']),
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

h3{
    padding-top: 11px;
}
</style>
