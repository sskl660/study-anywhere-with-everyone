<template>
  <!-- 부모로부터 text를 받아와서 입력한다. -->
  <button type="button" class="btn btn-light">{{ text }}</button>
</template>

<script>
export default {
  name: 'ButtonRound',
  props: {
    text: {
      type: String
    }
  }
}
</script>

<style scoped>
  button {
    border-radius: 2rem;
  }

  .btn-check:focus + .btn-light, .btn-light:focus {
    box-shadow: none;
  }

  .btn-check:checked + .btn-light:focus, .btn-check:active + .btn-light:focus, .btn-light:active:focus, .btn-light.active:focus, .show > .btn-light.dropdown-toggle:focus {
    box-shadow: none;
  }
</style>