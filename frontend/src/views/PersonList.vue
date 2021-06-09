<template>
  <div>
    <!--    <template #title>-->
    <!--      <h1 class="title">Person List</h1>-->
    <!--    </template>-->
    <div class="card">
      <DataTable :value="employees" :paginator="true" class="p-datatable-customers" :rows="10"
                 dataKey="id" v-model:filters="filter" filterDisplay="row" :loading="loading"
                 responsiveLayout="scroll"
                 :globalFilterFields="['name','email','isAdmin']">
        <template #header>
          <div class="p-d-flex p-jc-end">
            <span class="p-input-icon-left ">
                <i class="pi pi-search"/>
                <InputText v-model="filter['global'].value" placeholder="Keyword Search"/>
            </span>
          </div>
        </template>
        <template #empty>
          No employees found.
        </template>
        <template #loading>
          Loading employees. Please wait.
        </template>
        <Column field="name" header="Name" style="min-width:12rem">
          <template #body="{data}">
            {{ data.name }}
          </template>
          <template #filter="{filterModel,filterCallback}">
            <InputText type="text" v-model="filterModel.value" @keydown.enter="filterCallback()" class="p-column-filter"
                       :placeholder="`Search by name - `" v-tooltip.top.focus="'Hit enter key to filter'"/>
          </template>
        </Column>
        <Column header="Email" filterField="email" style="min-width:12rem">
          <template #body="{data}">
            <span class="email-text">{{ data.email }}</span>
          </template>
          <template #filter="{filterModel,filterCallback}">
            <InputText type="text" v-model="filterModel.value" @input="filterCallback()" class="p-column-filter"
                       placeholder="Search by email" v-tooltip.top.focus="'Filter as you type'"/>
          </template>
        </Column>
        <Column field="isAdmin" header="Is Admin" dataType="boolean" style="min-width:6rem">
          <template #body="{data}">
            <i class="pi"
               :class="{'true-icon pi-check-circle': data.isAdmin, 'false-icon pi-times-circle': !data.isAdmin}"></i>
          </template>
          <template #filter="{filterModel,filterCallback}">
            <TriStateCheckbox v-model="filterModel.value" @change="filterCallback()"/>
          </template>
        </Column>
      </DataTable>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import PersonEndpoints from "@/mixins/person/PersonEndpoints";
import {QueryPersonDto} from "@/mixins/person/dto/query.person.dto";
import {FilterMatchMode} from "primevue/api";

@Options({
  data() {
    return {
      filter: {
        'global': {value: null, matchMode: FilterMatchMode.CONTAINS},
        'name': {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        'email': {value: null, matchMode: FilterMatchMode.CONTAINS},
        'isAdmin': {value: null, matchMode: FilterMatchMode.EQUALS}
      },
      loading: false
    }
  }
})

export default class PersonList extends Vue {
  private employees: QueryPersonDto[] = [];

  async searchPerson(): Promise<void> {
    this.employees = await PersonEndpoints.methods.queryAll();
    console.log(this.employees)
  }

  created() {
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
