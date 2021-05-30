<template>
  <div class="labels">
    <label class="normal-label">Firstname:</label>
    <label class="normal-label">Lastname:</label>
    <label class="normal-label">Email:</label>
    <label class="address-label">Address:</label>
    <div class="after-address-labels">
      <label class="normal-label">Phone:</label>
      <label class="normal-label">Position:</label>
      <label class="normal-label">Username:</label>
    </div>
  </div>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";

export default class DataTitle extends Vue{}
</script>

<style scoped>
.labels {
  display: flex;
  flex-direction: column;
}

.normal-label {
  flex-grow: 1;
  margin-top: 3pt;

}
.address-label {
  flex-grow: 2;
}

.after-address-labels {
  display: flex;
  flex-direction: column;
  flex-grow: 3;
  margin-bottom: 20pt;
}
</style>
