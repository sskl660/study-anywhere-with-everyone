<template>
  <div>
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
import SearchBar from '@/components/SearchBar'
import ChallengeList from '@/components/ChallengeList'
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