<template>
  <div style="display:inline-block;">
    <div class="ticket-right-road" style="color:#F9D479" >1</div>
    <div class="ticket-right-round" :class="{'miss': checkMiss(ticketTask), 'submit': checkSubmit(ticketTask)}">{{num}}</div>
  </div>
</template>

<script>
export default {
  name: 'TicketSubmitRight',
  props: {
    num: {
      type: String,
    },
    ticketTask: {
      type: Object,
    }
  },
  methods: {
    checkMiss: function (ticketTask) {
      if(ticketTask == -1) return true;
      else false;
    },
    checkSubmit: function (ticketTask) {
      if(ticketTask != -1 && ticketTask != -2) return true;
      else false;
    },
  }
}
</script>

<style>
.ticket-right-round {
  width:100px; 
  height:100px; 
  background-color:#DDDDDD;
  border-radius: 70%;
  margin-right:8px;
  color: white;
  line-height: 100px;
  font-weight: bold;
  font-size: 30px;
  margin-bottom: 20px;

}
.ticket-right-road {
  width: 5px;
  height: 60px;
  background-color: #F9D479;
  transform: rotate(-35deg);
  position: relative;
  top: 12px;
  left: 0px
}
.miss {
  background-color: #EE4748 ;
}
.submit {
  background-color: #5974B3;
}
</style>