<template>
  <div>

    <!-- 챌린지 목록 제목 -->
    <div class="challenge-title-container">
      <div class="challenge-title">{{ title }}</div>
    </div>

    <!-- 챌린지 티켓들 -->
    <div class="challenge-list-container">
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
      <div class="d-flex justify-content-center">
        <ChallengeListItem :challenge="challenge"/>
      </div>
    </div>
    
  </div>
</template>

<script>
import ChallengeListItem from '@/components/challenges/ChallengeListItem'
import "@/components/css/challenges/challengelist.css";

export default {
  name: 'ChallengeList',
  props: {
    title: {
      type: String
    }
  },
  components: {
    ChallengeListItem
  },
  data: function () {
    return {
        challenge: {
        challenge_name: '희오니의 알고 10문제 챌린지',
        challenge_level: 3,
        challenge_remain_hour: 1,
        challenge_capacity: 2
      }
    }
  }
}
</script>

<style scoped>
  
</style>