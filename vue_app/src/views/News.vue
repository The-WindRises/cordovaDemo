<template>
  <div>
    <van-tabs v-model="activeTab" @click="changeTab">
      <van-tab
        v-for="(k, index) in types"
        :key="index"
        :title="Object.values(k)[0]"
        :name="Object.keys(k)[0]"
      >
        <van-list
          v-model="loading"
          finished="finished"
          finished-text="没有更多了"
          @load="getNewsList"
        >
          <van-cell
            v-for="item in news"
            :key="item.uniquekey"
            :title="item.title"
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
      </van-tab>
    </van-tabs>
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
      types: [{ top: "推荐" }, { guonei: "国内" }, { guoji: "国际" }],
      activeTab: 0,
      page: 1,
      page_size: 10,
      news: [],
      loading: false,
      finished: false,
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
            this.finished = true;
            this.loading = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeTab(name, title) {
      this.type = name;

      this.getNewsList();
    },
  },
};
</script>
