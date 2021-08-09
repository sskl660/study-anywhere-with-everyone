<template>
  <div class="d-flex justify-content-center">

    <!-- 검색바 -->
    <input class="search-bar" 
      type="text" 
      placeholder="챌린지명이나 참여자를 검색할 수 있습니다."
      onfocus="this.placeholder=''" 
      onblur="this.placeholder='챌린지명이나 참여자를 검색할 수 있습니다.'"
      v-model="keyword"
      @keyup.enter="onSearch(keyword)"
      >

    <!-- 검색 버튼 -->
    <div class="search-box">
      <button class="search-button" @click="getSearchResult(keyword)">
        <img class="search-icon" src="../../assets/searchicon.png" alt="search-icon">
      </button>
    </div>
    
  </div>
</template>

<script>
import "@/components/css/challenges/searchbar.css";
import axios from '@/util/http-common.js';

export default {
  name: 'SearchBar',
  data: function () {
    return {
      keyword: ''
    }
  },
  methods: {
    onSearch: function (keyword) {
      axios({
        method: 'get',
        url: `/challengelist/${keyword}`
      })
        .then(res => {
          this.$emit('search', res.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
    test: function () {
      console.log(1)
    },
  }
}
</script>