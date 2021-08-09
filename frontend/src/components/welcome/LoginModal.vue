<template>
  <div>
    <!-- 로그인 버튼 -->
    <button
      type="button"
      class="btn btn-primary login-btn"
      data-bs-toggle="modal"
      data-bs-target="#loginModal"
      data-bs-whatever="@mdo"
    >
      {{ text }}
    </button>

    <!-- 로그인 모달창 -->
    <div
      class="modal fade"
      id="loginModal"
      tabindex="-1"
      aria-labelledby="loginModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content login-content">
          <div class="modal-body">
            <form>
              <!-- 이메일 입력칸 -->
              <div class="d-flex">
                <label for="email-input" class="col-form-label input-label">이메일</label>
                <input
                  type="text"
                  class="form-control input-form"
                  id="email-input"
                  v-model="credentials.userEmail"
                />
              </div>
              <!-- 비밀번호 입력칸 -->
              <div class="d-flex">
                <label for="password-input" class="col-form-label input-label">비밀번호</label>
                <input
                  type="password"
                  class="form-control input-form"
                  id="password-input"
                  v-model="credentials.userPassword"
                />
              </div>
            </form>
          </div>
          <!-- 모달창 내부 로그인 버튼 -->
          <div class="modal-footer login-footer">
            <button
              type="button"
              class="btn btn-light login-button"
              data-bs-dismiss="modal"
              @click="login(credentials)"
            >
              로그인
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: 'LoginModal',
  props: {
    text: {
      type: String,
    },
  },
  data: function() {
    return {
      credentials: {
        userEmail: null,
        userPassword: null,
      },
    };
  },
  methods: {
    ...mapActions(['login']),
  },
};
</script>

<style scoped>
/* 폰트 설정 */
* {
  font-family: 'Noto Sans KR', sans-serif;
}

/* 로그인 버튼 */
.login-btn {
  color: #1c84c4;
  background-color: #ffffff;
  border-color: #ffffff;
  font-size: 25px;
  font-weight: bold;
  width: 180px;
  height: 60px;
  margin-right: 90px;
  border-radius: 2rem;
}

/* 로그인 버튼 색 변경 방지 */
.login-btn:hover {
  color: #1c84c4;
  background-color: #ffffff;
  border-color: #ffffff;
}

.btn-check:checked + .btn-primary,
.btn-check:active + .btn-primary,
.btn-primary:active,
.btn-primary.active,
.show > .btn-primary.dropdown-toggle {
  color: #1c84c4;
  background-color: #ffffff;
  border-color: #ffffff;
}

.btn-check:focus + .btn-primary,
.btn-primary:focus {
  color: #1c84c4;
  background-color: #ffffff;
  border-color: #ffffff;
  box-shadow: none;
}

.btn-check:checked + .btn-primary:focus,
.btn-check:active + .btn-primary:focus,
.btn-primary:active:focus,
.btn-primary.active:focus,
.show > .btn-primary.dropdown-toggle:focus {
  box-shadow: none;
}

/* 모달창 크기 */
.login-content {
  background-color: #99b7ff;
  border-radius: 1rem;
  margin-top: 350px;
}

/* 입력칸 라벨 */
.input-label {
  width: 100px;
  font-weight: bold;
  margin-top: 18px;
  margin-right: 5px;
}

/* 입력칸 */
.input-form {
  margin-top: 20px;
  margin-right: 45px;
  border-radius: 0.5rem;
}

/* 모달창 footer */
.login-footer {
  padding-top: 0px;
  border-style: none;
}

/* 모달창 내부 로그인 버튼 */
.login-button {
  margin-right: 50px;
  color: #ffffff;
  background-color: #1c84c4;
  border-style: none;
}

/* 모달창 내부 로그인 버튼 특수효과 방지 */
.login-button:hover {
  color: #ffffff;
  background-color: #1c84c4;
  border-style: none;
}

.btn-check:checked + .btn-light,
.btn-check:active + .btn-light,
.btn-light:active,
.btn-light.active,
.show > .btn-light.dropdown-toggle {
  color: #ffffff;
  background-color: #1c84c4;
  border-style: none;
}

.btn-check:focus + .btn-light,
.btn-light:focus {
  color: #ffffff;
  background-color: #1c84c4;
  border-style: none;
  box-shadow: none;
}

.btn-check:checked + .btn-light:focus,
.btn-check:active + .btn-light:focus,
.btn-light:active:focus,
.btn-light.active:focus,
.show > .btn-light.dropdown-toggle:focus {
  box-shadow: none;
}
</style>
