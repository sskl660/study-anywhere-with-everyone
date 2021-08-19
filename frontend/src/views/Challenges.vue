<template>
  <div class="challenges-container">

    <!-- 상단 별 데코레이션 -->
    <div class="stars-left-box">
      <img class="stars" src="../assets/manystar.png" alt="manystar">
    </div>
    <div class="challenge-list-title">
      챌린지 목록
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
        :idx="idx"
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
import { mapActions, mapGetters } from 'vuex';


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
    ...mapActions([
      'getChallengers',
    ]),
    getChallengeList: function () {
      axios({
        method: 'get',
        url: '/challengelist'
      })
        .then(res => {
          res.data.forEach(challenge => {

            axios({
              methods: 'get',
              url: `/challenge/info/${challenge.challengeNo}`,
            })
              .then(challInfo => {
                challenge.challengers = challInfo.data.challengeGroup.length
                
                let today = new Date().getTime()
                let deadline = new Date(challenge.challengeStartdate).getTime()
                let interval =  deadline - today;
                let day = Math.ceil(interval / (1000 * 60 * 60 * 24));
                let hour = Math.ceil((interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) + (day - 1) * 24 - 9;
                challenge.remainTime = hour
                
                if (challenge.challengeCategory === 'ALGO') {
                  this.algoList.push(challenge)
                  this.algoList.sort(function(a, b) {
                    return b.challengeNo - a.challengeNo
                  })
                }
                if (challenge.challengeCategory === 'CS') {
                  this.csList.push(challenge)
                  this.csList.sort(function(a, b) {
                    return b.challengeNo - a.challengeNo
                  })
                }
                if (challenge.challengeCategory === 'JOB') {
                  this.jobList.push(challenge)
                  this.jobList.sort(function(a, b) {
                    return b.challengeNo - a.challengeNo
                  })
                }
              })
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
  computed: {
    ...mapGetters([
      'challengers',
    ]),
  }
}
</script>