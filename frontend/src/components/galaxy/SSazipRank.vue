<template>
  <div class="SSazipRank" style="display: inline-block; d-flex">
    <div class="ssazipRank-container d-flex">
        <div class="TopFive d-flex" style="display: inline-block">
            <img id="oneTop" src="~@/assets/topone.png" alt="">
            <!-- <img id="one" src="~@/assets/onetop.png" alt="">
            <img id="oneKing" src="~@/assets/kingone.png" alt=""> -->

            <div class="rank-star-left-one">
                <ul id="shiny">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="twoTop" src="~@/assets/toptwo.png" alt="">
            <!-- <img id="two" src="~@/assets/twotop.png" alt="">
            <img id="twoKing" src="~@/assets/kingtwo.png" alt=""> -->

            <div class="rank-star-right-one">
                <ul id="shiny">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="threeTop" src="~@/assets/topthree.png" alt="">
            <!-- <img id="three" src="~@/assets/threetop.png" alt=""> -->
            <!-- <img id="threeKing" src="~@/assets/kingthree.png" alt=""> -->

            <div class="rank-star-left-two">
                <ul id="shiny">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="fourTop" src="~@/assets/topfour.png" alt="">
            <!-- <img id="fourKing" src="~@/assets/kingfour.png" alt=""> -->

            <div class="rank-star-right-two">
                <ul id="shiny">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="fiveTop" src="~@/assets/topfive.png" alt="">
            <!-- <img id="fiveKing" src="~@/assets/kingfive.png" alt=""> -->
        </div>
        <div>
            <!-- {{this.ranker[0].enterTime.substring(5,7)}}월
                    {{this.ranker[0].enterTime.substring(8,10)}}일
                    {{this.ranker[0].enterTime.substring(11,13)}}시
                    {{this.ranker[0].enterTime.substring(14,16)}}분
                    {{this.ranker[0].enterTime.substring(17,19)}}초 -->
            <div v-if="ranker[0]">
                <span id="firstTime">
                    {{this.ranker[0].enterTime.substring(5,7)}}월
                    {{this.ranker[0].enterTime.substring(8,10)}}일
                    {{this.ranker[0].enterTime.substring(11,13)}}시
                    {{this.ranker[0].enterTime.substring(14,16)}}분
                    {{this.ranker[0].enterTime.substring(17,19)}}초 입장
                </span>
                <span id="first">{{this.ranker[0].partName}}</span>
            </div>
            <div v-if="ranker[1]">
                <span id="secondTime">
                    {{this.ranker[1].enterTime.substring(5,7)}}월
                    {{this.ranker[1].enterTime.substring(8,10)}}일
                    {{this.ranker[1].enterTime.substring(11,13)}}시
                    {{this.ranker[1].enterTime.substring(14,16)}}분
                    {{this.ranker[1].enterTime.substring(17,19)}}초 입장
                </span>
                <span id="second" >{{this.ranker[1].partName}}</span>
            </div>
            <div v-if="ranker[2]">
                <span id="thirdTime">
                    {{this.ranker[2].enterTime.substring(5,7)}}월
                    {{this.ranker[2].enterTime.substring(8,10)}}일
                    {{this.ranker[2].enterTime.substring(11,13)}}시
                    {{this.ranker[2].enterTime.substring(14,16)}}분
                    {{this.ranker[2].enterTime.substring(17,19)}}초 입장
                </span>
                <span id="third">{{this.ranker[2].partName}}</span>
            </div>
            <div v-if="ranker[3]">
                <span id="forthTime">
                    {{this.ranker[3].enterTime.substring(5,7)}}월
                    {{this.ranker[3].enterTime.substring(8,10)}}일
                    {{this.ranker[3].enterTime.substring(11,13)}}시
                    {{this.ranker[3].enterTime.substring(14,16)}}분
                    {{this.ranker[3].enterTime.substring(17,19)}}초 입장
                </span>
                <span id="forth" >{{this.ranker[3].partName}}</span>
            </div>
            <div v-if="ranker[4]">
                <span id="fifthTime">
                    {{this.ranker[4].enterTime.substring(5,7)}}월
                    {{this.ranker[4].enterTime.substring(8,10)}}일
                    {{this.ranker[4].enterTime.substring(11,13)}}시
                    {{this.ranker[4].enterTime.substring(14,16)}}분
                    {{this.ranker[4].enterTime.substring(17,19)}}초 입장
                </span>
                <span id="fifth" >{{this.ranker[4].partName}}</span>
            </div>
        </div>
    </div>
  </div>
</template>

<script scoped>
export default {
  name: 'SSazipRank',
  props: {
    ranker:{
        type : Array
    }
  },
  data: function () {
    return {
        
    }
  },
  methods:{
      printt: function(){
        //   console.log('여기 확인 좀 해주소')
        //   console.log(this.ranker[1].enterTime.substring(5,19))
      }
  },
  created: function() {
      this.printt();
  },
}
</script>

<style>
/* .SSazipRank {
  margin-top: 15px;
  width:740px;
  height: 249px;
}

.ssazipRank{
  width:1000px; 
  height:250px;
  margin-left: 10px;
  margin-top:45px;
} */

/* galaxy.vue에서 컴포넌트 만들때 */
/* .TopFive.d-flex{
    padding-top: 300px;
    margin-left: -700px;
    position: relative;
} */

.TopFive.d-flex{
    padding-top: 125px;
    margin-left: -1400px;
    position: relative;
}

