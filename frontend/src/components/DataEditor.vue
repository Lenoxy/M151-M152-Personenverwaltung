<template>
  <section class="center-grid">
    <div class="form" v-if="!loading">
      <label class="normal-label">Firstname:</label>
      <InputText type="text" class="normal-input" placeholder="John" v-model="person.firstname"
                 :class="{'p-invalid': validations.includes('FIRSTNAME_INVALID')}"/>

      <label class="normal-label">Lastname:</label>
      <InputText type="text" class="normal-input" placeholder="Doe" v-model="person.lastname"
                 :class="{'p-invalid': validations.includes('LASTNAME_INVALID')}"/>

      <label class="normal-label">Email:</label>
      <InputText type="email" class="normal-input" placeholder="example@xy.com" v-model="person.email"
                 :class="{'p-invalid': validations.includes('EMAIL_INVALID')}"/>

      <label class="normal-label">Address:</label>
      <div class="street-number flex">
        <InputText type="text" class="address-big normal-input" placeholder="Downing street"
                   v-model="person.address.street"
                   :class="{'p-invalid': validations.includes('ADDRESS_STREET_INVALID')}"/>
        <InputText type="text" class="address-small normal-input" placeholder="10" v-model="person.address.number"
                   :class="{'p-invalid': validations.includes('ADDRESS_NUMBER_INVALID')}"/>
      </div>
      <div/>
      <div class="city-zipcode flex">
        <InputText class="address-big normal-input" type="text" placeholder="London" v-model="person.address.city"
                   :class="{'p-invalid': validations.includes('ADDRESS_CITY_INVALID')}"/>
        <InputText class="address-small normal-input" type="text" placeholder="9215"
                   v-model="person.address.zipcode"
                   :class="{'p-invalid': validations.includes('ADDRESS_ZIPCODE_INVALID')}"/>
      </div>
      <label class="normal-label">Phone:</label>
      <InputText type="text" class="normal-input" placeholder="111 111 1111" v-model="person.phonenumber"
                 :class="{'p-invalid': validations.includes('PHONENUMBER_INVALID')}"/>
      <label class="normal-label">Position:</label>
      <InputText type="text" class="normal-input" placeholder="librarian" v-model="person.position"
                 :class="{'p-invalid': validations.includes('POSITION_INVALID')}" v-bind:disabled="!person.admin"/>
      <label class="normal-label">Username:</label>
      <InputText type="text" class="normal-input" placeholder="john.doe" v-model="person.username"
                 :class="{'p-invalid': validations.includes('USERNAME_INVALID')}" v-bind:disabled="!person.admin"/>
      <label class="normal-label">Admin:</label>
      <Checkbox v-model="person.admin" :binary="true" disabled/>
      <div class="action-button-container">
        <Button
            class="p-button-danger action-button"
            label="Delete"
            v-on:click="deletePerson"
            v-bind:disabled="isCurrentUser()"/>
        <Button class="save action-button" label="Save" v-on:click="editPerson"/>

      </div>
    </div>
  </section>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";
import {GetPersonDto} from "@/mixins/person/dto/get.person.dto";
import router from '@/router';
import store from '@/store'

@Options({
  props: {
    id: String
  }
})

export default class DataEditor extends Vue {

  private validations: string[] = [];
  private id = '';
  private person!: GetPersonDto;
  private loading = true;

  created() {
    this.id = this.$route.params.id.toString();
    PersonEndpoints.methods.getPersonById(this.id).then((p) => {
      this.person = p;
      this.loading = false;
    });
  }

  async editPerson(): Promise<void> {
    this.validations = await PersonEndpoints.methods.editPerson(this.id, {
      firstname: this.person.firstname,
      lastname: this.person.lastname,
      email: this.person.email,
      address: {
        street: this.person.address.street,
        number: "2",
        zipcode: this.person.address.zipcode,
        city: this.person.address.city
      },
      phonenumber: this.person.phonenumber,
      position: this.person.position,
      isAdmin: this.person.admin,
    })
  }

  async deletePerson(): Promise<void> {

    this.$confirm.require({
      message: 'Are you sure you want to delete "' + this.person.firstname + ' ' + this.person.lastname + '"?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: async () => {
        await PersonEndpoints.methods.removePerson(this.id)
        await this.$toast.add({severity: 'success', summary: 'User "' + this.person.firstname + ' ' + this.person.lastname + '" has been deleted successfully.', life: 3000})
        await router.push('/list')
      }
    });
  }

  isCurrentUser(): boolean{
    return store.getters.getJwtData.id === this.id;
  }

  isAdmin(person: GetPersonDto): boolean {
    return person.admin;
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

.action-button-container {

}

.action-button {
  margin: 3px;
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
