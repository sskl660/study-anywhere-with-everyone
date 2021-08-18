<template>
  <div>

    <!-- 1. 챌린지 도전 소식 -->
    <div v-if="feed.feedType === 1">
      <div class="d-flex justify-content-center feed">
        <div>

          <!-- 챌린지 도전 상단 -->
          <div class="d-flex justify-content-start">
            <img :id=" 'my-image' + idx " class="profile-img-default" src="" alt="" >
            <div class="feed-content">
              <div class="feed-message"><span class="fw-bold follower-name" @click="moveToProfile(feed.userEmail)">{{ feed.userName }}</span>님이 챌린지에 도전하였습니다!</div>
              <div class="feed-time">{{ computedEventtime }}</div>
            </div>
          </div>

          <!-- 챌린지 도전 하단 -->
          <div class="feed-description d-flex align-items-center">
            <div class="d-flex">
              <div>
                <div class="challenge-name-box">{{ feed.challengeName }}</div>
                <div class="challenge-description">{{ feed.challengeDesc }}</div>
                <div class="d-flex">
                  <div class="challenge-member">{{ feed.challengeMemberCnt }}명 참여</div>
                  <div v-if="0 < remainTime" class="challenge-countdown">참여 마감까지 {{ remainTime }}시간</div>
                  <div v-else class="challenge-end">참여 마감</div>
                </div>
              </div>
              <div class="d-flex align-items-center">
                <div class="challenge-btn" @click="moveToChallenge(feed.challengeNo)">
                  <ButtonSquare text="챌린지 가기"/>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- 2. 챌린지 성공 소식 -->
    <div v-if="feed.feedType === 2">
      <div class="d-flex justify-content-center feed">
        <div>

          <!-- 챌린지 성공 상단 -->
          <div class="d-flex justify-content-start">
            <img :id=" 'my-image' + idx " class="profile-img-default" src="" alt="" >
            <div class="feed-content">
              <div class="feed-message"><span class="fw-bold follower-name" @click="moveToProfile(feed.userEmail)">{{ feed.userName }}</span>님이 챌린지를 성공하였습니다!</div>
              <div class="feed-time">{{ computedEventtime }}</div>
            </div>
          </div>
          
          <!-- 챌린지 성공 하단 -->
          <div class="feed-description d-flex align-items-center">
            <div class="d-flex">
               <div>
                <div class="challenge-name-box">{{ feed.challengeName }}</div>
                <div class="challenge-description">{{ feed.challengeDesc }}</div>
                <div class="d-flex">
                  <div class="challenge-member">{{ feed.challengeMemberCnt }}명 참여</div>
                  <div class="challenge-end">참여 마감</div>
                </div>
              </div>
              <div class="d-flex align-items-center">
                <div class="challenge-btn" @click="moveToChallenge(feed.challengeNo)">
                  <ButtonSquare text="챌린지 가기"/>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- 3. 갤럭시방 참여 소식 -->
    <div v-if="feed.feedType === 3">
      <div class="d-flex justify-content-center feed">
        <div>

          <!-- 갤럭시방 참여 상단 -->
          <div class="d-flex justify-content-start">
            <img :id=" 'my-image' + idx " class="profile-img-default" src="" alt="" >
            <div class="feed-content">
              <div class="feed-message"><span class="fw-bold follower-name" @click="moveToProfile(feed.userEmail)">{{ feed.userName }}</span>님이 갤럭시방에 참여하였습니다!</div>
              <div class="feed-time">{{ computedEventtime }}</div>
            </div>
          </div>

          <!-- 갤럭시방 참여 하단 -->
          <div class="feed-description d-flex align-items-center">
            <div>
              <div class="galaxy-comment">"{{ feed.galaxyComment }}"</div>
              <div class="galaxy-member-count">{{ participantsVuex.length }}명 참여중</div>
            </div>
             <div class="d-flex align-items-center">
              <div class="galaxy-btn" @click="galaxyEntranceModal()">
                <ButtonSquare text="갤럭시 가기"/>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- 4. 상대방끼리의 팔로우 소식 -->
    <div v-if="feed.feedType === 4">
      
      <div v-if="userEmail !== feed.followUserEmail">
        <div class="d-flex justify-content-center feed">
          <div>
            
            <!-- 팔로우 상단 -->
            <div class="d-flex justify-content-start">
              <img :id=" 'my-image' + idx " class="profile-img-default" src="" alt="" >
              <div class="feed-content">
                <div class="feed-message"><span class="fw-bold follower-name" @click="moveToProfile(feed.userEmail)">{{ feed.userName }}</span>님이 <span class="fw-bold">{{ feed.followUserName }}</span>님을 팔로우합니다.</div>
                <div class="feed-time">{{ computedEventtime }}</div>
              </div>
            </div>
            
            <!-- 팔로우 하단 -->
            <div class="d-flex feed-description justify-content-between align-items-center">
              <!-- 팔로우하는 사람 이미지 -->
              <div class="d-flex align-items-center">
                <img :id=" 'follower-image' + idx " class="profile-img-default thumnail" src="" alt="" >
              </div>
              <!-- 팔로우하는 사람 정보 -->
              <div class="d-flex align-items-center follow-user-content">
                <div>
                  <div class="follow-user-name">{{ feed.followUserName }}</div>
                  <div class="follow-count"> 팔로워 {{ feed.followerCnt }} / 팔로잉 {{ feed.followingCnt }}</div>
                </div>
              </div>
              <div class="d-flex align-items-center">
                <div class="follow-btn" @click="moveToProfile(feed.followUserEmail)">
                  <ButtonSquare text="프로필 가기"/>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>

    </div>

    <!-- 5. 상대방이 나를 팔로우할 때 -->
    <div v-if="feed.feedType === 5">
      <div class="d-flex justify-content-center feed">
        <div>
          
          <!-- 팔로우 상단 -->
          <div class="d-flex justify-content-start">
            <img :id=" 'my-image' + idx " class="profile-img-default" src="" alt="" >
            <div class="feed-content">
              <div class="feed-message"><span class="fw-bold follower-name" @click="moveToProfile(feed.userEmail)">{{ feed.userName }}</span>님이 나를 팔로우합니다.</div>
              <div class="feed-time">{{ computedEventtime }}</div>
            </div>
          </div>
          
          <!-- 팔로우 하단 -->
          <div class="d-flex feed-description justify-content-between align-items-center">
            <!-- 팔로우하는 사람 이미지 -->
            <div class="d-flex align-items-center">
              <img :id=" 'follower-image' + idx " class="profile-img-default thumnail" src="" alt="" >
            </div>
            <!-- 팔로우하는 사람 정보 -->
            <div class="d-flex align-items-center follow-user-content">
              <div>
                <div class="follow-user-name">{{ feed.followUserName }}</div>
                <div class="follow-count"> 팔로워 {{ feed.followerCnt }} / 팔로잉 {{ feed.followingCnt }}</div>
              </div>
            </div>
            <div class="d-flex align-items-center">
              <div class="follow-btn" @click="moveToProfile(feed.followUserEmail)">
                <ButtonSquare text="프로필 가기"/>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
