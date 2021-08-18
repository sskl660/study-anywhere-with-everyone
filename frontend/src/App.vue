<template>
    <div id="app">
        <video muted autoplay loop class="back" style="width:100%;position:fixed;top:0px;z-index:-99;left:0px">
            <source src="@/assets/universebackground.mp4" type="video/mp4">
        </video>
        <Header />
        <router-view :key="$route.fullPath" />
    </div>
</template>

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
    font-family: Avenir, Helvetica, Arial, Gothic A1, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
}

#nav {
    padding: 30px;
}
</style>
