<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.svg">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import Header from '@/components/Header.vue';

@Options({
  components: {
    HelloWorld,
    Header
  }
})
export default class Home extends Vue {}
</script>
