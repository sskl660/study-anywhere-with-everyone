<template>
  <div class="challenges-container">
    <div class="stars-left-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar">
    </div>
    <div class="stars-right-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar">
    </div>
    <SearchBar/>
    <div class="d-flex justify-content-around challenges">
      <ChallengeList title="알고리즘"/>
      <ChallengeList title="CS"/>
      <ChallengeList title="취업"/>
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/common/SearchBar'
import ChallengeList from '@/components/challenges/ChallengeList'
import "@/views/css/challenges.css";

export default {
  name: 'Challenges',
  components: {
    SearchBar,
    ChallengeList,
  },
}
</script>

<style>

</style>