<template>
    <div>
        <!-- 챌린지 모달 버튼 -->
        <div class="challenge-modal-button" data-bs-toggle="modal" data-bs-target="#challengeModal" data-bs-whatever="@getbootstrap">+</div>

        <!-- 챌린지 모달 -->
        <div class="modal fade" id="challengeModal" tabindex="-1" aria-labelledby="challengeModalLabel" aria-hidden="true">
            <div class="modal-dialog challenge-dialog">
                <div class="modal-content challenge-content">
                    <div class="modal-body">
                        <form>
                            <!-- 챌린지명 -->
                            <div class="d-flex">
                                <label for="challenge-name" class="col-form-label">챌린지명</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="challenge-name"
                                    v-model="challengeInfo.challengeName"
                                    @click="dataCheck()"
                                    @change="dataCheck()"
                                />
                            </div>

                            <!-- 카테고리 -->
                            <div class="d-flex">
                                <label for="category-select" class="col-form-label">카테고리</label>
                                <select
                                    class="form-select"
                                    aria-label="Default select example"
                                    id="category-select"
                                    v-model="challengeInfo.challengeCategory"
                                    @click="dataCheck()"
                                >
                                    <option value="ALGO">알고리즘</option>
                                    <option value="CS">CS</option>
                                    <option value="JOB">취업</option>
                                </select>
                            </div>

                            <!-- 난이도 -->
                            <div class="d-flex">
                                <div>난이도</div>
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
                            <div class="d-flex">
                                <label for="message-text" class="col-form-label">챌린지 소개</label>
                                <textarea
                                    class="form-control"
                                    id="message-text"
                                    v-model="challengeInfo.challengeDesc"
                                    @click="dataCheck()"
                                    @change="dataCheck()"
                                ></textarea>
                            </div>

                            <!-- 기간 -->
                            <label for="challenge-term" class="col-form-label">기간</label>
                            <input
                                type="date"
                                id="challenge-term"
                                v-model="challengeInfo.challengeStartdate"
                                @change="dataCheck()"
                                @click="dataCheck()"
                            />
                            ~
                            <input type="date" v-model="challengeInfo.challengeEnddate" @change="dataCheck()" @click="dataCheck()" />

                            <!-- 인원 -->
                            <div class="d-flex">
                                <label for="member-select" class="col-form-label">인원</label>
                                <select
                                    class="form-select"
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

                            <!-- 과제 -->
                            <div class="tasks">
                                <div class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 1</label>
                                    <input type="date" id="task-term" v-model="tasks[0]" @change="dataCheck()" @click="dataCheck()" />
                                    <div class="task-add-button" @click="showTask()">+</div>
                                    <div class="task-add-button" @click="hideTask()">-</div>
                                </div>
                                <div v-if="showFlag" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 2</label>
                                    <input type="date" id="task-term" v-model="tasks[1]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[2]" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 3</label>
                                    <input type="date" id="task-term" v-model="tasks[2]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[3]" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 4</label>
                                    <input type="date" id="task-term" v-model="tasks[3]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[4]" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 5</label>
                                    <input type="date" id="task-term" v-model="tasks[4]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[5]" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 6</label>
                                    <input type="date" id="task-term" v-model="tasks[5]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                                <div v-if="isShow[6]" class="d-flex task">
                                    <label for="task-term" class="col-form-label">과제 7</label>
                                    <input type="date" id="task-term" v-model="tasks[6]" @change="dataCheck()" @click="dataCheck()" />
                                </div>
                            </div>
                        </form>
                    </div>

                    <div v-if="warningShow">모든 항목을 입력해주세요.</div>
                    <div v-if="dateWarningShow">날짜가 올바르지 않습니다.</div>

                    <div class="modal-footer">
                        <button type="button" class="submit-button btn btn-primary" @click="submit()">등록</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';

