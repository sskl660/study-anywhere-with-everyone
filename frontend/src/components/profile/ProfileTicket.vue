<template>
  <div class="profileticket" style="display: inline-block; d-flex">
    <div class="ticket-container d-flex">
      <div class="ticket-left" style="display: inline-block">
        <div class="ticket-title">희오니의 알고챌린지</div>
        <div class="ticket-rate">100%</div>
      </div>
      <div class="ticket-right" div style="display: inline-block">
        <div>과제들</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.profileticket {
  background-image: url(~@/assets/ProfileTicket.png);
  width:1280px; 
  height:400px;
}
.ticket-container {
  width:1150px; 
  height:300px;
  margin-top: 40px;
  margin-left: 50px;
  /* background-color: rgb(225, 0, 255); */
}
.ticket-left{
  width:210px; 
  height:280px;
  /* background-color: aqua; */

}
.ticket-right{
  width:870px; 
  height:280px;
  margin-left: 80px;
  /* background-color: rgb(217, 255, 0); */
}
.ticket-title {
  color: #2B3C64;
  font-weight: 900;
  font-size: 21px;
  height: 100px;
  margin-top: 48px;

}
.ticket-rate {
  color: #FFF;
  font-weight: 900;
  font-size: 40px;
}
</style>