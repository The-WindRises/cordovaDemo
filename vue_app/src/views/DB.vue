<template>
  <div>
    <div class="action_box">
      <van-button type="primary" @click="initDB">初始化数据库</van-button>
      <van-button type="success" @click="createTable">创建用户表</van-button>
    </div>

    <div class="action_box">
      <van-cell-group>
        <van-field
          v-model="insertusername"
          label="用户名"
          placeholder="请输入用户名"
        />
        <van-cell
          title="点击选择生日"
          :value="insertbirthday"
          @click="showCalendar = true"
        />
      </van-cell-group>

      <van-calendar v-model="showCalendar" @confirm="oninsertBirthday" />

      <van-button type="danger" @click="insertUser">添加用户</van-button>
    </div>

    <div class="action_box">
      <van-cell-group>
        <van-field v-model="updateid" label="id" placeholder="请输入修改的id" />
        <van-field
          v-model="updateusername"
          label="用户名"
          placeholder="请输入修改的用户名"
        />
        <van-cell
          title="点击选择生日"
          :value="updatebirthday"
          @click="showupdateCalendar = true"
        />
      </van-cell-group>

      <van-calendar v-model="showupdateCalendar" @confirm="onupdateBirthday" />
      <van-button type="danger" @click="updateUser">修改用户</van-button>
    </div>

    <div class="action_box">
      <van-button type="danger" @click="queryAll">查询用户</van-button>
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="queryAll"
      >
        <van-cell
          v-for="item in userList"
          :key="item.id"
          @click="deleteUser(item.id)"
        >
          <span>用户id：{{ item.id }} </span>
          <span>用户名：{{ item.username }} </span>
          <span>生日：{{ item.birthday }} </span>
          <span>点击删除</span>
        </van-cell>
      </van-list>
    </div>
  </div>
</template>
<script>
export default {
  name: "DB",
  data: function () {
    return {
      updateid: "",
      updateusername: "",
      updatebirthday: "",
      showupdateCalendar: false,
      insertusername: "",
      insertbirthday: "",
      showCalendar: false,
      userList: [],
      loading: false,
      finished: false,
      temList: [],
    };
  },
  mounted() {
    this.queryAll;
  },
  methods: {
    oninsertBirthday(date) {
      this.insertbirthday = `${date.getMonth() + 1}/${date.getDate()}`;
      this.showCalendar = false;
    },
    onupdateBirthday(date) {
      this.updatebirthday = `${date.getMonth() + 1}/${date.getDate()}`;
      this.showupdateCalendar = false;
    },
    initDB() {
      SqliteHelper.initDB(
        "",
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
        this.error
      );
    },
    insertUser() {
      SqliteHelper.insert(
        [this.insertusername, this.insertbirthday],
        this.queryAll,
        this.error
      );
    },
    error(msg) {
      alert(msg);
    },
    queryAll() {
      this.loading = true;
      this.finished = false;
      SqliteHelper.query("", this.querySuccess, this.error);
    },
    querySuccess(msg) {
      this.userList = msg;
      this.loading = false;
      this.finished = true;
    },
    deleteUser(id) {
      SqliteHelper.delete([id], this.queryAll, this.error);
    },
    updateUser() {
      SqliteHelper.update(
        [this.updateid, this.updateusername, this.updatebirthday],
        this.queryAll,
        this.error
      );
    },
  },
};
</script>
<style scoped>
.action_box {
  margin-top: 1rem;
}
</style>