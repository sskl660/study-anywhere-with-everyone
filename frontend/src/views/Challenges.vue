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
      <ChallengeList 
        title="알고리즘"
        v-for="(idx, challenge) in algoList"
        :key="idx"
        :challenge="challenge"/>
      <ChallengeList 
        title="CS"
        v-for="(idx, challenge) in csList"
        :key="idx"
        :challenge="challenge"/>
      <ChallengeList 
        title="취업"
        v-for="(idx, challenge) in csList"
        :key="idx"
        :challenge="challenge"/>
    </div>

  </div>
</template>

<script>
import SearchBar from '@/components/challenges/SearchBar'
import ChallengeList from '@/components/challenges/ChallengeList'
import ChallengeModal from '@/components/challenges/ChallengeModal'
import "@/views/css/challenges.css";
import axios from '@/util/http-common.js';

export default {
  name: 'Challenges',
  components: {
    SearchBar, // 검색바
    ChallengeList, // 챌린지 목록
    ChallengeModal, //챌린지 모달
  },
  data: function () {
    return {
      algoList: [],
      csList: [],
      jobList: [],
    }
  },
  methods: {
    getChallengeList: function () {
      axios({
        method: 'get',
        url: '/challengelist'
      })
        .then(res => {
          res.data.forEach(challenge => {
            if (challenge.challengeCategory === 'ALGO') {
              this.algoList.push(challenge)
            }
            if (challenge.challengeCategory === 'CS') {
              this.csList.push(challenge)
            }
            if (challenge.challengeCategory === 'JOB') {
              this.jobList.push(challenge)
            }
          })
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created: function () {
    this.getChallengeList()
  }
}
</script>