<template>
    <div>
        <!-- 챌린지 모달 버튼 -->
        <div class="challenge-modal-button" data-bs-toggle="modal" data-bs-target="#challengeModal" data-bs-whatever="@getbootstrap">
            +
            <figcaption>챌린지 생성</figcaption> 
        </div>

        <!-- 챌린지 모달 -->
        <div class="modal fade" id="challengeModal" tabindex="-1" aria-labelledby="challengeModalLabel" aria-hidden="true">
            <div class="modal-dialog challenge-dialog">
                <div class="modal-content challenge-content">
                    <div class="modal-body">
                        <form>
                            <!-- 챌린지명 -->
                            <div class="d-flex name-container">
                                <label v-if="checkName" for="challenge-name" class="col-form-label">
                                    챌린지명
                                </label>
                                <label v-else for="challenge-name" class="col-form-label" style="color:red">
                                    챌린지명
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="challenge-name"
                                    placeholder="챌린지명은 12자 이내로 작성해주세요."
                                    onfocus="this.placeholder=''"
                                    onblur="this.placeholder='챌린지명은 12자 이내로 작성해주세요.'"
                                    v-model="challengeInfo.challengeName"
                                    @click="dataCheck()"
                                    @change="dataCheck()"
                                />
                            </div>

                            <!-- 카테고리 -->
                            <div class="d-flex category-container">
                                <label for="category-select" class="col-form-label">카테고리</label>
                                <select
                                    class="form-select"
                                    aria-label="Default select example"
                                    id="category-select"
                                    v-model="challengeInfo.challengeCategory"
                                    @click="dataCheck()"
                                >
                                    <option value="ALGO" selected>알고리즘</option>
                                    <option value="CS">CS</option>
                                    <option value="JOB">취업</option>
                                </select>
                            </div>

                            <!-- 난이도 -->
                            <div class="d-flex difficulty-container">
                                <label for="5-stars" class="difficulty-label">난이도</label>
                                <div class="d-flex justify-content-start">
                                    <div class="star-rating space-x-4 mx-auto">
                                        <input
                                            type="radio"
                                            id="5-stars"
                                            name="rating"
                                            value="5"
                                            v-model="challengeInfo.challengeLevel"
                                            @click="dataCheck()"
                                        />
                                        <label for="5-stars" class="star pr-4 star-rating-label">★</label>
                                        <input
                                            type="radio"
                                            id="4-stars"
                                            name="rating"
                                            value="4"
                                            v-model="challengeInfo.challengeLevel"
                                            @click="dataCheck()"
                                        />
                                        <label for="4-stars" class="star star-rating-label">★</label>
                                        <input
                                            type="radio"
                                            id="3-stars"
                                            name="rating"
                                            value="3"
                                            v-model="challengeInfo.challengeLevel"
                                            @click="dataCheck()"
                                        />
                                        <label for="3-stars" class="star star-rating-label">★</label>
                                        <input
                                            type="radio"
                                            id="2-stars"
                                            name="rating"
                                            value="2"
                                            v-model="challengeInfo.challengeLevel"
                                            @click="dataCheck()"
                                        />
                                        <label for="2-stars" class="star star-rating-label">★</label>
                                        <input
                                            type="radio"
                                            id="1-star"
                                            name="rating"
                                            value="1"
                                            v-model="challengeInfo.challengeLevel"
                                            @click="dataCheck()"
                                        />
                                        <label for="1-star" class="star star-rating-label">★</label>
                                    </div>
                                </div>
                            </div>

                            <!-- 챌린지 소개 -->
                            <div class="d-flex description-container">
                                <label v-if="checkLength == false" for="message-text" class="col-form-label" style="color:red"
                                    >챌린지 소개<br />({{ challengeInfo.challengeDesc.length }}/600)</label
                                >
                                <label v-else for="message-text" class="col-form-label"
                                    >챌린지 소개<br />({{ challengeInfo.challengeDesc.length }}/600)</label
                                >
                                <textarea
                                    class="form-control"
                                    id="message-text"
                                    :placeholder="computedDescription"
                                    onfocus="this.placeholder=''"
                                    onblur="this.placeholder='챌린지 자세한 설명\n(커리큘럼, 상세설명, 진행상황 등)\n챌린지 과제번호 - 과제 소개\n\n*과제별 소개를 명확히 써주세요.'"
                                    v-model="challengeInfo.challengeDesc"
                                    @click="dataCheck()"
                                    @change="dataCheck()"
                                ></textarea>
                            </div>

                            <!-- 기간 -->
                            <label for="challenge-term" class="col-form-label term-container">기간</label>
                            <input
                                type="date"
                                id="challenge-term"
                                class="date"
                                v-model="challengeInfo.challengeStartdate"
                                @change="dataCheck()"
                                @click="dataCheck()"
                            />
                            &nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="date" class="date" v-model="challengeInfo.challengeEnddate" @change="dataCheck()" @click="dataCheck()" />

                            <!-- 인원 -->
                            <div class="d-flex member-container">
                                <label for="member-select" class="col-form-label member-label">인원</label>
                                <select
                                    class="form-select member-select"
                                    aria-label="Default select example"
                                    id="member-select"
                                    v-model="challengeInfo.challengeCapacity"
                                    @click="dataCheck()"
                                >
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                            </div>

                            <hr />
                            <!-- 과제 -->
                            <div class="tasks">
                                <div class="d-flex first-task">
                                    <label for="task-term" class="col-form-label task-label">과제 1</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[0]" @change="dataCheck()" @click="dataCheck()" />
                                    <div class="d-flex button-container">
                                        <div class="task-add-button" @click="showTask()">+</div>
                                        <div class="task-add-button" @click="hideTask()">-</div>
                                    </div>
                                </div>
                                <div v-if="showFlag" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 2</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[1]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[2]" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 3</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[2]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[3]" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 4</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[3]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[4]" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 5</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[4]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[5]" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 6</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[5]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[6]" class="d-flex task">
                                    <label for="task-term" class="col-form-label task-label">과제 7</label>
                                    <input type="date" id="task-term" class="date" v-model="tasks[6]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer d-flex justify-content-between">
                        <!-- 경고 메시지 -->
                        <div>
                            <div class="warning" v-if="warningShow">모든 항목을 올바르게 입력해주세요.</div>
                            <div class="warning" v-if="dateWarningShow">날짜가 올바르지 않습니다.</div>
                        </div>

                        <!-- 버튼 -->
                        <div>
                            <button type="button" class="submit-button btn btn-primary" @click="submit()">
                                등록
                            </button>
                            <button type="button" class="cancel-button btn btn-warning" data-bs-dismiss="modal">
                                취소
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';
import swal from 'sweetalert';

