<template>
    <div class="flex_container" id="total">
        <!-- <Title :text="chall_info.challengeName" /> -->
        <div class="stars-box">
            <img class="stars" src="../assets/manystar.png" alt="manystar" />
        </div>
        <div class="challengetitle">
            <div class="Ctitle"><Title v-bind:text="chall_info.challengeName" /></div>
        </div>

        <div class="d-flex row wider justify-content-center" id="body">
            <div class="joinbox">
                <!-- 가입버튼 누르기 전에는 가입하기 버튼과 가입 마감까지 남은 시간이 보여진다 -->
                <li class="changebtn">
                    <div class="Cjoin_btn" @click="hidebtn(temp)"><ButtonRound :text="msg" /></div>
                    <!-- <div class="Cjoindone_btn" v-else><ButtonRound :text="가입완료" /></div> -->
                    <div class="alarm">
                        <h5 id="rest"></h5>
                    </div>
                </li>
                <!-- 가입하기 버튼을 누르면 가입완료 버튼으로 바뀌고 시간이 진행중으로 바뀐다 -->
                <!-- <li class="changebtn" v-else>
                    <div class="Cjoindone_btn"><ButtonRound :text="가입완료" /></div>
                    <div class="alarm">
                        <h5 id="rest">{{ restTime }}</h5>
                    </div>
                </li> -->
                <!-- 가입완료 후 챌린지 마감시간이 지나면 진행중이 종료 바뀜 -->
                <!-- <li class="changebtn">
                    <div class="Cjoindone_btn"><ButtonRound :text="가입완료"/></div>
                    <div class="alarm">
                        <h5 style="color: #EE4748">종료</h5>
                    </div>
                </li> -->
                <!-- <div class="alarm">
                    <h4> 가입 마감까지 20 : 56 </h4>
                </div> -->
            </div>

            <div class="col col-4 flex-item">
                <div class="outline">
                    <div class="outline2">
                        <table class="table">
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
                                <tr v-for="(person, index) in chall_info.challengeGroup" :key="person">
                                    <th scope="row" style="background-color: #b7beda">{{ person[1] }}</th>
                                    <td v-for="taskIdx in chall_info.challengeTaskCnt" :key="taskIdx" @click="taskblock(person[0], taskIdx - 1)">
                                        <!-- 제출 할 수 있는 아이들 -->
                                        <router-link to="/postDetail" v-if="task_info[index].taskNo[taskIdx - 1] == -1">
                                            <div class="before"></div>
                                        </router-link>
                                        <!-- -2, 마감기간 지난 미제출 -->
                                        <div v-else-if="task_info[index].taskNo[taskIdx - 1] == -2" class="fail"></div>
                                        <!-- 숫자, 제출 한 것 -->
                                        <router-link
                                            v-else
                                            :to="{ name: 'PostDetailAfter', params: { forwardTaskNo: task_info[index].taskNo[taskIdx - 1] } }"
                                        >
                                            <div class="after">taskNO = {{ task_info[index].taskNo[taskIdx - 1] }}</div>
                                        </router-link>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col col-5 flex-item">
                <div class="ChallengeDetail">
                    <div class="Cdetail">
                        <p>{{ chall_info.challengeDesc }}</p>
                        <strong
                            >참여멤버 :
                            <span v-for="(name, index) in chall_info.challengeGroup" :key="name" @click="nameprofile(index)"> {{ name[1] }} </span>
                        </strong>
                        <br /><br />
                        <div>
                            <strong> 난이도: </strong
                            ><span v-for="level in chall_info.challengeLevel" :key="level"
                                ><img src="../assets/star.png" alt="levelstar" id="levelstar"
                            /></span>
                        </div>
                    </div>
                </div>
                <div class="ChallengeTicket">
                    <img class="ticketbody" src="../assets/ticketbody.png" alt="ticketbody" />
                </div>
            </div>
        </div>
        <!-- 여기가 있으면 통신이 안된다 -->
        <!-- <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
    <detail-modal v-on:call-parent-change-to-delete="changeToDelete"></detail-modal> -->
    </div>
</template>
<script>
import Title from '@/components/common/Title.vue';
import ButtonRound from '@/components/common/ButtonRound.vue';
// import Difficulty from '@/components/common/Difficulty'
// import PostDetailModal from '@/components/PostDetailModal'
import '@/components/css/ChallengeRoom.css';
import axios from '@/util/http-common.js';
import { mapActions } from 'vuex';

// import InsertModal from '@/components/modals/InsertModal.vue'
// import DetailModal from '@/components/modals/DetailModal.vue';
// import router from '../router/index.js'

// import { Modal } from 'bootstrap';

// import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// Vue.use(VueAlertify);

