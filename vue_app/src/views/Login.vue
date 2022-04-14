<template>
  <div class="login_box">
    <van-button type="info" class="van_button" @click="loginClick()"
      >进入
    </van-button>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: function () {
    return {};
  },
  mounted() {},
  methods: {
    loginClick() {
      this.$router.push({ name: "/News" });
      // cordova.plugins.DBHelper.coolMethod("hello", onSuccess(), onError());
    },
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