export default {
    name: 'ChallengeModal',
    data: function() {
        return {
            tasksIndex: 0, // 과제 번호를 나타내는 인덱스. tasks에 접근할 때 쓰임.

            isOkay: true, // 챌린지 모달이 완전히 채워졌는지
            isEmpty: true, // 챌린지 모달이 완전히 비었는지

            warningShow: false, // 빈 칸 관련 경고 메시지를 보여줄지
            warningFlag: false, // warningShow를 조작하는 플래그 변수

            dateOkay: true, // 날짜가 올바른지
            dateFlag: true, // dataOkay를 조작하는 플래그 변수
            dateWarningShow: false, // 날짜 관련 경고 메시지를 보여줄지
            dateWarningFlag: false, // dateWarningShow를 조작하는 플래그 변수

            tasks: [null, null, null, null, null, null, null], // 과제의 데드라인을 담을 배열
            isShow: [false, false, false, false, false, false, false], // 과제가 보여지는지의 여부를 담은 배열

            checkLength: false, // 컨텐츠 길이 제한
            checkName: false, // 챌린지명 길이 제한

            description: '챌린지 자세한 설명\n(커리큘럼, 상세설명, 진행상황 등)\n챌린지 과제번호 - 과제 소개\n\n*과제별 소개를 명확히 써주세요.',

            /* API에 넘겨줄 챌린지 정보 */
            challengeInfo: {
                challengeCapacity: 0,
                challengeCategory: '',
                challengeDesc: '',
                challengeEnddate: '',
                challengeLevel: 0,
                challengeName: '',
                challengeStartdate: '',
                challengeTaskCnt: 0,
                challengeTaskdeadlines: [null, null, null, null, null, null, null],
            },
        };
    },
    methods: {
        /* 과제 추가 함수 */
        showTask: function() {
            if (this.tasksIndex < 6) {
                this.tasksIndex += 1;
                this.isShow[this.tasksIndex] = true;
                this.dataCheck();
            }
        },
        /* 과제 삭제 함수 */
        hideTask: function() {
            if (0 < this.tasksIndex) {
                this.isShow[this.tasksIndex] = false;
                this.tasks[this.tasksIndex] = null;
                this.tasksIndex -= 1;
                this.dataCheck();
            }
        },
        /* 모달 폼 안의 데이터 체크 함수 */
        dataCheck: function() {
            // 빈 칸 관련 변수들 초기화
            this.isOkay = true;
            this.isEmpty = false;

            // 날짜 관련 변수들 초기화
            this.dateFlag = true;
            this.dateOkay = true;

            // 과제의 데드라인과 과제의 갯수 갱신
            this.challengeInfo.challengeTaskdeadlines = this.tasks;
            this.challengeInfo.challengeTaskCnt = this.tasksIndex + 1;

            // 빈 칸이 있는지 체크
            for (const [key, value] of Object.entries(this.challengeInfo)) {
                if (key === 'challengeTaskdeadlines' && value.toString() === [null, null, null, null, null, null, null].toString()) {
                    this.isOkay = false;
                    break;
                } else {
                    if (!value) {
                        this.isOkay = false;
                        break;
                    }
                }
            }

            // 챌린지명, 챌린지 소개 체크
            if (!this.checkName || !this.checkLength) {
                this.isOkay = false;
            }

            // 날짜가 올바르게 입력되었는지 체크
            if (this.challengeInfo.challengeStartdate && this.challengeInfo.challengeEnddate) {
                const today = new Date()
                const startDate = new Date(this.challengeInfo.challengeStartdate)
                const endDate = new Date(this.challengeInfo.challengeEnddate)
                
                today.setHours(8)
        
                if (startDate > endDate || today > startDate) {
                    this.isOkay = false;
                    this.dateOkay = false;
                    this.dateFlag = false;
                } else {
                    const deadlines = this.challengeInfo.challengeTaskdeadlines;
                    for (let i = 0; i < this.challengeInfo.challengeTaskCnt; i++) {
                        console.log(i)
                        const deadline = new Date(deadlines[i]);
                        if ((!deadline) || (deadline < startDate) || (endDate < deadline)) {
                            this.isOkay = false;
                            this.dateOkay = false;
                            this.dateFlag = false;
                            break;
                        }
                    }
                }
            }

            // 날짜가 올바르면 경고 문구를 바로 없애버림
            if (this.dateFlag) {
                this.dateWarningFlag = false;
            }

            // 폼이 다 채워졌으면 경고 문구를 없애고 등록 버튼 활성화
            // 아닐 경우 경고 문구를 보이고 등록 버튼 비활성화
            if (this.isOkay) {
                this.warningFlag = false;
                this.dateWarningFlag = false;
                document.querySelector('.submit-button').setAttribute('data-bs-dismiss', 'modal');
            } else {
                document.querySelector('.submit-button').removeAttribute('data-bs-dismiss');
            }
        },
        /* 제출 함수 */
        submit: function() {
            this.dataCheck();

            // 폼이 비어있거나 날짜가 잘못되었을 경우 경고 메시지 생성
            // 아닐 경우 챌린지 생성
            if (this.isEmpty) {
                this.warningFlag = true;
            } else {
                if (!this.isOkay) {
                    if (!this.dateOkay) {
                        this.dateWarningFlag = true;
                    } else {
                        this.warningFlag = true;
                    }
                } else {
                    this.challengeCreate();
                }
            }
        },
        /* 챌린지 생성 함수 */
        challengeCreate: function() {
            var enterFlag = false;
            axios({
                method: 'post',
                url: '/challengelist/create',
                data: this.challengeInfo,
            })
                .then((res) => {
                    swal('챌린지 생성에 성공하였습니다.');
                    // console.log(res);
                    var info = [res.data, this.userEmail];
                    // console.log('생성 리스폰');
                    // console.log('챌린지 생성 완료' + res.data + ' ' + this.userEmail + '로가입신청');
                    axios({
                        method: 'post',
                        url: '/challenge/join',
                        data: { challengeNo: info[0], userEmail: info[1] },
                    })
                        .then((res) => {
                            // 통신이 넘어오는 것
                            this.$router.push({ name: 'ChallengeRoom', query: { cn: info[0] } });
                        })
                        .catch((err) => {
                            // console.log(err);
                        });
                })
                .catch((err) => {
                    swal('챌린지 생성에 실패하였습니다.');
                    // console.log(err);
                });
        },
        /* 텍스트 내용 길이 제한 체크*/
        checkform: function() {
            // 챌린지 명은 12글자 이하.
            if (0 < this.challengeInfo.challengeName.length && this.challengeInfo.challengeName.length <= 12) {
                this.checkName = true;
                // return;
            } else {
                this.checkName = false;
            }

            // 챌린지 소개 내용은 1글자 이상, 600글자 이하.
            if (0 < this.challengeInfo.challengeDesc.length && this.challengeInfo.challengeDesc.length <= 600) {
                this.checkLength = true;
                return;
            } else {
                this.checkLength = false;
            }
        },
        /* 챌린지 가입 함수 가져오기 */
        ...mapActions({
            joinChall: 'joinChallenge',
        }),
    },
    computed: {
        /* 과제 추가 및 생성 시 사용되는 플래그 */
        showFlag: function() {
            return this.isShow[this.tasksIndex];
        },
        computedDescription: function() {
            return this.description;
        },
        /* 사용자의 이메일 가져오기 */
        ...mapGetters(['userEmail']),
    },
    watch: {
        /* 빈 칸 관련 경고 메시지 플래그 */
        warningFlag: function(newValue) {
            if (newValue) {
                this.warningShow = true;
            } else {
                this.warningShow = false;
            }
        },
        /* 날짜 관련 경고 메시지 플래그 */
        dateWarningFlag: function(newValue) {
            if (newValue) {
                this.dateWarningShow = true;
            } else {
                this.dateWarningShow = false;
            }
        },
        'challengeInfo.challengeDesc': function() {
            this.checkform();
        },
        'challengeInfo.challengeName': function() {
            this.checkform();
        },
    },
};
</script>

