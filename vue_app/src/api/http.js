import axios from 'axios'

const service = axios.create({
    timeout: 3000
})
//请求拦截器
service.interceptors.request.use(config => {

    config.headers['Accept'] = "application/json"
    config.headers["Access-Control-Allow-Origin"] = "*"
    return config

}, error => {

    Promise.reject(error)

})
//响应拦截器
service.interceptors.response.use(response => {

    console.log(response)
    if (response.status === 200) {

        return response.data
    } else {

        return Promise.reject(error)
    }
}, error => {
    if (error.response) {
        return error.response.data
    } else {

        return Promise.reject(error)
    }

})
export default service