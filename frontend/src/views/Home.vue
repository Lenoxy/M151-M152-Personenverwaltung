<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.svg">
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
