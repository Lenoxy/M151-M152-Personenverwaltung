<template>
  <div class="inputs">
    <InputText type="text" class="normal-input"/>
    <InputText type="text" class="normal-input"/>
    <InputText type="email" class="normal-input"/>
    <div class="address-inputs">
      <InputText type="text" class="street"/>
      <InputText class="city" type="text"/>
      <InputText class="zip" type="text"/>
    </div>
    <InputNumber id="withoutgrouping" mode="decimal" :useGrouping="false" class="normal-input"/>
    <InputText type="text" class="normal-input"/>
    <InputText type="text" class="normal-input"/>
    <Button class="save" label="Save"/>
  </div>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";

export default class DataEditor extends Vue {

}
</script>

<style scoped>
.inputs {
  display:flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 100%;
}

.normal-input{
  flex-grow: 1;
  margin-bottom: 10pt;
  width: 100%;
}

.address-inputs {
  display: flex;
  flex-wrap: wrap;
  flex-grow: 3;
  margin-bottom: 10pt;
}

.city{
  width:70%;
}

.zip {
  width:30%;
}

.street{
  width: 100%;
}

@media screen and (max-width: 500px) {
  .inputs {
    width: 70%;
  }
  .normal-input {
    width:90%;
  }
}
</style>
