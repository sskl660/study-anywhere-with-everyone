<template>
  <div style="backgroundColor: red">
    <div v-if="isLogin">
      <!-- 에러 로그 제거를 위해 아래 퀵메뉴에서 뺀 아이 -->
      <!-- :menu-count="getCount" -->
      <!-- @process="print" -->
      <!-- :is-open-new-tab="getIsOpenNewTab" -->

      <QuickMenu
        :menu-count="count"
        :icon-class="icons"
        :menu-url-list="list"
        :background-color="backgroundColor"
        :color="color"
        :position="position"
      />

      <!-- <quick-menu
        :menu-count="count"
        :icon-class="icons"
        :menu-url-list="list"
        :background-color="backgroundColor"
        :color="color"
        :position="position"
      >
      </quick-menu> -->
    </div>
  </div>
</template>

<script>
// import quickMenu from 'vue-quick-menu';
import QuickMenu from '@/components/header/QuickMenu'
import { mapGetters } from 'vuex';
export default {
  name: 'Navbar',

  data: function() {
    return {
      count: 4,
      icons: ['fa fa-home', 'fa fa-address-card', 'fa fa-flag', 'fa fa-rocket'],
      list: [
        { isLink: true, url: '/homefeed' },
        { isLink: true, url: { path: '/profile', query: { user: 'myProfile' } } },
        { isLink: true, url: '/challenges' },
        { isLink: true, url: '/Galaxy' },
      ],
      backgroundColor: '#F9D479',
      color: '#EE4748',
      position: 'top-right',
      isOpenNewTab: false,
    };
  },
  methods: {
    one: function () {
      console.log(1)
    }
  },
  components: {
    // quickMenu: quickMenu,
    QuickMenu
  },
  computed: {
    ...mapGetters(['isLogin']),
  },
};
</script>

<style lang="less">
.menu-animate {
  -webkit-animation: bounce 1s linear 1s;
  -moz-animation: bounce 1s linear 1s;
  animation: bounce 1s linear 1s;
}

