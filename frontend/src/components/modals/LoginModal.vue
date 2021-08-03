<template>
  <div>
    <button type="button" class="btn btn-primary login-btn" data-bs-toggle="modal" data-bs-target="#loginModal" data-bs-whatever="@mdo">{{ text }}</button>

    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content login-content">
          <div class="modal-body">
            <form>
              <div class="d-flex">
                <label for="email-input" class="col-form-label input-label">이메일</label>
                <input type="text" class="form-control input-form" id="email-input">
              </div>
              <div class="d-flex">
                <label for="password-input" class="col-form-label input-label">비밀번호</label>
                <input type="text" class="form-control input-form" id="password-input">
              </div>
            </form>
          </div>
          <div class="form-check check-part">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
            <label class="form-check-label check-label" for="flexCheckDefault">
              로그인 상태 유지
            </label>
          </div>
          <div class="modal-footer login-footer">
            <button type="button" class="btn btn-light login-button" data-bs-dismiss="modal">로그인</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'LoginModal',
  props: {
    text: {
      type: String
    }
  }
}
</script>

<style scoped>
  /* 로그인 버튼 */
  .login-btn {
    color: #1C84C4;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
    font-size: 25px;
    font-weight: bold;
    width: 180px;
    height: 60px;
    margin-right: 90px;
    border-radius: 2rem;
  }

  /* 로그인 버튼 색 변경 방지 */
  .login-btn:hover {
    color: #1C84C4;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
  }

  .btn-check:checked + .btn-primary, .btn-check:active + .btn-primary, .btn-primary:active, .btn-primary.active, .show > .btn-primary.dropdown-toggle {
    color: #1C84C4;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
  }

  .btn-check:focus + .btn-primary, .btn-primary:focus {
    color: #1C84C4;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
    box-shadow: none;
  }

  .btn-check:checked + .btn-primary:focus, .btn-check:active + .btn-primary:focus, .btn-primary:active:focus, .btn-primary.active:focus, .show > .btn-primary.dropdown-toggle:focus {
    box-shadow: none;
  }

  /* 모달창 커스텀 */
  .login-content {
    font-family: "Noto Sans KR", sans-serif;
    background-color: #99b7ff;
    border-radius: 1rem;
    margin-top: 350px;
  }

  .input-label {
    width: 100px;
    font-weight: bold;
    margin-top: 18px;
    margin-right: 5px;
  }

  .input-form {
    margin-top: 20px;
    margin-right: 45px;
    border-radius: 0.5rem;
  }

  .login-footer {
    padding-top: 0px;
    border-style: none;
  }

  .check-part {
    width: 115px;
    margin-left: 120px;
    padding: 0px;
  }

  .form-control:focus {
    outline: none;
    box-shadow: none;
  }

  .form-check-input:focus {
    outline: none;
    box-shadow: none;
  }

  .login-button {
    margin-right: 50px;
    color: #ffffff;
    background-color: #1c84c4;
    border-style: none;
  }

  .login-button:hover {
    color: #ffffff;
    background-color: #1c84c4;
    border-style: none;
  }

  .btn-check:checked + .btn-light, .btn-check:active + .btn-light, .btn-light:active, .btn-light.active, .show > .btn-light.dropdown-toggle {
    color: #ffffff;
    background-color: #1c84c4;
    border-style: none;
  }

  .btn-check:focus + .btn-light, .btn-light:focus {
    color: #ffffff;
    background-color: #1c84c4;
    border-style: none;
    box-shadow: none;
  }

  .btn-check:checked + .btn-light:focus, .btn-check:active + .btn-light:focus, .btn-light:active:focus, .btn-light.active:focus, .show > .btn-light.dropdown-toggle:focus {
    box-shadow: none;
  }
</style>