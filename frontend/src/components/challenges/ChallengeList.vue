<template>
    <div>
        <!-- 챌린지 목록 제목 -->
        <div :class=" 'challenge-title-container' + idx">
            <div class="challenge-title">{{ title }}</div>
        </div>

        <!-- 챌린지 티켓 -->
        <div class="challenge-list-container">
            <ChallengeListItem 
                v-for="(challenge, idx) in reversedChallengeList" 
                :key="idx" 
                :challenge="challenge"
            />
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
        idx: {
            type: Number,
        }
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
            if (this.idx === 0) {
                this.title = '알고리즘';
            }
            if (this.idx === 1) {
                this.title = 'CS';
            }
            if (this.idx === 2) {
                this.title = '취업';
            }
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
}
</script>
