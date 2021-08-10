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
                    <div v-if="!overTime() && !didJoin() && !overMember()" class="Cjoin_btn" @click="hidebtn(chall_info.challengeNo, userEmail)">
                        <ButtonRound :text="msg" />
                    </div>
                    <div class="alarm">
                        <h5 id="rest"></h5>
                    </div>
                </li>
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
                            <!-- 블렛 저널 테이블 바디 -->
                            <tbody>
                                <tr v-for="(person, index) in chall_info.challengeGroup" :key="person">
                                    <th scope="row" style="background-color: #b7beda">{{ person[1] }}</th>
                                    <td v-for="taskIdx in chall_info.challengeTaskCnt" :key="taskIdx">
                                        <!-- 내가 제출 할 수 있는 아이들 -->
                                        <router-link
                                            :to="{ path: '/postDetail', query: { idx: taskIdx - 1, cn: chall_info.challengeNo } }"
                                            v-if="task_info[index].taskNo[taskIdx - 1] == -1 && task_info[index].userEmail == userEmail"
                                        >
                                            <div class="before"></div>
                                        </router-link>
                                        <!-- 제출가능이지만 내꺼가 아닌 아이들 -->
                                        <div
                                            class="before"
                                            v-else-if="task_info[index].taskNo[taskIdx - 1] == -1 && task_info[index].userEmail != userEmail"
                                        ></div>

                                        <!-- -2, 마감기간 지난 미제출 -->
                                        <div v-else-if="task_info[index].taskNo[taskIdx - 1] == -2" class="fail"></div>

                                        <!-- 숫자, 제출 한 것 -->
                                        <router-link
                                            v-else
                                            :to="{ path: '/postDetailAfter', query: { taskNo: task_info[index].taskNo[taskIdx - 1], cn: chall_info.challengeNo } }"
                                        >
                                            <div class="after">
                                                <!-- taskNO = {{ task_info[index].taskNo[taskIdx - 1] }} -->
                                            </div>
                                        </router-link>
                                    </td>
                                </tr>
                            </tbody>
                            <!-- 블렛 저널 테이블 바디 -->
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
                            <span v-for="(name, index) in chall_info.challengeGroup" :key="name" @click="nameprofile(index)" style="cursor:pointer;">
                                <router-link
                                    style="color:#420909; font-weight:600; text-decoration: none;"
                                    :to="{ name: 'Profile', query: { user: name[0] } }"
                                >
                                    #{{ name[1] }}
                                </router-link>
                            </span>
                            [정원]{{ chall_info.challengeGroup.length }}/{{ chall_info.challengeCapacity }}
                        </strong>
                        <br /><br />
                        <div>
                            <strong> 난이도 : </strong
                            ><span v-for="level in chall_info.challengeLevel" :key="level"
                                ><img src="../assets/star.png" alt="levelstar" id="levelstar"
                            /></span>
                        </div>
                        <div>
                            <div v-for="(task, index) in chall_info.challengeTaskdeadlines" :key="task">{{ index+1 }} 번째 과제 : {{ task }} 까지</div>
                        </div>
                    </div>
                </div>
                <div class="ChallengeTicket">
                    <ChallengeTicket :challTicket="chall_ticket" :ProcessRateArr="makeArr()" />
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Title from '@/components/common/Title.vue';
import ButtonRound from '@/components/common/ButtonRound.vue';
import ChallengeTicket from '@/components/challengeroom/ChallengeTicket.vue';
import '@/components/css/ChallengeRoom.css';
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';
// import router from '../router/index.js'
// import Vue from 'vue';

