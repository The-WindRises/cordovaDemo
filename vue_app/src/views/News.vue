<template>
  <div>News</div>
</template>
<script>

import {newsList} from '../api/server'
export default {
  name: "News",
  data: function () {
    return {
      type: 'top',
      page: 1,
      page_size: 10,

      news: [],
    };
  },
  mounted() {
    
    // cordova.plugins.DBHelper.coolMethod("hello", onSuccess(), onError());
    this.getNewsList();
  },
  methods: {
    getNewsList() {
      newsList(this.type, this.page, this.page_size)
        .then((res) => {
          console.log(res);
          if (res.error_code === 0) {
            this.news = res.result.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
