<template>
    <div class="commentline">
        <img :id=" 'msgimage' + idx " class="comment-profile" src="" alt="" />
        <h6 id="comment-writer"><strong>{{ comment.userName }}</strong></h6>
        <div id="comment-opinion">{{ comment.commentContent }}</div>
    </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
    name: 'CommentListItem',
    props: {
        comment: {type : Object},
        idx: {type : Number}
    },
    methods:{
        // 이미지 가져오기
        getProfileImageForComment: function(e) {
            // console.log('개별 사진 가져오기')
            // console.log(this.comment.userEmail);
            // console.log(this.idx);
            // console.log(this.index);
            http.get(`/viewimage/${this.comment.userEmail}`).then((response) => {
            // console.log("이미지성공");
            var imgsrc =
            "data:image/png;base64," +
            btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
            document.getElementById(`msgimage${this.idx}`).src = imgsrc;
            // this.comment.userImage = imgsrc;
            })
            .catch((error) => {
                // console.log("개별실패")
                if (this.imgsrc == null) {
                document.getElementById(`msgimage${this.idx}`).src = "/img/ssazip.43ffb363.png"
                }
            });
        },
    },
    created: function(){
        this.getProfileImageForComment();
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
  border-radius: 100%;
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
