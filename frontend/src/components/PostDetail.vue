<template>
    <div class="flex_container">
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar">
        </div>

        <div class="mainbody">
            <div class="d-flex justify-content-center">
                <div class="left flex-item">
                    <div class="col Tleft flex-item">
                        <h1>OOO의 과제2</h1>
                    </div>
                    <div class="col Dleft flex-item">

                        <div id="uploadimg">
                            <div class="input-group">
                                <input
                                type="text"
                                class="form-control"
                                placeholder="Input Image URL or  Drag & Drop or Select"
                                v-model="filename"
                                @dragover.prevent
                                @dragenter.prevent
                                @drop.prevent="onDrop">
                                <div class="input-group-append">
                                <span class="input-group-text" @click="onClickFile"><i class="fa fa-paperclip">
                                    </i></span>
                                    <button
                                    class="btn btn-outline-info"
                                    @click="onClickUpload">Upload</button>
                                </div>
                                <input type=file class="file-input" accept="image/*" ref="fileInput" @change="onFileChange">
                            </div>
                            <div v-show="imageSrc" class="upload-image">
                                <img :src="imageSrc">
                            </div>
                        </div>
                        <div id="post">
                            <div id="divCKEditor" class="writepost"></div>
                        </div>

                    </div>
                </div>

                <div class="right flex-item">

                </div>
            </div>
            
            
            <div class="Pjoin_btn"><ButtonSquare :text="생성" @click="sendPost"/></div>
            <div class="Pcancel_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="취소"/></router-link></div>
            <!-- <div class="Pback_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="뒤로"/></router-link></div> -->
            
        </div>
    </div>
</template>

<script>
import "@/components/css/PostDetail.css"
import ButtonSquare from '@/components/common/ButtonSquare.vue'

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
        ButtonSquare,    // 둥근 버튼 가져오기
    },
    data: function(){
        return{
            // 버튼에 들어갈 문구들
            생성: '생성',
            취소: '취소',
            뒤로: '돌아가기',
            CKEditor : '',
            filename: '',
            imageSrc: '',
        }
    },
    methods:{
        sendPost(){
            let message = this.CKEditor.getData();
            alert(message);
        },

        onDrop (event) {
        this.inputImageFile(event.dataTransfer.files)
        },
        onClickFile(event) {
        this.$refs.fileInput.click(event)
        },
        onFileChange(event) {
        this.inputImageFile(event.target.files)
        },
        inputImageFile (files) {
        if (files.length) {
            let file = files[0]
            if (!/^image\//.test(file.type)) {
            alert('이미지 파일만 등록이 가능합니다')
            return false
            }
            this.filename = file.name
            this.preview(file)
        }
        },
        onClickUpload () {
        this.preview (this.filename)
        },
        preview (file) {
        if (typeof file === 'string') {
            this.imageSrc = file
        } else {
            let vm = this
            let reader = new FileReader()
            reader.onload = () => {
            vm.imageSrc = reader.result
            }
            reader.readAsDataURL(file)
        }
        }
    },
    mounted(){
        ClassicEditor
        .create( document.querySelector('#divCKEditor'))
        .then(editor => {
            this.CKEditor = editor;
        })
        .catch(err => {
            console.log(err)
        })
    }
}
</script>

<style>
/* CKEditor 는 vue 와 별개로 rendering 되어서 scope 를 넣으면 반영되지 않는다. */
.ck-editor__editable {
    min-height: 300px !important;
}

.ck.ck-reset.ck-editor.ck-rounded-corners {
    margin-top: 200px;
    background-color: #e7eeff;
}

.thumbnail-wrapper{
    margin-top: 5px;
}

.thumbnail-wrapper img {
    width: 100px !important;
    margin-right: 5px;
    max-width: 100%;
}
</style>