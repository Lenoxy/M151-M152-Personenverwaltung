<template>
  <DataViewer></DataViewer>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import DataViewer from "@/components/DataViewer.vue";


@Options({
  components: {
    DataViewer
  }
})

export default class PersonDetail extends Vue {

}
</script>

<style scoped>

</style>
