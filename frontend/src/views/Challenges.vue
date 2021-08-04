<template>
  <div class="challenges-container">

    <!-- 상단 별 데코레이션 -->
    <div class="stars-left-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar">
    </div>
    <div class="stars-right-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar">
    </div>

    <!-- 검색바 -->
    <SearchBar/>

    <!-- 챌린지 모달 -->
    <ChallengeModal/>

    <!-- 챌린지 목록 -->
    <div class="d-flex justify-content-around challenges">
      <ChallengeList title="알고리즘"/>
      <ChallengeList title="CS"/>
      <ChallengeList title="취업"/>
    </div>\

  </div>
</template>

<script>
import SearchBar from '@/components/challenges/SearchBar'
import ChallengeList from '@/components/challenges/ChallengeList'
import ChallengeModal from '@/components/challenges/ChallengeModal'
import "@/views/css/challenges.css";

export default {
  name: 'Challenges',
  components: {
    SearchBar, // 검색바
    ChallengeList, // 챌린지 목록
    ChallengeModal, //챌린지 모달
  },
}
</script>

<style>

</style>