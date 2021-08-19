<template>
  <div>
    <div class="weeklyStudy">
        <h3 class="study-title d-flex align-items-center">
            <strong>
                주간 공부시간 TOP5
            </strong>
            <img class="crown" src="../../assets/crown.png" alt="crown"/>
        </h3>
        <div v-for="(galaxy, index) in weeklyStudy" :key="index" class="d-flex justify-content-start wsList">
            <span class="fw-bold">{{ index+1 }}</span> &nbsp;&nbsp;
            <span class="galaxy-member-name" @click="moveToProfile(galaxy.userEmail)">{{ galaxy.userName }}</span>
            <span class="galaxy-record">{{ parseInt(galaxy.week/3600) }}시간</span>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
    name: 'WeeklyStudyList',
    data(){
        return{
            weeklyStudy: null
        }
    },
    methods:{
        getWeeklyStudy: function(){
            axios({
                method: 'get',
                url: `/rank/galaxy`,
            })
            .then((res) => {
                this.weeklyStudy = res.data;
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
        this.getWeeklyStudy();
    },
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

.weeklyStudy{
    background-color: #9ba8da;
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 270px;
}

.study-title {
    text-align: left;
    margin-left: 50px;
    margin-bottom: 10px; 
    padding-top:10px
}

.wsList{
    font-family: 'NanumSquareRound';
    font-size: 22px;
    margin-left: 50px;
    margin-bottom: 10px;
}

.crown {
    margin-left: 5px;
    width: 50px;
}

.galaxy-member-name {
    font-family: 'NanumSquareRound';
    cursor: pointer;
    text-align: left;
    width: 100px;
    margin-top: 1px;
    font-weight: bold;
}

.galaxy-member-name:hover {
    color: #1C84C4;
}

.galaxy-record {
    font-weight: bold;
    color: #FFFFFF;
    line-height:20px;
}

.crown {
    margin-left: 5px;
    width: 50px;
}
</style>