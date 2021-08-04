<template>
  <div>
    <!-- 챌린지 모달 버튼 -->
    <div class="challenge-modal-button" data-bs-toggle="modal" data-bs-target="#challengeModal" data-bs-whatever="@getbootstrap">+</div>

    <div class="modal fade" id="challengeModal" tabindex="-1" aria-labelledby="challengeModalLabel" aria-hidden="true">
      <div class="modal-dialog challenge-dialog">
        <div class="modal-content challenge-content">
          <div class="modal-body">
            <form>
              
              <!-- 챌린지명 -->
              <div class="d-flex">
                <label for="challenge-name" class="col-form-label">챌린지명</label>
                <input type="text" class="form-control" id="challenge-name">
              </div>

              <!-- 카테고리 -->
              <div class="d-flex">
                <label for="category-select" class="col-form-label">카테고리</label>
                <select class="form-select" aria-label="Default select example">
                  <option value="algorithm">알고리즘</option>
                  <option value="cs">CS</option>
                  <option value="job">취업</option>
                </select>
              </div>

              <!-- 난이도 -->
              <div class="d-flex">
                <div>난이도</div>
                <div class="d-flex justify-content-start">
                  <div class="star-rating space-x-4 mx-auto">
                    <input type="radio" id="5-stars" name="rating" value="5" v-model="ratings"/>
                    <label for="5-stars" class="star pr-4">★</label>
                    <input type="radio" id="4-stars" name="rating" value="4" v-model="ratings"/>
                    <label for="4-stars" class="star">★</label>
                    <input type="radio" id="3-stars" name="rating" value="3" v-model="ratings"/>
                    <label for="3-stars" class="star">★</label>
                    <input type="radio" id="2-stars" name="rating" value="2" v-model="ratings"/>
                    <label for="2-stars" class="star">★</label>
                    <input type="radio" id="1-star" name="rating" value="1" v-model="ratings" />
                    <label for="1-star" class="star">★</label>
                  </div>
                </div> 
              </div>

              <div class="d-flex">
                <label for="message-text" class="col-form-label">Message:</label>
                <textarea class="form-control" id="message-text"></textarea>
              </div>

              <datepicker></datepicker>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Send message</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Datepicker from 'vuejs-datepicker';

export default {
  name: 'ChallengeModal',
  components: {
    Datepicker,
  },
  data: function() {
    return {
      rating: null,
    }
  },
  methods: {
    
  },
}
</script>

<style scoped>
  * {
    font-family: "Noto Sans KR", sans-serif;
  }

  .challenge-modal-button {
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60px;
    height: 60px;
    border-radius: 75px;
    bottom: 25px;
    right: 30px;
    background-color: #99B7FF;
    cursor: pointer;
    font-size: 40px;
    font-weight: bold;
  }

  .challenge-dialog {
    margin: 0;
  }

  .challenge-content {
    width: 1000px;
    height: 500px;
  }

  /* 별점 */
  .star-rating {
    display: flex;
    flex-direction: row-reverse;
    font-size: 2.25rem;
    line-height: 2.5rem;
    justify-content: space-around;
    padding: 0 0.2em;
    text-align: center;
    width: 5em;
    margin: 0;
  }
  
  .star-rating input {
    display: none;
  }
  
  .star-rating label {
    -webkit-text-fill-color: #d1cfcd;
    cursor: pointer;
  }
  
  .star-rating :checked ~ label {
    -webkit-text-fill-color: gold;
  }
  
  .star-rating label:hover,
  .star-rating label:hover ~ label {
    /* -webkit-text-fill-color: #fff58c; */
    -webkit-text-fill-color: gold;
  }
</style>