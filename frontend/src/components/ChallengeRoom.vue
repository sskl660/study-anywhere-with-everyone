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
                    <!-- 목록으로 이동 버튼 -->
                    <div class="goChallenge" @click="goBack()">
                        <ButtonRound :text="cmsg" />
                    </div>
                    &nbsp;
                    <!-- 가입하기 버튼 -->
                    <div v-if="!overStartDate && !didJoin() && !overMember()" class="Cjoin_btn" @click="hidebtn(chall_info.challengeNo, userEmail)">
                        <ButtonRound :text="msg" />
                    </div>
                    <!-- 마감 시간 -->
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
                            <tbody v-if="task_info[0].taskNo != 0">
                                <tr v-for="(person, index) in chall_info.challengeGroup" :key="index">
                                    <th scope="row" style="background-color: #b7beda">{{ person[1] }}</th>
                                    <td v-for="(taskIdx, index2) in chall_info.challengeTaskCnt" :key="index2">
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
                                            :to="{
                                                path: '/postDetailAfter',
                                                query: { taskNo: task_info[index].taskNo[taskIdx - 1], cn: chall_info.challengeNo },
                                            }"
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
                        <p v-html="handleNewLine(chall_info.challengeDesc)"></p>
                                        <div>챌린지 기간 : {{chall_info.challengeStartdate}}~{{chall_info.challengeEnddate}}</div>
                        <div>
                            <div v-for="(task, index) in chall_info.challengeTaskdeadlines" :key="task">
                                - {{ index + 1 }} 번째 과제 : {{ task }} 까지
                            </div>
                        </div>
                                    <br /><br />
                                    <strong
                                        >참여멤버 :
                                        <span
                                            v-for="(name, index) in chall_info.challengeGroup"
                                            :key="index"
                                            @click="nameprofile(index)"
                                            style="cursor:pointer;"
                                        >
                                            <router-link
                                                style="color:#blue; font-weight:600; text-decoration: none;"
                                                :to="{ name: 'Profile', query: { user: name[0] } }"
                                            >
                                                #{{ name[1] }}<span v-if="index == 0"> 팀장님 </span>
                                            </router-link>
                                        </span>
                                        &nbsp; [정원]{{ chall_info.challengeGroup.length }}/{{ chall_info.challengeCapacity }}
                                    </strong>
                                    <br />
                                    <div>
                                        <strong> 난이도 : </strong
                                        ><span v-for="level in chall_info.challengeLevel" :key="level"
                                            ><img src="../assets/star.png" alt="levelstar" id="levelstar"
                                        /></span>
                                        <br /><br />
                                    </div>
                                </div>
                            </div>
                            <div class="ChallengeTicket">
                                <ChallengeTicket :challTicket="chall_ticket" :ProcessRateArr="makeArr()" />
                            </div>
                        </div>
                    </div>

        <!-- <div class="tutorial"> -->
            <!-- <img id="explain" src="../assets/challengetutorialssazip.png" alt="" /> -->
            <!-- <img id="tuter" src="../assets/ssazip.png" alt=""> -->
        <!-- </div> -->
    </div>
</template>
<script>
import Title from '@/components/common/Title.vue';
import ButtonRound from '@/components/common/ButtonRound.vue';
import ChallengeTicket from '@/components/challengeroom/ChallengeTicket.vue';
import '@/components/css/ChallengeRoom.css';
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';

