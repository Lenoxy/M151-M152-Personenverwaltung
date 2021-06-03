<template>
  <div class="left-side-header">

    <Menubar :model="items" class="menubar">
      <template #start>
        <div class="intro">
          <img src="../assets/logo.svg" alt="logo">
          <h2>Person Management</h2>
        </div>
      </template>
      <template #end style="float: right">
        After
      </template>
    </Menubar>
  </div>
</template>

<script lang="ts">
import {Vue} from 'vue-class-component';
import {PrimeIcons} from 'primevue/api';
import store from '@/store';


export default class Header extends Vue {
  data() {
    return {
      items: [
        {
          label: 'Home',
          icon: PrimeIcons.HOME,
          to: '/',
          visible: true
        },
        {
          label: 'Login',
          icon: PrimeIcons.SIGN_IN,
          to: '/login',
          visible: !this.isAuthenticated()
        },
        {
          label: 'Search',
          icon: PrimeIcons.SEARCH,
          to: '/list',
          visible: this.isAuthenticated()
        },
        {
          label: 'Profile',
          icon: PrimeIcons.USER,
          visible: this.isAuthenticated(),
          items: [
            {
              label: 'Edit Profile',
              icon: PrimeIcons.USER_EDIT
            },
            {
              label: 'Change Password',
              icon: PrimeIcons.KEY
            },
            {
              label: 'Logout',
              icon: PrimeIcons.SIGN_OUT
            }
          ]
        },
      ]
    }
  }

  private isAuthenticated(): boolean {
    return store.getters.jwt !== "";
  }
}
</script>

<style scoped>
.intro {
  display: flex;
  flex-direction: row;
  align-items: center;
}

h2 {
  margin: 10px;
}

.menubar {
  width: 100%;
  margin: 15px;
}

img {
  height: 80px;
  margin: 0;
  padding: 0;
}
</style>
