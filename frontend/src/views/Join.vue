<template>
  <div class="d-flex justify-content-center">
    <div class="join-container">
      
      <!-- 계정 등록 -->
      <p>계정 등록</p>
      <div class="d-flex email-container">
        <label for="email" class="input-label">이메일</label>
        <input 
          type="text" 
          id="email" 
          class="account-input email-input" 
          placeholder="40글자 이내로 입력해주세요." 
          onfocus="this.placeholder=''" 
          onblur="this.placeholder='40글자 이내로 입력해주세요.'"
          v-model="credentials.userEmail"
        />
        <!-- 중복체크 버튼 -->
        <div class="check-button" @click="emailcheck(credentials.userEmail)">
          <ButtonSquare text="중복체크"/>
        </div>
      </div>
      <div>
        <label for="password" class="input-label">비밀번호</label>
        <input 
          type="text" 
          id="password" 
          class="account-input password-input" 
          placeholder="8 이상 16 이하 글자수로 입력해주세요."
          onfocus="this.placeholder=''" 
          onblur="this.placeholder='8 이상 16 이하 글자수로 입력해주세요.'"
          v-model="credentials.userPassword"
        />
      </div>
      <div>
        <label for="password-confirm" class="input-label">비밀번호 확인</label>
        <input type="text" id="password-confirm" class="account-input password-confirm-input">
      </div>
      <br>

      <!-- 싸집 이미지 -->
      <div class="eggs-container">
         <img class="eggs" src="../assets/eggs.png" alt="eggs">
      </div>
     
      <!-- 정보 등록 -->
      <p>정보 등록</p>
      <div class="d-flex justify-content-center">
        <div>
          <label for="name" class="input-label name-label">이름</label>
          <input 
            type="text" 
            id="name" 
            class="info-input name-input" 
            placeholder="8글자 이내로 입력해주세요."
            onfocus="this.placeholder=''" 
            onblur="this.placeholder='8글자 이내로 입력해주세요.'"
            v-model="credentials.userName"
          />
        </div>
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
      </div>
      <div class="d-flex justify-content-center last-row-input">
        <div>
          <label for="grade" class="input-label">기수</label>
          <input type="number" id="grade" class="info-input grade-input" v-model="credentials.userTerm">
        </div>
        <div class="d-flex"> 
          <label for="code" class="input-label">참여코드</label>
          <input type="text" id="code" class="info-input code-input" v-model="accessCode"/>
          <!-- 참여코드 확인 버튼 -->
          <div class="check-button" @click="codeCheck(accessCode)">
            <ButtonSquare text="확인"/>
          </div>
        </div>
      </div>
      <br>

      <div class="d-flex justify-content-center">
        <!-- 돌아가기 버튼 -->
        <div class="back-button">
          <router-link to="/"><ButtonRound text="돌아가기"/></router-link>
        </div>
        <!-- 가입 버튼 -->
        <div class="join-button" @click="join(credentials)">
          <ButtonRound text="가입하기"/>
        </div>
      </div>
      
    </div>
</template>

<script>
import ButtonRound from '@/components/common/ButtonRound';
import ButtonSquare from '@/components/common/ButtonSquare';
import { mapActions ,mapGetters } from 'vuex';

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
                userEmail: null,
                userPassword: null,
                userNumber: null,
                userName: null,
                userGraduated: false,
                userTerm: null,
            },
            accessCode : null,
            joinFlag :false,
        };
    },
    methods: {
        ...mapActions([
            'join', // 회원가입 함수 등록
            'emailcheck',//아이디 체크
        ]),
        codeCheck:function(accessCode){
          if(accessCode=="307"){
            alert("참여코드 확인 완료")
          }
          else
            alert("참여코드 틀림 ㅠㅠ")
        }
    },
    computed:{
      ...mapGetters([
        'emailposi',//이메일 사용 가능 여부 flag
      ])
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
    margin-top: 35px;
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
    margin-bottom: 25px;
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
    margin-bottom: 30px;
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
    color: #FFFFFF;
    background-color: #919599;;
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
    background-color: #99B7FF;
    border-style: none;
}

/* SSAZIP 여러 개 모인 이미지 */
.eggs {
    position: absolute;
    width: 370px;
    height: 200px;
    top: 302px;
    right: 470px;
  }

  /* SSAZIP 여러 개 모인 이미지 : 반응형 CSS */
  @media (min-width: 990px) and (max-width: 1058px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 10px;
    }
  }

  @media (min-width: 1059px) and (max-width: 1127px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 50px;
    }
  }

  @media (min-width: 1128px) and (max-width: 1200px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 80px;
    }
  }

   @media (min-width: 1201px) and (max-width: 1250px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 100px;
    }
  }

  @media (min-width: 1251px) and (max-width: 1300px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 120px;
    }
  }

  @media (min-width: 1301px) and (max-width: 1399px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 130px;
    }
  }

  @media (min-width: 1400px) and (max-width: 1499px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 250px;
    }
  }

   @media (min-width: 1500px) and (max-width: 1600px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 310px;
    }
  }

  @media (min-width: 1601px) and (max-width: 1700px) {
    .eggs {
      position: absolute;
      width: 370px;
      height: 200px;
      top: 302px;
      right: 360px;
    }
  }
</style>
