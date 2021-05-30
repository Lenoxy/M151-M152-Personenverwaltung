<template>
<Card>
  <template #title>
  <h1 class="title">Login</h1>
  </template>
  <template #content>
    <div class="login-step">
      <label class="form-label">Username</label>
      <InputText type="text" v-model="username"/>
    </div>
    <Button class="login-step" v-on:click="checkUser">Login
      <router-link v-if="state === 0" :to="'/verify-password/'"></router-link>
      <router-link v-if="state === 1" :to="'/verify-password/'"></router-link>
      <router-link v-if="state === 2" :to="'/verify-password/'"></router-link>
    </Button>
  </template>
</Card>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-class-component';
import AuthEndpoints from "../mixins/auth/AuthEndpoints";
import {LoginResponseDto} from "@/mixins/auth/dto/login.response.dto";
import Header from '@/components/Header.vue';

@Options({
  components: {
    Header
  }
})

export default class Login extends Vue {
   private username = "";
   private state = LoginResponseDto.INVALID_USER;

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

</style>
