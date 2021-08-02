import Vue from 'vue'
import VueRouter from 'vue-router'
import Welcome from '../views/Welcome.vue'
import Challenges from '../views/Challenges.vue'
import Profile from '../views/Profile.vue'
import ChallengeRoom from '../components/ChallengeRoom.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: Welcome
  },
  {
    path:'/challenges',
    name: 'Challenges',
    component: Challenges
  },
  {
    path:'/profile',
    name: 'Profile',
    component: Profile
  },
  {  
    path: '/ChallengeRoom',
    name: 'ChallengeRoom',
    component: ChallengeRoom
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
