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

<style scoped>
/* 폰트 등록 */
@font-face {
  font-family: 'GongGothicMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicMedium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

* {
  font-family: 'GongGothicMedium';
}
</style>