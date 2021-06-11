<template>
  <Card>
    <template #title>
      <h1 class="title">Login</h1>
    </template>
    <template #content>
      <div class="login-step">
        <label class="form-label">Username</label>
        <InputText type="text" v-model="username" v-on:keyup.enter="checkUser"/>
      </div>
      <Button class="login-step" v-on:click="checkUser">
        Login
      </Button>
    </template>
  </Card>
</template>

<script lang="ts">

import {Options, Vue} from 'vue-class-component';
import AuthEndpoints from "../mixins/auth/AuthEndpoints";
import {LoginResponseDto} from "@/mixins/auth/dto/login.response.dto";
import Header from '@/components/Header.vue';
import router from '@/router';
import store from '@/store'

@Options({
  components: {
    Header
  }
})

export default class Login extends Vue {
  private username = "";
  private state: LoginResponseDto | undefined;

  async checkUser(): Promise<void> {
    this.state = LoginResponseDto[await AuthEndpoints.methods.checkUser(this.username)];
    console.log(this.state);
    if (this.state == LoginResponseDto.NEEDS_PASSWORD) {
      store.commit('updateUsername', this.username)
      await router.push({path: '/set-password'});
    } else if (this.state == LoginResponseDto.HAS_PASSWORD) {
      store.commit('updateUsername', this.username)
      await router.push({path: '/verify-password'})
    } else if (this.state == LoginResponseDto.INVALID_USER) {
      this.$toast.add({severity: 'warn', summary: 'The specified user doesn\'t exist.', life: 3000});
    }
  }

  async created() {
    let jwt = await store.getters.getJwt;
    if (jwt !== '') {
      await router.push('/list')
    }
  }

}
</script>

<style scoped>

.form-label {
  display: block;
  margin-right: 80pt;
}

.login-step {
  margin: 1% auto;
}

</style>
