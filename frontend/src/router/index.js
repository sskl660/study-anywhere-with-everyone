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
<<<<<<< HEAD
  {// 제출 안한과제
    path: '/taskDetail',
    name: 'PostDetail',
    component: PostDetail,
  },
  { // 제출한 과제
    path: '/taskDetailAfter',
=======
  {//제출 안한과제 -> 내꺼일때만 들어기짐
    path: '/postDetail',
    name: 'PostDetail',
    component: PostDetail,
  },
  {//제출 한 과제 ->삭제만 있음
    path: '/postDetailAfter',
>>>>>>> 6f5338f3d55380f502dc7730ce4b5500c3b65286
    name: 'PostDetailAfter',
    component: PostDetailAfter,
    // true로 설정하면 데이터를 props로도 받습니다.
    props: true
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
