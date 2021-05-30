<template>
  <Header></Header>
  <router-view/>

</template>

<script lang="ts">

import {Options, Vue} from "vue-class-component";
import Header from "@/components/Header.vue";

@Options({
  components: {
    Header
  }
})

export default class App extends Vue {}
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
  font-size: 16pt;
}

.title {
  display: block;
  margin: 5% auto;
}
.form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 50%;
  margin: auto;
}
@media screen and (max-width: 500px) {
  .form {
    display: flex;
    justify-content: flex-start;
    margin: 0 0 0 0;
    width: 100%;
  }
}
</style>
