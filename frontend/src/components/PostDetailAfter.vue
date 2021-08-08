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
                        <!-- <img @click="sendLike(like)" class="like-img" src="../assets/redheart.png" alt="likeU"> -->
                    </div>

                    <!-- <div class="like-box">
                        <img @click="presslike()" class="like" src="../assets/redheart.png" alt="likeU">
                    </div> -->

                    <div class="like-num">
                        <h6>
                            <strong>{{ task_info.likes + ' 명' }}</strong
                            >이 좋아합니다
                        </h6>
                    </div>

                    <!-- <div class="like-box">
                        <img v-if="isShowing" :src="grayheart" alt="likeU" class="like" @click="isShowing = !isShowing">
                    </div> -->
                    <!-- <div class="like-box">
                        <img class="like" src="../assets/redheart.png" alt="likeU">
                    </div> -->
                </div>

                <!-- 오른쪽 - 댓글창 -->
                <div class="right flex-item">
                    <!-- 댓글창 맨 위 개인 프로필 -->
                    <div id="infowriter">
                        <ProfileImage class="comment-img-box" />
                        <h4 id="writername">{{ task_info.userTerm }} 기 {{ task_info.userName }}</h4>
                        <hr id="line" />
                    </div>
                    <div>
                        <!-- 댓글 -->
                        <div>
                            <CommentBox style="d-flex justify-content-center" />
                        </div>
                        <!-- <div class="writecomment">  
                            <input type="text" id="send_comment" placeholder="  댓글 달기" name="send_comment" value="" onKeypress="javascript:if(event.keyCode==13) {search_onclick_submit}"/>
                        </div> -->
                    </div>
                </div>
            </div>

            <div style="margin-left:876px; margin-top:7px;" class="d-flex">
                <button v-if="checkUser()" class="btn btn-danger d-flex align-items-center">
                    <div>삭제</div></button
                >&nbsp;
                <button class="btn btn-warning d-flex align-items-center">
                    <router-link to="/ChallengeRoom" style="text-decoration: none; color: #ffffff">
                        <div>뒤로가기</div>
                    </router-link>
                </button>
            </div>

            <!-- <div class="d-flex" style="margin-left: 700px"> -->
            <!-- <button v-if="this.checkUser()" type="button" class="btn btn-danger Pdelete_btn">삭제</button> -->
            <!-- <div class="Pjoin_btn"><ButtonSquare :text="생성" @click="sendPost"/></div>
                <div class="Pcancel_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="취소"/></router-link></div> -->
            <!-- <button type="button" ><router-link to="/ChallengeRoom">뒤로가기</router-link></button> -->
            <!-- <div class="Pback_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="뒤로"/></router-link></div> -->
            <!-- </div> -->
        </div>
    </div>
</template>

<script>
import '@/components/css/postdetailafter.css';
import ButtonSquare from '@/components/common/ButtonSquare.vue';
import ProfileImage from '@/components/common/ProfileImage.vue';
import CommentBox from '@/components/challengeroom/CommentBox.vue';
import axios from '@/util/http-common.js';
import { mapActions, mapState } from 'vuex';

// import Vue from 'vue';
// import CKEditor from '@ckeditor/ckeditor5-vue2';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

// Vue.use(CKEditor);

// import InsertModal from '@/components/modals/InsertModal.vue'
// import DetailModal from '@/components/modals/DetailModal.vue';
// import router from '../router/index.js'

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// Vue.use(VueAlertify);

export default {
    name: 'PostDetailAfter',
    components: {
        // Title,          // 타이틀 가져오기
        ButtonSquare, // 둥근 버튼 가져오기
        ProfileImage,
        CommentBox,
    },
    // props: { //여기를 this. router. query. url에 있는 key값 바꿔줘야한다
    //         forwardTaskNo: {
    //             type: String,
    //             default : ''
    //         },
    //     },
    data: function() {
        return {
            // 버튼에 들어갈 문구들
            // 생성: '생성',
            // 취소: '취소',
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
            ApiTaskNo: '',
            heart: '',
            // CKEditor : '',
            // filename: '',
            // imageSrc: '',
            // attachFile: false,
            // isShowing : true,
            // grayheart : require('../assets/grayheart.png'),
            // redheart : require('../assets/redheart.png')
        };
    },
    computed: {
        ...mapState(['userEmail']),
    },
    methods: {
        getTaskInfo: function() {
            axios({
                methods: 'get',
                url: `/challenge/task/${this.ApiTaskNo}`,
            })
                .then((res) => {
                    // alert("과제 상세 정보가 들어왔습니다.");
                    console.log('getTaskInfo res로그');

                    console.log(res.data);
                    this.task_info = res.data;
                    console.log(this.task_info);
                })
                .catch((err) => {
                    console.log('getTaskInfo err로그');
                    console.log(err);
                });
        },
        ...mapActions(['presslike', 'pressunlike']),
        taskNumbering: function(urlNo) {
            this.ApiTaskNo = urlNo;
            // 여기에 create에 있는 함수를 바로 넣어줬다.
        },
        checkUser: function() {
            // alert('삭제버튼 안보이게 할꺼야')
            console.log(this.task_info.userEmail);
            console.log(this.userEmail);
            if (this.userEmail === this.task_info.userEmail) {
                // alert('true');
                return true;
            } else {
                // alert('false');
                return false;
            }
        },
        // presslike(){
        //     document.querySelector('.like').src ="/img/redheart.20ffa944.png";
        // },
        sendLike: function(like) {
            document.querySelector('.like-img').style.color = 'red';
            this.like.userEmail = this.userEmail;
            this.like.taskNo = this.task_info.taskNo;
            this.presslike(like);
            this.task_info.likes++;
            alert('좋아요');
            this.heart = true;
            console.log(like);

            // if(document.querySelector('.like-img').style.color =="red"){
            //     document.querySelector('.like-img').style.color =="black";
            //     this.pressunlike(like);
            //     this.task_info.likes --;
            //     alert('좋아요 취소');
            // }
            // else{
            //     document.querySelector('.like-img').style.color =="red";
            //     this.pressunlike(like);
            //     this.task_info.likes ++;
            //     alert('좋아요')
            // }
        },
        sendUnLike: function(like) {
            document.querySelector('.like-img').style.color = 'black';
            this.like.userEmail = this.userEmail;
            this.like.taskNo = this.task_info.taskNo;
            this.pressunlike(like);
            this.task_info.likes--;
            alert('좋아요 취소');
            this.heart = false;
            console.log(like);
        },
        getLikeInfo: function() {
            console.log(' res로그');
            axios({
                method: 'get',
                url: `challenge/task/like/${this.userEmail}/${this.task_info.taskNo}`,
            })
                .then((res) => {
                    console.log('getLikeInfo res로그');
                    console.log(res.data.userLikeFlag);
                    this.heart = res.data.userLikeFlag;
                })
                .catch((err) => {
                    console.log('getLikeInfo err로그');
                    console.log(err);
                });
        },

        // A함수를 만들고
        // 클릭 시 A함수 실행
        // A함수 안에는 이미지 변하는 함수, 통신하는 함수(mapActions함수)
        // 페이지 reload 요청 한번 다시 하기. 리프레쉬 용
        // getTaskInfo함수를 다시 불러라 -> 데이터 바꿔주기 좋아요 +1

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
        this.taskNumbering(this.$route.query.taskNo);
        this.getTaskInfo();
    },
    updated: function(){
        this.getLikeInfo();

    },    // mounted(){
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
</style>
