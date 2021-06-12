<template>
  <Card>
    <template #title>
      <h1 v-if="!loading" class="title">{{ person.firstname }} {{ person.lastname }}</h1>
    </template>
    <template #content>
      <section class="center-grid">
        <div class="form" v-if="!loading">
          <label class="normal-label">Firstname:</label>
          <InputText type="text" class="normal-input" placeholder="John" v-model="person.firstname" disabled/>

          <label class="normal-label">Lastname:</label>
          <InputText type="text" class="normal-input" placeholder="Doe" v-model="person.lastname" disabled/>

          <label class="normal-label">Email:</label>
          <InputText type="email" class="normal-input" placeholder="example@xy.com" v-model="person.email" disabled/>

          <label class="normal-label">Address:</label>
          <div class="street-number flex">
            <InputText type="text" class="address-big normal-input" placeholder="Downing street"
                       v-model="person.address.street"
                       disabled/>
            <InputText type="text" class="address-small normal-input" placeholder="10" v-model="person.address.number"
                       disabled/>
          </div>
          <div/>
          <div class="city-zipcode flex">
            <InputText class="address-big normal-input" type="text" placeholder="London" v-model="person.address.city"
                       disabled/>
            <InputText class="address-small normal-input" type="text" placeholder="9215"
                       v-model="person.address.zipcode" disabled/>
          </div>
          <label class="normal-label">Phone:</label>
          <InputText type="text" class="normal-input" placeholder="111 111 1111" v-model="person.phonenumber" disabled/>
          <label class="normal-label">Position:</label>
          <InputText type="text" class="normal-input" placeholder="librarian" v-model="person.position" disabled/>
          <label class="normal-label">Username:</label>
          <InputText type="text" class="normal-input" placeholder="john.doe" v-model="person.username" disabled/>
          <label class="normal-label">Admin:</label>
          <Checkbox v-model="person.admin" :binary="true" disabled/>
        </div>
      </section>
    </template>
  </Card>
</template>

<script lang = "ts">
import {Vue} from "vue-class-component";
import {GetPersonDto} from "@/mixins/person/dto/get.person.dto";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";

export default class DataViewer extends Vue {
  private person!: GetPersonDto;
  private loading = true;

  created() {
    let id: string = this.$route.params.id.toString();
    PersonEndpoints.methods.getPersonById(id).then((p) => {
      this.person = p;
      this.loading = false;
    });

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

@media screen and (max-width: 750px) {
  .form {
    display: grid;
    grid-template-columns: 100px 250px;
    grid-template-rows: repeat(8, 50px);
  }
}
</style>
