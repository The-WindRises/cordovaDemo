<template>
  <div>
    <van-tabs v-model="activeTab" @click="changeTab">
      <van-tab
        v-for="(k, index) in types"
        :key="index"
        :title="Object.values(k)[0]"
        :name="Object.keys(k)[0]"
      >
        <van-pull-refresh
          v-model="loading"
          @refresh="onRefresh"
          success-text="刷新成功"
        >
          <van-list
            v-model="loading"
            finished="finished"
            finished-text="没有更多了"
            @load="getNewsList"
            error-text="请求失败，点击重新加载"
            :error.sync="error"
          >
            <van-cell
              v-for="item in news"
              :key="item.uniquekey"
              :title="item.title"
              @click="showPopup"
            >
              <template #icon>
                <img
                  :src="item.thumbnail_pic_s"
                  alt=""
                  style="width: 6rem; height: 4rem; margin-right: 0.2rem"
                />
              </template>
              <template #title>
                <span>{{ item.title }}</span>
              </template>
              <template #label>
                <span>{{ item.date }}</span>
                <span>{{ item.author_name }}</span>
              </template>
            </van-cell>
          </van-list>
        </van-pull-refresh>
      </van-tab>
    </van-tabs>
    <van-popup
      v-model:show="showPop"
      position="right"
      :style="{ width: '70%', height: '100%' }"
      ><div>
        <van-form @submit="onSubmit">
          <van-cell-group>
            <van-field
              v-model="work_name"
              label="作业名称"
              placeholder="请输入作业名称"
            />
          </van-cell-group>

          <van-field
            readonly
            clickable
            name="datetimePicker"
            :value="create_time"
            label="创建事件"
            placeholder="点击选择时间"
            @click="showPicker = true"
          />
          <van-popup v-model="showPicker" position="bottom">
            <van-datetime-picker
              type="time"
              @confirm="onConfirm"
              @cancel="showPicker = false"
            />
          </van-popup>
          <div style="margin: 1rem">
            <van-button plain type="primary">重置</van-button>
            <van-button type="info">确认</van-button>
          </div>
        </van-form>
      </div></van-popup
    >
  </div>
</template>
<script>
import { newsList } from "../api/server";
export default {
  name: "News",
  data: function () {
    return {
      type: "top",
      types: [{ top: "推荐" }],
      // types: [{ top: "推荐" }, { guonei: "国内" }, { guoji: "国际" }],
      activeTab: 0,
      page: 1,
      page_size: 10,
      news: [],
      loading: false,
      finished: false,
      showPop: false,
      error: false,
      value: "",
      showPicker: false,
      create_time:'',
      work_name:''
    };
  },
  mounted() {
    // cordova.plugins.DBHelper.coolMethod("hello", onSuccess(), onError());
  },
  methods: {
    getNewsList() {
      this.loading = true;
      this.finished = false;
      newsList(this.type, this.page, this.page_size)
        .then((res) => {
          console.log(res);
          if (res.error_code === 0) {
            this.news = res.result.data;
          } else {
            this.error = true;
          }
          this.finished = true;
          this.loading = false;
        })
        .catch((err) => {
          this.error = true;
          this.finished = true;
          this.loading = false;
        });
    },
    changeTab(name, title) {
      this.type = name;

      this.getNewsList();
    },
    showPopup() {
      this.showPop = true;
    },
    onRefresh() {
      setTimeout(() => {
        this.page = 1;
        this.isLoading = false;
        this.getNewsList();
      }, 1000);
    },
    onConfirm(time) {
      this.create_time = time;
      this.showPicker = false;
    },
    onSubmit(){

    }
  },
};
</script>
