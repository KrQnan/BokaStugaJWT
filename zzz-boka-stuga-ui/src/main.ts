import { createApp } from 'vue'
import router from './router'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import './style.css'
import App from './App.vue'
import { createPinia } from 'pinia'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

const pinia = createPinia()
createApp(App)
.use(pinia)
.use(router)
.component('font-awesome-icon', FontAwesomeIcon)
.component('VueDatePicker', VueDatePicker)
.mount('#app')
