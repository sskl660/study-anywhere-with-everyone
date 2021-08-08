<template>
  <!-- <div class="d-flex justify-content-center">
    <div class="godseven"></div>
  </div> -->
  <div>
    <div class="itemFileBox" ref="itemFileBox">
      <input
        type="file"
        class="item-file-image"
        id="uploadItemFile"
        ref="uploadItemFile"
        @change="onFileSelected"
        accept="image/*"
      />
      <label for="uploadItemFile">
        <div class="wrapper-image">
          <img ref="uploadItemImage" />
        </div>
      </label>
      <button v-on:click="getImage()">사나이 유희왕 할수있다</button>
      <label for="ItemFile">
        <div class="wrapper-image">
          <img id="image" src="" />
        </div>
      </label>
    </div>
    <form @submit.prevent="submitForm" class="form" enctype="multipart/form-data">
      <div>
        <span>challenge no</span>
        <input type="number" v-model="challengeno" />
      </div>
      <div>
        <span>file</span>
        <input type="file" ref="taskfile" name="taskfile" id="taskfile" @change="taskfileSelect" />
      </div>
      <div>
        <span>img</span>
        <input type="file" ref="taskimg" name="taskimg" id="taskimg" @change="taskimgSelect" />
      </div>
      <div>
        <span>taskContent</span>
        <input type="text" v-model="taskcontent" />
      </div>
      <div>
        <span>taskDesc</span>
        <input type="text" v-model="taskdesc" />
      </div>
      <div>
        <span>taskIndex</span>
        <input type="number" v-model="taskindex" />
      </div>
      <div>
        <span>userEmail</span>
        <input type="text" v-model="useremail" />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import http from ".././util/http-common.js";

export default {
  data() {
    return {
      challengeno: 0,
      taskfile: new File(),
      taskimg: new File(),
      taskcontent: "",
      taskdesc: "",
      taskindex: 0,
      useremail: "",
      // formData: new FormData(),
    };
  },
  methods: {
    //파일 선택시 이미지 미리보기
    onFileSelected(event) {
      let image = event.target;
      if (image.files[0]) {
        let itemImage = this.$refs.uploadItemImage; //img dom 접근
        itemImage.src = window.URL.createObjectURL(image.files[0]); //img src에 blob주소 변환
        this.itemImageInfo.uploadImages = itemImage.src; //이미지 주소 data 변수에 바인딩해서 나타내게 처리
        itemImage.width = "100"; // 이미지 넓이
        itemImage.onload = () => {
          window.URL.revokeObjectURL(this.src); //나중에 반드시 해제해주어야 메모리 누수가 안생김.
        };
      }
    },
    getImage: function(e) {
      //주의: BLOB 파일 용량 제한은 64kb까지임.. ->  ㅡ
      http.get("/viewimage/youhhi0509@naver.com").then((response) => {
        console.log(response.data);
        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));

        document.getElementById("image").src = imgsrc;
      });
      http.get("/profile/info/youhhi0509@naver.com").then((response) => {
        console.log(response.data);
        var imgsrc =
          "data:image/png;base64," +
          btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));

        // document.getElementById("image").src = imgsrc;
      });
    },
    submitForm() {
      var frm = new FormData();
      var tfile = document.getElementById("taskfile");
      var timg = document.getElementById("taskimg");
      frm.append("challengeNo", this.challengeno);
      frm.append("file", tfile.files[0]);
      frm.append("img", timg.files[0]);
      frm.append("taskContent", this.taskcontent);
      frm.append("taskDesc", this.taskdesc);
      frm.append("taskIndex", this.taskindex);
      frm.append("userEmail", this.useremail);
      http
        .post("/challenge/task", frm, {
          header: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          // console.log(formData.getAll);
        })
        .catch((error) => {
          // for (var pair of this.formData.entries()) {
          //   console.log(pair[0] + "," + pair[1]);
          // }
          console.log(error);
        });
    },
  },
};
</script>

<style>
.godseven {
  width: 900px;
  height: 821px;
  /* position:relative; */
  background-image: url(~@/assets/EPeacefulWorld.png);
  margin-top: 100px;
}
</style>
