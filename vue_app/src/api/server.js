import http from './http'

//新闻列表
export function newsList(type = 'top', page = 1, page_size = 10) {

    return http.get('news/toutiao/index', {
        params: {

            key: '9747a6350054228d9577a9dfb8d8d515',
            type,
            page,
            page_size
        }

    })
}