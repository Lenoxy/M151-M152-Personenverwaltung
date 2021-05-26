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
      <Button class="login-step" label="Login"/>
  </template>
</Card>

</template>

<script lang="ts">
import {Vue} from 'vue-class-component';


export default class Login extends Vue {
   private username = "" as string;
   private password = "" as string;

}

</script>

<style scoped>

.form-label {
  display: block;
  margin-right: 80pt;
}

.login-step {
  margin: 5% 5% auto auto;
}

.card {
  width: 80%;
  margin: 0 auto;
}

.title {
  display: block;
  margin: 5% 5% auto auto;
}

</style>
