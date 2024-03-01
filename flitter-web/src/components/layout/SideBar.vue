<template>
  <div class="sidebar">
    <ul>
      <li><a href="#"><font-awesome-icon :icon="['fas', 'house']" /></a></li>
      <li><a href="#"><font-awesome-icon :icon="['fas', 'magnifying-glass']" /></a></li>
    </ul>
    <div class="login-user-info">
      <img src="https://via.placeholder.com/50" alt="프로필 이미지">
      <div>
        <h3>사용자</h3>
        <span>@user</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.sidebar {
  width: 220px;
  height: 100vh;
  background-color: transparent; /* 투명한 배경색 */
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.sidebar ul {
  list-style: none;
}

.sidebar ul li {
  padding: 15px 0;
  text-align: center;
}

.sidebar ul li:hover {
  background-color: rgba(255, 255, 255, 0.1); /* 투명한 배경색에 마우스 호버 시 약간의 투명한 하이라이트 */
}

.sidebar ul li a {
  color: #1da1f2; /* 아이콘 색상 변경 */
  text-decoration: none;
  display: block;
}

.login-user-info {
  display: flex;
  align-items: center;
  padding: 15px;
}

.login-user-info h3 {
  margin: 0;
  font-size: 16px;
  color: #1da1f2;
}

.login-user-info span {
  color: #657786;
  font-size: 14px;
}

.login-user-info img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}
</style>

<script>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  components: {FontAwesomeIcon},
}
</script>