#oneTop{
    width: 125px;
    height: 180px;
    margin-left: 20px;
    margin-top: 30px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#twoTop{
    width: 117px;
    height: 150px;
    /* margin-top: -120px;
    margin-left: 60px; */
    margin-top: -140px;
    margin-left: 80px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#threeTop{
    /* width: 95px;
    height: 119px;
    margin-top: 100px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0)); */
    width: 97px;
    height: 135px;
    margin-top: 100px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fourTop{
    width: 90px;
    height: 110px;
    margin-top: -25px;
    margin-left: 68px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fiveTop{
    width: 85px;
    height: 102px;
    margin-top: 190px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

.rank-star-left-one {
    transform: rotate(-40deg);
    position: relative;
    top: -100px;
    left: 29px;
    z-index: -1;
}

.rank-star-left-two {
    transform: rotate(-40deg);
    position: relative;
    top: -40px;
    left: 32px;
    z-index: -1;
}

.rank-star-right-one {
    transform: rotate(65deg);
    position: relative;
    top: -83px;
    left: -10px;
    z-index: -1;
}

.rank-star-right-two {
    transform: rotate(65deg);
    position: relative;
    top: -15px;
    left: -15px;
    z-index: -1;
}

/* #one{
    position: absolute;
    width: 62px;
    height: 67px;
    margin-top: 115px;
    margin-left: 65px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

/* #two{
    position: absolute;
    width: 52px;
    height: 62px;
    margin-top: -35px;
    margin-left: 260px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

#three{
    position: absolute;
    width: 52px;
    height: 67px;
    margin-top: 175px;
    margin-left: 360px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

/* #oneKing{
    position: absolute;
    width: 80px;
    height: 80px;
    margin-top: -20px;
    margin-left: 75px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */
/* 
#twoKing{
    position: absolute;
    width: 65px;
    height: 65px;
    margin-top: -145px;
    margin-left: 255px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

#threeKing{
    position: absolute;
    width: 60px;
    height: 45px;
    margin-top: 81px;
    margin-left: 356px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fourKing{
    position: absolute;
    width: 60px;
    height: 30px;
    margin-top: -37px;
    margin-left: 516px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fiveKing{
    position: absolute;
    width: 40px;
    height: 20px;
    margin-top: 195px;
    margin-left: 617px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#shiny {
  position:absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  margin:0;
  padding:0;
  display:flex;
  
}
#shiny li{
  list-style:none;
  width:40px;
  height:40px;
  background:#fff;
  border-radius:50%;
  animation:grow 1.6s ease-in-out infinite;
}
  @keyframes grow
  {
    0% , 40% , 100%
    {
      transform:scale(0.1);
    }
    20%{
       transform:scale(0.3);

    }
  }

#shiny li:nth-child(1){
  animation-delay:-1.4s;
  background:#ffff00;
  box-shadow:0 0 50px #ffff00;
  
}
#shiny li:nth-child(2){
  animation-delay:-1.2s;
  background:#76ff03;
  box-shadow:0 0 50px #76ff03;
  
}
#shiny li:nth-child(3){
  animation-delay:-1s;
  background:#f06292;
  box-shadow:0 0 50px #f06292;
  
}
#shiny li:nth-child(4){
  animation-delay:-0.8s;
  background:#4fc3f7;
  box-shadow:0 0 50px #4fc3f7;
  
}
#shiny li:nth-child(5){
  animation-delay:-0.6s;
  background:#ba68c8;
  box-shadow:0 0 50px #ba68c8;
  
}
#shiny li:nth-child(6){
  animation-delay:-0.4s;
  background:#f57c00;
  box-shadow:0 0 50px #f57c00;
  
}
#shiny li:nth-child(7){
  animation-delay:-0.2s;
  background:#673ab7;
  box-shadow:0 0 50px #673ab7;
  
}

#first{
    /* position: absolute;
    margin-left: -645px;
    margin-top: 285px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0)); */
    position: absolute;
    margin-left: -655px;
    margin-top: 324px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#second{
    /* position: absolute;
    margin-left: -440px;
    margin-top: 135px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0)); */
    position: absolute;
    margin-left: -440px;
    margin-top: 130px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#third{
    position: absolute;
    margin-left: -345px;
    margin-top: 353px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#forth{
    position: absolute;
    margin-left: -176px;
    margin-top: 205px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fifth{
    position: absolute;
    margin-left: -90px;
    margin-top: 410px;
    transform:rotate(20deg);
    font-weight: bold;
    color: white;
    font-size: 20px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#firstTime{
    position: absolute;
    /* margin-left: -655px; 
    margin-top: 360px; */
    margin-left: -720px;
    margin-top: 350px;
    transform:rotate(20deg);
    color: white;
    font-size: 15px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#secondTime{
    position: absolute;
    margin-left: -512px;
    margin-top: 156px;
    transform:rotate(20deg);
    color: white;
    font-size: 15px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#thirdTime{
    position: absolute;
    margin-left: -414px;
    margin-top: 380px;
    transform:rotate(20deg);
    color: white;
    font-size: 15px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#forthTime{
    position: absolute;
    margin-left: -250px;
    margin-top: 230px;
    transform:rotate(20deg);
    color: white;
    font-size: 15px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fifthTime{
    position: absolute;
    margin-left: -160px;
    margin-top: 435px;
    transform:rotate(20deg);
    color: white;
    font-size: 15px;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}
</style>