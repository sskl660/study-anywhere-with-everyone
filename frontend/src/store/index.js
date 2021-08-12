import axios from '@/util/http-common.js';
import Vue from 'vue';
import Vuex from 'vuex';
import router from '@/router';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false,
    config: null, // jwt 담는 객체
    comments: [],
    emailposi: false, //false 일때 중복
    userEmail: null,
    userName: null,
    userTerm: null,
    // Galaxy 관련
    chatType: 'algo', // 채팅창 타입(Algo, CS, Job)
    participantsVuex:[],
  },
  // state를 유지하기 위해
  plugins: [
    createPersistedState({
      paths: ['userEmail', 'userName', 'isLogin', 'userTerm', 'config', 'comments'],
    }),
  ],
  mutations: {
    // 가입
    JOIN: function(state) {
      console.log(state);
      router.push({ name: 'Welcome' });
    },
    // 로그인
    LOGIN: function(state, token) {
      localStorage.setItem('jwt', token);
      state.isLogin = true;
      router.push({ name: 'HomeFeed' }); // 페이지 이동도 가능
    },
    // 로그아웃
    LOGOUT: function(state) {
      console.log('로그아웃 성공');
      state.isLogin = false;
      localStorage.removeItem('jwt');
      state.config = null;
      state.user = null;
      state.userName = null;
      state.userTerm = null;
      state.userEmail = null;
      state.comments = [];
      router.push({ name: 'Welcome' });
    },
    // 토큰 부여
    SET_TOKEN: function(state, userInfoResponse) {
      const token = localStorage.getItem('jwt');
      state.config = {
        Anthorization: `JWT ${token}`,
      };
      console.log('토큰 부여중');
      console.log(userInfoResponse);
      console.log(userInfoResponse.userEmail);
      state.userEmail = userInfoResponse.userEmail;
      state.userName = userInfoResponse.userName;
      state.userTerm = userInfoResponse.userTerm;
    },
    // 댓글
    ADD_COMMENT(state, commentItem) {
      console.log(state);
      state.comments.push(commentItem);
    },
    //이메일 체크
    EMAIL_CHECK(state, returnflag) {
      // state는 기본값. 그냥 써주기 // returnflag는 res.data(t인지 f인지 들어있는 정보)
      state.emailposi = returnflag; // 저장해주고 값 바꿔주기
      if (!returnflag) {
        alert('이메일이 중복 됩니다!');
      } else {
        alert('중복체크 완료!');
      }
    },
    JOIN_CHALL(state) {
      // state에서 사용하는 변수는 클라이언트가 사용하는 변수들.
      console.log(state);
    },
    // PRESSLIKE: function (state) {
    //     console.log(state);
    //     alert('좋아요를 눌렀습니다');
    // }
    // 채팅창 타입 변경
    CHANGE_CHAT_TYPE(state, chatType) {
      if (chatType == 1) {
        state.chatType = 'algo';
      } else if (chatType == 2) {
        state.chatType = 'cs';
      } else if (chatType == 3) {
        state.chatType = 'job';
      }
      console.log('채팅 변경');
    },
    SET_PARTICIPANTS(state, payload) {
      console.log("SET_PARTICIPANTS mutation working" + payload);
      console.log(payload);
      state.participantsVuex = payload;
    },
  },
  // 젠킨스를 위한 변경사항
  actions: {
    // 회원가입
    join: function({ commit }, credentials) {
      axios({
        method: 'post',
        url: '/signup',
        data: credentials,
      })
        .then((res) => {
          console.log(res);
          commit('JOIN');
        })
        .catch((err) => {
          console.log('actionerr');
          console.log(err);
        });
    },
    // 로그인
    login: function({ commit }, credentials) {
      axios({
        method: 'post',
        url: '/login',
        data: credentials,
      })
        .then((res) => {
          console.log('로그인 통신 성공');
          console.log(credentials);
          console.log(res.data.userInfoResponse);
          commit('LOGIN', res.data.accessToken);
          commit('SET_TOKEN', res.data.userInfoResponse);
        })
        .catch((err) => {
          alert('계정이나 인터넷을 확인해주세요');
          console.log(err);
        });
    },
    // 로그아웃
    logout: function({ commit }) {
      commit('LOGOUT');
    },
    // 토큰 부여
    setToken: function({ commit }) {
      commit('SET_TOKEN');
    },
    //이메일 체크
    emailcheck: function({ commit }, email) {
      axios({
        method: 'get',
        url: `/signup/check/${email}`,
      })
        .then((res) => {
          console.log(email);
          commit('EMAIL_CHECK', res.data);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
          alert('잠시후 다시 시도 해주세요');
        });
    },
    // 챌린지 가입
    joinChallenge: function({ commit }, info) {
      axios({
        method: 'post',
        url: '/challenge/join',
        data: { challengeNo: info[0], userEmail: info[1] },
      })
        .then((res) => {
          // 통신이 넘어오는 것
          console.log(res);
          commit('JOIN_CHALL');
          // commit('JOIN_CHALL', res.data); //res는 백엔드에서 넘겨주는 response, res.data는 body부분
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 좋아요
    presslike: function({ commit }, like) {
      // alert('좋아요 들어오니?');
      // console.log(like);
      axios({
        method: 'post',
        url: '/challenge/task/like',
        data: like,
      })
        .then((res) => {
          console.log(res);
          console.log(commit);
          // commit('PRESSLIKE');
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 좋아요 취소
    pressunlike: function({ commit }, like) {
      axios({
        method: 'delete',
        url: '/challenge/task/unlike',
        data: like,
      })
        .then((res) => {
          console.log(res);
          console.log(commit);
          // commit('PRESSUNLIKE');
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 댓글 보내기
    leaveMessage: function({ commit }, msg) {
      // alert('댓글 들어오니?');
      // console.log('여기는 store')
      // console.log(msg);
      axios({
        method: 'post',
        url: '/comment',
        data: msg,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 댓글 기능
    // action은 mutation을 호출하고 mutations는 state값을 가져온다.
    addMessage: function({ commit }, commentItem) {
      commit('ADD_COMMENT', commentItem);
    },
    // 채팅 타입 변경
    changeChatType: function({ commit }, chatType) {
      commit('CHANGE_CHAT_TYPE', chatType);
    },
    setPart: function ({ commit }, participants){
      commit('SET_PARTICIPANTS', participants);
    }
  },

  getters: {
    // state값이 바뀌면 그걸 가져오는 것. state를 가져오기 위한 getter함수들
    config: function(state) {
      return state.config;
    },
    isLogin: function(state) {
      return state.isLogin;
    },
    emailposi: function(state) {
      return state.emailposi;
    },
    userEmail: function(state) {
      return state.userEmail;
    },
    userName: function(state) {
      return state.userName;
    },
    userTerm: function(state) {
      return state.userTerm;
    },
    chatType: function(state) {
      return state.chatType;
    },
    participantsVuex: function (state) {
      return state.participantsVuex;
    }
  },
});
