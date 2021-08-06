<template>
  <div class="d-flex justify-content-center">

    <!-- 검색바 -->
    <input class="search-bar" type="text" placeholder="챌린지명이나 참여자를 검색할 수 있습니다."
      onfocus="this.placeholder=''" onblur="this.placeholder='챌린지명이나 참여자를 검색할 수 있습니다.'">

    <!-- 검색 버튼 -->
    <div class="search-box">
      <button class="search-button">
        <img class="search-icon" src="../../assets/searchicon.png" alt="search-icon">
      </button>
    </div>
    
  </div>
</template>

<script>
import "@/components/css/common/searchbar.css";

export default {
  name: 'SearchBar',
}
</script>