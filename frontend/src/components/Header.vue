<template>
  <div class="left-side-header">

    <Menubar v-bind:model="jwt === '' ? notAuthItems : authItems" class="menubar">
      <template #start>
        <div class="intro" v-on:click="routeToList">
          <img src="../assets/logo.svg" alt="logo">
          <h2>Person Management</h2>
        </div>
      </template>
    </Menubar>
  </div>
</template>

<script lang="ts">
import {PrimeIcons} from 'primevue/api';
import store from '@/store';
import router from '@/router';


export default {
  computed: {
    jwt: () => store.getters.getJwt
  },
  data: () => {
    return {
      notAuthItems: [
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
        }
      ],
      authItems: [
        {
          label: 'Home',
          icon: PrimeIcons.HOME,
          to: '/',
        },
        {
          label: 'Search',
          icon: PrimeIcons.SEARCH,
          to: '/list',
        },
        {
          label: 'Create person',
          icon: PrimeIcons.USER_PLUS,
          to: '/create',
        },
        {
          label: 'Profile',
          icon: PrimeIcons.USER,
          items: [
            {
              label: 'Edit Profile',
              to: '/edit',
              icon: PrimeIcons.USER_EDIT
            },
            {
              label: 'Change Password',
              to: '/set-password',
              icon: PrimeIcons.KEY
            },
            {
              label: 'Logout',
              command: () => {
                store.commit('logout');
                router.push('/')
              },
              icon: PrimeIcons.SIGN_OUT
            }
          ]
        },
      ],
    }
  },
  methods: {
    routeToList() {
      router.push('/list');
    }
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
}

img {
  height: 80px;
  margin: 0;
  padding: 0;
}
</style>
