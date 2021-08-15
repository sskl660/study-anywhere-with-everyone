import Vue from 'vue';
import VueRouter from 'vue-router';
import Welcome from '../views/Welcome.vue';
import Join from '../views/Join.vue';
import HomeFeed from '../views/HomeFeed.vue';
import Challenges from '../views/Challenges.vue';
import Profile from '../views/Profile.vue';
import ProfileMy from '../views/ProfileMy.vue';
import ChallengeRoom from '../components/ChallengeRoom.vue';
import PostDetail from '../components/PostDetail.vue';
import PostDetailAfter from '../components/PostDetailAfter.vue';
import EPeacefulWorld from '../views/EPeacefulWorld.vue';
import Galaxy from '../views/Galaxy.vue';
import store from "../store/index";
// import SSazip from '../views/SSazip.vue';

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
    path: '/homefeed',
    name: 'HomeFeed',
    component: HomeFeed,
  },
  {
    path: '/profilemy',
    name: 'ProfileMy',
    component: ProfileMy,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    props: true,
  },
  {
    path: '/challengeRoom',
    name: 'ChallengeRoom',
    component: ChallengeRoom,
    props: true,
  },
  {//제출 안한과제 -> 내꺼일때만 들어기짐
    path: '/postDetail',
    name: 'PostDetail',
    component: PostDetail,
  },
  {//제출 한 과제 ->삭제만 있음
    path: '/postDetailAfter',
    name: 'PostDetailAfter',
    component: PostDetailAfter,
    // true로 설정하면 데이터를 props로도 받습니다.
    props: true,
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
  //   path: '/SSazip',
  //   name: 'SSazip',
  //   component: SSazip,
  // },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;

router.beforeEach(function (to, from, next) {
  // 1. JWT 토큰을 가져온다.
  const token = localStorage.getItem('jwt');
  // 2. JWT 토큰이 있거나, 웰컴 페이지이거나, 회원가입 페이지일 경우에는 접근 가능
  // 3. 이외의 경우에는 로그인해야 접근 가능. 토큰이 없으면 경고창을 띄운 뒤 웰컴 페이지로 이동.
  if (token || to.name === 'Welcome' ||  to.name === 'Join') {
    // 4. 로그인한 상태에서 갤럭시방을 URL로 접근하려고 하면, 경고창을 띄우고 피드로 이동시킴.
    if (to.name === 'Galaxy') {
      if (!store.state.message) {
        alert('올바르지 않은 접근입니다! 이전 페이지로 이동합니다.')
        router.go(-1)
      } else {
        next()
      }
    } else {
      next();
    }
  } else {
    alert('로그인이 필요합니다!');
    next('/')
  }
});