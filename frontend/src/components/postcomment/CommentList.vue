<template>
    <div class="">
        <div v-for="comment in showMsg" :key="comment">
            <div class="commentline">
                <ProfileImage class="comment-profile" />
                <h6 id="comment-writer">{{comment.userName}}</h6>
                <div id="comment-opinion">{{ comment.commentContent }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"
import axios from '@/util/http-common.js';

export default {
    name: 'CommentList',
    components:{
        ProfileImage,
    },
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
                console.log('getShowMsg res 성공')
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
        this.getShowMsg();
        this.$route.query.taskNo;
    }
}
</script>

<style scoped>
.commentline{
    text-align: left;
    margin-bottom: 25px;
}

.comment-profile {
  width: 50px;
  height: 50px;
  position: relative;
}

#comment-writer {
    margin-left: 59px;
    margin-top: -45px;
    margin-bottom: 20px;
}

#comment-opinion{
    margin-left: 60px;
    margin-top: -20px;
}
</style>