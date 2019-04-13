<template>
  <div class="dashboard-container">
    <router-link to="/doc/layout">
      <div v-for="a in 10" :key="a" class="boxs">
        <div class="icon">
          <img class="pic-404__child right" src="@/assets/doc_images/doc.png" alt="404">
        </div>
        <h2 style="position: relative;top:206px;font-size: 17px; margin:5px 15px;">
          标题
        </h2>
        <p class="desc">
          描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述
        </p>
      </div>
    </router-link>
  </div>
</template>

<script>

export default {
  name: 'Dashboard'
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    width: 984px;
    padding: 20px 00px 40px 00px;
    margin: 0 auto;
    .boxs{
      width: 247px;
      height: 290px;
      min-width: 245px;
      float: left;
      display: block;
      position: relative;
      min-height: 120px;
      overflow: hidden;
      cursor: pointer;
      margin-left: -1px;
      margin-top: -1px;
      border: 1px solid #e4ebf0;
      padding: 0;
      .icon{
        width: 100%;
        height: 209px;
        position: absolute;
        top: 15px;
      }
      .desc{
        position:relative;
        top:202px;
        font-size:13px;
        margin:5px 15px;
        overflow: hidden;
        line-height: 20px;
        max-height: 40px;
      }
      // 兼容火狐
      .desc::after{
        content: "...";
        position: absolute;
        bottom: 0;
        right: 0;
        padding-left: 50px;
        background: -webkit-linear-gradient(left, transparent, #fff 55%);
        background: -o-linear-gradient(right, transparent, #fff 55%);
        background: -moz-linear-gradient(right, transparent, #fff 55%);
        background: linear-gradient(to right, transparent, #fff 55%);
      }
    }
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
