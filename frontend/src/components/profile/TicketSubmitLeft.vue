<template>
  <div style="display:inline-block;">
    <div class="ticket-left-round" :class="{'miss': checkMiss(ticketTask), 'submit': checkSubmit(ticketTask)}">{{num}}</div>
    <div class="ticket-left-road" style="color:#F9D479">1</div>
  </div>
  
</template>

<script>
export default {
  name: 'TicketSubmitLeft',
  props: {
    num: {
      type: Number,
    },
    ticketTask: {
      type: Number,
    }
  },
  methods: {
    checkMiss: function (ticketTask) {
      if(ticketTask == -2) return true;
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
.ticket-left-round {
  width:100px; 
  height:100px; 
  background-color:#DDDDDD;
  border-radius: 70%;
  margin-right:8px;
  color: white;
  line-height: 100px;
  font-weight: bold;
  font-size: 30px;
  margin-bottom:70px;
}
.ticket-left-road {
  width: 5px;
  height: 60px;
  background-color: #F9D479;
  transform: rotate(35deg);
  position: relative;
  top: -90px;
  left: -10px
}
.miss {
  background-color: #EE4748 ;
}
.submit {
  background-color: #5974B3;
}
</style>