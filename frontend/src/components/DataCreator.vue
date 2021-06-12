<template>
  <section class="center-grid">
    <div class="form">
      <label class="normal-label">Firstname:</label>
      <InputText type="text" class="normal-input" placeholder="John" v-model="firstname"
                 :class="{'p-invalid': validations.includes('FIRSTNAME_INVALID')}"/>

      <label class="normal-label">Lastname:</label>
      <InputText type="text" class="normal-input" placeholder="Doe" v-model="lastname"
                 :class="{'p-invalid': validations.includes('LASTNAME_INVALID')}"/>

      <label class="normal-label">Email:</label>
      <InputText type="email" class="normal-input" placeholder="example@xy.com" v-model="email"
                 :class="{'p-invalid': validations.includes('EMAIL_INVALID')}"/>

      <label class="normal-label">Address:</label>
      <div class="street-number flex">
        <InputText type="text" class="address-big normal-input" placeholder="Downing street"
                   v-model="street"
                   :class="{'p-invalid': validations.includes('ADDRESS_STREET_INVALID')}"/>
        <InputText type="text" class="address-small normal-input" placeholder="10" v-model="number"
                   :class="{'p-invalid': validations.includes('ADDRESS_NUMBER_INVALID')}"/>
      </div>
      <div/>
      <div class="city-zipcode flex">
        <InputText class="address-big normal-input" type="text" placeholder="London" v-model="city"
                   :class="{'p-invalid': validations.includes('ADDRESS_CITY_INVALID')}"/>
        <InputText class="address-small normal-input" type="text" placeholder="9215"
                   v-model="zipcode" :class="{'p-invalid': validations.includes('ADDRESS_ZIPCODE_INVALID')}"/>
      </div>
      <label class="normal-label">Phone:</label>
      <InputText type="text" class="normal-input" placeholder="111 111 1111" v-model="phonenumber"
                 :class="{'p-invalid': validations.includes('PHONENUMBER_INVALID')}"/>
      <label class="normal-label">Position:</label>
      <InputText type="text" class="normal-input" placeholder="librarian" v-model="position"
                 :class="{'p-invalid': validations.includes('POSITION_INVALID')}"/>
      <label class="normal-label">Username:</label>
      <InputText type="text" class="normal-input" placeholder="john.doe" v-model="username"
                 :class="{'p-invalid': validations.includes('USERNAME_INVALID')}"/>
      <label class="normal-label">Admin:</label>
      <Checkbox v-model="isAdmin" :binary="true"/>
      <Button class="save" label="Save" v-on:click="createPerson"/>
    </div>
  </section>
</template>

<script lang="ts">
import {Vue} from "vue-class-component";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";
import router from '@/router';

export default class DataEditor extends Vue {
  private firstname = "";
  private lastname = "";
  private email = "";
  private street = "";
  private number = "";
  private city = "";
  private zipcode = "";
  private phonenumber = "";
  private position = "";
  private isAdmin = false;
  private username = "";
  private validations: string[] = [];


  async createPerson(): Promise<void> {
    let person = {
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      address: {street: this.street, number: this.number, zipcode: this.zipcode, city: this.city},
      phonenumber: this.phonenumber,
      position: this.position,
      isAdmin: this.isAdmin,
      username: this.username
    };
    try {
      this.validations = await PersonEndpoints.methods.createPerson(person)
      await router.push('/list');
    }catch (e) {
      this.$toast.add({severity: 'warn', summary: 'This username or email already exists ', life: 3000})
    }
  }
}
</script>

<style scoped>
.center-grid {
  display: flex;
  justify-content: center;
}

.form {
  display: grid;
  grid-template-columns: 200px 500px;
  grid-template-rows: repeat(8, 50px);
}

.normal-label {
  grid-column: 1;
  align-self: center;
  text-align: left;
}

.normal-input {
  height: 30px;
  grid-column: 2;
  align-self: center;

  text-align: left;
}

.flex {
  display: flex;
  flex-direction: row;

}

.address-big {
  height: 30px;
  width: 100%;
}

.address-small {
  height: 30px;
  width: 80px;
}

.save {
  height: 30px;
  width: 80px;
  justify-content: right;
}


@media screen and (max-width: 750px) {
  .form {
    display: grid;
    grid-template-columns: 100px 250px;
    grid-template-rows: repeat(8, 50px);
  }
}
</style>
