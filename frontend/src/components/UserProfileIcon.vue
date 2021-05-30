<template>
  <div>
    <Button icon="pi pi-user" @click="visibleRight = true" class="sidebar"/>
    <Sidebar v-model:visible="visibleRight" :baseZIndex="1000" position="right">
      <h3>User Options</h3>
      <Menu :model="items"/>
    </Sidebar>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  data() {
    return {
      visibleRight: false,

      items: [{
        label: 'Edit profile',
        icon: 'pi pi-pencil',

      },
        {
          label: 'Change Pasword',
          icon: 'pi pi-key',
        },
        {
          label: 'Logout',
          icon: 'pi pi-sign-out',
        }
      ]
    }
  }
})

export default class UserProfileIcon extends Vue {
}
</script>

<style scoped>

.sidebar {
  float: right;
  fontSize: 2rem;
}
</style>
