<template>
  <div>
    <div class="list-container"><GraduationList/></div>
    <div class="list-container"><WeeklyChallengeList/></div>
    <div class="list-container"><WeeklyStudyList/></div>
    
  </div>
</template>

<script>
import GraduationList from '@/components/homefeed/GraduationList'
import WeeklyChallengeList from '@/components/homefeed/WeeklyChallengeList'
import WeeklyStudyList from '@/components/homefeed/WeeklyStudyList'

export default {
  name: 'WinnerList',
  components: {
    GraduationList,
    WeeklyChallengeList,
    WeeklyStudyList
  }
}
</script>

<style scoped>
.list-container {
  margin-left: 0px;
}
</style>