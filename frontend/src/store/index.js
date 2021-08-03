import axios from '@/util/http-common.js';
import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
    JOIN: function (state) {
      console.log(state)
      router.push({ name: 'Welcome'})
    }
  },
  actions: {
    //회원가입 1
    join: function ({ commit }, credentials) {

      axios({
        method:'post',
        url: '/signup',
        data: credentials
      })
        .then(res => {
          console.log(res)
          commit('JOIN')
        })
        .catch(err => {
          console.log("actionerr")
          console.log(err)
          
        })
    }
  },
  modules: {
  }
})
