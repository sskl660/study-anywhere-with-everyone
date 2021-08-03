<template>
  <div class="commentline">{{ comment.opinion }}</div>
</template>

<script>
export default {
    name: 'CommentListItem',
    props: ['comment'],
}
</script>

<style scoped>
.commentline{
    text-align: left;
}
</style>