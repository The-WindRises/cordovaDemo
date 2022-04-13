import Vue from 'vue'
<template id="login_page">
  <van-button type="info" class="van-button" 
    >进入</van-button
  >
</template>

<script>

export default {
  name: "Login",
};
// var app = new Vue({
//   // element的缩写，挂载元素
//   el: "#login_page",
//   data: {},
//   mounted() {
//           alert("123");
//   },
//   methods: {
    
//     initDBHelper: function () {
//       alert("123");
//       cordova.plugins.DBHelper.coolMethod("hello", onSuccess, onError);
//     },
//     onSuccess: function () {},
//     onerror: function () {},
//   },
// });
</script>

<style lang="css" scoped>
.login_box {
  text-align: center;
  background-color: blue;
}
.van-button {
  display: block;
  margin: 0 auto;
  margin-top: 200px;
  background-color: chocolate;
}
#login_page {
  background: blue;
}
</style>