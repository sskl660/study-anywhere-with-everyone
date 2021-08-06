<template>
  <div class="profileticket" style="display: inline-block; d-flex">
    <div class="ticket-container d-flex">
      <!-- 티켓 오른쪽 부분 -->
      <div class="ticket-right" style="display: inline-block"> 
        <!-- 첫번째꺼 -->
        <TicketSubmit v-if="this.ticket.taskNo[0]" :ticketTask="ticket.taskNo[0]"/>
        <!-- 대각선과 위 1->2 -->
        <TicketSubmitLeft v-if="this.ticket.taskNo[1]" :num="this.two" :ticketTask="ticket.taskNo[1]"/>
        <!-- 대각선과 2->3 -->
        <TicketSubmitRight v-if="this.ticket.taskNo[2]" :num="this.three" :ticketTask="ticket.taskNo[2]"/>
        <TicketSubmitLeft v-if="this.ticket.taskNo[3]" :num="this.four" :ticketTask="ticket.taskNo[3]"/>
        <TicketSubmitRight v-if="this.ticket.taskNo[4]" :num="this.five" :ticketTask="ticket.taskNo[4]"/>
        <TicketSubmitLeft v-if="this.ticket.taskNo[5]" :num="this.six" :ticketTask="ticket.taskNo[5]"/>
        <TicketSubmitRight v-if="this.ticket.taskNo[6]" :num="this.seven" :ticketTask="ticket.taskNo[6]"/>
      </div>
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
    ticket: Object,
  },
  data: function () {
    return {
    //   task_ticket:[
    //       {
    //         achieveRate: 0,
    //         inProgress: true
    //       }
    //   ],
      task_ticket:[
          {
            achieveRate: 88, //첫번째 과제 88프로 달성
            inProgress: true // 아직 진행중인 챌린지 -> 회색
          },
          {
            achieveRate: 100,
            inProgress: true
          },
          {
            achieveRate: 12,
            inProgress: false
          },
          {
            achieveRate: 8,
            inProgress: false
          },
          {
            achieveRate: 100,
            inProgress: true
          },
          {
            achieveRate: 8,
            inProgress: true
          }
      ]
    }
  },
  components :{
    ChallengeSubmit,
    ChallengeSubmitLeft,
    ChallengeSubmitRight,
  },
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


</style>