<template>
  <div>
    <!-- 환영인사 -->
    <div class="greeting">{{userName}}님 환영합니다</div>
    <div class="d-flex justify-content-between homefeed">
      <FeedList/>
      <Winners/>
    </div>  
  </div>
</template>

<script>
import FeedList from '@/components/homefeed/FeedList'
import Winners from '@/components/homefeed/Winners'
import { mapGetters } from 'vuex';

export default {
  name: 'HomeFeed',
  components: {
    FeedList,
    Winners,
  },
  computed: {
    ...mapGetters(['userName']),
  }
}

</script>
<style scoped>
.greeting {
  position:fixed;
  top: 35px;
  width: 300px;
  height: 50px;
  background-color:rgba(255,255,255, 0.2);
  color:white; 
  font-size:25px;
  line-height:50px;
  border-radius: 1rem;
  margin-left:124px;
}
</style>
