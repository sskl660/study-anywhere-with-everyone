<template>  
  <div>
    <div class="profile-status d-flex justify-content-between" style="display:inline-block">
      <div class="profile-status-info d-flex justify-content-between" style="display:inline-block">
        <div >challenge 
          <div style="font-weight:bold">0</div>
        </div>
        <div>
          follower
          <div style="font-weight:bold">0</div>
        </div>
        <div>
          following
          <div style="font-weight:bold">0</div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
export default {

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
</style>