<template>
    <div>
        <!-- 챌린지 목록 제목 -->
        <div :class=" 'challenge-title-container' + idx" class="d-flex justify-content-center">
            <div style="display: inline-block" >
                <img v-if="imgAlgo" src="@/assets/algo.png" style="width:90px; cursor: pointer; margin-left:-50px;" @click="changeChatType(1)" />
                <img v-if="imgCs" src="@/assets/cs.png" style="width:90px; cursor: pointer; margin-left:0px;" @click="changeChatType(1)" />
                <img v-if="imgJob" src="@/assets/job.png" style="width:90px; cursor: pointer; margin-left:-10px;" @click="changeChatType(1)" />
            </div>
            <div :class="'challenge-title'+idx" style="display:inline-block; width:150px; line-height:90px;">{{ title }}</div>
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
