import { createApp } from 'vue'
import App from './App.vue'
import GetBooks from './components/GetBooks.vue'

const app = createApp(App)
app.component('get-books', GetBooks)
app.mount('#app')
