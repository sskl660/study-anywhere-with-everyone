<template>
  <div>
    <div class="d-flex justify-content-center feed-container">
      <div class="feed">

        <div v-if="following === 0 && follower === 0">
          <div class="HomeFeedTutorial">
            <img id="ssaziptuto" src="~@/assets/ssazip.png" alt="">
            <h1 style="color: white">SSA.zip에 오신 것을 환영합니다!</h1>
            <br><br>
            <div id="hometuto">
              <img id="homefeedimg" src="~@/assets/homenavi.png" alt="">
              <h5 style="text-align:left">
                여기는 <strong style="color:#1C84C4; font-size:1.1rem">홈피드</strong> 입니다!<br>
                친구를 팔로우 한다면, 팔로워의 <strong style="color:#1C84C4; font-size:1.1rem">다양한 소식들</strong>을 볼 수 있습니다!<br>
                그럼 스터디 메이트를 찾으러 가보실까요? <br>
              </h5>
            </div>
            <br><br>
            <div id="hometuto">
              <img id="homefeedimg" src="~@/assets/profilenavi.png" alt="">
              <h5 style="text-align:left">
                여기는 당신의 <strong style="color:#1C84C4; font-size:1.1rem">프로필</strong>입니다!<br>
                내가 참여한 챌린지들과 <strong style="color:#1C84C4; font-size:1.1rem">각 챌린지 별 과제 제출 현황</strong>도 한눈에 볼 수 있어요.<br>
                이제 진짜 스터디 메이트를 찾으러 가봅시다! <br>
              </h5>
            </div>
            <br><br>
            <div id="hometuto">
              <img id="homefeedimg" src="~@/assets/challengenavi.png" alt="">
              <h5 style="text-align:left">
                알고리즘, CS, 취업 등 다양한 <strong style="color:#1C84C4; font-size:1.1rem">챌린지</strong>를 만들 수 있고 참여할 수 있습니다!<br>
                오른쪽 아래 플러스 버튼을 누르면 원하는 챌린지도 직접 만들 수 있어요~<br>
                관심 있는 챌린지에 참여하시고 <strong style="color:#1C84C4; font-size:1.1rem">참여하고 있는 친구들의 프로필에 놀러 가보세요</strong>.<br>
              </h5>
            </div>
            <br><br>
            <div id="hometuto">
              <img id="homefeedimg" src="~@/assets/galaxynavi.png" alt="">
              <h5 style="text-align:left">
                <strong style="color:#1C84C4; font-size:1.1rem">우리 모두가 만드는 갤럭시방 입니다! 각 지역 친구들을 만나고 팔로우 할 수 있어요!</strong><br>
                <strong style="color:#1C84C4; font-size:1.1rem">채팅</strong>하면서 모르는 것도 물어보고 담소도 나누면서 재밌게 공부합시다~<br>
                열심히 공부해서 <strong style="color:#1C84C4; font-size:1.1rem">갤럭시 랭킹 Top 5</strong> 안에 들어가 봅시다!<br>
              </h5>
            </div>
          </div>
        </div>
        
        <div v-else>
          <FeedListItem
            v-for="(feed, idx) in feedList" 
            :key="idx" 
            :feed="feed"
            :idx="idx"
          />
        </div>
        
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
      feedList: []
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
      'following',
      'follower'
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
  margin-top: 110px;
  padding-top: 80px;
  overflow-y: scroll;
}

.feed-container {
  width: 1300px;
}

h5 {
  font-family: 'NanumSquareRound';
    font-weight: bold;
    padding-top: 17px;
    font-size: 1rem;
    line-height: inherit;
}

#hometuto{
  background-color: white;
  margin-left: 65px;
  width: 820px;
  height: 109px;
  border-radius: 30px;
  padding-left: 130px;
}

.HomeFeedTutorial{
  margin-top: -2%;
}

#ssaziptuto{
  width: 60px;
  height: 72px;
  position: absolute;
  margin-top: -20px;
  margin-left: 277px;
}

#homefeedimg{
  width: 60px;
  height: 60px;
  position: absolute;
  margin-top: 24px;
  margin-left: -440px;
}
</style>