export default {
    name: 'ChallengeRoom',
    el: 'goprofile',
    components: {
        Title, // 타이틀 가져오기
        ButtonRound, // 둥근 버튼 가져오기
        // Difficulty      // 난이도 가져오기
        //PostDetailModal,
        //InsertModal, // 작성자가 처음 과제를 제출하는 모달
        //DetailModal, // 작성 후 보여지는 모달
    },
    data: function() {
        return {
            // 버튼에 들어갈 문구들
            msg: '가입하기',
            // 가입완료: '가입완료',
            submit: true,
            fail: false,
            // 챌린지: '히오니의 알고 챌린지',
            challengeno: 4,

            //이동할 테스크 고유 넘버pk
            forwardTaskNo: -1,

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

                // challengeNo: 1,
                // challengeName: 'SSA.ZIP',
                // challengeCategory: 'Algorithm',
                // challengeLevel: 3,
                // challengeCapacity: 6,
                // challengeStartdate: '2021-08-19',
                // challengeEnddate: '2021-08-30',
                // challengeDesc: '히오니의 알고리즘 챌린지',
                // challengeTaskCnt: 7,
                // challengeTaskdeadlines: ['2021-08-01', '2021-08-02', '2021-08-03'],
                // challengeGroup: [
                //     ['123', '주인공'],
                //     ['456', '제발좀'],
                //     ['789', '김이름'],
                // ],
            },
            // 과제 블럭 하나씩 이전 버전
            // task_info:[{
            //   "taskIndex": 0, // 몇번째 과제인지
            //   "taskNo": 0, // 고유값. 과제 페이지로 넘어갈 때 사용
            //   "userEmail": "string",
            //   "userName": "string"
            // }]

            // task_info:[
            //   {
            //     "userEmail": "string",
            //     "userName": "string"
            //      "taskNo": [
            //       0
            //     ],
            //   }
            // ]
            //-1 기간 안지난 미제출(흰)
            //-2 기간 지난 미제출(빨강)
            task_info: [
                {
                    userName: '이장섭',
                    userEmail: 'jang@naver.com',
                    taskNo: [-2, 1, 2, -1, 3, -1],
                },
                {
                    userName: '차은채',
                    userEmail: 'cha@naver.com',
                    taskNo: [-2, 4, -1, -1, -1, -1],
                },
                {
                    userName: '아이유',
                    userEmail: 'IU-love@naver.com',
                    taskNo: [-2, 5, -1, 6, -1, -1],
                },
                {
                    userName: '아이유',
                    userEmail: 'IU-love@naver.com',
                    taskNo: [-1, 7, -1, -1, -1, -1],
                },
            ],
            temp: {
                challengeNo: 4,
                userEmail: 'aaa@naver.com',
            },
            // 모달
            // insertModal : null,
            // detailModal : null
        };
    },
    methods: {
        // // insert
        // showInsertModal(){
        // this.insertModal.show();
        // },

        // closeAfterInsert(){
        // this.insertModal.hide();
        // },
        // BJ 누르면 개인 정보로 넘어가는 통신
        getTaskInfo: function() {
            axios({
                methods: 'get',
                url: `/challenge/tasklist/${this.challengeno}`,
            })
                .then((res) => {
                    this.task_info = res.data;
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        //챌린지 페이지에서 챌린지 정보 불러오는 통신
        getChallInfo: function() {
            axios({
                methods: 'get',
                url: `/challenge/info/${this.challengeno}`,
            })
                .then((res) => {
                    this.chall_info = res.data;
                    this.chall_info.challengeStartdate += ' 23:59:59';
                    this.countDownTimer('rest', this.chall_info.challengeStartdate);
                })
                .catch((err) => {
                    alert('false');
                    console.log(err);
                });
        },

        countDownTimer: function(id) {
            var date = this.chall_info.challengeStartdate;
            //const countDownTimer = function (id) {
            var _vDate = new Date(date); // 전달 받은 일자
            var _second = 1000;
            var _minute = _second * 60;
            var _hour = _minute * 60;
            var _day = _hour * 24;
            var timer;
            function showRemaining() {
                var now = new Date();
                var distDt = _vDate - now - 1;
                if (distDt < 0) {
                    clearInterval(timer);
                    document.getElementById(id).textContent = '모집이 종료 되었습니다!';
                    return;
                }
                var days = Math.floor(distDt / _day);
                var hours = Math.floor((distDt % _day) / _hour);
                var minutes = Math.floor((distDt % _hour) / _minute);
                var seconds = Math.floor((distDt % _minute) / _second);
                //document.getElementById(id).textContent = date.toLocaleString() + "까지 : ";
                document.getElementById(id).textContent = '마감까지 ' + days + '일 ';
                document.getElementById(id).textContent += hours + '시간 ';
                document.getElementById(id).textContent += minutes + '분 ';
                document.getElementById(id).textContent += seconds + '초';
            }
            timer = setInterval(showRemaining, 1000);
        },
        // 가입하기 버튼 눌렀을 때
        hidebtn(temp) {
            // this.beforejoin=false;
            this.msg = '가입완료';
            console.log(this.msg);
            // document.getElementById('Cjoin_btn').style.backgroundColor = '#f9d479';
            document.querySelector('.Cjoin_btn .btn-light').style.backgroundColor = '#f9d479';
            // 여기에다가 로직을 작성해야한다
            alert('버튼 작동 확인');
            this.joinchall(temp); // email이랑 챌린지 번호 전송
        },

        nameprofile(num) {
            var email = this.chall_info.challengeGroup[num][0];
            alert(email);
        },
        // bj 블럭의 과제 블럭을 눌렀을 때
        //     taskblock(email, index){
        //       // var email = this.chall_info.challengeGroup[];
        //       // var index = this.
        //       let taskno = -1;
        //       for( let n = 0; n < this.chall_info.challengeTaskCnt; n++){
        //         if(this.task_info[n].userEmail == email & this.task_info[n].taskIndex == index){
        //           taskno = this.task_info[n].taskNo;
        //           this.submit = !this.submit;
        //         }

        //       if(taskno != -1){ // 과제 제출 했을 경우
        //         alert(taskno)

        //         this.submit = true;
        //         console.log(!this.submit)
        //         // submit == true;
        //       }

        //       // alert(email + ' ' + index + '' + taskno);
        //     }
        // },
        taskblock(email, idx) {
            // var email = this.chall_info.challengeGroup[];
            // var index = this.
            // let taskno = 0;
            // for (let n = 0; n < this.chall_info.challengeTaskCnt; n++) {
            //     // taskno 고유값 뽑아내기
            //     // 과제 제출 했을 경우
            //     if ((this.task_info[n].userEmail == email) & (this.task_info[n].taskNo[n] != -1) & (this.task_info[n].taskNo[n] != -2)) {
            //         taskno = this.task_info[n].taskNo[n];
            //         alert(taskno);
            //         // this.submit = !this.submit;
            //     }
            //     // 기간 안지난 미제출 : 노랑
            //     if (this.task_info[n].taskNo[n] == -1) {
            //         alert(this.task_info[n].taskNo[n]);
            //         // this.submit = true;
            //         // console.log(!this.submit)
            //         // submit == true;
            //     }
            //     // 기간 지난 미제출 : 빨강
            //     if (this.task_info[n].taskNo[n] == -2) {
            //         alert(this.task_info[n].taskNo[n]);
            //     }
            //     // alert(email + ' ' + index + '' + taskno);
            // }
        },
        ...mapActions([
            // import 해주는 느낌
            'joinchall',
        ]),
    },
    created: function() {
        this.getChallInfo(); //생성할 때 바로 불러줘
        // this.getTaskInfo();
        // this.countDownTimer('rest', this.chall_info.challengeStartdate);
    },
};
</script>
<style scoped>
/* 가입하기 버튼 */
.Cjoin_btn .btn-light {
    color: #1f4256;
    background-color: #99b7ff;
    /* border-color: #99b7ff; */
    font-size: 25px;
    font-weight: bold;
    width: 171px;
    height: 54px;
    margin-right: 69%;
    margin-bottom: 5px;
    border-style: none;
}

/* 가입완료 버튼 */
.Cjoindone_btn .btn-light {
    color: #1f4256;
    background-color: #f9d479;
    border-color: #f9d479;
    font-size: 25px;
    font-weight: bold;
    width: 171px;
    height: 54px;
    margin-right: 69%;
    margin-bottom: 5px;
}

.alarm {
    color: #e92828;
    margin-left: 10px;
    margin-top: 20px;
}

/* .joinbox{
    justify-content: center;
} */

.changebtn {
    display: flex;
    justify-content: left;
    padding-top: 20px;
    border-style: none;
    margin-left: 210px;
}

/* .check{
    background-color: #f9d479;
    width: 40px;
    height: 40px;
    border-radius: 100%;
} */

.table > :not(caption) > * > * {
    padding: 0 0;
}

/* 과제 제출 전 칸 */
.before {
    background-color: #f9d479;
    width: 100%;
    height: 100%;
}

/* 과제 제출 후 파란색으로 변함 */
.after {
    background-color: #1f4256;
    width: 100%;
    height: 100%;
}

/* 과제 제출 안하면 빨간색으로 변함 */
.fail {
    background-color: #ee4748;
    width: 100%;
    height: 100%;
}

th {
    width: 100px;
}

#levelstar {
    width: 45px;
    height: 45px;
}
</style>