export default {
    name: 'ChallengeRoom',
    clocker: null,
    // el: 'goprofile',
    components: {
        Title, // 타이틀 가져오기
        ButtonRound, // 둥근 버튼 가져오기
        ChallengeTicket,
    },
    data: function() {
        return {
            // 버튼에 들어갈 문구들
            msg: '가입',
            cmsg: '목록',
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
            counter: '',
            timerStopFlag: false,
            second: 1000,
            minute: this.second * 60,
            hour: this.minute * 60,
            day: this.hour * 24,
            vDate: '',
        };
    },

    methods: {
        // 개행 처리
        handleNewLine(text) {
            return String(text).replace(/(?:\r\n|\r|\n)/g, '</br>');
        },

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
                    // console.log(err);
                });
        },
        //챌린지 페이지에서 챌린지 정보 불러오는 통신
        getChallInfo: function() {
            // console.log("챌린지 정보 요청 중")
            axios({
                methods: 'get',
                url: `/challenge/info/${this.challengeno}`,
            })
                .then((res) => {
                    // console.log('챌린지 정보 부르기 성공');
                    this.chall_info = res.data;
                    this.getChallengers(this.chall_info.challengeGroup.length);
                    this.chall_info.challengeStartdate += ' 00:00:01';
                    var startD = new Date(this.chall_info.challengeStartdate);
                    if (startD - new Date() - 1 < 0) this.makeTrue();
                    // var pre = new Date();
                    // if (startD.getFullYear() - pre.getFullYear() + startD.getMonth() - pre.getMonth() + startD.getDate() - pre.getDate() < 0) {
                    // }

                    this.countDownTimer();
                })
                .catch((err) => {
                    // console.log(err);
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
                .catch((err) => {});
        },

        countDownTimer: function() {
            let date = this.chall_info.challengeStartdate;
            this.vDate = new Date(date); // 전달 받은 일자
            this.second = 1000;
            this.minute = this.second * 60;
            this.hour = this.minute * 60;
            this.day = this.hour * 24;
        },
        async showRemaining() {
            let now = new Date();
            let distDt = this.vDate - now - 1;
            if (distDt < 0) {
                // this.makeTrue();
                clearInterval(this.clocker);
                // console.log('챌린지마감');
                document.getElementById('rest').textContent = '챌린지를 완주하세요!';
                return;
            }
            var days = Math.floor(distDt / this.day);
            var hours = Math.floor((distDt % this.day) / this.hour);
            var minutes = Math.floor((distDt % this.hour) / this.minute);
            var seconds = Math.floor((distDt % this.minute) / this.second);
            document.getElementById('rest').textContent = '시작까지 ' + days + '일 ';
            document.getElementById('rest').textContent += hours + '시간 ';
            document.getElementById('rest').textContent += minutes + '분 ';
            document.getElementById('rest').textContent += seconds + '초';
        },
        ...mapActions({
            // import 해주는 느낌
            joinChall: 'joinChallenge',
            getChallengers: 'getChallengers'
        }),
        // 가입하기 버튼 눌렀을 때
        hidebtn: function(chall_No, user) {
            this.msg = '완료';
            document.querySelector('.Cjoin_btn .btn-light').style.backgroundColor = '#f9d479';

            var info = [chall_No, user];
            this.joinChall(info); // email이랑 챌린지 번호 전송
            this.$router.go();
        },
        goBack: function() {
            // alert('goBack function')
            this.$router.go(-1);
        },
        nameprofile(num) {
            var email = this.chall_info.challengeGroup[num][0];
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
            if (this.chall_info.challengeGroup.length >= this.chall_info.challengeCapacity) return true;
            else return false;
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
    beforeRouteLeave(to, from, next) {
        clearInterval(this.clocker);
        next();
    },
    computed: {
        ...mapGetters(['userEmail']),
    },
    mounted() {
        this.clocker = setInterval(this.showRemaining, 1000);
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
    width: 100px;
    height: 54px;
    margin-right: 69%;
    margin-bottom: 10px;
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
    margin-bottom: 10px;
}

.goChallenge .btn-light {
    color: #1f4256;
    background-color: #e1af4e;
    border-color: #e1af4e;
    font-size: 25px;
    font-weight: bold;
    width: 100px;
    height: 54px;
    margin-right: 99%;
    margin-bottom: 10px;
    border-style: none;
}

.alarm {
    color: #e92828;
    margin-left: 10px;
    margin-top: 20px;
    height: 24px;
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

th {
    position: relative;
    /* display: inline-block; */
}

.namepoint {
    /* position: absolute; */
    /* top: 50%; */
    /* left: 0; */
    /* transform: translateY(-50%); */
    /* width: 50%; */
    /* font-size: 25px; */
    /* color: #ffffff; */
    /* font-weight: 900; */
    height: 100%;
}

#tuter {
    width: 50px;
    height: 63px;
    position: relative;
    margin-left: -3730px;
    margin-top: 750px;
}
#explain {
    width: 150px;
    height: 311px;
    position: relative;
    margin-left: -3625px;
    margin-top: 503px;
}
</style>
