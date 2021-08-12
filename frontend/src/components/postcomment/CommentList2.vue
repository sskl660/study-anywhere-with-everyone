<template>
  <div class="">
    <div v-for="(comment, index) in showMsg" :key="index">
      <div class="commentline">
        <!-- <ProfileImage class="profile-img-default" />// -->
        <img id="msgimage" class="comment-profile" src="" alt="" @click="getProfileImage(index)"/>
        <h6 id="comment-writer">{{ comment.userName }}</h6>
        <div id="comment-opinion">{{ comment.commentContent }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileImage from "@/components/common/ProfileImage.vue";
import axios from "@/util/http-common.js";
import http from "@/util/http-common.js";
import { mapState } from "vuex";

export default {
  name: "CommentList",
  components: {
    ProfileImage,
  },
  data() {
    return {
      showMsg: [
        {
          commentContent: "string",
          commentDate: "2021-08-08T08:15:23.457Z",
          userEmail: "string",
          userImage: "string",
          userName: "string",
        },
      ],
      pimage: '',
    };
  },
  computed: {
    ...mapState(["userEmail"]),
  },
  methods: {
    //댓글 목록 출력
    getShowMsg: function() {
      // alert('댓글 이제 보이려나?')
      // console.log(this.taskInfo.taskNo)
      axios({
        method: "get",
        url: `/comment/${this.$route.query.taskNo}`,
      })
        .then((res) => {
          this.showMsg = res.data;
          console.log("getShowMsg res 성공");
          console.log(this.showMsg)
          // console.log(res.data)
        })
        .catch((err) => {
          console.log("getShowMsg 에러");
          console.log(err);
        });
    },
    // 이미지 가져오기
    getProfileImage: function(index) {
        console.log(this.showMsg);
        http.get(`/viewimage/${this.showMsg[index].userEmail}`).then((response) => {
        console.log("이미지성공");

        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
        document.getElementById("msgimage").src = imgsrc;
        this.showMsg.userImage = imgsrc;
      })
      .catch((error) => {
          if (this.imgsrc == null) {
              document.getElementById('image').src = '/img/ssazip.43ffb363.png';
          }
      });
    },
    // getProfileImage: function(e) {
    //   http.get(`/comment/${this.$route.query.taskNo}`).then((response) => {
    //     console.log("이미지성공");
    //     console.log(response.data[2].userImage);
    //     // console.log(response.data[2].userImage);
    //     var imgsrc =
    //       "data:image/png;base64," +
    //       btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
    //     document.getElementById("msgimage").src = imgsrc;
    //     this.showMsg.userImage = imgsrc;
    //   });
    // },
  },
  created: function() {
    // this.getImg();
    this.$route.query.taskNo;
    this.getShowMsg();
  },
  updated: function() {
      this.getProfileImage();
  },
};
</script>

<style scoped>

</style>
