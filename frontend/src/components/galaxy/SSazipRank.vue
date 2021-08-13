<template>
  <div class="SSazipRank" style="display: inline-block; d-flex">
    <div class="ssazipRank-container d-flex">
        <div class="TopFive d-flex" style="display: inline-block">
            <img id="oneTop" src="~@/assets/ssazip_purple.png" alt="">
            <img id="one" src="~@/assets/onetop.png" alt="">
            <img id="oneKing" src="~@/assets/kingone.png" alt="">


            <!-- <div class="rank-star-left-one" style="color:#F9D479">1</div> -->

            <div class="rank-star-left-one">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="twoTop" src="~@/assets/ssazip_mint.png" alt="">
            <img id="two" src="~@/assets/twotop.png" alt="">
            <img id="twoKing" src="~@/assets/kingtwo.png" alt="">

            <!-- <div class="rank-star-right-one" style="color:#F9D479">1</div> -->

            <div class="rank-star-right-one">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="threeTop" src="~@/assets/ssazip_red.png" alt="">
            <img id="three" src="~@/assets/threetop.png" alt="">
            <img id="threeKing" src="~@/assets/kingthree.png" alt="">

            <!-- <div class="rank-star-left-two" style="color:#F9D479">1</div> -->

            <div class="rank-star-left-two">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="fourTop" src="~@/assets/ssazip_yellow.png" alt="">
            <img id="fourKing" src="~@/assets/kingfour.png" alt="">
            
            <!-- <div class="rank-star-right-two" style="color:#F9D479">1</div> -->

            <div class="rank-star-right-two">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>

            <img id="fiveTop" src="~@/assets/ssazip_orange.png" alt="">
            <img id="fiveKing" src="~@/assets/kingfive.png" alt="">
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SSazipRank',
  props: {
    // challTicket:{type : Array},
    // ProcessRateArr:{type : Array},
  },
  data: function () {
    return {
        
    }
  },
  components :{
    
  },
  methods:{
      printt: function(){
          console.log('안녕')
          console.log(this.challTicket)
          console.log(this.ProcessRateArr)
      }
  },
  created: function() {
      // this.printt();
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

.TopFive.d-flex{
    /* position: relative; */
    padding-top: 300px;
    margin-left: -700px;
    position: relative;
}

#oneTop{
    width: 123px;
    height: 140px;
    margin-left: 20px;
    margin-top: 30px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#twoTop{
    width: 115px;
    height: 130px;
    margin-top: -120px;
    margin-left: 60px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#threeTop{
    width: 95px;
    height: 119px;
    margin-top: 100px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fourTop{
    width: 99px;
    height: 110px;
    margin-top: -30px;
    margin-left: 65px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#fiveTop{
    width: 90px;
    height: 100px;
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

/* .rank-star-left-one {
    width: 5px;
    height: 128px;
    background-color: #f9d479;
    transform: rotate(53deg);
    position: relative;
    top: -40px;
    left: 29px;
    z-index: -1;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

.rank-star-left-two {
    transform: rotate(-40deg);
    position: relative;
    top: -40px;
    left: 32px;
    z-index: -1;
}
/* .rank-star-left-two {
    width: 5px;
    height: 120px;
    background-color: #f9d479;
    transform: rotate(53deg);
    position: relative;
    top: 30px;
    left: 32px;
    z-index: -1;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

.rank-star-right-one {
    transform: rotate(65deg);
    position: relative;
    top: -83px;
    left: -10px;
    z-index: -1;
}

/* .rank-star-right-one {
    width: 5px;
    height: 145px;
    background-color: #f9d479;
    transform: rotate(-24deg);
    position: relative;
    top: -5px;
    left: -10px;
    z-index: -1;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

.rank-star-right-two {
    transform: rotate(65deg);
    position: relative;
    top: -35px;
    left: -22px;
    z-index: -1;
}

/* .rank-star-right-two {
    width: 5px;
    height: 169px;
    background-color: #f9d479;
    transform: rotate(-24deg);
    position: relative;
    top: 63px;
    left: -10px;
    z-index: -1;
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
} */

#one{
    position: absolute;
    width: 62px;
    height: 67px;
    margin-top: 115px;
    margin-left: 65px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#two{
    position: absolute;
    width: 52px;
    height: 62px;
    margin-top: -35px;
    margin-left: 260px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
    /* z-index: 5; */
}

#three{
    position: absolute;
    width: 52px;
    height: 67px;
    margin-top: 175px;
    margin-left: 360px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#oneKing{
    position: absolute;
    width: 80px;
    height: 80px;
    margin-top: -20px;
    margin-left: 75px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

#twoKing{
    position: absolute;
    width: 65px;
    height: 65px;
    margin-top: -145px;
    margin-left: 255px;
    transform:rotate(20deg);
    filter: drop-shadow(0 0 0.75rem rgb(255, 217, 0));
}

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

ul{
  position:absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%);
  margin:0;
  padding:0;
  display:flex;
  
}
ul li{
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

ul li:nth-child(1){
  animation-delay:-1.4s;
  background:#ffff00;
  box-shadow:0 0 50px #ffff00;
  
}
ul li:nth-child(2){
  animation-delay:-1.2s;
  background:#76ff03;
  box-shadow:0 0 50px #76ff03;
  
}
ul li:nth-child(3){
  animation-delay:-1s;
  background:#f06292;
  box-shadow:0 0 50px #f06292;
  
}
ul li:nth-child(4){
  animation-delay:-0.8s;
  background:#4fc3f7;
  box-shadow:0 0 50px #4fc3f7;
  
}
ul li:nth-child(5){
  animation-delay:-0.6s;
  background:#ba68c8;
  box-shadow:0 0 50px #ba68c8;
  
}
ul li:nth-child(6){
  animation-delay:-0.4s;
  background:#f57c00;
  box-shadow:0 0 50px #f57c00;
  
}
ul li:nth-child(7){
  animation-delay:-0.2s;
  background:#673ab7;
  box-shadow:0 0 50px #673ab7;
  
}
</style>