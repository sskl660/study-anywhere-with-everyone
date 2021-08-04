<template>
  <div class="commentline">
    <ProfileImage class="comment-profile" />
    <h6 id="comment-writer">5기 아이유</h6>
    <div id="comment-opinion">{{ comment.opinion }}</div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue"

export default {
    name: 'CommentListItem',
    props: ['comment'],
    components:{
        ProfileImage,
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