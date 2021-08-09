<template>
  <div>
    <GraduationList/>
    <WeeklyChallengeList/>
    <WeeklyStudyList/>
    <!-- <WinnerListItem/> -->
  </div>
</template>

<script>
// import WinnerListItem from '@/components/homefeed/WinnerListItem'
import GraduationList from '@/components/homefeed/GraduationList'
import WeeklyChallengeList from '@/components/homefeed/WeeklyChallengeList'
import WeeklyStudyList from '@/components/homefeed/WeeklyStudyList'

export default {
  name: 'WinnerList',
  components: {
    // WinnerListItem,
    GraduationList,
    WeeklyChallengeList,
    WeeklyStudyList
  }
}
</script>

<style>

</style>