<template>
  <!-- 부모로부터 text를 받아와서 입력한다. -->
  <b-button>{{ text }}</b-button>
</template>

<script>
export default {
  name: 'ButtonSquare',
  props: {
    text: {
      type: String
    }
  }
}
</script>

<style>

</style>