<template>
  <div class="">
      <div v-for="(comment, index) in showMsg" :key="comment">
        <CommentListItem :comment="comment" :index="index"/>
      </div>
  </div>
</template>

<script>
import CommentListItem from '@/components/postcomment/CommentListItem';
import axios from "@/util/http-common.js";

export default {
    name: 'CommentList',
    components : { CommentListItem },
    data(){
        return{
            showMsg:[
                {
                    commentContent: "string",
                    commentDate: "2021-08-08T08:15:23.457Z",
                    userEmail: "string",
                    userImage: "string",
                    userName: "string"
                }
            ],
        }
    },
    methods:{
        //댓글 목록 출력
        getShowMsg: function(){
            // alert('댓글 이제 보이려나?')
            // console.log(this.taskInfo.taskNo)
            axios({
                method: 'get',
                url: `/comment/${this.$route.query.taskNo}`,
            })
            .then((res) => {
                this.showMsg = res.data;
                console.log('getShowMsg 성공')
                // console.log(this.showMsg)
                // console.log(res.data)
            })
            .catch((err) => {
                console.log('getShowMsg 에러')
                console.log(err);
            });
        },
    },
    created: function(){
        this.$route.query.taskNo;
        this.getShowMsg();
    }
}

</script>

<style scoped>

</style>