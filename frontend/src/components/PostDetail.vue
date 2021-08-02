<template>
    <div class="flex_container">
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar">
        </div>

        <div class="mainbody">
            <div class="d-flex justify-content-center">
                <div class="left flex-item">
                    <div class="col Tleft flex-item">
                        
                    </div>
                    <div class="col Dleft flex-item">
                        
                    </div>
                </div>

                <div class="right flex-item">

                </div>
            </div>
            
            
            <div class="Pjoin_btn"><ButtonSquare :text="생성"/></div>
            <div class="Pcancel_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="취소"/></router-link></div>
            <!-- <div class="Pback_btn"><router-link to="/ChallengeRoom"><ButtonSquare :text="뒤로"/></router-link></div> -->
            
        </div>
    </div>
</template>

<script>
import "@/components/css/PostDetail.css"
import ButtonSquare from '@/components/common/ButtonSquare.vue'

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
        }
    },
}
</script>
<style scoped>
    
</style>