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
              <div class="drop-list-title" style="">followers</div>
              <div class="drop-list">
                <li><a class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" href="#">권희은</a></li>
              </div>              
            </ul>
          </div>
          <!-- <div @click="followerList" style="font-weight:bold">{{userInfo.userFollower}}</div> -->
        </div>
        
        <div>
          following
          <div class="dropdown">
            <button class="btn pt-0 pb-0" style="height:30px; text-align:center; font-weight:bold;" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              {{userInfo.userFollower}}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
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
      console.log(this.userInfo.userFollower)
      console.log(this.followers)
    },
    followingList: function () {
      console.log(this.userInfo.userFollowing)
    }
  },
  created:function (){
    this.followerList()
  }
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