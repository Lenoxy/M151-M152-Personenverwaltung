<template>
  <md-field>
    <label>Username</label>
    <md-input v-model="username"></md-input>
  </md-field>
  <md-field>
    <label>Password</label>
    <md-input type="password" v-model="password"></md-input>
  </md-field>
</template>

<script lang="ts">
import {Vue} from 'vue-class-component';


export default class Login extends Vue {
   private username = "" as string;
   private password = "" as string;



}

</script>

<style scoped>

</style>
