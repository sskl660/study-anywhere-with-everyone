<template>
  <div>
    <div class="d-flex justify-content-center feed-container">
      <div class="feed">
        <FeedListItem/>
      </div>
    </div>
  </div>
</template>

<script>
import FeedListItem from '@/components/homefeed/FeedListItem'

export default {
  name: 'ChallengeListItem',
  components: {
    FeedListItem
  }
}
</script>

<style scoped>
  .feed {
    width: 1000px;
    height: 800px;
    border-radius: 20px;
    background-color:rgba(255,255,255, 0.2);
    margin-top: 130px;
  }

  .feed-container {
    width: 1300px;
  }
</style>