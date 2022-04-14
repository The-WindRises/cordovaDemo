import Vue from 'vue'
<template>
  <div class="login_box">
    <van-button
      type="info"
      class="van_button"
      @click="initDBHelper('123213wdssds')"
      >进入</van-button
    >
  </div>
</template>

<script>
export default {
  name: "Login",
  data: {},
  mounted() {
    alert("123");
  },
  methods: {
    initDBHelper: function (message) {
      alert(message);
      cordova.plugins.DBHelper.coolMethod("hello", onSuccess(), onError());
    },
    onSuccess: function () {},
    onerror: function () {},
  },
};
</script>

<style lang="css" scoped>

.van_button {
  display: block;
  margin: 0 auto;
  margin-top: 20rem;
  background-color: chocolate;
  border: 0rem;
}
</style>