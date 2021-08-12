<template>
  <div>
    <div class="d-flex justify-content-center feed-container">
      <div class="feed">
        <FeedListItem
          v-for="(feed, idx) in feedList" 
          :key="idx" 
          :feed="feed"
          :idx="idx"
        />
      </div>
    </div>
  </div>
</template>

<script>
import FeedListItem from '@/components/homefeed/FeedListItem'
import { mapGetters } from 'vuex';
import axios from '@/util/http-common.js';

export default {
  name: 'FeedList',
  components: {
    FeedListItem
  },
  data: function () {
    return {
      feedList: null
    }
  },
  methods: {
    getFeedList: function (useremail) {
      axios({
        method: 'get',
        url: `/feed/${useremail}`
      })
        .then(res => {
          this.feedList = res.data.reverse()
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created: function() {
    this.getFeedList(this.userEmail)
  },
  computed: {
    ...mapGetters([
      'userEmail',
    ]),
  }
}
</script>

<style scoped>
.feed {
  width: 950px;
  height: 800px;
  border-radius: 30px;
  background-color:rgba(255,255,255, 0.2);
  margin-top: 120px;
  padding-top: 80px;
  overflow-y: scroll;
}

.feed-container {
  width: 1300px;
}
</style>