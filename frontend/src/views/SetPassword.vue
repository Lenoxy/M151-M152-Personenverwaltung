<template>
  <Card>
    <template #title>
      <h1 class="title">Set Password</h1>
    </template>
    <template #content>
  <div class="login-step">
    <label class="form-label">Password</label>
    <Password v-model="newPassword">
      <template #header>
        <h6>Pick a password</h6>
      </template>
      <template #footer="sp">
        {{sp.level}}
        <Divider />
        <p class="p-mt-2">Suggestions</p>
        <ul class="p-pl-2 p-ml-2 p-mt-0" style="line-height: 1.5">
          <li>At least one lowercase</li>
          <li>At least one uppercase</li>
          <li>At least one numeric</li>
          <li>Minimum 8 characters</li>
        </ul>
      </template>
    </Password>
  </div>
      <div class="login-step">
        <label class="form-label">Confirm</label>
        <Password :feedback="false" v-model="confirmPassword"/>
      </div>
      <Button class="login-step" label="Set password" v-on:click="setNewPassword"/>
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

export default class SetPassword extends Vue {
  private newPassword = "";
  private confirmPassword = "";

  validatePassword(): boolean {
    return this.newPassword === this.confirmPassword;
  }

  async setNewPassword(): Promise<void> {
    this.validatePassword()
        ? await AuthEndpoints.methods.register({username: "", password: this.newPassword})
        : console.log('The 2 Fields didnt match');
  }

}
</script>

<style scoped>

.form-label {
  display: grid;
  margin-right: 90pt;
  grid-auto-flow: column;
}

.login-step {
  margin: 1% auto;
}

</style>
