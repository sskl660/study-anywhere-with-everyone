<template>
  <div class="challengeticket" style="display: inline-block; d-flex">
    <div class="chall-ticket-container d-flex">
      <!-- 티켓 오른쪽 부분 -->
      <div class="chall-ticket-right" style="display: inline-block"> 
          
        <ChallengeSubmit v-if="ProcessRateArr[0]" :TaskTotalRate="challTicket[0]"/>
        <ChallengeSubmitLeft v-if="ProcessRateArr[1]" :num="this.two" :TaskTotalRate="challTicket[1]"/>
        <ChallengeSubmitRight v-if="ProcessRateArr[2]" :num="this.three" :TaskTotalRate="challTicket[2]"/>
        <ChallengeSubmitLeft v-if="ProcessRateArr[3]" :num="this.four" :TaskTotalRate="challTicket[3]"/>
        <ChallengeSubmitRight v-if="ProcessRateArr[4]" :num="this.five" :TaskTotalRate="challTicket[4]"/>
        <ChallengeSubmitLeft v-if="ProcessRateArr[5]" :num="this.six" :TaskTotalRate="challTicket[5]"/>
        <ChallengeSubmitRight v-if="ProcessRateArr[6]" :num="this.seven" :TaskTotalRate="challTicket[6]"/>
      </div>
      <span id="ment" style="font-weight:bold;">모든 사람이 해당 과제를 제출해야 도장이 찍힙니다😉</span>
      <!-- {{challTicket[0].achieveRate}} -->
    </div>
  </div>
</template>

<script>
import ChallengeSubmit from "@/components/challengeroom/ChallengeSubmit.vue";
import ChallengeSubmitLeft from "@/components/challengeroom/ChallengeSubmitLeft.vue";
import ChallengeSubmitRight from "@/components/challengeroom/ChallengeSubmitRight.vue";

export default {
  name: 'ChallengeTicket',
  props: {
    challTicket:{type : Array},
    ProcessRateArr:{type : Array},
  },
  data: function () {
    return {
        two: "2",
        three: "3",
        four: "4",
        five: "5",
        six: "6",
        seven: "7",
    }
  },
  components :{
    ChallengeSubmit,
    ChallengeSubmitLeft,
    ChallengeSubmitRight,
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
.challengeticket {
  background-image: url(~@/assets/ticketbody.png);
  margin-top: 15px;
  width:740px;
  height: 249px;
}

.chall-ticket-right{
  width:1000px; 
  height:250px;
  margin-left: 10px;
  margin-top:45px;
}

#ment{
  color: white;
  font-size: 16px;
  position: absolute;
  margin-top: 240px;
  margin-left: 360px;
  position: fixed;
}
</style>