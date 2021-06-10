<template>
  <Card>
    <template #title>
      <h1 class="title">Person XY Profile</h1>
    </template>
    <template #content>
      <div class="form">
        <DataViewer :id="id"></DataViewer>
      </div>
    </template>
  </Card>

</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataTitle from "@/components/DataTitle.vue";
import DataViewer from "@/components/DataViewer.vue";
import PersonEndpoints from '@/mixins/person/PersonEndpoints';
import {GetPersonDto} from '@/mixins/person/dto/get.person.dto';


@Options({
  components: {
    DataTitle,
    DataViewer
  }
})

export default class PersonDetail extends Vue {
  private id = "";

  async created() {
    this.id = this.$route.params.id.toString();
    console.log(this.id)
    let person: GetPersonDto = await PersonEndpoints.methods.getPersonById(this.id);

    console.log(person)
  }

}
</script>

<style scoped>


</style>
