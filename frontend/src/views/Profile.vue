<template>
    <div>
        <!-- <p>{{this.user_info}}</p> -->
        <div class="profile-info-continer">
            <!-- 타이틀도 추후에 bg-img로 바꿔주기 -->
            <Title style="display: inline-block" :text="this.profileTitle" />
            <!-- 프로필 상단의 카드와 팔로워, 각오 소개글 -->
            <ProfileInfo class="id-container" :userInfo="newUserInfo" :followers="followers" :followings="followings" />
        </div>
        <!-- 프로필 티켓 -->
        <div v-for="ticket in task_tickets" :key="ticket.challengeNo">
            <ProfileTicket :ticket="ticket" style="d-flex justify-content-center" />
        </div>
        <ProfileEditModal :userInfo="user_info" />
    </div>
</template>

<script>
import Title from '@/components/common/Title.vue';
import ProfileInfo from '@/components/profile/ProfileInfo.vue';
import ProfileTicket from '@/components/profile/ProfileTicket.vue';
import ProfileEditModal from '@/components/profile/ProfileEditModal.vue';
import './css/profile.css';
// import axios from 'axios'
import axios from '@/util/http-common.js';
// import { mapState } from 'vuex'
// import func from 'vue-editor-bridge'
import { mapGetters } from 'vuex';
export default {
    name: 'Profile',
    components: {
        Title,
        ProfileInfo,
        ProfileTicket,
        ProfileEditModal,
    },
    // props: {
    //   userEmail: String,
    // },
    data: function() {
        return {
            // 유저 이메일 정보 받아와서 넣기
            // useremail: "jang@naver.com",
            profileTitle: 'string',
            user_info: {
                userBlog: '',
                userDevstyle: '',
                userEmail: '',
                userFollower: 0,
                userFollowing: 0,
                userGit: '',
                userGraduated: true,
                userImage: '',
                userIntroduce: '',
                userMbti: '',
                userName: '',
                userNumber: '',
                userTechstack: '',
                userTerm: 0,
                userTotalcomplete: 0,
                userTotaltime: 0,
                userWeekcomplete: 0,
                userWeektime: 0,
                userWishfield: '',
            },
            task_tickets: [
                // 챌린지 하나 내용
                {
                    achieveRate: 0,
                    challengeName: '',
                    challengeNo: 0,
                    challangeTaskCnt: 0,
                    isComplete: false,
                    taskNo: [],
                },
            ],
            followers: [],
            followings: [],
            pageOwner: '페이지주인',
        };
    },
    methods: {
        // 유저 정보 가져오는 함수
        getUserInfo: function() {
            console.log(this.pageOwner);
            axios({
                method: 'get',
                //url: `/profile/info/${this.$route.query.user}`,
                url: `/profile/info/${this.pageOwner}`,
            })
                .then((res) => {
                    console.log('유저정보 통신 성공');
                    this.user_info = res.data;
                    // console.log(this.user_info);
                    this.getTitle(this.user_info.userName); //타이틀 내용 채워주기
                    // console.log('follower');
                    // console.log(this.followings);
                    // console.log('follower')
                    // console.log(this.userEmail)
                })
                .catch((err) => {
                    console.log(err);
                });

            axios({
                method: 'get',
                //url: `/profile/taskticket/${this.$route.query.user}`,
                url: `/profile/taskticket/${this.pageOwner}`,
                //url: { path: '/profile/taskticket', query: { user: pageOwner } },
            })
                .then((res) => {
                    this.task_tickets = res.data;
                })
                .catch((err) => {
                    console.log(err);
                });

            axios({
                method: 'get',
                //url: { path: '/follow/followers/follow/followers', query: { user: pageOwner } },
                url: `/follow/followers/${this.pageOwner}`,
            })
                .then((res) => {
                    this.followers = res.data;
                    // console.log('follower')
                    // console.log(this.followers)
                    // console.log('follower')
                    // console.log(this.userEmail)
                })
                .catch((err) => {
                    console.log(err);
                });

            axios({
                method: 'get',
                //url: `/follow/followings/${this.$route.query.user}`,
                url: `/follow/followings/${this.pageOwner}`,
                //url: { path: '/follow/followings', query: { user: pageOwner } },
            })
                .then((res) => {
                    this.followings = res.data;
                    // console.log('following!!!!!')
                    // console.log(this.followings)
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        // 최상단의 타이틀 부분 텍스트 함수
        getTitle: function(name) {
            this.profileTitle = name+" 님의 프로필";
        },
        userChoose: function(user) {
            if (user == 'myProfile') {
                this.pageOwner = this.userEmail;
                // alert(this.pageOwner + ' 님의 페이지');
            } else {
                // alert('친구 페이지');
                this.pageOwner=user;
                // alert(this.pageOwner + ' 님의 페이지');
            }
            this.getUserInfo();
        },
    },
    // computed: {
    //   ...mapState([
    //     'userEmail',
    //   ])
    // },
    computed: {
        newUserInfo: function() {
            return this.user_info;
        },
        ...mapGetters(['userEmail']),
    },
    created: function() {
        //alert("pro.vue");
        //alert('url에 쿼리는'+this.$route.query.user);
        // 생성과 동시에 유저정보 가져오기
        this.userChoose(this.$route.query.user); //쿼리에 있는 유저 키값에 있는 value값을 가져올 수 있다.
        //this.getUserInfo();
    },
};
</script>

<style scoped>
/* 티켓상단의 개인정보부분 영역 컨테이너*/
.id-container {
    height: 350px;
    position: relative;
    top: 60px;
}
.profile-info-continer {
    height: 570px;
}
.profile-edit-btn {
    background-color: #e1af4e !important;
    border-color: #e1af4e !important;
    color: #420909 !important;
    font-weight: bold !important;
    margin-left: 10px;
}
</style>
