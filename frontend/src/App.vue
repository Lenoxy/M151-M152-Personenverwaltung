<template>
  <ConfirmDialog/>
  <Toast/>
  <Header/>
  <router-view :key="$route.fullPath"/>

</template>

<script lang="ts">

import {Options, Vue} from "vue-class-component";
import Header from "@/components/Header.vue";

@Options({
  components: {
    Header
  }
})

export default class App extends Vue {
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

Card {
  width: 80%;
  margin: 20% auto;
}

h1 {
  font-size: 20pt;
}

h2 {
  font-size: 16pt;
}

h3 {
  font-size: 12pt;
}

</style>
