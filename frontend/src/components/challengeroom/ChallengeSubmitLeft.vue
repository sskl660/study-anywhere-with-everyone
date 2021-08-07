<template>
  <div style="display:inline-block;">
    <div class="cticket-left-round" :class="{'cfail': checkFail(TaskTotalRate), 'csuccess': checkSuccess(TaskTotalRate)}">{{num}}</div>
    <div class="cticket-left-road" style="color:#F9D479">1</div>
  </div>
  
</template>

<script>
export default {
  name: 'ChallengeSubmitLeft',
  props: {
    num: {
      type: String,
    },
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
.cticket-left-round {
  width:70px; 
  height:70px; 
  background-color:#DDDDDD;
  border-radius: 70%;
  margin-right:8px;
  color: white;
  line-height: 74px;
  font-weight: bold;
  font-size: 30px;
  margin-bottom:45px;
}
.cticket-left-road {
  width: 5px;
  height: 43px;
  background-color: #F9D479;
  transform: rotate(40deg);
  position: relative;
  top: -62px;
  left: -10px
}
.cfail {
  background-color: #EE4748 ;
}
.csuccess {
  background-color: #5974B3;
}
</style>