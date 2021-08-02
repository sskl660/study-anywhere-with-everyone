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
      
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      ok-title="생성"
      cancel-title="취소"
      style="top: 5;"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <div class="d-flex justify-content-center">
            <div class="left flex-item">
                <div class="col Tleft flex-item">
                    
                </div>
                <div class="col Dleft flex-item">
                    <div class="d-flex justify-content-center">
                        <!-- 이메일 입력칸 라벨 -->
                        <b-form-group
                            label="이메일"
                            label-for="name-input"
                            invalid-feedback="E-mail is required"
                            :state="nameState"
                        ></b-form-group>
                        <!-- 이메일 입력칸 -->
                        <b-form-input
                            id="name-input"
                            v-model="name"
                            :state="nameState"
                            required
                            style="width:50%; margin-bottom:15px;"
                        ></b-form-input>
                        </div>

                        <div class="d-flex justify-content-center">
                        <!-- 비밀번호 입력칸 라벨 -->
                        <b-form-group
                            label="비밀번호"
                            label-for="name-input"
                            invalid-feedback="Password is required"
                            :state="nameState"
                        ></b-form-group>
                        <!-- 비밀번호 입력칸 -->
                        <b-form-input
                            id="name-input"
                            v-model="name"
                            :state="nameState"
                            required
                            style="margin-right:15px;"
                        ></b-form-input>
                        </div>
                </div>
            </div>

            <div class="right flex-item">

            </div>
        </div>

      </form>
    </b-modal>
  </div>
</template>

<script>
  // import "@/components/css/PostDetailModal.css";

  export default {
    name: 'PostDetailModal',
    props: {
      text: {
        type: String
      }
    },
    data() {
      return {
        name: '',
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

