import {createStore} from 'vuex'
import AuthEndpoints from '@/mixins/auth/AuthEndpoints';
import {VerifyPasswordDto} from '@/mixins/auth/dto/verify.password.dto';

export default createStore({
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
        verifyPassword(context, verifyPasswordDto: VerifyPasswordDto) {
            context.commit('updateJwt', AuthEndpoints.methods.verifyPassword(verifyPasswordDto));
        }
    },
    getters: {
        username: state => state.username,
        getJwt: state => state.jwt,
        isAuthenticated: state => state.jwt !== '',
    }
})
