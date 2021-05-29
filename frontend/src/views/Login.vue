<template>
<Card class="card">
  <template #title>
  <h1 class="title">Login</h1>
  </template>
  <template #content>
    <div class="login-step">
      <label class="form-label">Username</label>
      <InputText type="text" v-model="username"/>
    </div>
    <Button class="login-step" label="Login" v-on:click="checkUser">
      <router-link v-if="valid === null" :to="'/verify-password/'"></router-link>
      <router-link v-if="valid" :to="'/verify-password/'"></router-link>
      <router-link v-else :to="'/verify-password/'"></router-link>
    </Button>
  </template>
</Card>
</template>

<script lang="ts">
import {Vue} from 'vue-class-component';
import AuthEndpoints from "../mixins/auth/AuthEndpoints";


export default class Login extends Vue {
   private username = "";
   private valid = false;
   private state: any;

  async checkUser() : Promise<void> {
    this.state = await AuthEndpoints.methods.checkUser(this.username);
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

.card {
  width: 80%;
  margin: 0 auto;
}

.title {
  display: block;
  margin: 5% auto;
}

</style>
