<template>
  <!-- Modal -->
  <div class="modal fade" id="profileEditModal" tabindex="-1" aria-labelledby="profileEditLabel" aria-hidden="true">
    <div class="modal-dialog profile-modal-position">
      <div class="modal-content profile-modal-custom">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          ...
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
          <button type="button" class="btn profile-submit-btn">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
/* 모달창 커스텀 */
.profile-modal-custom {
  width:800px;
  height: 800px;
  background-color: #EDE0C6;
}
/* 모달창 중앙위치하도록 조정 */
.profile-modal-position {
  margin-right: 850px;
}
.profile-submit-btn {
  background-color: #1C84C4 ;
  border-color: #1C84C4 ;
  color: #fff ;
  font-weight:bold ;
  margin-left: 10px;
}
</style>