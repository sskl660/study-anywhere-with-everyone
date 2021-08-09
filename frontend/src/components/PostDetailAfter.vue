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
                        <h1>{{ task_info.userName + ' 과제' + (task_info.taskIndex + 1) }}</h1>
                        <!-- 작성자가 보이는 부분 -->
                    </div>
                    <div class="col Dleft flex-item">
                        <div id="uploadimg">
                            <!-- 업로드 된 사진이 보이는 부분 -->
                            <div v-show="imageSrc" class="upload-image">
                                <img :src="imageSrc" />
                            </div>
                        </div>
                        <div id="post">
                            {{ task_info.taskDesc }}
                        </div>

                        <div>
                            <!-- 파일 다운로드 하는 부분 -->
                        </div>
                    </div>

                    <div class="like-box">
                        <i v-show="!heart" class="fas fa-heart like-img" @click="sendLike(like)" style="cursor:pointer;"></i>
                        <i v-show="heart" class="fas fa-heart like-img" @click="sendUnLike(like)" style="cursor:pointer; color:red"></i>
                    </div>

                    <div class="like-num">
                        <h6>
                            <strong>{{ task_info.likes + ' 명' }}</strong
                            >이 좋아합니다
                        </h6>
                    </div>
                </div>

                <!-- 오른쪽 - 댓글창 -->
                <div class="right flex-item">
                    <!-- 댓글창 맨 위 개인 프로필 -->
                    <div id="infowriter">
                        <img id="profileimage" class="comment-img-box" src="" alt="" />
                        <h4 id="writername">{{ task_info.userTerm }} 기 {{ task_info.userName }}</h4>
                        <hr id="line" />
                    </div>
                    <div>
                        <!-- 댓글 -->
                        <div>
                            <CommentBox style="d-flex justify-content-center" :taskInfo="task_info" />
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-left:876px; margin-top:7px;" class="d-flex">
                <button v-if="checkUser()" class="btn btn-danger d-flex align-items-center" @click="erazeTask()">
                    <div>삭제</div></button
                >&nbsp;
                <button class="btn btn-warning d-flex align-items-center" @click="goBack()">
                    <div style="text-decoration: none; color: #ffffff">뒤로가기</div>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import '@/components/css/postdetailafter.css';
import ButtonSquare from '@/components/common/ButtonSquare.vue';
import CommentBox from '@/components/challengeroom/CommentBox.vue';
import axios from '@/util/http-common.js';
import http from "@/util/http-common.js";
import { mapActions, mapState } from 'vuex';

// import Vue from 'vue';
// import CKEditor from '@ckeditor/ckeditor5-vue2';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

// Vue.use(CKEditor);

// import router from '../router/index.js'

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// Vue.use(VueAlertify);

