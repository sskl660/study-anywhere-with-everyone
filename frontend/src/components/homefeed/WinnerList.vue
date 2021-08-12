<template>
  <div>
    <GraduationList/>
    <WeeklyChallengeList/>
    <WeeklyStudyList/>
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