<template>
  <div>
      <input type="text" id="send_comment" placeholder="  댓글 달기" v-model.trim="commentOpinion" @keypress.enter="addOpinion"/>&nbsp;
      <button type="button" class="btn btn-primary sendbtn" @click="addOpinion" id="sendbtn">전송</button>
  </div>
</template>

<script>
export default {
    name: 'CommentForm',
    data(){
        return{
            commentOpinion: '',
        }
    },
    methods:{
        addOpinion(){
            const opinionItem = {
                opinion: this.commentOpinion,
                completed: false,
            };
            if (this.commentOpinion)
                //actions가 가지고 있는 함수 호출 하려면 dispatch를 해야한다.
                // action같은 경우는 일반적인 메소드와 똑같이 사용할 것이다.
                this.$store.dispatch('addComment', opinionItem);
                // store가 가지고 있는 mutations를 호출하는 것
                // 첫번째 인자값으로 mutations의 이름을 가져간다
                // mutation은 state를 관리하기 때문에 mutation이 가지는 메소드 이름은 대문자
                //this.$store.commit('ADD_COMMENT', opinionItem);
                // this.$store.state.comments.push(opinionItem);
            this.commentOpinion = '';
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