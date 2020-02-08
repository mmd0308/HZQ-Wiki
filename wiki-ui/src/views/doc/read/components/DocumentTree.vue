<template>
  <div class="doc-tree" style=" min-height: calc(100vh - 60px)">
    <el-input v-model="filterText" placeholder="输入关键字进行过滤" />

    <div class="tree-box">
      <div class="zTreeDemoBackground left">
        <ul id="treeContent" class="ztree" />
      </div>
    </div>

  </div>
</template>

<script>
import { getShowAllByDocId } from '@/api/doc/content'
import $ from 'jquery'
import bus from '@/assets/js/eventbus'
export default {
  data() {
    return {
      moudle: 'contents',
      docId: this.$route.params.id,
      setting: this.initSetting(),
      zNodes: [],
      filterText: '',
      treeVisible: false,
      treeId: '',
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      checkContentId: null
    }
  },
  watch: {
    filterText(val) {
      this.filterNode(val)
    }
  },
  created() {
    this.tree()
  },
  methods: {
    initConentForm() {
      return {
        id: null,
        title: '',
        sequence: 0,
        parentId: -1, // 表示顶级菜单
        docId: this.docId,
        docType: 'A'
      }
    },
    initSetting() {
      var set = {
        view: {
          showLine: true,
          showIcon: false,
          selectedMulti: false,
          dblClickExpand: true // 双击展开
        },
        data: {
          key: {
            name: 'title',
            isParent: 'parent'
          },
          simpleData: {
            enable: true,
            idKey: 'id',
            pIdKey: 'parentId'
          }
        },
        callback: {
          beforeDrag: this.beforeDragMethod, // 取消拖拽
          onClick: this.onClickMethod
        }
      }
      return set
    },
    tree(conId) {
      getShowAllByDocId(this.docId).then(res => {
        this.zNodes = res
        $.fn.zTree.init($('#treeContent'), this.initSetting(), res)
        const zTree_Menu = $.fn.zTree.getZTreeObj('treeContent')

        if (conId == null) {
          // 通过请求路径打开指定的文章
          conId = this.$route.params.contentId
        }
        if (conId != null) {
          var treeNode = zTree_Menu.getNodeByParam('id', conId, null)
          zTree_Menu.selectNode(treeNode)
          // 加载数据
          this.selectContentTree(treeNode)
          return
        }
        // else {
        //   // 如果没有指定打开文档的某一份文章,TODO
        // }
      })
    },
    // 取消树拖拽
    beforeDragMethod(treeId, treeNodes) {
      return false
    },
    onClickMethod(event, treeId, treeNode) {
      // 只有是父亲级别节点进行展开
      if (treeNode.parent) {
        console.log(treeNode.open)
        var zTree = $.fn.zTree.getZTreeObj('treeContent')
        // 展开节点
        zTree.expandNode(treeNode)
        // 修改图标
        var icon_bottom = 'hzq-tree-right-icon-bottom-' + treeNode.tId
        var icon_right = 'hzq-tree-right-icon-right-' + treeNode.tId
        if (treeNode.open) {
          $('#' + icon_bottom).css('display', 'block')
          $('#' + icon_right).css('display', 'none')
        } else {
          $('#' + icon_right).css('display', 'block')
          $('#' + icon_bottom).css('display', 'none')
        }
      } else {
        this.selectContentTree(treeNode)
      }
    },
    // 选择指定节点,显示内容
    selectContentTree(treeNode) {
      this.routerPush(treeNode)
      // 加载内容
      bus.$emit('clickReadContentTree', treeNode)
    },
    routerPush(treeNode) {
      this.$router.push({ path: this.$route.path.substr(0, 9) + '/' + treeNode.docId + '/' + treeNode.id })
    },
    filterNode(val) {
      var filterZNode = []
      this.zNodes.forEach(item => {
        if (item.title.indexOf(val) !== -1) {
          filterZNode.push(item)
        }
      })
      $.fn.zTree.init($('#treeContent'), this.initSetting(), filterZNode)
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.doc-tree {
  .tree-box {
    position: absolute;
    left: 0;
    bottom: 0px;
    top: 100px;
    overflow: auto;
  }
  .ztree li {
    padding: 0;
    margin: 0;
    list-style: none;
    line-height: 36px;
    text-align: left;
    white-space: nowrap;
    outline: 0;
  }
  .ztree li a {
    padding-top: 0px;
    margin: 0;
    text-decoration: none;
    vertical-align: top;
    display: inline-block;
    overflow: hidden;
  }
  .ztree li a:hover {
    background: #ff7d44;
  }
  .ztree li a.curSelectedNode {
    background-color: #ff7d44;
    border: 0;
  }
  .ztree li span.button {
    vertical-align: middle;
  }
  .ztree li span.button.add {
    margin-left: 2px;
    margin-right: -1px;
    background-position: -144px 0;
  }
}
</style>

