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
            const startDate = new Date(challenge.challengeStartdate)
            let interval =  startDate - this.today;
            let day = Math.ceil(interval / (1000 * 60 * 60 * 24));
            let hour = Math.ceil((interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) + (day - 1) * 24 - 9;

            if (0 < hour) {
              if (challenge.challengeCategory === 'ALGO') {
                this.algoList.push(challenge)
              }
              if (challenge.challengeCategory === 'CS') {
                this.csList.push(challenge)
              }
              if (challenge.challengeCategory === 'JOB') {
                this.jobList.push(challenge)
              }
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
  },
  created: function () {
    this.getChallengeList()
  },
}
</script>