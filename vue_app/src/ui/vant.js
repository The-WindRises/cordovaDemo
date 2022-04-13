import { Overlay, Button, Loading, Uploader, Toast } from 'vant'

const vant = {
    install: function (Vue) {
        Vue.use(Overlay)
        Vue.use(Button)
        Vue.use(Loading)
        Vue.use(Uploader)
        Vue.use(Toast)
    }
}

export default vant