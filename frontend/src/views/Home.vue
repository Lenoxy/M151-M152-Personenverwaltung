<template>
  <div class="home">
    <h1 class="title">Start page</h1>
    <img alt="Vue logo" src="../assets/logo.svg" class="big">
    <h1 class="title">Roles</h1>
    <img alt="Admin" src="../assets/admin_pixel.png" class="role">
    <h1 class="title">Admin</h1>
    <img alt="User" src="../assets/user_pixel.png" class="role">
    <h1 class="title">User</h1>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import Header from '@/components/Header.vue';

@Options({
  components: {
    Header
  }
})
export default class Home extends Vue {}
</script>
<style scoped>
  .big{
    width: 100%;
  }

  .title {
    width: 100%;
    font-size: 50pt;
    margin: auto;
  }
</style>
