<template>
  <div>
    <div class="weeklyChallenge">
        <h3 style="margin-bottom: 20px; padding-top:20px"><strong>주간 챌린지 TOP5 👑</strong></h3>
        <div v-for="(weekly, index) in weeklyChallenge" :key="weekly" class="d-flex justify-content-left wcList">
            {{index+1}}&nbsp;&nbsp; {{weekly.userName}}
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
            weeklyChallenge:[
                {
                    "userEmail": "string",
                    "userName": "string",
                    "week": 0
                }
            ]
        }
    },
    methods:{
        getWeeklyChallenge: function(){
            axios({
                method: 'get',
                url: `/rank/challenge`,
            })
            .then((res) => {
                this.weeklyChallenge = res.data;
                console.log('getWeeklyChallenge 성공')
                console.log(res.data)
            })
            .catch((err) => {
                console.log('getWeeklyChallenge 에러')
                console.log(err);
            });
        },
    },
    created: function(){
        this.getWeeklyChallenge();
    }
}
</script>

<style>
.weeklyChallenge{
    background-color: #FFF59D;
    /* justify-content: center; */
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 270px;
}

.wcList{
    font-size: 25px;
    margin-left: 50px;
    margin-bottom: 7px;
}
</style>