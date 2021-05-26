<template>
  <Card class="card">
    <template #title>
      <h1 class="title">Set Password</h1>
    </template>
    <template #content>
  <div class="login-step">
    <label class="form-label">Password</label>
    <Password v-model="password">
      <template #header>
        <h6>Pick a password</h6>
      </template>
      <template #footer="sp">
        {{sp.level}}
        <Divider />
        <p class="p-mt-2">Suggestions</p>
        <ul class="p-pl-2 p-ml-2 p-mt-0" style="line-height: 1.5">
          <li>At least one lowercase</li>
          <li>At least one uppercase</li>
          <li>At least one numeric</li>
          <li>Minimum 8 characters</li>
        </ul>
      </template>
    </Password>
  </div>
      <Button class="login-step" label="Set password"/>
    </template>
  </Card>
</template>

<script>
export default {
  name: "SetPassword"
}
</script>

<style scoped>
.form-component {
  display: block;
}

.form-label {
  display: block;
  margin-right: 80pt;
}

.login-step {
  margin: 5% 5% auto auto;
}

.title {
  display: block;
  margin: 5% 5% auto auto;
}

</style>
