<template>
  <Card>
    <template #title>
      <h1 class="title">Edit User</h1>
    </template>
    <template #content>
        <DataEditor></DataEditor>
    </template>
  </Card>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataEditor from "@/components/DataEditor.vue";

@Options({
  components: {
    DataEditor,
  }
})

export default class PersonEdit extends Vue {
}

</script>

<style scoped>

</style>
