<template>
  <div class="left-side-header">
    <img src="../assets/logo.svg" alt="logo"><h1>Person Management</h1>
  </div>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";

export default class Header extends Vue {}
</script>

<style scoped>

.left-side-header {
  display: flex;
  float: left;
  width: 100%;

}
img {
  width: 128px;
  height: 128px;
}
</style>
