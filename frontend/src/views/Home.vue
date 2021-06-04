<template>
  <div class="home">
    <h1>Fancy start page</h1>
    <p>With uncentered logo</p>
    <img alt="Vue logo" src="../assets/logo.svg" class="big">
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
</style>
