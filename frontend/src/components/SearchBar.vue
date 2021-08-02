<template>
  <div class="d-flex justify-content-center">
    <input class="search-bar" type="text" placeholder="챌린지명이나 참여자를 검색할 수 있습니다.">
    <div class="search-box">
      <button class="search-button">
        <img class="search-icon" src="../assets/searchicon.png" alt="search-icon">
      </button>
    </div>
    
  </div>
</template>

<script>
import "@/components/css/searchbar.css";

export default {
  name: 'SearchBar',
}
</script>

<style scoped>

</style>