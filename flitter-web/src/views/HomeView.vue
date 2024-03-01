<template>
  <SideBar />

  <div id="app">
    <div class="container">
      <div class="tab-container">
        <div class="tab" :class="{ 'active-tab': activeTab === 'forYou' }" @click="changeTab('forYou')">For You</div>
        <div class="tab" :class="{ 'active-tab': activeTab === 'following' }" @click="changeTab('following')">Following</div>
      </div>

      <PostFormComp />

      <div class="tab-content" :class="{ 'active': activeTab === 'forYou' }">
        <PostComp v-for="post in postsForYou"
              :post="post"
        />
      </div>

      <div class="tab-content" :class="{ 'active': activeTab === 'following' }">
        <PostComp v-for="post in postsFollowing"
              :post="post"
        />
      </div>
    </div>
  </div>
</template>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f5f8fa;
}

.container {
  max-width: 600px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 20px auto 20px 240px;
}

.tab-container {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.tab {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border-radius: 5px 5px;
  background-color: #ffffff;
  margin-right: 10px;
}

.active-tab {
  background-color: #1da1f2;
  color: #ffffff;
}

.tab-content {
  display: none;
}

.tab-content.active {
  display: block;
}
</style>

<script>
import SideBar from "@/components/layout/SideBar.vue";
import PostFormComp from "@/components/feed/PostFormComp.vue";
import PostComp from "@/components/feed/PostComp.vue";

export default {
  components: {PostComp, PostFormComp, SideBar},
  data() {
    return {
      postsForYou: [],
      postsFollowing: [
        {
          id: 2,
          username: '김철수',
          handle: '@kimchulsu',
          content: '포스트를 날려요!',
          profileImage: 'https://via.placeholder.com/50',
          timestamp: new Date()
        }
        // 필요에 따라 더 많은 트윗 추가 가능
      ],
      activeTab: 'forYou'
    };
  },
  methods: {
    generateDummyPosts() {
      const dummyPosts = [];
      for (let i = 0; i < 50; i++) {
        dummyPosts.push({
          id: i + 1,
          username: `사용자${i + 1}`,
          handle: `@user${i + 1}`,
          content: `이것은 더미 포스트입니다. ${i + 1}`,
          profileImage: 'https://via.placeholder.com/50',
          timestamp: new Date()
        });
      }
      return dummyPosts;
    },
    changeTab(tab) {
      this.activeTab = tab;
    }
  },
  mounted() {
    this.postsForYou = this.generateDummyPosts();
  }
}
</script>