<template>
  <div>
    <!-- 모달창 띄우는 버튼 -->
    <b-button pill v-b-modal.modal-prevent-closing variant="outline-primary">{{ text }}</b-button>

    <!-- 필요 없는 부분이나 참고를 위해 남겨둠. 구현 완료 후 지울 예정 -->
    <!-- <div class="mt-3">
      Submitted Names:
      <div v-if="submittedNames.length === 0">--</div>
      <ul v-else class="mb-0 pl-3">
        <li v-for="(name, idx) in submittedNames" :key="idx">{{ name }}</li>
      </ul>
    </div> -->

    <!-- 모달창 -->
    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      ok-only
      ok-title="로그인"
      style="top: 5;"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <div class="d-flex justify-content-center">
          <!-- 이메일 입력칸 라벨 -->
          <b-form-group
            label="이메일"
            label-for="email-input"
            invalid-feedback="E-mail is required"
            :state="nameState"
          ></b-form-group>
          <!-- 이메일 입력칸 -->
          <b-form-input
            id="email-input"
            v-model="email"
            :state="nameState"
            required
            style="width:50%; margin-bottom:15px;"
          ></b-form-input>
        </div>
        <div class="d-flex justify-content-center">
          <!-- 비밀번호 입력칸 라벨 -->
          <b-form-group
            label="비밀번호"
            label-for="password-input"
            invalid-feedback="Password is required"
            :state="nameState"
          ></b-form-group>
          <!-- 비밀번호 입력칸 -->
          <b-form-input
            id="password-input"
            v-model="password"
            :state="nameState"
            required
            style="margin-right:15px;"
          ></b-form-input>
        </div>
        <!-- 로그인 상태 유지 체크 박스 -->
        <b-form-checkbox>
          로그인 상태 유지
        </b-form-checkbox>
      </form>
    </b-modal>
  </div>
</template>

<script>
  import "@/components/css/loginmodal.css";

  export default {
    name: 'LoginModal',
    props: {
      text: {
        type: String
      }
    },
    data() {
      return {
        email: '',
        password: '',
        nameState: null,
        submittedNames: []
      }
    },
    methods: {
      checkFormValidity() {
        const valid = this.$refs.form.checkValidity()
        this.nameState = valid
        return valid
      },
      resetModal() {
        this.name = ''
        this.nameState = null
      },
      handleOk(bvModalEvt) {
        // Prevent modal from closing
        bvModalEvt.preventDefault()
        // Trigger submit handler
        this.handleSubmit()
      },
      handleSubmit() {
        // Exit when the form isn't valid
        if (!this.checkFormValidity()) {
          return
        }
        // Push the name to submitted names
        // this.submittedNames.push(this.name)
        // Hide the modal manually
        this.$nextTick(() => {
          this.$bvModal.hide('modal-prevent-closing')
        })
      },
      close() {
        const obj = document.querySelector('#modal-prevent-closing');
        obj.style.display = 'none';
      }
    },
    // created: function() {
    //   document.querySelector('.btn-secondary').innerText = '로그인'
    // }
  }
</script>