import ButtonSquare from '@/components/common/ButtonSquare';
import swal from 'sweetalert';
import http from "@/util/http-common.js";
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'FeedListItem',
  props: {
    feed: {
      type: Object
    },
    idx: {
      type: Number
    }
  },
  components: {
    ButtonSquare
  },
  data: function () {
    return {
      imgsrc: ''
    }
  },
  methods: {
    ...mapActions([
      'getMessage',
    ]),
    getMyImage: function() {
      http.get(`/viewimage/${this.feed.userEmail}`)
      .then((response) => {
        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
        document.getElementById(`my-image${this.idx}`).src = imgsrc;
      })
      .catch((error) => {});
    },
    getFollowerImage: function() {
      http.get(`/viewimage/${this.feed.followUserEmail}`)
      .then((response) => {
        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
        document.getElementById(`follower-image${this.idx}`).src = imgsrc;
      })
      .catch((error) => {});
    },
    moveToChallenge: function(challNo) {
      this.$router.push({path: '/challengeRoom', query: {cn: challNo}});
    },
    moveToProfile: function(email) {
      this.$router.push({path: '/profile', query: {user: email}});
    },
    galaxyEntranceModal () {
      if (window.location.pathname != '/Galaxy') {
        if (window.location.pathname == '/profile' || window.location.pathname == '/challengeRoom' || window.location.pathname == '/postDetailAfter') {
          this.$router.go(-1)
        }
        swal({
          title: '갤럭시에 오신 것을 환영합니다!',
          text: '상태메시지를 입력해주세요.',
          icon: "/img/star.5dee8d8d.png",
          content: {
            element: "input",
            attributes: {
              placeholder: '메시지는 20자 이내로 작성해주세요.'
            }
          },
          button: {
            text: '입장',
            closeModal: false
          }
        })
          .then(message => {
            if (!message) {
              return swal({
                text: '메시지는 필수 입력사항입니다!',
                button: {
                  text: '확인',
                }
              })
            }

            if (20 < message.length) {
              return swal({
                text: '메시지는 20자 이내여야 합니다!',
                button: {
                  text: '확인',
                }
              })
            }

            const userInfo = {
              message: message,
              userEmail: this.userEmail
            } 

            axios({
              method: 'post',
              url: '/galaxy/entry',
              data: userInfo
            })
              .then(res => {
                this.getMessage(message)
                this.$router.push({path: '/Galaxy'})
              })
              .catch(err => {
                console.log(err)
              })

            swal.close()
          })
        .catch(err => {
          console.log(err)
        })
      }
    },
  },
  created: function () {
    this.getMyImage()
    if (this.feed.feedType === 4 || this.feed.feedType === 5) {
      this.getFollowerImage()
    }
  },
  computed: {
    computedEventtime: function () {
      var date = new Date(this.feed.eventtime)
      var year = date.getFullYear().toString()

      var month = date.getMonth() + 1
      month = month < 10 ? '0' + month.toString() : month.toString()

      var day = date.getDate();
      day = day < 10 ? '0' + day.toString() : day.toString()

      /* 시차 보정 : 해외 시간으로 적용될 시 주석 해제하여 사용 */
      // var hour = date.getHours() + 9
      // if (24 <= hour) {
      //   day = (parseInt(day) + 1).toString()
      //   hour -= 24
      // }

      var hour = date.getHours()
      hour = hour < 10 ? '0' + hour.toString() : hour.toString()

      var minites = date.getMinutes()
      minites = minites < 10 ? '0' + minites.toString() : minites.toString()

      var seconds = date.getSeconds()
      seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString()

      return year + '/' + month + '/' + day + ' ' + hour + ':' + minites + ':' + seconds
    },
    remainTime: function () {
      let today = new Date().getTime()
      let deadline = new Date(this.feed.challengeStartdate).getTime()
      let interval =  deadline - today;
      let day = Math.ceil(interval / (1000 * 60 * 60 * 24));
      let hour = Math.ceil((interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) + (day - 1) * 24 - 9;

      return hour
    },
    ...mapGetters([
      'userEmail',
      'participantsVuex'
    ])
  }
}
</script>

