<template>
  <UserProfileIcon></UserProfileIcon>
  <Card>
    <template #title>
      <h1 class="title">Person XY Profile</h1>
    </template>
    <template #content>
      <div class="form">
        <DataTitle></DataTitle>
        <DataViewer></DataViewer>
      </div>
    </template>
  </Card>

</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataTitle from "@/components/DataTitle.vue";
import DataViewer from "@/components/DataViewer.vue";


@Options({
  components: {
    DataTitle,
    DataViewer
  }
})

export default class PersonDetail extends Vue{

}
</script>

<style scoped>


</style>
