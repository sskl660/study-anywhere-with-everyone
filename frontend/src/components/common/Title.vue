<template>   
  <div class="png">
    <img class="title " src="@/assets/title.png" alt="title">
    <div class="d-flex justify-content-center">
      <div class="png-content" >
        <p class="title-text">{{ text }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Title',
  props: {
    text: {
      type: String
    }
  }
}
</script>
