<template>
  <div>
    <van-button type="primary" @click="initDB">创建数据库</van-button>
    <van-button type="success" @click="createTable">创建用户表</van-button>
    <van-button type="default" @click="insertUser">添加用户</van-button>
    <van-button type="warning">删除用户</van-button>
    <van-button type="danger">修改用户</van-button>
    <van-button type="danger">查询用户</van-button>
  </div>
</template>
<script>
export default {
  name: "DB",
  data: function () {
    return {};
  },
  mounted() {},
  methods: {
    initDB() {
      SqliteHelper.initDB(
        "initDB",
        function (msg) {
          alert(msg);
        },
        function (msg) {
          alert(msg);
        }
      );
    },
    createTable() {
      SqliteHelper.createTable(
        "",
        function (msg) {
          alert(msg);
        },
        function (msg) {
          alert(msg);
        }
      );
    },
    insertUser() {
      SqliteHelper.insertUser(
        "",
        function (msg) {
          alert(msg);
        },
        function (msg) {
          alert(msg);
        }
      );
    },
  },
};
</script>
<style scoped>
</style>