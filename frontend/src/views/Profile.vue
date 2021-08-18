<template>
    <div>
        <div class="profile-info-container">
            <!-- 타이틀도 추후에 bg-img로 바꿔주기 -->
            <Title style="display: inline-block" :text="this.profileTitle" />
            <!-- 프로필 상단의 카드와 팔로워, 각오 소개글 -->
            <ProfileInfo class="id-container" :userInfo="newUserInfo" :followers="followers" :followings="followings" />
        </div>
        <div class="d-flex justify-content-center mt-3" style="height:100px;">
            <div class="ticket-line" style="color:#e1af4e;font-size:30px;line-height:80px;">
                챌린지 과제제출 현황
            </div>
        </div>
        <!-- 프로필 티켓 -->
        <div v-for="(ticket, idx) in task_tickets" :key="idx">
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
import axios from '@/util/http-common.js';
import { mapActions, mapGetters } from 'vuex';
export default {
    name: 'Profile',
    components: {
        Title,
        ProfileInfo,
        ProfileTicket,
        ProfileEditModal,
    },
    data: function() {
        return {
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
        ...mapActions([
            'getFollower',
            'getFollowing'
        ]),
        // 유저 정보 가져오는 함수
        getUserInfo: function() {
            axios({
                method: 'get',
                url: `/profile/info/${this.pageOwner}`,
            })
                .then((res) => {
                    this.user_info = res.data;
                    this.getTitle(this.user_info.userName); //타이틀 내용 채워주기
                    this.getFollower(this.user_info.userFollower)
                    this.getFollowing(this.user_info.userFollowing)
                })
                .catch((err) => {});
            
            axios({
                method: 'get',
                url: `/profile/info/${this.userEmail}`,
            })
                .then((res) => {
                    this.getFollower(res.data.userFollower)
                    this.getFollowing(res.data.userFollowing)
                })
                .catch((err) => {});

            axios({
                method: 'get',
                url: `/profile/taskticket/${this.pageOwner}`,
            })
                .then((res) => {
                    this.task_tickets = res.data.reverse();
                })
                .catch((err) => {});

            axios({
                method: 'get',
                url: `/follow/followers/${this.pageOwner}`,
            })
                .then((res) => {
                    this.followers = res.data;
                })
                .catch((err) => {});

            axios({
                method: 'get',
                url: `/follow/followings/${this.pageOwner}`,
            })
                .then((res) => {
                    this.followings = res.data;
                })
                .catch((err) => {});
        },
        // 최상단의 타이틀 부분 텍스트 함수
        getTitle: function(name) {
            this.profileTitle = name+" 님의 프로필";
        },
        userChoose: function(user) {
            if (user == 'myProfile') {
                this.pageOwner = this.userEmail;
            } else {
                this.pageOwner=user;
            }
            this.getUserInfo();
        },
    },
    computed: {
        newUserInfo: function() {
            return this.user_info;
        },
        ...mapGetters(['userEmail']),
    },
    created: function() {
        this.userChoose(this.$route.query.user); //쿼리에 있는 유저 키값에 있는 value값을 가져올 수 있다.
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
.profile-info-container {
    height: 530px;
}
.profile-edit-btn {
    background-color: #e1af4e !important;
    border-color: #e1af4e !important;
    color: #420909 !important;
    font-weight: bold !important;
    margin-left: 10px;
}
.ticket-line {
  background-image: url(~@/assets/ticket-line.png);
  width:1280px; 
  height:100px;
}
</style>
