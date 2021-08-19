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
        <div v-if="challenge.remainTime <= 0 || challenge.challengers >= challenge.challengeCapacity" class="challenge-content challenge-end">참여가 마감되었습니다.</div>
        <div v-else class="challenge-content challenge-remain-hour">참여마감 : {{ challenge.remainTime }}시간 뒤</div>
        <div class="challenge-content challenge-capacity">참가인원 : {{ challenge.challengers }}/{{ challenge.challengeCapacity }}</div>
      </div>
    </div>
    
    
  </div>
</template>

<script>
import "@/components/css/challenges/challengelistitem.css";

export default {
  name: 'ChallengeListItem',
  props: {
    challenge: {
      type: Object
    },
    idx: {
      type: Number,
    }
  },
  methods: {
    moveToChallenge: function(chall_no) {
      this.$router.push({path:'/challengeRoom',query:{cn:chall_no}});
    },
  },
}
</script>