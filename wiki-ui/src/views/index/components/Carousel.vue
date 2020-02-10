<template>
  <el-carousel :interval="5000" arrow="always" class="carousel">
    <el-carousel-item>
      <img src="static/img/banner_1.jpg">
    </el-carousel-item>
    <el-carousel-item>
      <img src="static/img/banner_4.jpg">
    </el-carousel-item>
    <el-carousel-item>
      <img src="static/img/banner_5.jpeg">
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {

}
</script>
<style rel="stylesheet/scss" lang="scss">
    .carousel{
        border-radius: 10px;
        img{
            width: 100%;
            height: 100%;
        }
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: var(--hzq-h3-font-size);
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>
