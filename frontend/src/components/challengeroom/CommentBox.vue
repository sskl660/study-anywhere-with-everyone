<template>
    <div>
        <div class="commentblock">
            <CommentList />
        </div>
        <div class="commentform">
            <CommentForm :taskInfo="taskInfo"/>
        </div>
    </div>
</template>
<script>
import CommentForm from '@/components/postcomment/CommentForm';
import CommentList from '@/components/postcomment/CommentList';
// import { mapActions } from 'vuex';

export default {
    name : 'CommentBox',
    components:{
        CommentForm,
        CommentList,
    },
    props:{
        taskInfo:{
            type : Array
        },
    },
}

</script>
<style scoped>
    /* 댓글 입력 칸 클릭시 사각 경계라인 없애기 */
    #send_comment {
    width: 96%;
    height: 55px;
    border-radius: 20px;
    margin-top: 495px;
    }

    input {
    border: 3px solid #4c67aa;
    }

    input:focus {
    outline: none;
    box-shadow: 0 0 3px 2px #4c67aa;
    }

    .commentblock{
        width: 380px;
        height: 480px;
        /* border: 3px solid rebeccapurple; */
        overflow: scroll;
        margin-left: 30px;
    }
</style>