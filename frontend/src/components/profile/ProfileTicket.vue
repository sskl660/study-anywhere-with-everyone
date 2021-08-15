<template>
  <div class="profileticket profile-ticket-scale" style="display: inline-block; d-flex">
    <div class="ticket-container d-flex" @click="profileToChallenge()">
      <div class="ticket-left" style="display: inline-block">
        <div class="ticket-title">{{this.ticket.challengeName}}</div>
        <div class="ticket-rate">{{this.ticket.achieveRate}}%</div>
      </div>
      <!-- 티켓 오른쪽 부분 -->
      <div class="ticket-right" style="display: inline-block"> 
        <!-- 첫번째꺼 -->
        <TicketSubmit v-if="this.ticket.taskNo[0]" :num="this.one" :ticketTask="ticket.taskNo[0]"/>
        <!-- 대각선과 위 1->2 -->
        <TicketSubmitLeft v-if="this.ticket.taskNo[1]" :num="this.two" :ticketTask="ticket.taskNo[1]"/>
        <!-- 대각선과 2->3 -->
        <TicketSubmitRight v-if="this.ticket.taskNo[2]" :num="this.three" :ticketTask="ticket.taskNo[2]"/>
        <TicketSubmitLeft v-if="this.ticket.taskNo[3]" :num="this.four" :ticketTask="ticket.taskNo[3]"/>
        <TicketSubmitRight v-if="this.ticket.taskNo[4]" :num="this.five" :ticketTask="ticket.taskNo[4]"/>
        <TicketSubmitLeft v-if="this.ticket.taskNo[5]" :num="this.six" :ticketTask="ticket.taskNo[5]"/>
        <TicketSubmitRight v-if="this.ticket.taskNo[6]" :num="this.seven" :ticketTask="ticket.taskNo[6]"/>
      </div>
      <div v-if="this.ticket.isComplete">
        <img v-if="this.checkExcellent()" class="stamp" style="float:right" src="@/assets/excellent.png" alt="">
        <img v-if="this.checkTryAgain()" class="stamp" style="float:right" src="@/assets/try.png" alt="">
        <img v-if="this.checkAwesome()" class="stamp" style="float:right" src="@/assets/awesome.png" alt="">
      </div>
    </div>
    
  </div>
</template>

<script>
import TicketSubmit from "@/components/profile/TicketSubmit.vue";
import TicketSubmitLeft from "@/components/profile/TicketSubmitLeft.vue";
import TicketSubmitRight from "@/components/profile/TicketSubmitRight.vue";

export default {
  name: 'ProfileTicket',
  props: {
    ticket: Object,
  },
  data: function () {
    return {
      one: 1,
      two: 2,
      three: 3,
      four: 4,
      five: 5,
      six: 6,
      seven: 7,
      excellent: false,
      asewome: false,
      tryagain:false,
    }
  },
  components :{
    TicketSubmit,
    TicketSubmitLeft,
    TicketSubmitRight,
  },
  methods: {
    checkExcellent: function () {
      if (this.ticket.achieveRate == 100) return true;
      else false
    },
    checkTryAgain: function () {
      if (this.ticket.achieveRate == 0) return true;
      else false
    },
    checkAwesome: function () {
      if (100> this.ticket.achieveRate && this.ticket.achieveRate>0) return true;
      else false
    },
    checkFinish: function () {
      for (var task in this.ticket.taskNo) {
        if (task==-2) return false
        else true
      }
    },
    profileToChallenge: function() {
      this.$router.push({path:'/challengeRoom',query:{cn:this.ticket.challengeNo}});
    }
  }
}
</script>

<style>
.profileticket {
  background-image: url(~@/assets/ProfileTicket.png);
  width:1280px; 
  height:400px;
}
.ticket-container {
  position: relative;
  width:1150px; 
  height:300px;
  margin-top: 40px;
  margin-left: 50px;
  /* background-color: rgb(225, 0, 255); */
}
.ticket-left{
  width:210px; 
  height:280px;
}
.ticket-right{
  width:870px; 
  height:250px;
  margin-left: 80px;
  margin-top:30px;
}
.ticket-title {
  color: #2B3C64;
  font-weight: 900;
  font-size: 21px;
  height: 100px;
  margin-top: 48px;

}
.ticket-rate {
  color: #FFF;
  font-weight: 900;
  font-size: 40px;
}
.stamp {
  position: absolute;
  right: -80px;
  bottom: -80px;
  width:400px;
  height: 330px;
}
.profile-ticket-scale {
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out;
  cursor: pointer;
}

.profile-ticket-scale:hover {
  transform: scale(1.05);
  -webkit-transform: scale(1.);
  -moz-transform: scale(1.05);
  -ms-transform: scale(1.05);
  -o-transform: scale(1.05);
}
</style>