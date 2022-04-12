<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-link to="login"/>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

