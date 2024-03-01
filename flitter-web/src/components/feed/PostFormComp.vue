<template>
  <form @submit.prevent="posting">
    <textarea v-model="newPost" placeholder="무슨 일이 일어나고 있나요?"></textarea>
    <div class="buttons">
      <button class="image">
        <font-awesome-icon :icon="['fas', 'image']" />
      </button>
      <button type="submit">트윗</button>
    </div>
  </form>
</template>

<style scoped>
form {
  margin-bottom: 20px;
  border-bottom: 1px solid #e1e8ed;
  padding-bottom: 15px;
}

form textarea {
  width: calc(100% - 20px);
  padding: 10px;
  font-size: 14px;
  resize: none;
  border: none;
  border-radius: 5px;
  margin-bottom: 10px;
}

form button {
  padding: 10px 20px;
  font-size: 14px;
  background-color: #1da1f2;
  color: #ffffff;
  border: none;
  border-radius: 20px;
  cursor: pointer;
}

form .buttons {
  justify-content: space-between;
  display: flex;
}

form .buttons .image {
  background-color: transparent;
  color: #1da1f2;
}
</style>

<script>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  components: {FontAwesomeIcon},
  data() {
    return {
      newPost: '',
    };
  },
  methods: {
    posting() { },
  }
}
</script>