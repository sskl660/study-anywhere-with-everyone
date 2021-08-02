<template>
  <div>
    <input class="search-bar" type="text" placeholder="챌린지명이나 참여자를 검색할 수 있습니다.">
    <button class="search-button">
      <img class="search-icon" src="../assets/searchicon.png" alt="search-icon">
    </button>
  </div>
</template>

<script>
import "@/components/css/searchbar.css";

export default {
  name: 'SearchBar',
}
</script>

<style>

</style>