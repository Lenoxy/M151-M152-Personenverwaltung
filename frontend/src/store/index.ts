import {createStore} from 'vuex'
import AuthEndpoints from '@/mixins/auth/AuthEndpoints';
import {VerifyPasswordDto} from '@/mixins/auth/dto/verify.password.dto';
import createPersistedState from "vuex-persistedstate";


export default createStore<State>({
    plugins: [
        createPersistedState({storage: window.sessionStorage})
    ],
    state: {
        username: "",
        jwt: "",
        decodedJwtData: {isAdmin: false, iss: "", user: "", id: ""},
    },
    mutations: {
        updateUsername(state, username) {
            state.username = username;
        },
        updateJwt(state, jwt) {
            const base64Url = jwt.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
            state.decodedJwtData = JSON.parse(jsonPayload);
            state.jwt = jwt;
        },
        logout(state) {
            state.jwt = '';
            state.username = '';
            state.decodedJwtData = {isAdmin: false, iss: "", user: "", id: ""};
        }
    },
    actions: {
        async verifyPassword(context, verifyPasswordDto: VerifyPasswordDto) {
            context.commit('updateJwt', await AuthEndpoints.methods.verifyPassword(verifyPasswordDto));
        },
    },
    getters: {
        getUsername: state => state.username,
        getJwt: state => state.jwt,
        getJwtData: state => state.decodedJwtData,
        isAdmin: state => state.decodedJwtData.isAdmin,

    }
})

interface State {
    username: string,
    jwt: string,
    decodedJwtData: { isAdmin: boolean, iss: string, user: string, id: string },
}
