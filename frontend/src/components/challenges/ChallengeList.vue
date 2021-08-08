<template>
    <div>
        <!-- 챌린지 목록 제목 -->
        <div class="challenge-title-container">
            <div class="challenge-title">{{ title }}</div>
        </div>

        <!-- 챌린지 티켓 -->
        <div class="challenge-list-container" >
            <ChallengeListItem v-for="(challenge, idx) in reversedChallengeList" :key="idx" :challenge="challenge" />
        </div>
    </div>
</template>

<script>
import ChallengeListItem from '@/components/challenges/ChallengeListItem';
import '@/components/css/challenges/challengelist.css';

export default {
    name: 'ChallengeList',
    props: {
        challengeList: {
            type: Array,
        },
    },
    data: function() {
        return {
            title: null,
        };
    },
    components: {
        ChallengeListItem,
    },
    methods: {
        getTitle: function() {
            if (this.challengeList[0].challengeCategory === 'ALGO') {
                this.title = '알고리즘';
            }
            if (this.challengeList[0].challengeCategory === 'CS') {
                this.title = 'CS';
            }
            if (this.challengeList[0].challengeCategory === 'JOB') {
                this.title = '취업';
            }
        },
        moveToChallenge: function() {
            alert('뚜둥');
            router.push({path:'/challengeRoom', query:{}})
        },
    },
    created: function() {
        this.getTitle();
    },
    computed: {
        reversedChallengeList: function() {
            return [...this.challengeList].reverse();
        },
    },
};
</script>
