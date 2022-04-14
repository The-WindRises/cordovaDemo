import { Overlay, Button, Loading, Uploader, Toast, Cell, List, Tabs, Tab, Popup, DatetimePicker, PullRefresh, Form, Field, CellGroup } from 'vant'

const vant = {
    install: function (Vue) {
        Vue.use(Overlay).use(Button)
            .use(Loading)
            .use(Uploader)
            .use(Toast)
            .use(List)
            .use(Cell)
            .use(Tabs)
            .use(Tab).use(Popup).use(DatetimePicker).use(PullRefresh).use(Form).use(Field).use(CellGroup)
    }
}

export default vant