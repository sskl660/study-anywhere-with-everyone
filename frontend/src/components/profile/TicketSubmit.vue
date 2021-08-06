<template>
  <div class="ticket-round" style="display:inline-block" :class="{'miss': checkMiss(ticketTask), 'submit': checkSubmit(ticketTask)}">1</div>
</template>

<script>
export default {
  name: 'TicketSubmit',
  props: {
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
.ticket-round {
  width:100px; 
  height:100px; 
  /* 디폴트 회색 */
  background-color:#DDDDDD;
  border-radius: 70%;
  margin-right:8px;
  color: white;
  line-height: 100px;
  font-weight: bold;
  font-size: 30px;
}
.miss {
  background-color: #EE4748 ;
}
.submit {
  background-color: #5974B3;
}
</style>