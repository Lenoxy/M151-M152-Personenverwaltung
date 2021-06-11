<template>
  <Card>
    <template #title>
      <h1 class="title">Create User</h1>
    </template>
    <template #content>
        <DataCreator></DataCreator>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataCreator from "@/components/DataCreator.vue";

@Options({
  components: {
    DataCreator
  }
})

export default class PersonCreate extends Vue{

}
</script>

<style scoped>


</style>
