<template>
    <div class="flex_container">
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar">
        </div>

        <div class="mainbody">
            <div class="d-flex justify-content-center">
                <!-- 왼쪽 - 과제 설명란 -->
                <div class="left flex-item">
                    <div class="col Tleft flex-item">
                        <h1>아이유의 과제2</h1>
                        <!-- 작성자가 보이는 부분 -->
                    </div>
                    <div class="col Dleft flex-item">

                        <div id="uploadimg">
                            <!-- 업로드 된 사진이 보이는 부분 -->
                            <div v-show="imageSrc" class="upload-image">
                                <img :src="imageSrc">
                            </div>
                        </div>
                        <div id="post">
                            <!-- 작성된 내용이 보이는 부분 CKEditor부분 -->
                        </div>
                        
                        <div>
                            <!-- 파일 다운로드 하는 부분 -->
                        </div>
                    </div>

                    <div class="like-box">
                        <img class="like" src="../assets/grayheart.png" alt="likeU">
                    </div>
                    <div class="like-num">
                        <h6><strong>307명</strong>이 좋아합니다</h6>
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
                    <div id='infowriter'>
                        <ProfileImage class="comment-img-box" />
                        <h4 id="writername">5기 아이유</h4>
                        <hr id="line">
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
            
            <button type="button" class="btn btn-danger Pdelete_btn" @click="changeToDelete">삭제</button>

            <!-- <div class="Pjoin_btn"><ButtonSquare :text="생성" @click="sendPost"/></div>
            <div class="Pcancel_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="취소"/></router-link></div> -->
            <div class="Pback_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="뒤로"/></router-link></div>
            
        </div>
    </div>
</template>

<script>
import "@/components/css/postdetailafter.css"
import ButtonSquare from '@/components/common/ButtonSquare.vue'
import ProfileImage from "@/components/common/ProfileImage.vue"
import CommentBox from "@/components/challengeroom/CommentBox.vue"

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
        ButtonSquare,    // 둥근 버튼 가져오기
        ProfileImage,
        CommentBox,
    },
    data: function(){
        return{
            // 버튼에 들어갈 문구들
            // 생성: '생성',
            // 취소: '취소',
            뒤로: '돌아가기',
            // CKEditor : '',
            // filename: '',
            // imageSrc: '',
            // attachFile: false,
            // isShowing : true,
            // grayheart : require('../assets/grayheart.png'),
            // redheart : require('../assets/redheart.png')
        }
    },
    methods:{
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
}
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

.thumbnail-wrapper{
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
</style>