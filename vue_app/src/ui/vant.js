import { Overlay, Button, Loading, Uploader, Toast, Cell, List, Tabs, Tab } from 'vant'

const vant = {
    install: function (Vue) {
        Vue.use(Overlay)
        Vue.use(Button)
        Vue.use(Loading)
        Vue.use(Uploader)
        Vue.use(Toast)
        Vue.use(List)
        Vue.use(Cell)
        Vue.use(Tabs)
        Vue.use(Tab)
    }
}

export default vant