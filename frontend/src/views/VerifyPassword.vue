<template>
  <Header></Header>
  <Card class="card">
    <template #title>
      <h1 class="title">Verify Password</h1>
    </template>
    <template #content>
      <div class="login-step">
        <label class="form-label">Password</label>
        <Password v-model="password" :feedback="false" />
      </div>
      <Button class="login-step" label="Login" v-on:click="verifyPassword"/>
    </template>
  </Card>
</template>

<script lang="ts">
import {Vue, Options} from "vue-class-component";
import AuthEndpoints from "../mixins/auth/AuthEndpoints";
import Header from "@/components/Header.vue";

@Options({
  components: {
    Header
  }
})


export default class VerifyPassword extends Vue {
  private password = "";

  async verifyPassword() : Promise<void> {
    await AuthEndpoints.methods.verifyPassword({username: "", password: this.password});
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

.title {
  display: block;
  margin: 5% auto;
}
</style>
