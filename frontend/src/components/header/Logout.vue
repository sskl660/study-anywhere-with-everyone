<template>
  <div>
    <button v-if="isLogin" type="button" class="btn btn-primary logout-btn" data-bs-toggle="modal" data-bs-target="#logoutModal">
      <i class="fas fa-sign-out-alt fa-flip-horizontal fa-2x"></i>
    </button>

    <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content logout-content">
          <div class="modal-body">
            <div>로그아웃 하시겠어요?</div>
          </div>
          <div class="modal-footer logout-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="logout()">네</button>
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
    components: {
    },
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
  }

  .logout-btn:hover {
    color: #1F3E52;
    background-color: #DDDDDD;
    border-color: #DDDDDD;
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

  .logout-content {
    font-family: "Noto Sans KR", sans-serif;
    background-color: #99b7ff;
    border-radius: 1rem;
    margin-top: 350px;
  }

  .logout-footer {
    display: flex;
    justify-content: center;
    padding-top: 0px;
    border-style: none;
  }
</style>