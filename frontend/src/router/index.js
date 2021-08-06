import Vue from 'vue';
import VueRouter from 'vue-router';
import Welcome from '../views/Welcome.vue';
import Join from '../views/Join.vue';
import Challenges from '../views/Challenges.vue';
import Profile from '../views/Profile.vue';
import ChallengeRoom from '../components/ChallengeRoom.vue';
import PostDetail from '../components/PostDetail.vue';
import PostDetailAfter from '../components/PostDetailAfter.vue';
import EPeacefulWorld from '../views/EPeacefulWorld.vue';
import Galaxy from '../views/Galaxy.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: Welcome,
  },
  {
    path: '/join',
    name: 'Join',
    component: Join,
  },
  {
    path: '/challenges',
    name: 'Challenges',
    component: Challenges,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
  },
  {
    path: '/challengeRoom',
    name: 'ChallengeRoom',
    component: ChallengeRoom,
  },
  {// 제출 안한과제
    path: '/taskDetail',
    name: 'PostDetail',
    component: PostDetail,
  },
  { // 제출한 과제
    path: '/taskDetailAfter',
    name: 'PostDetailAfter',
    component: PostDetailAfter,
  },
  {
    path: '/EPeacefulWorld',
    name: 'EPeacefulWorld',
    component: EPeacefulWorld,
  },
  {
    path: '/Galaxy',
    name: 'Galaxy',
    component: Galaxy,
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
