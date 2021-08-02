<template>
  <div>
    <div class="challenge">
      <div class="challenge-name">{{ challenge.challenge_name }}</div>
      <div class="challenge-content challenge-level">난이도: {{ challenge.challenge_level }}</div>
      <div class="challenge-content challenge-remain-hour">마감: {{ challenge.challenge_remain_hour }}시간 뒤</div>
      <div class="challenge-content challenge-capacity">참가인원: {{ challenge.challenge_capacity }}/10</div>
    </div>
  </div>
</template>

<script>
import "@/components/css/challengelistitem.css";

export default {
  name: 'ChallengeListItem',
  props: {
    challenge: {
      type: Object
    }
  },
}
</script>

<style>

</style>