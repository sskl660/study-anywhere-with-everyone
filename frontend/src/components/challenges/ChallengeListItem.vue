<template>
  <div>

    <!-- 챌린지 티켓 디자인 -->
    <div class="d-flex justify-content-center">
      <div class="challenge scale" @click="moveToChallenge(challenge.challengeNo)">
        <!-- <router-link :to="{path:'/challengeRoom'}"> -->
        <div class="challenge-name">{{ challenge.challengeName }}</div>
        <div class="challenge-content challenge-level">난이도: {{ challenge.challengeLevel }}</div>
        <div class="challenge-content challenge-remain-hour">마감: 1시간 뒤</div>
        <div class="challenge-content challenge-capacity">참가인원: {{ challenge.challengeCapacity }}/10</div>
      <!-- </router-link> -->
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
    }
  },
  methods:{
    moveToChallenge: function(chall_no) {
            this.$router.push({path:'/challengeRoom',query:{cn:chall_no}});
        },
  }
}
</script>