<style scoped>
* {
    font-family: 'Noto Sans KR', sans-serif;
}

/* 모달 */
.challenge-modal-button {
    color: #1f3e52;
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60px;
    height: 60px;
    border-radius: 75px;
    bottom: 25px;
    right: 30px;
    background-color: #99b7ff;
    cursor: pointer;
    font-size: 40px;
    font-weight: bold;
    z-index: 5;
}

.challenge-dialog {
    margin: 0px;
}

.challenge-content {
    width: 800px;
    height: 815px;
    margin-top: 80px;
    margin-left: 560px;
    padding: 50px 70px 20px 70px;
    border-radius: 40px;
    overflow-y: scroll;
    background-color: #99b7ff;
}

.form-control {
    width: 500px;
    border-radius: 0.5rem;
}

.form-control:focus {
    border-style: none;
    outline: 0;
    box-shadow: none;
}

.form-select {
    border-radius: 0.5rem;
}

.form-select:focus {
    border-style: none;
    outline: 0;
    box-shadow: none;
}

input {
    border-style: none;
}

select {
    width: 500px;
    border-style: none;
}

label {
    font-weight: bold;
    font-size: 20px;
    width: 150px;
}

textarea {
    height: 200px;
    border-style: none;
}

.modal-body {
    padding-bottom: 0px;
}

