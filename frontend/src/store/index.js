import axios from '@/util/http-common.js';
import Vue from 'vue';
import Vuex from 'vuex';
import router from '@/router';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false,
        config: null, // jwt 담는 객체
        comments: [],
    },

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
            router.push({ name: 'Welcome' });
        },
        // 토큰 부여
        SET_TOKEN: function(state) {
            const token = localStorage.getItem('jwt');
            state.config = {
                Anthorization: `JWT ${token}`,
            };
      },
        // 댓글
        ADD_COMMENT(state, commentItem) {
            console.log(state);
            state.comments.push(commentItem);
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
      addComment: function ({ commit }, commentItem) {
        commit('ADD_COMMENT', commentItem);
      },
      // 위의 것 축약형
      // addComment(context, commentItem) {
      //   context.commit('ADD_COMMENT', commentItem);
      //   }
    },

    getter: {
        config: function(state) {
            return state.config;
        },
        isLogin: function(state) {
            return state.isLogin;
        },
    },
});
