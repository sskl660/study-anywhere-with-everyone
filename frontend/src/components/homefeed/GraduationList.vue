<template>
  <div>
    <div class="graduation">
        <h3 class="graduation-title d-flex align-items-center">
            <strong>
                졸업자
            </strong>
            <img class="bachelor-cap" src="../../assets/bachelor_cap.png" alt="bachelor-cap"/>
        </h3>
        <div class="d-flex">
            <div v-for="(student, index) in graduate" :key="index" class="d-flex justify-content-left graduatedStudent">
                <div class="graduate-name" @click="moveToProfile(student.userEmail)">{{student.userName}}</div>
            </div>
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
            graduate: null
        }
    },
    methods:{
        getGraduate: function () {
            axios({
                method: 'get',
                url: '/rank/graduated',
            })
            .then((res) => {
                this.graduate = res.data;
            })
            .catch((err) => {
                console.log(err);
            });
        },
        moveToProfile: function(email) {
            this.$router.push({path: '/profile', query: {user: email}});
        },
    },
    created: function () {
        this.getGraduate();
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

.graduation-title {
    font-family: "Noto Sans KR", sans-serif;
    text-align: left;
    margin-bottom: 10px; 
    margin-left: 50px;
}

.graduation{
    background-color: #F4D6A6;
    width: 80%;
    border-radius: 33px;
    margin-top: 30px;
    margin-left: 57px;
    height: 120px;
}

.graduatedStudent{
    font-size: 23px;
    margin-left: 50px;
    margin-bottom: 7px;
}

.graduate-name {
    font-family: 'NanumSquareRound';
    cursor: pointer;
}

.graduate-name:hover {
    color: #1C84C4;
}

.bachelor-cap {
    margin-left: 5px;
    width: 50px;
}
</style>