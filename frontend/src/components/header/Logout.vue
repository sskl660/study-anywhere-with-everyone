<template>
  <div>
    <button v-if="isLogin" type="button" class="btn btn-primary logout-btn" data-bs-toggle="modal" data-bs-target="#logoutModal">
      <i class="fas fa-sign-out-alt fa-flip-horizontal fa-2x"></i>
      <figcaption class="logout-msg">로그아웃</figcaption> 
    </button>

    <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content logout-content">
          <div class="modal-body logout-modal-body">
            <img class="star" src="../../assets/star.png" alt="star"/>
            <div class="logout-message">로그아웃 하시겠어요?</div>
          </div>
          <div class="modal-footer logout-footer">
            <button type="button" class="btn btn-success" data-bs-dismiss="modal" @click="logout()">네</button>
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">아니요</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex'
  export default {
    name: 'Logout',
    methods: {
      ...mapActions([
        'logout'
      ])
    },
    computed:{
      ...mapState([
        'isLogin'
      ])
    },
  }
</script>

<style scoped>
figcaption {
  opacity: 0;
  position: absolute;
  font-size: 14px;
  color: white;
  left: 5px;
}

.logout-btn {
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #1F3E52;
  background-color: #DDDDDD;
  width: 60px;
  height: 60px;
  border-radius: 75px;
  top: 30px;
  left: 30px;
  padding-right: 15px;
  cursor: pointer;
  border-style: none;
  box-shadow: none;
  z-index: 5;
}

.logout-btn:hover {
  color: #1F3E52;
  background-color: rgb(48, 48, 48);
  border-color: #DDDDDD;
}

.logout-btn:hover .logout-msg {
  opacity: 1
}

.btn-check:checked + .btn-primary, .btn-check:active + .btn-primary, .btn-primary:active, .btn-primary.active, .show > .btn-primary.dropdown-toggle {
  color: #1F3E52;
  background-color: #DDDDDD;
  border-color: #DDDDDD;
}

.btn-check:focus + .btn-primary, .btn-primary:focus {
  color: #1F3E52;
  background-color: #DDDDDD;
  border-color: #DDDDDD;
  box-shadow: none;
}

.btn-check:checked + .btn-primary:focus, .btn-check:active + .btn-primary:focus, .btn-primary:active:focus, .btn-primary.active:focus, .show > .btn-primary.dropdown-toggle:focus {
  box-shadow: none;
}

.btn-check:checked + .btn-primary, .btn-check:active + .btn-primary, .btn-primary:active, .btn-primary.active, .show > .btn-primary.dropdown-toggle {
  color: #1F3E52;
  background-color: #DDDDDD;
  border-color: #DDDDDD;
}

.logout-modal-body {
  position: relative;
  padding-bottom: 0px;
}

.logout-content {
  background-color: #99b7ff;
  width: 500px;
  height: 170px;
  border-radius: 1.5rem;
  margin-top: 390px;
}

.logout-footer {
  padding-top: 0px;
  border-style: none;
  padding-bottom: 15px;
  padding-right: 25px;
}

.logout-message {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 27px;
  font-weight: bold;
  position: absolute;
  margin-left: 120px;
  top: 35px;
}

button {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 17px;
  width: 90px;
  height: 40px;
}

.star {
  width: 70px;
  height: 70px;
  margin-right: 220px;
}

.btn-success {
  color: #fff;
  background-color: #1C84C4;
  border-style: none;
  font-weight: bold;
}

.btn-check:focus + .btn-success, .btn-success:focus {
  color: #fff;
  background-color: #1C84C4;
  border-style: none;
  box-shadow: none;
}

.btn-check:checked + .btn-success, .btn-check:active + .btn-success, .btn-success:active, .btn-success.active, .show > .btn-success.dropdown-toggle {
  color: #fff;
  background-color: #1C84C4;
  border-color: #1C84C4;
  border-style: none;
}

.btn-check:checked + .btn-success:focus, .btn-check:active + .btn-success:focus, .btn-success:active:focus, .btn-success.active:focus, .show > .btn-success.dropdown-toggle:focus {
    box-shadow: none;
}

.btn-success:hover {
  color: #fff;
  background-color: #1C84C4;
  border-color: #1C84C4;
  border-style: none;
}

.btn-danger {
  color: #fff;
  background-color: #F53535;
  border-color: #F53535;
  border-style: none;
  font-weight: bold;
}

.btn-danger:hover {
  color: #fff;
  background-color: #F53535;
  border-color: #F53535;
}

.btn-check:checked + .btn-danger, .btn-check:active + .btn-danger, .btn-danger:active, .btn-danger.active, .show > .btn-danger.dropdown-toggle {
  color: #fff;
  background-color: #F53535;
  border-style: none;
}

.btn-check:focus + .btn-danger, .btn-danger:focus {
  color: #fff;
  background-color: #F53535;
  border-style: none;
  box-shadow: none;
}

.btn-check:checked + .btn-danger:focus, .btn-check:active + .btn-danger:focus, .btn-danger:active:focus, .btn-danger.active:focus, .show > .btn-danger.dropdown-toggle:focus {
  box-shadow: none;
}
</style>