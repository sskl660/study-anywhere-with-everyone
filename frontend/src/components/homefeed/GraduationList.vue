<template>
  <div>
    <div class="graduation">
        <h3 style="margin-bottom: 20px; padding-top:20px"><strong>졸업자 🎓</strong></h3>
        <div v-for="student in weeklyStudy" :key="student" class="d-flex justify-content-left graduatedStudent">
            {{student.userName}}
        </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
    name: 'GraduationList',
    data(){
        return{
            graduate:[
                {
                    "userEmail": "string",
                    "userName": "string"
                }
            ]
        }
    },
    methods:{
        getGraduate: function(){
            axios({
                method: 'get',
                url: `/rank/graduated`,
            })
            .then((res) => {
                this.graduate = res.data;
                console.log('getGraduate 성공')
                console.log(res.data)
            })
            .catch((err) => {
                console.log('getGraduate 에러')
                console.log(err);
            });
        },
    },
    created: function(){
        this.getGraduate();
    }
}
</script>

<style>
.graduation{
    background-color: #F4D6A6;
    /* justify-content: center; */
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 120px;
}

.graduatedStudent{
    font-size: 25px;
    margin-left: 50px;
    margin-bottom: 7px;
}
</style>