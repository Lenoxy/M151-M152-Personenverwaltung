<template>
  <Card>
    <template #title>
      <h1 class="title">Verify Password</h1>
    </template>
    <template #content>
      <div class="login-step">
        <label class="form-label">Password</label>
        <Password v-model="password" :feedback="false" v-on:keyup.enter="verifyPassword" ref="verifyPassword"/>
      </div>
      <Button class="login-step" label="Login" v-on:click="verifyPassword()"/>
    </template>
  </Card>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";
import store from "@/store"
import router from '@/router';
import {VerifyPasswordDto} from '@/mixins/auth/dto/verify.password.dto';

export default class VerifyPassword extends Vue {
  private password = "";

  async verifyPassword(): Promise<void> {
    let username = await store.getters.getUsername;

    try {
      await store.dispatch('verifyPassword', {username: username, password: this.password} as VerifyPasswordDto)
      await router.push('/list')
      this.$toast.add({severity: 'success', summary: 'Welcome, ' + username, life: 3000})

    }catch (e) {
      this.$toast.add({severity: 'warn', summary: 'Your password is incorrect', life: 3000})
    }
  }

  // Deny users arriving at this page directly
  async created() {
    //this.$refs.verifyPassword.focus();
    let username = await store.getters.getUsername;
    if (username === '') {
      await router.push('/login')
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
