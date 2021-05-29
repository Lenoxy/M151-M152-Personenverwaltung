import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import PrimeVue from 'primevue/config';
import 'primevue/resources/themes/saga-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Card from 'primevue/card';
import Listbox from 'primevue/listbox';
import Dropdown from 'primevue/dropdown';



createApp(App)
    .use(store)
    .use(router)
    .use(PrimeVue)
    .component('Card', Card)
    .component('Button', Button)
    .component('InputText', InputText)
    .component('Password', Password)
    .component('Listbox', Listbox)
    .component('Dropdown', Dropdown)
    .mount('#app')
