<template>
  <div>
      <input type="text" class="sendMsg" id="send_comment" placeholder="  댓글 달기" v-model.trim="commentContent" @keypress.enter="addComment(leaveMsg)"/>&nbsp;
      <button type="button" class="btn btn-primary sendbtn" @click="addComment(leaveMsg)" id="sendbtn">전송</button>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
    name: 'CommentForm',
    props:{
        taskInfo:{
            type : Object
        },
    },
    data(){
        return{
            commentContent: '',
            leaveMsg:{
                "commentContent": "string",
                "taskNo": 0,
                "userEmail": "string"
            },
        }
    },
    computed:{
        ...mapState([
            'userEmail',
        ])
    },
    methods:{
        ...mapActions([
            'leaveMessage',
        ]),
        addComment: function(leaveMsg){
            this.leaveMsg.userEmail = this.userEmail;
            console.log(this.leaveMsg.taskNo);
            console.log(this.taskInfo);
            this.leaveMsg.taskNo = this.taskInfo.taskNo;
            this.leaveMsg.commentContent = document.querySelector('.sendMsg').value

            if(this.leaveMsg.commentContent == ''){
                return false;
            }

            console.log('메시지 들어오니?')
            console.log(this.commentContent)

            this.leaveMessage(leaveMsg);
            this.$router.go();
        },
    }
}
</script>

<style scoped>
 /* 댓글 입력 칸 클릭시 사각 경계라인 없애기 */
    #send_comment {
    width: 81%;
    height: 55px;
    border-radius: 20px;
    margin-top: 10px;
    /* margin-top: 495px; */
    }

    input {
    border: 3px solid #4c67aa;
    }

    input:focus {
    outline: none;
    box-shadow: 0 0 3px 2px #4c67aa;
    }
    .sendbtn{
        background-color: #4C67AA;
    }
</style>