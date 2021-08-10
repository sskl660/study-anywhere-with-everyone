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
import { mapGetters } from 'vuex';
import axios from '@/util/http-common.js';

export default {
  name: 'FeedList',
  components: {
    FeedListItem
  },
  methods: {
    getFeedList: function (useremail) {
      axios({
        method: 'get',
        url: `/feed/${useremail}`
      })
        .then(res => {
          console.log('피드 불러오기')
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created: function() {
    console.log(this.userEmail)
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