<template>
  <div class="login_box">
    <van-button type="info" class="van_button" @click="loginClick()"
      >进入
    </van-button>
      <van-button type="info" class="van_button" @click="dBClick()"
      >DBHelper插件
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

    },
    dBClick(){
       this.$router.push({ name: "/DB" });
    }
  },
};
</script>

<style lang="css" scoped>
.van_button {
  display: block;
  margin: 0 auto;
  margin-top: 5rem;
  background-color: chocolate;
  border: 0rem;
}
</style>