export default {
    name: 'ChallengeModal',
    data: function() {
        return {
            tasksIndex: 0,

            isOkay: true,
            isEmpty: true,

            warningShow: false,
            warningFlag: false,

            dateOkay: true,
            dateFlag: true,
            dateWarningShow: false,
            dateWarningFlag: false,

            tasks: [null, null, null, null, null, null, null],
            isShow: [false, false, false, false, false, false, false],

            challengeInfo: {
                challengeCapacity: 0,
                challengeCategory: null,
                challengeDesc: null,
                challengeEnddate: null,
                challengeLevel: 0,
                challengeName: null,
                challengeStartdate: null,
                challengeTaskCnt: 0,
                challengeTaskdeadlines: [null, null, null, null, null, null, null],
            },
        };
    },
    methods: {
        showTask: function() {
            if (this.tasksIndex < 6) {
                this.tasksIndex += 1;
                this.isShow[this.tasksIndex] = true;
                this.dataCheck();
            }
        },
        hideTask: function() {
            if (0 < this.tasksIndex) {
                this.isShow[this.tasksIndex] = false;
                this.tasks[this.tasksIndex] = null;
                this.tasksIndex -= 1;
                this.dataCheck();
            }
        },
        dataCheck: function() {
            this.isOkay = true;
            this.isEmpty = false;

            this.dateFlag = true;
            this.dateOkay = true;

            this.challengeInfo.challengeTaskdeadlines = this.tasks;
            this.challengeInfo.challengeTaskCnt = this.tasksIndex + 1;

            // console.log(this.tasksIndex)
            // console.log(this.challengeInfo.challengeTaskCnt)

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

            if (this.challengeInfo.challengeStartdate && this.challengeInfo.challengeEnddate) {
                const startDate = new Date(this.challengeInfo.challengeStartdate);
                const endDate = new Date(this.challengeInfo.challengeEnddate);
                if (startDate > endDate) {
                    this.isOkay = false;
                    this.dateOkay = false;
                    this.dateFlag = false;
                } else {
                    const deadlines = this.challengeInfo.challengeTaskdeadlines;
                    for (let i = 0; i < this.challengeInfo.challengeTaskCnt; i++) {
                        const deadline = new Date(deadlines[i]);
                        if (!deadline || deadline < startDate || endDate < deadline) {
                            this.isOkay = false;
                            this.dateOkay = false;
                            this.dateFlag = false;
                            break;
                        }
                    }
                }
            }

            if (this.dateFlag) {
                this.dateWarningFlag = false;
            }

            if (this.isOkay) {
                this.warningFlag = false;
                this.dateWarningFlag = false;
                document.querySelector('.submit-button').setAttribute('data-bs-dismiss', 'modal');
            } else {
                document.querySelector('.submit-button').removeAttribute('data-bs-dismiss');
            }
        },
        submit: function() {
            this.dataCheck();

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
                    // console.log(this.challengeInfo)
                    // console.log('성공!!!')
                    this.challengeCreate();
                }
            }
        },
        challengeCreate: function() {
            axios({
                method: 'post',
                url: '/challengelist/create',
                data: this.challengeInfo,
            })
                .then((res) => {
                    console.log(res);
                    var info = [res.data, this.userEmail];
                    this.joinChall(info);
                    console.log('챌린지 생성 완료' + res.data + ' ' + this.userEmail + '로가입신청');
                    this.$({ name: 'ChallengeRoom', query: { cn: res.data } });
                })
                .catch((err) => {
                    alert('챌린지 생성에 실패하였습니다.');
                    console.log(err);
                });
        },
        ...mapActions({
            // import 해주는 느낌
            joinChall: 'joinChallenge',
        }),
    },
    computed: {
        showFlag: function() {
            return this.isShow[this.tasksIndex];
        },
        ...mapGetters(['userEmail']),
    },
    watch: {
        challengeInfo: function() {
            this.flag = true;
        },
        warningFlag: function(newValue) {
            if (newValue) {
                this.warningShow = true;
            } else {
                this.warningShow = false;
            }
        },
        dateWarningFlag: function(newValue) {
            if (newValue) {
                this.dateWarningShow = true;
            } else {
                this.dateWarningShow = false;
            }
        },
    },
};
</script>

<style scoped>
* {
    font-family: 'Noto Sans KR', sans-serif;
}

.challenge-modal-button {
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
}

.challenge-dialog {
    margin: 0;
}

.challenge-content {
    width: 1000px;
    height: 500px;
    overflow-y: scroll;
}

/* 별점 */
.star-rating {
    display: flex;
    flex-direction: row-reverse;
    font-size: 2.25rem;
    line-height: 2.5rem;
    justify-content: space-around;
    padding: 0 0.2em;
    text-align: center;
    width: 5em;
    margin: 0;
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
    cursor: pointer;
    width: 20px;
    height: 20px;
    border-radius: 75px;
    background-color: #99b7ff;
}
</style>
