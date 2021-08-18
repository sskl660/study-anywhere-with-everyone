<template>
  <div class="" v-if="showMsg[0].userEmail!='string'">
      <div v-for="(comment, iddd) in showMsg" :key="iddd">
        {{comment.userEmail}}
        <CommentListItem :comment="comment" :idx="iddd"/>
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
            axios({
                method: 'get',
                url: `/comment/${this.$route.query.taskNo}`,
            })
            .then((res) => {
                // console.log("123")
                this.showMsg = res.data;
                // console.log('getShowMsg 성공')
                // console.log(this.showMsg)
                // console.log(res.data)
            })
            .catch((err) => {
                // console.log('getShowMsg 에러')
                // console.log(err);
            });
        },
    },
    created: function(){
        this.getShowMsg();
        this.$route.query.taskNo;
        // console.log(this.showMsg)
    },
    updated: function(){
        // this.getShowMsg();

    },
}

</script>

<style scoped>

</style>