.quick-menu {
  color: #fff;
  position: fixed;
  width: 60px;
  height: 60px;
  -webkit-transition: all 1s ease;
  -moz-transition: all 1s ease;
  transition: all 1s ease;
  right: 30px;
  > .menu {
    display: block;
    position: absolute;
    border-radius: 50% !important;
    width: 60px;
    height: 60px;
    text-align: center;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.23), 0 3px 10px rgba(0, 0, 0, 0.16);
    color: #1f4256 !important;
    -webkit-transition: all 1s ease;
    -moz-transition: all 1s ease;
    transition: all 1s ease;
    .core-menu {
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0px;
      top: 0px;
      width: 65px;
      height: 65px;
      -webkit-transform: rotate(180deg);
      -moz-transform: rotate(180deg);
      -ms-transform: rotate(180deg);
      -o-transform: rotate(180deg);
      transform: rotate(180deg);
      -webkit-transition: all 1s ease;
      -moz-transition: all 1s ease;
      transition: all 1s ease;
      .bar {
        -webkit-transition: all 1s ease;
        -moz-transition: all 1s ease;
        transition: all 1s ease;
        width: 28px;
        height: 4.5px !important;
        background: #1f4256 !important;
        position: absolute;
        top: 40%;
        margin-top: -1.5px;
        left: 21px;
        -webkit-transform-origin: 0% 50%;
        -moz-transform-origin: 0% 50%;
        -ms-transform-origin: 0% 50%;
        -o-transform-origin: 0% 50%;
        transform-origin: 0% 50%;
        &:before,
        &:after {
          -webkit-transition: all 1s ease;
          -moz-transition: all 1s ease;
          transition: all 1s ease;
          content: '';
          width: 28px;
          height: 4.5px !important;
          background: #1f4256 !important;
          position: absolute;
          left: 0px;
          -webkit-transform-origin: 0% 50%;
          -moz-transform-origin: 0% 50%;
          -ms-transform-origin: 0% 50%;
          -o-transform-origin: 0% 50%;
          transform-origin: 0% 50%;
        }
        &:before {
          margin-top: 30%;
        }
        &:after {
          margin-top: 60%;
        }
      }
    }
  }
  .sub-menu {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    position: absolute;
    width: 60px;
    height: 60px;
    font-size: 30px;
    text-align: center;
    border-radius: 50% !important;
    a {
      outline: none;
      text-decoration: none;
      display: inline-block;
      border-radius: 50% !important;
      width: 100%;
      height: 100%;
      i {
        outline: none;
        font-size: 30px;
        margin-top: 12px;
        color: #1f4256;
        &:before {
          vertical-align: middle;
        }
      }
      &:hover {
        cursor: pointer;
        background-color: #fff !important;
      }
    }
  }
  &.active {
    -webkit-transform: rotate(0deg) !important;
    -moz-transform: rotate(0deg) !important;
    -ms-transform: rotate(0deg) !important;
    -o-transform: rotate(0deg) !important;
    transform: rotate(0deg) !important;
    .menu {
      -webkit-transform: scale(0.7);
      -moz-transform: scale(0.7);
      -ms-transform: scale(0.7);
      -o-transform: scale(0.7);
      transform: scale(0.7);
      .bar {
        top: 50%;
        margin-top: -1.5px;
        left: 50%;
        margin-left: -12px;
        -webkit-transform-origin: 50% 50%;
        -moz-transform-origin: 50% 50%;
        -ms-transform-origin: 50% 50%;
        -o-transform-origin: 50% 50%;
        transform-origin: 50% 50%;
        -webkit-transform: rotate(405deg);
        -moz-transform: rotate(405deg);
        -ms-transform: rotate(405deg);
        -o-transform: rotate(405deg);
        transform: rotate(405deg);
        &:before {
          -webkit-transform-origin: 50% 50%;
          -moz-transform-origin: 50% 50%;
          -ms-transform-origin: 50% 50%;
          -o-transform-origin: 50% 50%;
          transform-origin: 50% 50%;
          -webkit-transform: rotate(-450deg);
          -moz-transform: rotate(-450deg);
          -ms-transform: rotate(-450deg);
          -o-transform: rotate(-450deg);
          transform: rotate(-450deg);
          margin-top: 0px;
        }
        &:after {
          opacity: 0;
        }
      }
    }
  }
}
@-webkit-keyframes bounce {
  0%,
  100% {
    -webkit-transform: translateY(0px);
  }
  10% {
    -webkit-transform: translateY(6px);
  }
  30% {
    -webkit-transform: translateY(-4px);
  }
  70% {
    -webkit-transform: translateY(3px);
  }
  90% {
    -webkit-transform: translateY(-2px);
  }
}
@-moz-keyframes bounce {
  0%,
  100% {
    -moz-transform: translateY(0px);
  }
  10% {
    -moz-transform: translateY(6px);
  }
  30% {
    -moz-transform: translateY(-4px);
  }
  70% {
    -moz-transform: translateY(3px);
  }
  90% {
    -moz-transform: translateY(-2px);
  }
}
@keyframes bounce {
  0%,
  100% {
    -webkit-transform: translateY(0px);
    -moz-transform: translateY(0px);
    -ms-transform: translateY(0px);
    -o-transform: translateY(0px);
    transform: translateY(0px);
  }
  10% {
    -webkit-transform: translateY(6px);
    -moz-transform: translateY(6px);
    -ms-transform: translateY(6px);
    -o-transform: translateY(6px);
    transform: translateY(6px);
  }
  30% {
    -webkit-transform: translateY(-4px);
    -moz-transform: translateY(-4px);
    -ms-transform: translateY(-4px);
    -o-transform: translateY(-4px);
    transform: translateY(-4px);
  }
  70% {
    -webkit-transform: translateY(3px);
    -moz-transform: translateY(3px);
    -ms-transform: translateY(3px);
    -o-transform: translateY(3px);
    transform: translateY(3px);
  }
  90% {
    -webkit-transform: translateY(-2px);
    -moz-transform: translateY(-2px);
    -ms-transform: translateY(-2px);
    -o-transform: translateY(-2px);
    transform: translateY(-2px);
  }
}
</style>
