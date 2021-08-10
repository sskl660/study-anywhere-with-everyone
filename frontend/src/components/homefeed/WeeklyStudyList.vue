<template>
  <div>
    <div class="weeklyStudy">
        <h3 style="margin-bottom: 20px; padding-top:20px"><strong>주간 랜각공 TOP5 👑</strong></h3>
        <div v-for="(galaxy, index) in weeklyStudy" :key="galaxy" class="d-flex justify-content-left wsList">
            {{index+1}}&nbsp;&nbsp; {{galaxy.userName}}
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
            weeklyStudy:[
                {
                    "userEmail": "string",
                    "userName": "string",
                    "week": 0
                }
            ]
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
                console.log('getWeeklyStudy 성공')
                console.log(res.data)
            })
            .catch((err) => {
                console.log('getWeeklyStudy 에러')
                console.log(err);
            });
        },
    },
    created: function(){
        this.getWeeklyStudy();
    }
}
</script>

<style>
.weeklyStudy{
    background-color: #B7BEDA;
    /* justify-content: center; */
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 270px;
}

.wsList{
    font-size: 25px;
    margin-left: 50px;
    margin-bottom: 7px;
}
</style>