export default {
    name: 'ChallengeRoom',
    el: 'goprofile',
    components: {
        Title, // 타이틀 가져오기
        ButtonRound, // 둥근 버튼 가져오기
        ChallengeTicket,
    },
    data: function() {
        return {
            // 버튼에 들어갈 문구들
            msg: '가입하기',
            // 가입완료: '가입완료',
            submit: true,
            fail: false,
            challengeno: 'string',

            //이동할 테스크 고유 넘버pk
            forwardTaskNo: -1,
            // ProcessRateArr: [], 이렇게 데이터 값을 넘겨주면 안된다. 위에서 바로 메소드 함수로 접근

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

            task_info: [
                {
                    userEmail: 'string',
                    userName: 'string',
                    taskNo: [0], //여러개가 리스트로
                },
            ],
            chall_ticket: [
                {
                    achieveRate: 0,
                    inProgress: true,
                },
            ],
            overStartDate: false, //가입 오버 타입 여부
            over: null,
            myTask: false,
        };
    },

    methods: {
        makeArr: function() {
            var ProcessRateArr = [false, false, false, false, false, false, false];
            // console.log(ProcessRateArr)
            for (let i = 0; i < this.chall_ticket.length; i++) {
                ProcessRateArr[i] = true;
            }
            return ProcessRateArr;
        },
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
                    var startD = new Date(this.chall_info.challengeStartdate);
                    var pre = new Date();
                    //var present = new Date().getFullYear()+"-0"+(new Date().getMonth()+1)+ "-"+new Date().getDate();
                    //alert(startD.getFullYear()-pre.getFullYear());
                    //alert(startD.getFullYear()-pre.getFullYear()+startD.getMonth()-pre.getMonth()+startD.getDate()-pre.getDate());
                    if (startD.getFullYear() - pre.getFullYear() + startD.getMonth() - pre.getMonth() + startD.getDate() - pre.getDate() < 0)
                        this.overTime(true);
                    //alert(pre.getFullYear());
                    //alert(new this.chall_info.challengeStartdate);
                    //alert(new Date(present)-new Date(this.chall_info.challengeStartdate));

                    this.chall_info.challengeStartdate += ' 00:00:01';
                    this.countDownTimer('rest');
                    // if(new Date(this.chall_info.challengeStartdate+' 23:59:59')>new Date()){
                    //     this.overStartdate=true;
                    // }
                })
                .catch((err) => {
                    console.log('정보부르기 실패');
                    console.log(err);
                });
        },

        // 챌린지 티켓 정보 불러오는 통신
        getChallTicket: function() {
            axios({
                method: 'get',
                url: `/challenge/taskticket/${this.challengeno}`,
            })
                .then((res) => {
                    this.chall_ticket = res.data;
                })
                .catch((err) => {
                    console.log(err);
                });
        },

        countDownTimer: function(rest) {
            var date = this.chall_info.challengeStartdate;
            //const countDownTimer = function (id) {
            var _vDate = new Date(date); // 전달 받은 일자
            var _second = 1000;
            var _minute = _second * 60;
            var _hour = _minute * 60;
            var _day = _hour * 24;
            var timer;
            function showRemaining() {
                if (document.getElementById(rest) == null) return;
                var now = new Date();
                var distDt = _vDate - now - 1;
                if (distDt < 0) {
                    clearInterval(rest);
                    // if (!this.overStartDate) {
                    //     makeTrue();
                    //     //this.overStartDate = true;
                    //     //alert('overs');
                    // }
                    document.getElementById(rest).textContent = '챌린지를 완주하세요!';
                    return;
                }
                var days = Math.floor(distDt / _day);
                var hours = Math.floor((distDt % _day) / _hour);
                var minutes = Math.floor((distDt % _hour) / _minute);
                var seconds = Math.floor((distDt % _minute) / _second);
                //document.getElementById(id).textContent = date.toLocaleString() + "까지 : ";
                document.getElementById(rest).textContent = '시작까지 ' + days + '일 ';
                document.getElementById(rest).textContent += hours + '시간 ';
                document.getElementById(rest).textContent += minutes + '분 ';
                document.getElementById(rest).textContent += seconds + '초';
            }
            timer = setInterval(showRemaining, 1000);
        },
        ...mapActions({
            // import 해주는 느낌
            joinChall: 'joinChallenge',
        }),
        // 가입하기 버튼 눌렀을 때
        hidebtn: function(chall_No, user) {
            this.msg = '가입완료';
            console.log('가입완료');
            //alert(chall_No + ' ' + user);
            document.querySelector('.Cjoin_btn .btn-light').style.backgroundColor = '#f9d479';

            var info = [chall_No, user];
            //alert(this.overMember() + ' ' + this.overTime() + ' ' + this.didJoin() + ' ');
            this.joinChall(info); // email이랑 챌린지 번호 전송
            this.$router.go();
        },

        nameprofile(num) {
            var email = this.chall_info.challengeGroup[num][0];
            console.log(email);
        },
        didJoin: function() {
            //가입되 있는 그룹인지 확인 가입시 트루 비가입시 풜스
            var user = this.userEmail;
            for (let i = 0; i < this.chall_info.challengeGroup.length; i++) {
                if (user == this.chall_info.challengeGroup[i][0]) {
                    return (this.joined = true);
                }
            }
            return (this.joined = false);
        },
        overMember: function() {
            //모집기간 종료 혹은 10명 초과시 가입 종료
            //alert(this.overStartdate);
            if (this.chall_info.challengeGroup.length >= this.chall_info.challengeCapacity) return true;
            else return false;
        },
        overTime: function(flag) {
            if (flag) {
                //alert("타임옹바")
                return true;
            } else return false;
        },
        makeTrue: function() {
            this.overStartDate = true;
        },
    },
    created: function() {
        this.challengeno = this.$route.query.cn;
        this.getChallInfo(); //생성할 때 바로 불러줘
        this.makeArr();
        this.getTaskInfo();
        // this.countDownTimer('rest', this.chall_info.challengeStartdate);
        this.getChallTicket();
        //document.getElementById(id).textContent = '';
    },
    watch: {
        // overStartDate: function() {
        //     alert('overStartDate');
        //     this.over = true;
        //},     
    },
    computed: {
        ...mapGetters(['userEmail']),
        // newover: function() {
        //     alert('newover');
        //     return this.overStartDate;
        // },
    },
    mounted: {},
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
    border: none;
}

/* 과제 제출 전 칸 */
.before {
    background-color: #ffffff;
    width: 100%;
    height: 100%;
    border: 1px solid #dddddd;
}

/* 과제 제출 후 파란색으로 변함 */
.after {
    background-color: #1f4256;
    width: 100%;
    height: 100%;
    border: 1px solid #dddddd;
}

/* 과제 제출 안하면 빨간색으로 변함 */
.fail {
    background-color: #ee4748;
    width: 100%;
    height: 100%;
    border: 1px solid #dddddd;
}

th {
    width: 100px;
}

#levelstar {
    width: 45px;
    height: 45px;
}
</style>
