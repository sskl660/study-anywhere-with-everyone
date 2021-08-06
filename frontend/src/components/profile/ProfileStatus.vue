<template>  
  <div>
    <div class="profile-status d-flex justify-content-between" style="display:inline-block">
      <div class="profile-status-info d-flex justify-content-between" style="display:inline-block">
        <div >challenge 
          <div style="margin-top:5px; font-weight:bold;">{{userInfo.userTotalcomplete}}</div>
        </div>
        
        <div>
          follower
          <!-- 팔로워 목록 리스트 -->
          <div class="dropdown">
            <button class="btn pt-0 pb-0" style="height:30px; text-align:center; font-weight:bold;" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              {{userInfo.userFollower}}
            </button>
            <ul class="dropdown-menu text-center" style="background-color: #E1AF4E; border-radius: 1rem" aria-labelledby="dropdownMenuButton1">
              <div class="drop-list-title" style="">follower</div>
              <div class="drop-list">
                <div v-for="follower in followers" :key="follower[0]">
                  <!-- <li><a class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" href="#">{{follower[1]}}</a></li> -->
                  <li>
                    <router-link class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" :to="{ name: 'Profile', query: { userEmail: follower[0] } }">
                      {{follower[1]}}
                    </router-link>
                  </li>
                  
                </div>
              </div>              
            </ul>
          </div>
          <!-- <div @click="followerList" style="font-weight:bold">{{userInfo.userFollower}}</div> -->
        </div>
        
        <div>
          following
          <div class="dropdown">
            <button class="btn pt-0 pb-0" style="height:30px; text-align:center; font-weight:bold;" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              {{userInfo.userFollowing}}
            </button>
            <ul class="dropdown-menu text-center" style="background-color: #E1AF4E; border-radius: 1rem" aria-labelledby="dropdownMenuButton1">
              <div class="drop-list-title" style="">following</div>
              <div class="drop-list">
                <div v-for="following in followings" :key="following[0]">
                  <li>
                    <router-link :to="{ name: 'Profile', query: { userEmail: following[0] } }" class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;">
                      {{following[1]}}
                    </router-link>
                  </li>
                  <!-- <li><a class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" href="#">{{following[1]}}</a></li> -->
                </div>
              </div>              
            </ul>
          </div>
          <!-- <div @click="followingList" style="font-weight:bold">{{userInfo.userFollowing}}</div> -->
        </div>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  name: 'ProfileStatus',
  props: {
    userInfo: {
      type: Object
    },
    followers: {
      type: Array
    },
    followings: {
      type: Array
    }
  },
  methods: {
    followerList: function () {
      // console.log('cpzm')
      // console.log(this.userInfo)
      // console.log(this.followers)
    },
    followingList: function () {
      console.log('cpzsssm')
      // console.log(this.userInfo.userFollowing)
      console.log(this.followings)
    }
  },
  created:function (){
    //alert('profile status')
     this.followerList()
     this.followingList()
  },
 
}
</script>

<style>
.profile-status {
  width: 550px;
  height: 70px;
  background-color: #E1AF4E ;
  border-radius: 1rem;
  margin-top: 5px;
  margin-left:10px;
}
.profile-status-info {
  width: 420px;
  height: 60px;
  margin-left: 70px;
  margin-top: 10px;
}
/* 목록을 보여주는 컨테이너 */
.drop-list {
  margin-left:20px; 
  width:120px; 
  background-color: #EDE0C6;
}
.drop-list-title {
  color:#420909; 
  font-weight:bold; 
  font-size:20px;
}
</style>