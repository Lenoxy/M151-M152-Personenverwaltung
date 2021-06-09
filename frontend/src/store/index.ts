import {createStore} from 'vuex'
import AuthEndpoints from '@/mixins/auth/AuthEndpoints';
import {VerifyPasswordDto} from '@/mixins/auth/dto/verify.password.dto';
import createPersistedState from "vuex-persistedstate";


export default createStore({
    plugins: [
        createPersistedState({storage: window.sessionStorage})
    ],
    state: {
        username: "",
        jwt: ""
    },
    mutations: {
        updateUsername(state, username) {
            state.username = username;
        },
        updateJwt(state, jwt) {
            state.jwt = jwt;
        },
        logout(state) {
            state.jwt = ''
        }
    },
    actions: {
        async verifyPassword(context, verifyPasswordDto: VerifyPasswordDto) {
            context.commit('updateJwt', await AuthEndpoints.methods.verifyPassword(verifyPasswordDto));
        }
    },
    getters: {
        username: state => state.username,
        getJwt: state => state.jwt

    }
})
