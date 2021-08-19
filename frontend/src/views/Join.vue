<template>
  <div class="d-flex justify-content-center">
    <div class="join-container">
      <!-- 계정 등록 -->
      <p class="account-title">계정 등록</p>
      <div class="d-flex email-container">
        <label for="email" class="input-label">이메일</label>
        <input
          type="text"
          id="email"
          class="account-input email-input"
          placeholder="40자 이내로 입력해주세요."
          onfocus="this.placeholder=''"
          onblur="this.placeholder='40자 이내로 입력해주세요.'"
          v-model="credentials.userEmail"
        />
        <!-- 중복체크 버튼 -->
        <div class="check-button" @click="emailcheck(credentials.userEmail)">
          <ButtonSquare text="중복체크" />
        </div>
        <br />
        <br />
      </div>

      <h6 v-if="error.userEmail == 'styleError'" class="account-warning" style="color:red">
        이메일 형식이 일치하지 않습니다.
      </h6>
      <h6 v-if="error.userEmail == 'lengthError'" class="account-warning" style="color:red">
        아이디는 최대 30자 이상이어야 합니다.
      </h6>

      <div>
        <label for="password" class="input-label">비밀번호</label>
        <input
          type="password"
          id="password"
          class="account-input password-input"
          placeholder="영문, 숫자 포함 8자 이상 16자 이하로 입력해주세요."
          onfocus="this.placeholder=''"
          onblur="this.placeholder='영문, 숫자 포함 8자 이상 16자 이하로 입력해주세요.'"
          v-model="credentials.userPassword"
        />
      </div>

      <h6 v-if="error.userPassword == 'notValid'" class="account-warning" style="color:red">
        비밀번호 양식이 맞지 않습니다.
      </h6>

      <div>
        <label for="password-confirm" class="input-label">비밀번호 확인</label>
        <input
          type="password"
          id="password-confirm"
          class="account-input password-confirm-input"
          placeholder="비밀번호 확인을 입력해주세요."
          v-model="credentials.userPasswordConfirm"
        />
      </div>
      <br />

      <h6 v-if="error.userPasswordConfirm == 'notSame'" class="password-confirm-warning" style="color:red">
        비밀번호가 일치하지 않습니다!
      </h6>

      <!-- 싸집 이미지 -->
      <div class="eggs-container">
        <img class="eggs" src="../assets/eggs.png" alt="eggs" />
      </div>

      <!-- 정보 등록 -->
      <p>정보 등록</p>
      <div class="d-flex justify-content-center">
        <div>
          <div>
            <label for="name" class="input-label name-label">이름</label>
            <input
              type="text"
              id="name"
              class="info-input name-input"
              placeholder="3자 이내로 입력해주세요."
              onfocus="this.placeholder=''"
              onblur="this.placeholder='3자 이내로 입력해주세요.'"
              v-model="credentials.userName"
            />
          </div>

          <h6 v-if="error.userName == 'notValid'" class="name-warning" style="color:red">
            이름의 길이는 1자 이상, 3자 이하입니다.
          </h6>
        </div>

        <div>
          <div>
            <label for="phone" class="input-label">전화번호</label>
            <input
              type="text"
              id="phone"
              class="info-input phone-input"
              placeholder="ex) 01012345678"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='ex) 01012345678'"
              v-model="credentials.userNumber"
            />
          </div>

          <h6 v-if="error.userNumber == 'notValid'" class="phone-number-warning" style="color:red">
            전화번호 양식이 맞지 않습니다.
          </h6>
        </div>
      </div>

      <div class="d-flex justify-content-center last-row-input">

        <div>
          <div>
            <label for="grade" class="input-label">기수</label>
            <input
              type="number"
              id="grade"
              class="info-input grade-input"
              v-model="credentials.userTerm"
            />
          </div>

          <h6 v-if="error.userTerm == 'notValid'" class="term-warning" style="color:red">
            올바른 기수를 입력해주세요!
          </h6>
        </div>
        

        <div class="d-flex">
          <label for="code" class="input-label">참여코드</label>
          <input type="text" id="code" class="info-input code-input" v-model="accessCode" />
          <!-- 참여코드 확인 버튼 -->
          <div class="check-button" @click="codeCheck(accessCode)">
            <ButtonSquare text="확인" />
          </div>
        </div>
      </div>
      <br />

      <div class="d-flex justify-content-center button-container">
        <!-- 돌아가기 버튼 -->
        <div class="back-button">
          <router-link to="/"><ButtonRound text="돌아가기"/></router-link>
        </div>
        <!-- 가입 버튼 -->
        <div class="join-button" @click="joinCheck(credentials)">
          <ButtonRound text="가입하기" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound';
import ButtonSquare from '@/components/common/ButtonSquare';
import { mapActions, mapGetters } from 'vuex';
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';
import swal from 'sweetalert';

