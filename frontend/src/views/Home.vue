<template>
  <div class="home">
    <h1>Welcome to Person Management</h1>
    <h2>Speeding up your workflow!</h2>
    <h1 class="title">Roles</h1>
    <h2>Admin</h2>
    <img alt="Admin" src="../assets/admin_pixel.png" class="role" height="100">
    <p>The admin can create, edit or delete other users. Ideally this role can be given to your recruiter or HR
      department.</p>
    <h2>User</h2>
    <img alt="User" src="../assets/user_pixel.png" class="role" height="100">
    <p>The User role can look up other users and edit his own basic information.</p>
    <h1 class="title">The effects</h1>
    <p>Using our software within your company has a proven positive effect on your employees.</p>
    <!-- Source: https://pixabay.com/photos/children-win-success-video-game-593313/ -->
    <img alt="Happy children" src="../assets/happy.jpg" class="happy-image">
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component';
import Header from '@/components/Header.vue';

@Options({
  components: {
    Header
  }
})
export default class Home extends Vue {
}
</script>
<style scoped>
.title {
  width: 100%;
  font-size: 30pt;
  margin: auto;
}

.happy-image{
  width: 80%;
}
</style>
