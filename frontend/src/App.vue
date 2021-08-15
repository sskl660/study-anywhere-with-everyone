<template>
    <div id="app">
        <Header />
        <router-view :key="$route.fullPath" />
    </div>
</template>
vue
<script>
import Header from '@/components/common/Header.vue';

export default {
    name: 'app',
    components: {
        Header,
    },
};
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
}

#nav {
    padding: 30px;
}
</style>
