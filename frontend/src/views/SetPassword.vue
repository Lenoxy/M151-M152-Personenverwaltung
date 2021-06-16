<template>
  <Card>
    <template #title>
      <h1 class="title">Set Password</h1>
    </template>
    <template #content>
      <div class="login-step">
        <p>Since you are logging in for the first time, you need to set a password first.</p>
      </div>
      <RepeatPassword v-on:send="setNewPassword"/>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import AuthEndpoints from "../mixins/auth/AuthEndpoints";
import store from "@/store"
import router from '@/router';
import RepeatPassword from '@/components/RepeatPassword.vue';

@Options({
  components: {
    RepeatPassword,
  },
  props: {
    username: String
  }
})
export default class SetPassword extends Vue {


  // Deny users arriving at this page directly without
  async created() {
    let username = store.getters.getUsername;
    if (username === '') {
      username = store.getters.getJwtData.user;
      if (username === '') {
        await router.push('/login')
      }
    }
  }

  async setNewPassword(password: string): Promise<void> {
    let jwt: string = await AuthEndpoints.methods.register({
      username: store.getters.getUsername,
      password: password
    });
    store.commit('updateJwt', jwt)
    this.$toast.add({severity: 'success', summary: 'Welcome, ' + store.getters.getUsername, life: 3000})
    await this.$router.push('list');

  }
}
</script>

<style scoped>
.login-step {
  margin: 1% auto;
}

</style>
