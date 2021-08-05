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
    },
    // state를 유지하기 위해
    plugins: [createPersistedState({
        paths: ['userEmail', 'userName','emailposi',
            'isLogin', 'userTerm', 'config','comments']
    })],
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
            router.push({ name: 'Challenges' });
        },
        // 로그아웃
        LOGOUT: function(state) {
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
        SET_TOKEN: function(state, userEntity) {
            const token = localStorage.getItem('jwt');
            state.config = {
                Anthorization: `JWT ${token}`,
            };
            state.userEmail = userEntity.userEmail;
            state.userName = userEntity.userName;
            state.userTerm = userEntity.userTerm;
        },
        // 댓글
        ADD_COMMENT(state, commentItem) {
            console.log(state);
            state.comments.push(commentItem);
        },
        //이메일 체크
        EMAIL_CHECK(state, returnflag) {
            state.emailposi = returnflag;
            alert('중복체크 완료' + returnflag);
        },
    },

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
                    console.log(credentials);
                    commit('LOGIN', res.data.accessToken);
                    commit('SET_TOKEN', res.data.userEntity);
                })
                .catch((err) => {
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
        // 댓글 기능
        // action은 mutation을 호출하고 mutations는 state값을 가져온다.
        addComment: function({ commit }, commentItem) {
            commit('ADD_COMMENT', commentItem);
        },
        // 위의 것 축약형
        // addComment(context, commentItem) {
        //   context.commit('ADD_COMMENT', commentItem);
        //   }
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
    },

    getters: {
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
    },
});
