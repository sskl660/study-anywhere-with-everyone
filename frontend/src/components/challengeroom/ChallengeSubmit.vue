<template>
  <div class="cticket-round" style="display:inline-block" :class="{'cfail': checkFail(TaskTotalRate), 'csuccess': checkSuccess(TaskTotalRate)}">1</div>
</template>

<script>
export default {
  name: 'ChallengeSubmit',
  props: {
  TaskTotalRate: {
      type: Array,
    }
  },
  methods: {
    checkFail: function (TaskTotalRate) {
      if(TaskTotalRate.achieveRate != 100 && TaskTotalRate.inProgress == false) return true;
      else false;
    },
    checkSuccess: function (TaskTotalRate) {
      if(TaskTotalRate.achieveRate == 100) return true;
      else false;
    },
  }
}
</script>

<style>
.cticket-round {
  width:70px; 
  height:70px; 
  /* 디폴트 회색 */
  background-color:#DDDDDD;
  border-radius: 70%;
  margin-right:8px;
  color: white;
  line-height: 75px;
  font-weight: bold;
  font-size: 30px;
}
.cfail {
  background-color: #EE4748 ;
}
.csuccess {
  background-color: #5974B3;
}
</style>