export default {
  name: 'Join',
  components: {
    ButtonRound, // 둥근 버튼을 가져옴
    ButtonSquare, // 사각 버튼을 가져옴
  },
  data: function() {
    return {
      /* API로 보낼 회원 정보 데이터. v-model을 통해 input 태그에서 값을 받는다. */
      credentials: {
        userEmail: '',
        userName: '',
        userGraduated: false,
        userNumber: '',
        userPassword: '',
        userPasswordConfirm: '',
        userTerm: '',
      },
      error: {
        userEmail: false,
        userName: false,
        userGraduated: false,
        userNumber: false,
        userPassword: false,
        userPasswordConfirm: false,
        userTerm: false,
        userCode: false,
      },
      isSubmit: false,
      accessCode: null,
      joinFlag: false,
      passwordSchema: new PV(),
    };
  },
  methods: {
    ...mapActions([
      'join', // 회원가입 함수 등록
      'emailcheck', //아이디 체크
    ]),
    logined: function() {
      if (this.isLogin) {
        this.$router.push({ path: '/homefeed' });
      }
    },
    codeCheck: function(accessCode) {
      if (accessCode == '307') {
        this.error.userCode = true;
        swal('참여코드 확인 완료');
      } else {
        this.error.userCode = false;
        swal('참여코드를 확인해주세요!');
      }
    },
    // 입력 규칙 체크
    checkform() {
      this.isSubmit = false;
      // 이메일 형식이 맞는가?
      if (!EmailValidator.validate(this.credentials.userEmail)) {
        this.error.userEmail = 'styleError';
        return;
      }
      // 이메일 길이가 1글자 이상, 40글자 미만인가?
      else if (this.credentials.userEmail.length <= 0 || this.credentials.userEmail.length >= 40) {
        this.error.userEmail = 'lengthError';
        return;
      }
      // 이메일 체크 통과
      else this.error.userEmail = true;

      // 비밀번호가 적합한가?
      if (!this.passwordSchema.validate(this.credentials.userPassword)) {
        this.error.userPassword = 'notValid';
        return;
      }
      // 비밀번호 체크 통과
      else this.error.userPassword = true;

      // 비밀번호 확인
      if (this.credentials.userPassword != this.credentials.userPasswordConfirm) {
        this.error.userPasswordConfirm = 'notSame';
        return;
      }
      // 비밀번호 확인 통과
      else this.error.userPasswordConfirm = true;

      // 이름의 길이가 1자 이상, 3자 이하인가?
      if (this.credentials.userName.length <= 0 || this.credentials.userName.length > 3) {
        this.error.userName = 'notValid';
        return;
      }
      // 이름 확인 통과
      else this.error.userName = true;

      // 전화번호
      if (this.credentials.userNumber.length != 11 || this.credentials.userNumber.slice(0, 3) != '010') {
        this.error.userNumber = 'notValid';
        return;
      }
      // 전화번호 확인 통과
      else this.error.userNumber = true;

      // 기수 입력 여부
      if (this.credentials.userTerm <= 0 || this.credentials.userTerm > 100) {
        this.error.userTerm = 'notValid';
        return;
      }
      // 기수 입력 통과
      else this.error.userTerm = true;

      // 참여 코드
      Object.values(this.error).map((v) => {
        if (!v) this.isSubmit = false;
      });

      this.isSubmit = true;
    },
    joinCheck(credentials) {
      if (!this.emailposi) {
        swal('이메일 중복 여부를 체크해주세요!');
        return;
      }
      if (!this.isSubmit) {
        swal('모든 정보를 정확히 기입해주세요!');
        return;
      }
      if (!this.error.userCode) {
        swal('코드를 인증해주세요!!');
        return;
      }
      this.join(credentials);
    },
  },
  computed: {
    ...mapGetters([
      'emailposi', //이메일 사용 가능 여부 flag
      'isLogin' // 로그인 여부 flag
    ]),
  },
  // 입력 변수 감시
  watch: {
    'credentials.userEmail': function() {
      this.checkform();
    },
    'credentials.userPassword': function() {
      this.checkform();
    },
    'credentials.userPasswordConfirm': function() {
      this.checkform();
    },
    'credentials.userName': function() {
      this.checkform();
    },
    'credentials.userNumber': function() {
      this.checkform();
    },
    'credentials.userTerm': function() {
      this.checkform();
    },
  },
  created() {
    this.logined();
    // 패스워드 규칙. 최소 8자 ~ 최대 100자. 숫자와 문자 포함해야함.
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(16)
      .has()
      .digits()
      .has()
      .letters();
  },
};
</script>

<style scoped>
/* 폰트 등록 */
@font-face {
  font-family: 'Godo';
  font-style: normal;
  font-weight: 400;
  src: url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2') format('woff2'),
    url('//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff') format('woff');
}

