import {createApp} from 'vue'
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
import InputNumber from 'primevue/inputnumber';
import Sidebar from 'primevue/sidebar';
import Menu from 'primevue/menu';
import Menubar from 'primevue/menubar';
import Column from 'primevue/column';
import TriStateCheckbox from 'primevue/tristatecheckbox';
import DataTable from 'primevue/datatable';
import Tooltip from 'primevue/tooltip';
import Checkbox from 'primevue/checkbox';
import ToastService from 'primevue/toastservice';
import Toast from 'primevue/toast';
import ConfirmDialog from 'primevue/confirmdialog';
import ConfirmationService from 'primevue/confirmationservice';


createApp(App)
    .use(store)
    .use(router)
    .use(PrimeVue)
    .use(ToastService)
    .use(ConfirmationService)
    .component('ConfirmDialog', ConfirmDialog)
    .component('Toast', Toast)
    .component('Column', Column)
    .component('TriStateCheckbox', TriStateCheckbox)
    .component('DataTable', DataTable)
    .component('Menubar', Menubar)
    .component('Card', Card)
    .component('Button', Button)
    .component('InputText', InputText)
    .component('Password', Password)
    .component('Listbox', Listbox)
    .component('Dropdown', Dropdown)
    .component('InputNumber', InputNumber)
    .component('Sidebar', Sidebar)
    .component('Menu', Menu)
    .directive('Tooltip', Tooltip)
    .component('Checkbox', Checkbox)
    .mount('#app')
