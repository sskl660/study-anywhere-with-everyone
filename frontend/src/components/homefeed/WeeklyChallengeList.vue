<template>
  <div>
    <div class="weeklyChallenge">
        <h3 class="weekly-title d-flex align-items-center">
            <strong>
                주간 챌린지 TOP5
            </strong>
            <img class="crown" src="../../assets/crown.png" alt="crown"/>
        </h3>
        <div v-for="(weekly, index) in weeklyChallenge" :key="index" class="d-flex justify-content-start wcList">
            <span class="fw-bold">{{index+1}}</span> &nbsp;&nbsp; 
            <span class="challenger-name" @click="moveToProfile(weekly.userEmail)">{{weekly.userName}}</span> &nbsp;&nbsp; 
            <span class="challenge-record">{{weekly.week}}건 성공!</span>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
    name: 'WeeklyChallengeList',
    data(){
        return{
            weeklyChallenge: null
        }
    },
    methods:{
        getWeeklyChallenge: function () {
            axios({
                method: 'get',
                url: `/rank/challenge`,
            })
            .then((res) => {
                this.weeklyChallenge = res.data;
            })
            .catch((err) => {
                console.log(err);
            });
        },
        moveToProfile: function(email) {
            this.$router.push({path: '/profile', query: {user: email}});
        },
    },
    created: function(){
        this.getWeeklyChallenge();
    }
}
</script>

<style scoped>
@font-face {
  font-family: 'NanumSquareRound';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

* {
    font-family: "Noto Sans KR", sans-serif;
}

.weeklyChallenge{
    background-color: #FFF59D;
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 280px;
}

.weekly-title {
    text-align: left;
    margin-left: 50px;
    margin-bottom: 10px; 
    padding-top:20px
}

.wcList{
    font-family: 'NanumSquareRound';
    font-size: 23px;
    margin-left: 50px;
    margin-bottom: 10px;
}

.challenger-name {
    font-family: 'NanumSquareRound';
    text-align: left;
    width: 100px;
    margin-top: 1px;
    cursor: pointer;
    font-weight: bold;
}

.challenger-name:hover {
    color: #1C84C4;
}

.challenge-record {
    font-weight: bold;
    color: #FC9B41;
}

.crown {
    margin-left: 5px;
    width: 50px;
}
</style>