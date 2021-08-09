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
                                        <span class="input-group-text" @click="onClickFile"><i class="fa fa-paperclip"> </i></span>
                                        <!-- <button class="btn btn-outline-info" @click="onClickUpload">Upload</button> -->
                                    </div>
                                    <input type="file" class="file-input" accept="image/*" ref="fileInput" id="taskimgId" @change="onFileChange" />
                                </div>
                                <div v-show="taskimg" class="upload-image">
                                    <img :src="taskimg" />
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
                            <button style="display:none" type="submit" id="submitId"></button>
                        </form>
                    </div>
                    <!-- <div class="like-box">
                        <img class="like" src="../assets/grayheart.png" alt="likeU">
                    </div> -->
                </div>

                <!-- 오른쪽 - 댓글창 -->
                <div class="right flex-item">
                    <!-- 댓글창 맨 위 개인 프로필 -->
                    <div id="infowriter">
                        <ProfileImage class="comment-img-box" />
                        <h4 id="writername">{{ userTerm }}기 {{ userName }}</h4>
                        <hr id="line" />
                        "해당 과제는 {{ chall_info.challengeTaskdeadlines[idx] }} 까지입니다"
                        <br />
                        <br />
                        <hr id="line" />
                        {{ chall_info.challengeDesc }} <br />
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
            taskimg: '',
            //imageSrc: '',
            attachFile: false,

            newImgSrc: '',
            idx: 0,

            challengeno: 0,
            // taskfile: new File(),
            //taskimg: '', //new File(),
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
        submitForm() {
            alert('폼실행');
            let message = this.CKEditor.getData();
            if (message == '' || message == null) {
                alert('글을 작성해주세요');
                return;
            }
            //alert(this.CKEditor.getData());
            //console.log(this.CKEditor.getData());
            var frm = new FormData();
            //var tfile = document.getElementBy
            // Id('taskfile');
            var timg = document.getElementById('taskimgId');
            //var timg = this.taskimg;
            console.log(timg.files);
            //frm.append('file', tfile.files[0]);
            if (timg.files != null) {
                // alert("파일없는데?")
                console.log('이미지 탑승');
                frm.append('img', timg.files[0]);
            }
            //frm.append('taskContent', message);
            frm.append('taskDesc', message);
            frm.append('challengeNo', this.challengeno);
            frm.append('taskIndex', this.idx);
            frm.append('userEmail', this.userEmail);
            console.log('보내는 값');
            console.log(frm.getAll('img'));
            axios.post('/challenge/task', frm, {
                header: {
                    'Content-Type': 'multipart/form-data',
                },
            })
                .then((response) => {
                    // console.log(formData.getAll);
                    console.log('과제 제출 성공!');
                    alert("과제 제출이 성공하였습니다.")
                                            this.$router.push({ path: '/challengeRoom', query: { cn: this.challengeno } });

                })
                .catch((error) => {
                    // for (var pair of this.formData.entries()) {
                    //   console.log(pair[0] + "," + pair[1]);
                    // }
                                        console.log('과제 제출 실패!');

                    console.log(error);
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
                    alert('이미지 파일만 등록이 가능합니다');
                    return false;
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
            // alert('goBack function')
            this.$router.go(-1);
        },
        getChallInfo: function() {
            axios({
                methods: 'get',
                url: `/challenge/info/${this.challengeno}`,
            })
                .then((res) => {
                    this.chall_info = res.data;
                    console.log('페이지 로드 성공');
                    console.log(res);
                })
                .catch((err) => {
                    alert('정보부르기 실패');
                    console.log(err);
                });
        },
        clickerSubmit: function() {
            document.getElementById('submitId').click();
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

.comment-img-box {
    width: 75px;
    height: 75px;
    position: relative;
    top: 17px;
    left: -160px;
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
</style>
