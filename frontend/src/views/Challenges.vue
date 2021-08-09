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
    <SearchBar @search="getSearchResult"/>

    <!-- 챌린지 모달 -->
    <ChallengeModal/>

    <!-- 챌린지 목록 -->
    <div class="d-flex justify-content-around challenges">
      <ChallengeList 
        v-for="(challengeList, idx) in allList"
        :key="idx"
        :challengeList="challengeList"
        />
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
      today: new Date().getTime(),
      allList: [],
      algoList: [], // 알고리즘 챌린지
      csList: [],  // CS 챌린지
      jobList: [], // 취업 챌린지
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

          this.allList.push(this.algoList)
          this.allList.push(this.csList)
          this.allList.push(this.jobList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getSearchResult: function (searchResult) {
      this.allList.splice(0);
      this.algoList.splice(0);
      this.csList.splice(0);
      this.jobList.splice(0);

      searchResult.forEach(challenge => {
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
      this.allList.push(this.algoList)
      this.allList.push(this.csList)
      this.allList.push(this.jobList)
    }
  },
  created: function () {
    this.getChallengeList()
  },
}
</script>