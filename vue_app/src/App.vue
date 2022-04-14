<template>
  <div id="app">
   
    <router-view />
  </div>
</template>

<script>
import Login from "./views/Login.vue";
import News from "./views/News.vue";
export default {
  components: { Login },
  name: "App",
};
</script>