.modal-footer {
    padding-top: 15px;
    margin-bottom: 10px;
    border-style: none;
}

.name-container {
    margin-bottom: 20px;
}

.category-container {
    margin-bottom: 20px;
}

.difficulty-container {
    margin-bottom: 20px;
}

.difficulty-label {
    padding-top: 8px;
}

.description-container {
    margin-bottom: 20px;
}

.term-container {
    margin-bottom: 20px;
    width: 145px;
}

.member-container {
    margin-bottom: 25px;
}

.member-label {
    width: 145px;
}

.member-select {
    width: 165.19px;
}

.button-container {
    padding-top: 5px;
}

.date {
    height: 44px;
    padding-left: 5px;
    padding-right: 5px;
    border-radius: 0.5rem;
}

.date:focus {
    outline: none;
}

.first-task {
    margin-top: 25px;
}

.task {
    margin-top: 20px;
}

.task-label {
    width: 145px;
}

.warning {
    color: red;
}

/* 별점 */
.star-rating {
    display: flex;
    flex-direction: row-reverse;
    font-size: 3.5rem;
    line-height: 2.5rem;
    justify-content: space-around;
    text-align: left;
    width: 5em;
}

.star {
    font-size: 30px;
}

.star-rating input {
    display: none;
}

.star-rating label {
    -webkit-text-fill-color: #d1cfcd;
    cursor: pointer;
}

