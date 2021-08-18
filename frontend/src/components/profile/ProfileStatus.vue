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
                <div v-for="(follower, idx) in followers" :key="idx">
                  <li>
                    <router-link class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;" :to="{ path: '/profile', query: { user: follower[0]  }}">
                      {{follower[1]}}
                    </router-link>
                  </li>
                  
                </div>
              </div>              
            </ul>
          </div>
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
                <div v-for="(following, idx) in followings" :key="idx">
                  <li>
                    <router-link :to="{ path: '/profile', query: { user: following[0]  }}" class="dropdown-item" style="color:#420909; font-weight:600; font-size:18px;">
                      {{following[1]}}
                      <!-- 키값 : value값 -->
                    </router-link>
                  </li>
                </div>
              </div>              
            </ul>
          </div>
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
  box-shadow:3px 4px 4px #665b46;
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