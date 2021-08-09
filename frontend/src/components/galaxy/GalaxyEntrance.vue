<template>
  <div>
    <!-- 갤럭시 입장 버튼 -->
    <button
      type="button"
      class="btn btn-danger login-btn"
      data-bs-toggle="modal"
      data-bs-target="#GalaxyEntranceModal"
      data-bs-whatever="@mdo"
    >
      Hello Galaxy!
    </button>

    <!-- 갤럭시 입장 모달창 -->
    <div
      class="modal fade"
      id="GalaxyEntranceModal"
      tabindex="-1"
      aria-labelledby="GalaxyEntranceModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <h2>갤럭시에 오신 것을 환영합니다!</h2>
            <form>
              <!-- 이메일 입력칸 -->
              <div class="d-flex">
                <input
                  type="text"
                  class="form-control input-form"
                  id="status-message"
                  v-model="statusMessage"
                  placeholder="메시지는 20자 이내로 작성해주세요."
                />
              </div>
            </form>
          </div>
          <!-- 모달창 내부 로그인 버튼 -->
          <div class="modal-footer login-footer">
            <button
              type="button"
              class="btn btn-primary login-button"
              data-bs-dismiss="modal"
              @click="entrance(credentials)"
            >
              입장
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginModal',
  props: {
    text: {
      type: String,
    },
  },
  data: function() {
    return {
      statusMessage: null,
    };
  },
  methods: {
    entrance() {
      this.$router.push('/Galaxy');
    },
  },
};
</script>

<style></style>
