<template>
  <div class="post">
    <img :src="post.profileImage" alt="프로필 이미지">
    <div class="post-content">
      <div class="user-info">
        <div>
          <h3>{{ post.username }}</h3>
          <span>{{ post.handle }}</span>
        </div>
        <span class="timestamp">{{ getTimeString() }}</span>
      </div>
      <p>{{ post.content }}</p>
    </div>
  </div>
</template>

<style scoped>
.post {
  display: flex;
  justify-content: space-between;
  padding: 15px;
  border-bottom: 1px solid #e1e8ed;
}

.post img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.post-content {
  flex-grow: 1;
}

.user-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.user-info h3 {
  margin: 0;
  font-size: 16px;
  color: #1da1f2;
}

.user-info span {
  color: #657786;
  font-size: 14px;
}

.timestamp {
  font-size: 12px;
}

.post-content p {
  margin: 5px 0;
  font-size: 14px;
}
</style>

<script setup>
defineProps({
  post: {
    id: Number,
    username: String,
    handle: String,
    content: String,
    profileImage: String,
    timestamp: Date
  }
})
</script>

<script>
export default {
  methods: {
    getTimeString() {
      return new Date().toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    },
  }
}
</script>