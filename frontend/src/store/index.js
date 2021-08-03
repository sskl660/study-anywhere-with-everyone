import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    comments: []
  },
  getters: {

  },
  mutations: {
    ADD_COMMENT(state, commentItem) {
      console.log(state);
      state.comments.push(commentItem)
    }
  },
  actions: {
  },
  modules: {
  }
})
