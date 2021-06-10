<template>
  <div class="inputs">
    <InputText type="text" class="normal-input" placeholder="John" v-model="firstname" :class="{'p-invalid': validations.includes('FIRSTNAME_INVALID')}"/>
    <InputText type="text" class="normal-input" placeholder="Doe" v-model="lastname" :class="{'p-invalid': validations.includes('LASTNAME_INVALID')}"/>
    <InputText type="email" class="normal-input" placeholder="example@xy.com" v-model="email" :class="{'p-invalid': validations.includes('EMAIL_INVALID')}"/>
    <div class="address-inputs">
      <InputText type="text" class="street" placeholder="downing street 10" v-model="street" :class="{'p-invalid': validations.includes('ADDRESS_STREET_INVALID')}"/>
      <InputText class="city" type="text" placeholder="London" v-model="city" :class="{'p-invalid': validations.includes('ADDRESS_CITY_INVALID')}"/>
      <InputText class="zip" type="text" placeholder="9215" v-model="zip" :class="{'p-invalid': validations.includes('ADDRESS_ZIPCODE_INVALID')}"/>
    </div>
    <InputText type="text" class="normal-input" placeholder="111 111 1111" v-model="phone" :class="{'p-invalid': validations.includes('PHONENUMBER_INVALID')}"/>
    <InputText type="text" class="normal-input" placeholder="librarian" v-model="job" :class="{'p-invalid': validations.includes('POSITION_INVALID')}"/>
    <InputText type="text" class="normal-input" :class="{'p-invalid': validations.includes('USERNAME_INVALID')}" placeholder="john.doe" v-model="username"/>
    <Checkbox v-model="isAdmin" :binary="true" />
    <label>Admin</label>
    <Button class="save" label="Save" v-on:click="createPerson"/>
  </div>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";

export default class DataEditor extends Vue {
  private firstname = "";
  private lastname = "";
  private email = "";
  private street = "";
  private city = "";
  private zip = "";
  private phone = "";
  private job = "";
  private isAdmin = false;
  private username = "";
  private validations: string[] = [];


  async createPerson(): Promise<void> {
    this.validations = (await PersonEndpoints.methods.createPerson({
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      address: {street: this.street, number: "2", zipcode: this.zip, city: this.city},
      phonenumber: this.phone,
      position: this.job,
      isAdmin: this.isAdmin,
      username: this.username
    }))
    console.log(this.validations);
  }
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