export default {
    name: 'PostDetailAfter',
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
    data: function() {
        return {
            뒤로: '돌아가기',
            task_info: {
                likemembers: ['string'],
                likes: 0,
                taskContent: 'string',
                taskDesc: 'string',
                taskFile: 'string',
                taskImage: 'string',
                taskIndex: 0,
                taskNo: 0,
                userEmail: 'string',
                userName: 'string',
                userTerm: 0,
            },
            like: {
                taskNo: 0,
                userEmail: 'string',
            },
            unlike: {
                taskNo: 0,
                userEmail: 'string',
            },
            task_No: 0,
            heart: false,
            chall_no: 0,
            // CKEditor : '',
            // filename: '',
            // imageSrc: '',
            // attachFile: false,
        };
    },
    computed: {
        ...mapState(['userEmail']),
    },
    methods: {
        getTaskInfo: function() {
            axios({
                methods: 'get',
                url: `/challenge/task/${this.$route.query.taskNo}`,
            })
                .then((res) => {
                    // alert("과제 상세 정보가 들어왔습니다.");
                    console.log('getTaskInfo res로그');

                    console.log(res.data);
                    this.task_info = res.data;
                    console.log(this.task_info);
                    if(this.task_info.taskNo==0){
                        alert("없는 과제 입니다.");
                    this.$router.push({path: '/homefeed'});
                    }
                })
                .catch((err) => {
                    console.log('getTaskInfo err로그');
                    console.log(err);
                });
        },
        ...mapActions(['presslike', 'pressunlike']),
        checkUser: function() {
            if (this.userEmail === this.task_info.userEmail) {
                // alert('true');
                return true;
            } else {
                // alert('false');
                return false;
            }
        },
        // 좋아요
        sendLike: function(like) {
            document.querySelector('.like-img').style.color = 'red';
            this.like.userEmail = this.userEmail;
            this.like.taskNo = this.task_info.taskNo;
            this.presslike(like);
            this.task_info.likes++;
            //alert('좋아요');
            this.heart = true;
            console.log(like);
        },
        // 좋아요 취소
        sendUnLike: function(like) {
            document.querySelector('.like-img').style.color = 'black';
            this.like.userEmail = this.userEmail;
            this.like.taskNo = this.task_info.taskNo;
            this.pressunlike(like);
            this.task_info.likes--;
            // alert('좋아요 취소');
            this.heart = false;
            console.log(like);
        },
        // 뒤로가기
        goBack: function() {
            // alert('goBack function')
            this.$router.go(-1);
        },
        getLikeInfo: function() {
            axios({
                method: 'get',
                url: `challenge/task/like/${this.userEmail}/${this.$route.query.taskNo}`,
            })
                .then((res) => {
                    console.log('getLikeInfo res로그');
                    console.log(res.data.userLikeFlag);
                    this.heart=res.data.userLikeFlag;
                })
                .catch((err) => {
                    console.log('getLikeInfo err로그');
                    console.log(err);
                });
        },
        erazeTask: function() {
            if (confirm('정말 삭제하시겠습니까??') == true) {
                //확인
                // document.form.submit();
                axios({
                    method: 'delete',
                    url: `/challenge/task/${this.task_No}`,
                })
                    .then((res) => {
                        console.log('erazeTask res로그 삭제성공');
                        console.log(this.$route.query.taskNo+"번호 삭제");
                        this.$router.go(-1);
//                        this.$router.push({ path: '/challengeRoom', query: { cn: this.chall_no } });
                    })
                    .catch((err) => {
                        console.log('erazeTask err로그');                        
                        console.log(this.chall_no+"삭제시도");
                        console.log(this.$route.query.taskNo+"번호 삭제 시도");
                        console.log(err);
                    });
            } else {
                //취소
                return;
            }
        },

        // 이미지 가져오기
        getProfileImage: function(e) {
            console.log('프로필 사진 가져오기')
            http.get(`/viewimage/${this.userEmail}`).then((response) => {
            console.log("과제 창 이미지성공");
            var imgsrc =
            "data:image/png;base64," +
            btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
            document.getElementById("profileimage").src = imgsrc;
            // this.comment.userImage = imgsrc;
            });
        },
        // sendPost(){
        //     let message = this.CKEditor.getData();
        //     alert(message);
        // },

        // // 여기서부터 사진 업로드
        // onDrop (event) {
        // this.inputImageFile(event.dataTransfer.files)
        // },
        // onClickFile(event) {
        // this.$refs.fileInput.click(event)
        // },
        // onFileChange(event) {
        // this.inputImageFile(event.target.files)
        // },
        // inputImageFile (files) {
        // if (files.length) {
        //     let file = files[0]
        //     if (!/^image\//.test(file.type)) {
        //     alert('이미지 파일만 등록이 가능합니다')
        //     return false
        //     }
        //     this.filename = file.name
        //     this.preview(file)
        // }
        // },
        // onClickUpload () {
        // this.preview (this.filename)
        // },
        // preview (file) {
        // if (typeof file === 'string') {
        //     this.imageSrc = file
        // } else {
        //     let vm = this
        //     let reader = new FileReader()
        //     reader.onload = () => {
        //     vm.imageSrc = reader.result
        //     }
        //     reader.readAsDataURL(file)
        // }
        // }
    },
    created: function() {
        // alert(this.forwardTaskNo);
        this.task_No=this.$route.query.taskNo;
        this.chall_no = this.$route.query.cn;
        this.getProfileImage();
        this.getTaskInfo();
        
    },
    updated: function() {
        this.getLikeInfo();
    },    
    // mounted(){
    //     ClassicEditor
    //     .create( document.querySelector('#divCKEditor'))
    //     .then(editor => {
    //         this.CKEditor = editor;
    //     })
    //     .catch(err => {
    //         console.log(err)
    //     })
    // }
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
    border: 4px outset #99b7ff;
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
</style>