/* 반투명 배경 */
.join-container {
  background-color: rgba(255, 255, 255, 0.2);
  width: 1000px;
  height: 780px;
  margin-top: 100px;
  border-radius: 2rem;
  padding-top: 30px;
}

/* 큰 제목 */
p {
  font-size: 40px;
  font-family: 'Noto Sans KR', sans-serif;
  color: #f59c35;
  text-align: left;
  margin-left: 100px;
  /* margin-top: 35px; */
  margin-bottom: 25px;
}

/* 전체 input 태그 CSS */
input {
  padding-left: 15px;
  border-style: none;
}

input:focus {
  outline: none;
}

input::placeholder {
  font-family: 'Noto Sans KR', sans-serif;
  color: #b3b3b3;
}

/* 전체 input-label CSS */
.input-label {
  font-family: 'Godo';
  color: #f9d479;
  font-size: 25px;
  margin-right: 15px;
}

/* 계정 등록 전체 영역 */
.account-input {
  width: 400px;
  height: 40px;
  border-radius: 0.5rem;
  margin-bottom: 40px;
}

/* 이메일 입력 전체 영역 */
.email-container {
  margin-left: 173px;
}

/* 이메일 입력칸 */
.email-input {
  margin-right: 20px;
}

/* 비밀번호 입력칸 */
.password-input {
  margin-right: 190px;
}

/* 비밀번호 확인 입력칸 */
.password-confirm-input {
  margin-right: 240px;
}

/* 정보 등록 전체 영역 */
.info-input {
  width: 250px;
  height: 40px;
  border-radius: 0.5rem;
  margin-bottom: 40px;
}

/* 이름 입력칸 */
.name-input {
  margin-right: 60px;
}

/* 전화번호 입력칸 */
.phone-input {
  margin-right: 60px;
}

/* 기수 입력칸 */
.grade-input {
  margin-right: 60px;
}

/* 참여코드 입력칸 */
.code-input {
  margin-right: 20px;
}

/* 마지막 열 입력칸 전체 부분 */
.last-row-input {
  margin-left: 17px;
}

/* 중복체크 버튼 */
.check-button .btn-light {
  font-family: 'Godo';
  font-size: 18px;
  height: 40px;
  color: white;
  background-color: #1c84c4;
  border-style: none;
  border-radius: 0.5rem;
}

/* 돌아가기 버튼 */
.back-button .btn-light {
  font-family: 'Godo';
  font-size: 23px;
  width: 170px;
  height: 60px;
  margin-top: 10px;
  margin-right: 50px;
  color: #1f4256;
  background-color: #e1af4e;
  border-style: none;
}

/* 회원가입 버튼 */
.join-button .btn-light {
  font-family: 'Godo';
  font-size: 23px;
  width: 170px;
  height: 60px;
  margin-top: 10px;
  margin-left: 50px;
  background-color: #99b7ff;
  border-style: none;
}

/* SSAZIP 여러 개 모인 이미지 */
.eggs {
  position: absolute;
  width: 370px;
  height: 200px;
  top: 310px;
  right: 470px;
}

/* 계정 경고 메시지 */
.account-warning {
  position: absolute;
  margin-top: -29px;
  margin-left: 270px;
}

/* 계정 경고 메시지 */
.password-confirm-warning {
  position: absolute;
  margin-top: -53px;
  margin-left: 270px;
}

/* 이름 경고 메시지 */
.name-warning {
  position: absolute;
  margin-top: -30px;
  margin-left: 61px;
}

/* 전화번호 경고 메시지 */
.phone-number-warning {
  position: absolute;
  margin-top: -30px;
  margin-left: 105px;
}

/* 기수 경고 메시지 */
.term-warning {
  position: absolute;
  margin-top: -30px;
  margin-left: 61px;
}

.account-title {
  margin-top: 15px;
}

/* SSAZIP 여러 개 모인 이미지 : 반응형 CSS */
@media (min-width: 990px) and (max-width: 1058px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 10px;
  }
}

@media (min-width: 1059px) and (max-width: 1127px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 50px;
  }
}

@media (min-width: 1128px) and (max-width: 1200px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 80px;
  }
}

@media (min-width: 1201px) and (max-width: 1250px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 100px;
  }
}

@media (min-width: 1251px) and (max-width: 1300px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 120px;
  }
}

@media (min-width: 1301px) and (max-width: 1399px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 130px;
  }
}

@media (min-width: 1400px) and (max-width: 1499px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 250px;
  }
}

@media (min-width: 1500px) and (max-width: 1600px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 310px;
  }
}

@media (min-width: 1601px) and (max-width: 1700px) {
  .eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 310px;
    right: 360px;
  }
}
</style>
