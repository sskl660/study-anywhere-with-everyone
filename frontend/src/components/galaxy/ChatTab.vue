<!-- 채팅 Tab CSS 수정-->
<template>
    <div class="GalaxyTab d-flex">
        <div class="AlgoTitle d-flex align-items-center">
            <div class="AlgoImg">
                <img src="@/assets/algo.png" style="width:80px; cursor: pointer;" @click="changeChatType(1)" />
            </div>
            <span class="AlgoName" @click="changeChatType(1)" style="cursor: pointer; pointer-events :none">Algo</span>
        </div>
        &nbsp;

        <div class="CSTitle d-flex align-items-center">
            <div class="CSImg">
                <img src="@/assets/cs.png" style="width:80px; cursor: pointer;" @click="changeChatType(2)" />
            </div>
            <span class="CSName" @click="changeChatType(2)" style="cursor: pointer; pointer-events :none">CS</span>
        </div>
        &nbsp;

        <div class="JobTitle d-flex align-items-center">
            <div class="JobImg">
                <img src="@/assets/job.png" style="width: 80px; cursor: pointer;" @click="changeChatType(3)" />
            </div>
            <span class="JobName" @click="changeChatType(3)" style="cursor: pointer; pointer-events :none">Job</span>
        </div>
    </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
    data() {
        return {
            chatType: null,
        };
    },
    methods: {
        ...mapActions(['changeChatType']),
    },
};
</script>

<style>
h4 {
    color: #ffffff;
}

.GalaxyTab {
    padding-top: 48px;
    width: 20%;
    margin-left: 75%;
}

.AlgoTitle {
    position: relative;
    display: inline-block;
}

.AlgoTitle span.AlgoName {
    position: absolute;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
    width: 100%;
    font-size: 25px;
    color: #ffffff;
    font-weight: 900;
    text-align: center;
}

.AlgoImg {
    transition: all 1.5s ease-in-out;
}

.AlgoImg:hover {
    transform: scale(1.2);
    -webkit-transform: scale(1.1) rotateZ(720deg);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
}

.CSTitle {
    position: relative;
    display: inline-block;
}

.CSTitle span.CSName {
    position: absolute;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
    width: 100%;
    font-size: 25px;
    color: #ffffff;
    font-weight: 900;
    text-align: center;
}

.CSImg {
    transition: all 1.5s ease-in-out;
}

.CSImg:hover {
    transform: scale(1.2);
    -webkit-transform: scale(1.1) rotateZ(720deg);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
}

.JobTitle {
    position: relative;
    display: inline-block;
}

.JobTitle span.JobName {
    position: absolute;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
    width: 100%;
    font-size: 25px;
    color: #ffffff;
    font-weight: 900;
    text-align: center;
}

.JobImg {
    transition: all 1.5s ease-in-out;
}

.JobImg:hover {
    transform: scale(1.2);
    -webkit-transform: scale(1.1) rotateZ(720deg);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
}
/* 
.AlgoImg{
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out;
  
}

.AlgoImg:hover{
  transform: scale(1.2);
  -webkit-transform: scale(1.2);
  -moz-transform: scale(1.2);
  -ms-transform: scale(1.2);
  -o-transform: scale(1.2);
}

.CSTitle{
  position: relative;
  display: inline-block;
}

.CSTitle span.CSName{
  position: absolute;
  top: 50%;
  left: 0;
  transform: translateY(-50%);
  width: 100%;
  font-size: 25px;
  color: #ffffff;
  font-weight: 900;
  text-align: center;
}

.CSImg{
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 2s ease-in-out;
}

.CSImg:hover{
  transform: scale(1.2);
  -webkit-transform: scale(1.2);
  -moz-transform: scale(1.2);
  -ms-transform: scale(1.2);
  -o-transform: scale(1.2);
}

.JobTitle{
  position: relative;
  display: inline-block;
}

.JobTitle span.JobName{
  position: absolute;
  top: 50%;
  left: 0;
  transform: translateY(-50%);
  width: 100%;
  font-size: 25px;
  color: #ffffff;
  font-weight: 900;
  text-align: center;
}

.JobImg{
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 3s ease-in-out;
}

.JobImg:hover{
  transform: scale(1.2);
  -webkit-transform: scale(1.2) ;
  -moz-transform: scale(1.2);
  -ms-transform: scale(1.2);
  -o-transform: scale(1.2);
} */
</style>
