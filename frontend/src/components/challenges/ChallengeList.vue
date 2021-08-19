<template>
    <div>
        <!-- 챌린지 목록 제목 -->
        <div :class=" 'challenge-title-container' + idx" class="d-flex justify-content-center">
            <div style="display: inline-block" >
                <img v-if="imgAlgo" src="@/assets/algo.png" style="width:90px; margin-left:-50px; margin-top: -2px;"/>
                <img v-if="imgCs" src="@/assets/cs.png" style="width:90px; margin-left:0px;"/>
                <img v-if="imgJob" src="@/assets/job.png" style="width:90px; margin-left:-10px;"/>
            </div>
            <div :class="'challenge-title'+idx" style="display:inline-block; width:150px; line-height:90px;">{{ title }}</div>
        </div>

        <!-- 챌린지 티켓 -->
        <div class="challenge-list-container">
            <ChallengeListItem 
                v-for="(challenge, idx) in challengeList" 
                :key="idx" 
                :challenge="challenge"
                :idx="idx"
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
            imgAlgo: false,
            imgCs: false,
            imgJob: false,
        };
    },
    components: {
        ChallengeListItem,
    },
    methods: {
        getTitle: function() {
            if (this.idx === 0) {
                this.title = '알고리즘';
                this.imgAlgo = true;
            }
            if (this.idx === 1) {
                this.title = 'CS';
                this.imgCs = true;
            }
            if (this.idx === 2) {
                this.title = '취업';
                this.imgJob = true
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
