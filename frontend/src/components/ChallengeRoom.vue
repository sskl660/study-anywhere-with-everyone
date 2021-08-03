<template>
    <div class="flex_container" id="total">
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar">
        </div>
        <div class="challengetitle">
            <div class="Ctitle"><Title :text="챌린지"/></div>
        </div>

        <div class="d-flex row wider justify-content-center" id="body">
            <li class="changebtn" v-if="!beforejoin">
                <div class="Cjoin_btn"><ButtonRound :text="가입하기"/></div>
            </li>
            <li class="changebtn" v-else>
                <div class="Cjoindone_btn"><ButtonRound :text="가입완료"/></div>
            </li>
            <div class="col col-4 flex-item">
                <div class="outline">
                    <div class="outline2">
                        <table class="table ">
                            <thead>
                                <tr>
                                <th scope="col"></th>
                                <th scope="col">과제1</th>
                                <th scope="col">과제2</th>
                                <th scope="col">과제3</th>
                                <th scope="col">과제4</th>
                                <th scope="col">과제5</th>
                                <th scope="col">과제6</th>
                                <th scope="col">과제7</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">권희은</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김태현</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김준형</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">이장섭</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">유희원</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">차은채</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김싸피</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김싸피</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김싸피</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tr>
                                <tr>
                                <th scope="row" style="background-color: #b7beda">김싸피</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><router-link to="/PostDetail">과제</router-link></td>
                                </tr>
                            </tbody>
                        </table> 
                    </div>
            </div>
            </div>
            <div class="col col-5 flex-item">
                <div class="ChallengeDetail">
                    <div class="Cdetail">
                        <h4>🔥 삼성 코테 기출 알고 4시간 박살 🔥</h4>
                        <br>
                        저희 스터디는 1일 1알고리즘으로
                        네카라쿠배를 갈 사람들 입니다!<br>
                        일주일동안 백준 총 7문제를 풀 것입니다.<br>

                        과제 1 : BJ 3049<br>
                        과제 2 : BJ 8490<br>
                        과제 3 : BJ 908<br>
                        과제 4 : BJ 193<br>
                        과제 5 : BJ 9830<br>
                        과제 6 : BJ 1394<br>
                        과제 7 : BJ 9033<br>

                        <br>
                        <strong>참여멤버 : @권희은 @김준형 @김태현 @이장섭 @유희원 @차은채</strong>
                    </div>
                </div>
                <div class="ChallengeTicket">
                    <img class="ticketbody" src="../assets/ticketbody.png" alt="ticketbody">
                </div>
            </div>
        </div>
    <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
    <detail-modal v-on:call-parent-change-to-delete="changeToDelete"></detail-modal>
    </div>
</template>
<script>
import Title from '@/components/common/Title.vue'
import ButtonRound from '@/components/common/ButtonRound.vue'
// import Difficulty from '@/components/common/Difficulty'
// import PostDetailModal from '@/components/PostDetailModal'
import "@/components/css/ChallengeRoom.css"

// import InsertModal from '@/components/modals/InsertModal.vue'
// import DetailModal from '@/components/modals/DetailModal.vue';
// import router from '../router/index.js'

// import { Modal } from 'bootstrap';

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify'; 
// Vue.use(VueAlertify);


export default {
    name: 'ChallengeRoom',
    components: {
        Title,          // 타이틀 가져오기
        ButtonRound,    // 둥근 버튼 가져오기
        // Difficulty      // 난이도 가져오기
        //PostDetailModal,
        //InsertModal, // 작성자가 처음 과제를 제출하는 모달
        //DetailModal, // 작성 후 보여지는 모달
    },
    data: function(){
        return{
            // 버튼에 들어갈 문구들
            가입하기: '가입하기',
            가입완료: '가입완료',
            챌린지: '히오니의 알고 챌린지',

            // 모달
            // insertModal : null,
            // detailModal : null
        }
    },
    // methods : {
    //     // insert
    //     showInsertModal(){
    //     this.insertModal.show();
    //     },

    //     closeAfterInsert(){
    //     this.insertModal.hide();
    //     },

    // }
}
</script>
<style scoped>
    
</style>