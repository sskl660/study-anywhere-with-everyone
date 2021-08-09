<template>
  <div class="d-flex justify-content-between">
    <FeedList/>
    <Winners/>
  </div>
</template>

<script>
import FeedList from '@/components/homefeed/FeedList'
import Winners from '@/components/homefeed/Winners'

export default {
  name: 'HomeFeed',
  components: {
    FeedList,
    Winners,
  }
}
</script>