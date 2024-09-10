import { createRouter, createWebHistory } from 'vue-router';
import Index from '../views/Index.vue';
import BusinessList from '../views/BusinessList.vue';
import BusinessInfo from '../views/BusinessInfo.vue';
import Login from '../views/Login.vue';
import Orders from '../views/Orders.vue';
import UserAddress from '../views/UserAddress.vue';
import Payment from '../views/Payment.vue';
import OrderList from '../views/OrderList.vue';
import AddUserAddress from '../views/AddUserAddress.vue';
import EditUserAddress from '../views/EditUserAddress.vue';
import Register from '../views/Register.vue';
import Selfpage from '../views/Selfpage.vue';
import Person from '../views/Person.vue';
import EditUserName from '../views/EditUserName.vue';
import Search from '../views/Search.vue';
import BecomeBusiness from '../views/BecomeBusiness.vue';
import Payok from '../views/Payok.vue';
import BusinessUpload from '../views/BusinessUpload.vue';
import AssociationOf from '../views/AssociationOf.vue';
import EditUserImg from '../views/EditUserImg.vue';

const routes = [{
	path: '/',
	name: 'Home',
	component: Index
}, {
	path: '/index',
	name: 'Index',
	component: Index
}, {
	path: '/businessList',
	name: 'BusinessList',
	component: BusinessList
}, {
	path: '/businessInfo',
	name: 'BusinessInfo',
	component: BusinessInfo
}, {
	path: '/login',
	name: 'Login',
	component: Login
}, {
	path: '/orders',
	name: 'Orders',
	component: Orders
}, {
	path: '/userAddress',
	name: 'UserAddress',
	component: UserAddress
}, {
	path: '/payment',
	name: 'Payment',
	component: Payment
}, {
	path: '/orderList',
	name: 'OrderList',
	component: OrderList
}, {
	path: '/addUserAddress',
	name: 'AddUserAddress',
	component: AddUserAddress
}, {
	path: '/editUserAddress',
	name: 'EditUserAddress',
	component: EditUserAddress
}, {
	path: '/register',
	name: 'Register',
	component: Register
}, {
	path: '/selfpage',
	name: 'Selfpage',
	component: Selfpage
}, {
	path: '/person',
	name: 'Person',
	component: Person
}, {
	path: '/editUserName',
	name: 'EditUserName',
	component: EditUserName
}, {
	path: '/search',
	name: 'Search',
	component: Search
}, {
	path: '/becomeBusiness',
	name: 'BecomeBusiness',
	component: BecomeBusiness
}, {
	path: '/payok',
	name: 'Payok',
	component: Payok
}, {
	path: '/editUserImg',
	name: 'EditUserImg',
	component: EditUserImg
}, {
	path: '/businessUpload',
	name: 'BusinessUpload',
	component: BusinessUpload
}, {
	path: '/associationOf',
	name: 'AssociationOf',
	component: AssociationOf
}
];

// 解决重复路由报异常问题
const originalPush = createRouter.prototype.push;
createRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err);
};

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
});

export default router;
