<template>
  <label class="form-label">Your new password</label>
  <Password v-model="password">
    <template #header>
      <h6>Pick a password</h6>
    </template>
    <template #footer="sp">
      {{ sp.level }}
      <p class="p-mt-2">Requirements</p>
      <ul class="p-pl-2 p-ml-2 p-mt-0" style="line-height: 1.5">
        <li>At least one lowercase character</li>
        <li>At least one uppercase character</li>
        <li>At least one number</li>
        <li>At least eight characters</li>
      </ul>
    </template>
  </Password>
  <div class="login-step">
    <label class="form-label">Repeat your password</label>
    <Password :feedback="false" v-model="confirmPassword"/>
  </div>
  <Button class="login-step" label="Set password" v-on:click="send"/>

</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  emits: ["send"],
})
export default class RepeatPassword extends Vue {
  private password = "";
  private confirmPassword = "";

  send() {
    if (this.validatePassword()) {
      this.$emit('send', this.password)
    }
  }

  validatePassword(): boolean {
    if (this.password !== this.confirmPassword) {
      this.$toast.add({severity: 'warn', summary: 'The passwords don\'t match.', life: 3000})
      return false;
    } else if (this.password.length < 8) {
      this.$toast.add({severity: 'warn', summary: 'The passwords length needs to be greater than eight ', life: 3000})
      return false;
    } else if (!this.password.match(/(?=.*[a-z])/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one lowercase character', life: 3000})
      return false;
    } else if (!this.password.match(/(?=.*[A-Z])/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one uppercase character', life: 3000})
      return false;
    } else if (!this.password.match(/(?=.*\d)/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one digit', life: 3000})
      return false;
    }

    return true;
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
