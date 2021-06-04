<template>
  <Card>
    <template #title>
      <h1 class="title">Person List</h1>
    </template>
    <template #content>
      <div class="content">
        <Dropdown :options="userDataFilter" v-model="property" optionLabel="name" :filter="true" placeholder="Filter by"
                  class="filter" :showClear="true">
          <template #value="slotProps">
            <div v-if="slotProps.value">
              <div>{{ slotProps.value.name }}</div>
            </div>
            <span v-else>
            {{ slotProps.placeholder }}
          </span>
          </template>
          <template #option="slotProps">
            <div>
              <div>{{ slotProps.option.name }}</div>
            </div>
          </template>
        </Dropdown>
        <Listbox v-model="value" :options="employees" v-on:input="searchPerson" :multiple="false" :filter="true"
                 :optionLabel="property" listStyle="max-height:250px" class="search" filterPlaceholder="Search">

          <template #option="slotProps">
            <div>
              <div>{{ slotProps.option.name }}</div>
              <div>{{ slotProps.option.lastname }}</div>
            </div>
          </template>
        </Listbox>
      </div>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";
import {QueryPersonDto} from "@/mixins/person/dto/query.person.dto";

@Options({
  data() {
    return {
      userDataFilter: [
        {name: 'firstname'},
        {name: 'lastname'},
        {name: 'email'},
        {name: 'address'}
      ]
    }
  }
})

export default class PersonList extends Vue {
  private property = "";
  private value = "";
  private employees: QueryPersonDto[] = [];

  async searchPerson(): Promise<void> {
    console.log(this.value)
    console.log("changed", this.property, this.value)
    this.employees = await PersonEndpoints.methods.getQuery(this.property, this.value);
    console.log(this.employees)
  }
  created(){
    this.searchPerson();
  }
}
</script>

<style scoped>
.title {
  display: block;
  margin: 5% auto;
}

.content {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: auto;
  width: 80%;
}

.search {
  flex-grow: 9;
}

.filter {
  float: left;
  height: 20%;
  flex-grow: 1;
}
</style>
