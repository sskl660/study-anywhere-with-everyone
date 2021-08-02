<template>
    <div class="flex_container">
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar">
        </div>
        <div class="challengetitle">
            <div class="Ctitle"><Title :text="챌린지"/></div>
        </div>

        <div class="d-flex row wider justify-content-center" id="body">
            <div class="Cjoin_btn"><ButtonRound :text="가입하기"/></div>
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
                                <td><PostDetailModal text="모달"/></td>
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
                                <td></td>
                                </tr>
                            </tbody>
                        </table> 
                    </div>
            </div>
            </div>
            <div class="col col-5 flex-item">
                <div class="ChallengeDetail">
                    
                </div>
                <div class="ChallengeTicket">
                    <img class="ticketbody" src="../assets/ticketbody.png" alt="ticketbody">
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Title from '@/components/common/Title.vue'
import ButtonRound from '@/components/common/ButtonRound.vue'
// import Difficulty from '@/components/common/Difficulty'
import PostDetailModal from '@/components/PostDetailModal'
import "@/components/css/ChallengeRoom.css";

export default {
    name: 'ChallengeRoom',
    components: {
        Title,          // 타이틀 가져오기
        ButtonRound,    // 둥근 버튼 가져오기
        // Difficulty      // 난이도 가져오기
        PostDetailModal,
    },
    data: function(){
        return{
            // 버튼에 들어갈 문구들
            모달: '모달',
            가입하기: '가입하기',
            챌린지: '히오니의 알고 챌린지',
        }
    }
}
</script>
<style scoped>
    
</style>