<template>
  <div class="cticket-round" style="display:inline-block" :class="{'cfail': checkFail(TaskTotalRate), 'csuccess': checkSuccess(TaskTotalRate)}">1</div>
</template>

<script>
export default {
  name: 'ChallengeSubmit',
  props: {
  TaskTotalRate: {
      type: [],
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
  box-shadow:1px 3px 10px #5a1f24;
}
.csuccess {
  background-color: #5974B3;
  box-shadow:3px 3px 10px #3e479b;
}
</style>