.star-rating :checked ~ label {
    -webkit-text-fill-color: gold;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
    -webkit-text-fill-color: gold;
}

/* 과제 */
.task-add-button {
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    width: 33px;
    height: 33px;
    border-radius: 75px;
    font-size: 20px;
    font-weight: bold;
    color: #ffffff;
    background-color: #4c67aa;
    margin-left: 10px;
}

/* 버튼 */
.submit-button {
    font-size: 19px;
    background-color: #1c84c4;
    border-style: none;
    border-radius: 0.7rem;
    margin-top: 5px;
}

.btn-check:checked + .btn-primary,
.btn-check:active + .btn-primary,
.btn-primary:active,
.btn-primary.active,
.show > .btn-primary.dropdown-toggle {
    background-color: #1c84c4;
    border-style: none;
}

.btn-check:focus + .btn-primary,
.btn-primary:focus {
    background-color: #1c84c4;
    border-style: none;
    box-shadow: none;
}

.btn-primary:hover {
    background-color: #1c84c4;
    border-style: none;
}

.btn-check:checked + .btn-primary:focus,
.btn-check:active + .btn-primary:focus,
.btn-primary:active:focus,
.btn-primary.active:focus,
.show > .btn-primary.dropdown-toggle:focus {
    box-shadow: none;
}

.cancel-button {
    font-size: 19px;
    margin-left: 15px;
    color: white;
    background-color: #f53535;
    border-style: none;
    border-radius: 0.7rem;
    margin-top: 5px;
}

.btn-check:checked + .btn-warning:focus,
.btn-check:active + .btn-warning:focus,
.btn-warning:active:focus,
.btn-warning.active:focus,
.show > .btn-warning.dropdown-toggle:focus {
    box-shadow: none;
}

.btn-warning:hover {
    color: white;
    background-color: #f53535;
    border-style: none;
}

.btn-check:focus + .btn-warning,
.btn-warning:focus {
    color: white;
    background-color: #f53535;
    border-style: none;
    box-shadow: none;
}

.btn-check:checked + .btn-warning,
.btn-check:active + .btn-warning,
.btn-warning:active,
.btn-warning.active,
.show > .btn-warning.dropdown-toggle {
    color: white;
    background-color: #f53535;
    border-style: none;
}

.challenge-modal-button:hover {
    background-color: rgb(48, 48, 48);
}

figcaption {
  opacity: 0;
  position: absolute;
  font-size: 16px;
  color: white;
  top: 7px;
}

.challenge-modal-button:hover figcaption {
    opacity: 1;
}
</style>
