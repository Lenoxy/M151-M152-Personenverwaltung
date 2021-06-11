<template>
  <Card>
    <template #title>
      <h1 class="title">Set Password</h1>
    </template>
    <template #content>
      <div class="login-step">
        <p>Since you are logging in for the first time, you need to set a password first.</p>
        <label class="form-label">Your new password</label>
        <Password v-model="newPassword">
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
      </div>
      <div class="login-step">
        <label class="form-label">Repeat your password</label>
        <Password :feedback="false" v-model="confirmPassword"/>
      </div>
      <Button class="login-step" label="Set password" v-on:click="setNewPassword"/>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import AuthEndpoints from "../mixins/auth/AuthEndpoints";
import Header from "@/components/Header.vue";
import store from "@/store"
import router from '@/router';

@Options({
  components: {
    Header
  },
  props: {
    username: String
  }
})
export default class SetPassword extends Vue {

  private newPassword = "";
  private confirmPassword = "";

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

  validatePassword(): boolean {
    if (this.newPassword !== this.confirmPassword) {
      this.$toast.add({severity: 'warn', summary: 'The passwords don\'t match.', life: 3000})
      return false;
    } else if (this.newPassword.length < 8) {
      this.$toast.add({severity: 'warn', summary: 'The passwords length needs to be greater than eight ', life: 3000})
      return false;
    } else if (!this.newPassword.match(/(?=.*[a-z])/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one lowercase character', life: 3000})
      return false;
    } else if (!this.newPassword.match(/(?=.*[A-Z])/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one uppercase character', life: 3000})
      return false;
    } else if (!this.newPassword.match(/(?=.*\d)/)) {
      this.$toast.add({severity: 'warn', summary: 'Please include at least one digit', life: 3000})
      return false;
    }

    return true;
  }

  async setNewPassword(): Promise<void> {
    if (this.validatePassword()) {
      let jwt: string = await AuthEndpoints.methods.register({
        username: store.getters.getUsername,
        password: this.newPassword
      });
      store.commit('updateJwt', jwt)
      this.$toast.add({severity: 'success', summary: 'Welcome ' + store.getters.getUsername, life: 3000})
      await this.$router.push('list');
    }
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
