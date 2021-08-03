<template>
  <div class="">
      <CommentListItem v-for="(comment, idx) in comments" :key="idx" :comment="comment"/>
  </div>
</template>

<script>
import CommentListItem from '@/components/postcomment/CommentListItem';

export default {
    name: 'CommentList',
    components : { CommentListItem },
    // getters에 해당하는 vue instance
    // 값을 넘겨줘야 하니까 일반적으로 return이 존재한다.
    computed:{ 
        comments(){
            // v-for 안에 있는 todos에 이제 값이 전달된다.
            return this.$store.state.comments;
        }
    }
}
</script>

<style scoped>

</style>