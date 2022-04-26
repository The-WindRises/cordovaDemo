import http from './http'

var newsProxy = ""
if (process.env.NODE_ENV === 'production') {
    //cordova 不存在跨域 

    newsProxy = "https://v.juhe.cn"
} else {
    //vue运行在浏览器存在跨域问题

    newsProxy = "/news"
}
//新闻列表
export function newsList(type = 'top', page = 1, page_size = 10) {

    return http.get(newsProxy + '/toutiao/index', {
        params: {

            // key: '6d7ee8d88bd4fb137f5d20ce7066a700',
            key: '9747a6350054228d9577a9dfb8d8d515',
            type,
            page,
            page_size
        }

    })
}