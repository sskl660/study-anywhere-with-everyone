<template>
  <div>

    <!-- 챌린지 티켓 디자인 -->
    <div class="d-flex justify-content-center">
      <div class="challenge scale" @click="moveToChallenge(challenge.challengeNo)">
        <div class="challenge-name">{{ challenge.challengeName }}</div>
        <div class="challenge-content challenge-level">
          난이도 : 
          <span class="challenge-star" v-for="level in challenge.challengeLevel" :key="level">
            ★
          </span>
        </div>
        <div v-if="hour <= 0" class="challenge-content challenge-end">참여가 마감되었습니다.</div>
        <div v-else class="challenge-content challenge-remain-hour">참여마감 : {{ hour }}시간 뒤</div>
        <div class="challenge-content challenge-capacity">참가인원 : {{ challenge.challengers }}/{{ challenge.challengeCapacity }}</div>
      </div>
    </div>
    
    
  </div>
</template>

<script>
import "@/components/css/challenges/challengelistitem.css";
import { mapGetters } from 'vuex';

export default {
  name: 'ChallengeListItem',
  props: {
    challenge: {
      type: Object
    }
  },
  data: function () {
    return {
      today: new Date().getTime(),
      deadline: new Date(this.challenge.challengeStartdate).getTime(),
      hour: null
    }
  },
  methods: {
    remainTime: function () {
      let interval =  this.deadline - this.today;
      let day = Math.ceil(interval / (1000 * 60 * 60 * 24));
      this.hour = Math.ceil((interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) + (day - 1) * 24 - 9;
    },
    moveToChallenge: function(chall_no) {
      this.$router.push({path:'/challengeRoom',query:{cn:chall_no}});
    },
  },
  created: function () {
    this.remainTime()
  },
  computed: {
    ...mapGetters(['challengers']),
  }
}
</script>