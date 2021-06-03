import { createStore } from 'vuex'

export default createStore({
  state: {
    username: "",
    jwt: ""
  },
  mutations: {
    updateUsername(state, username){
      state.username = username;
    },
    updateJwt(state, jwt){
      state.jwt = jwt;
    }
  },
  actions: {
  },
  modules: {
  },
  getters: {
    username: state => state.username,
    jwt: state => state.jwt
  }
})
