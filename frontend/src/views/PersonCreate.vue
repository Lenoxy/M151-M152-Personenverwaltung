<template>
  <Card>
    <template #title>
      <h1 class="title">Create User</h1>
    </template>
    <template #content>
      <div class="form">
        <DataTitle></DataTitle>
        <DataEditor></DataEditor>
      </div>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataTitle from "@/components/DataTitle.vue";
import DataEditor from "@/components/DataCreator.vue";

@Options({
  components: {
    DataTitle,
    DataEditor
  }
})

export default class PersonCreate extends Vue{

}
</script>

<style scoped>


</style>
