<template>
  <div id="ssazip">
    <img :src="ssazip_pics[snum]" style="width:50px" />
    <img style="display:none" src="@/assets/ssazip.png" alt="" />
    <img style="display:none" src="@/assets/ssazip_mint.png" alt="" />
    <img style="display:none" src="@/assets/ssazip_orange.png" alt="" />
    <img style="display:none" src="@/assets/ssazip_purple.png" alt="" />
    <img style="display:none" src="@/assets/ssazip_yellow.png" alt="" />
    <img style="display:none" src="@/assets/ssazip_red.png" alt="" />
    <span style="color:#fff">권희은</span>
  </div>
</template>

<script>
export default {
  name: 'SmallSSazip',
  props: {
    idx: Number,
  },
  data: function() {
    return {
      ssazip_pics: [
        '/img/ssazip.43ffb363.png',
        '/img/ssazip_mint.3c829d99.png',
        '/img/ssazip_orange.6b3c93af.png',
        '/img/ssazip_purple.fe37cc0a.png',
        '/img/ssazip_red.d0a2d170.png',
        '/img/ssazip_yellow.137c1665.png',
      ],
      snum: Number,
    };
  },
  methods: {
    getnum: function() {
      const num = parseInt(this.idx % 6);
      this.snum = num;
    },
  },
  created: function() {
    this.getnum();
  },
};
</script>

<style></style>
