<template>
  <div class="login-step">
    <label class="form-label">Enter your current password</label>
    <Password :feedback="false" v-model="oldPassword"/>
  </div>
  <RepeatPassword v-on:send="send"></RepeatPassword>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import RepeatPassword from '@/components/RepeatPassword.vue';
import AuthEndpoints from '@/mixins/auth/AuthEndpoints';
import router from '@/router';

@Options({
  components: {
    RepeatPassword
  }
})
export default class ChangePassword extends Vue {
  private oldPassword = "";
  async send(newPassword: string) {
    if(
        this.oldPassword.length < 8 ||
        !this.oldPassword.match(/(?=.*[a-z])/) ||
        !this.oldPassword.match(/(?=.*[A-Z])/) ||
        !this.oldPassword.match(/(?=.*\d)/)
    ){
      this.$toast.add({severity: 'warn', summary: 'Your current password is wrong', life: 3000})
      return;
    }
    let success = false;
    try{
      success = await AuthEndpoints.methods.resetPassword({oldPassword: this.oldPassword, newPassword: newPassword});
    }catch (e){
      this.$toast.add({severity: 'error', summary: 'Something went wrong while changing your password', detail: 'Check if your current password is correct', life: 3000});
    }
    if(success){
      this.$toast.add({severity: 'success', summary: 'Your password has been changed successfully.', life: 3000});
      await router.push('/list');
    }

  }
}
</script>

<style scoped>
.login-step {
  margin: 1% auto;
}
.form-label {
  display: block;
  margin-right: 80pt;
}

</style>