<style scoped>
@font-face {
  font-family: 'NanumSquareRound';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

* {
  font-family: 'NanumSquareRound';
}

/* 기본 이미지 */
.profile-img-default {
  width: 90px;
  height: 90px;
}

/* 피드 하단 설명창 */
.feed-description {
  background-color: white;
  width: 800px;
  height: 140px;
  border-radius: 30px;
  padding-left: 60px;
  padding-right: 60px;
}

/* 챌린지 이름 */
.challenge-name-box {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 22px;
  font-weight: bold;
  text-align: left;
  margin-bottom: 5px;
  text-align: left;
  width: 500px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}

/* 챌린지 설명 */
.challenge-description {
  font-size: 20px;
  margin-bottom: 5px;
  text-align: left;
  width: 500px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}

/* 챌린지 인원 수 */
.challenge-member {
  font-size: 18px;
  font-weight: bold;
  color: #024979;
  margin-right: 15px;
}

/* 챌린지 참여까지 남은 시간 */
.challenge-countdown {
  font-size: 18px;
  color: #F59C35;
  font-weight: bold;
}

/* 챌린지 마감 */
.challenge-end {
  font-size: 18px;
  color: #F53535;
  font-weight: bold;
}

/* 챌린지 이동 버튼 */
.challenge-btn .btn-light {
  color: white;
  background-color: #1C84C4;
  border-style: none;
  font-size: 20px;
  width: 150px;
  height: 55px;
  border-radius: 10px;
  margin-left: 40px;
}

/* 단일 피드 전체 */
.feed {
  margin-bottom: 90px;
}

/* 피드 상단 전체 */
.feed-content {
  margin-left: 20px;
}

/* 피드 상단 메시지 */
.feed-message {
  text-align: left;
  color: white;
  font-size: 22px;
}

/* 피드 상단 시간 */
.feed-time {
  text-align: left;
  color: #8FC8FE;
  font-size: 18px;
}

/* 갤럭시방 코멘트 */
.galaxy-comment {
  font-size: 25px;
  font-weight: bold;
  margin-bottom: 3px;
  text-align: left;
  width: 500px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}

/* 갤럭시방 참여인원 */
.galaxy-member-count {
  font-size: 18px;
  margin-left: 3px;
  color: #024979;
  font-weight: bold;
  text-align: left;
}

/* 갤럭시방 이동 버튼 */
.galaxy-btn .btn-light {
  color: white;
  background-color: #1C84C4;
  border-style: none;
  font-size: 20px;
  width: 150px;
  height: 55px;
  border-radius: 10px;
  margin-left: 40px;
}

/* 팔로워 썸네일 */
.thumnail {
  margin-top: 39px;
}

/* 팔로워 정보 */
.follow-user-content {
  margin-right: 230px;
}

/* 팔로워 이름 */
.follow-user-name {
  text-align: left;
  font-size: 22px;
  font-weight: bold;
}

/* 팔로워, 팔로잉 수 */
.follow-count {
  color: #949599;
}

/* 프로필 이동 버튼 */ 
.follow-btn .btn-light {
  color: white;
  background-color: #1C84C4;
  border-style: none;
  font-size: 20px;
  width: 150px;
  height: 55px;
  border-radius: 10px;
}

/* 피드 상단 팔로워 이름 */
.follower-name {
  cursor: pointer;
}

.follower-name:hover {
  color: #70c8ff;
}
</style>