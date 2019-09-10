<template>
  <div class="doc-tree" style=" min-height: calc(100vh - 60px)">
    <el-input v-if="docStatus == 'R'" v-model="filterText" placeholder="输入关键字进行过滤" />
    <div v-else>
      <el-button style="width:100%;" @click="toAdd">添加文档
        <i class="el-icon-plus" />
      </el-button>
    </div>

    <div class="tree-box">
      <div class="zTreeDemoBackground left">
        <ul id="treeContent" class="ztree" />
      </div>
    </div>

    <div id="rMenu">
      <ul>
        <li id="m_add">
          <el-button class="m_button" size="mini" @click="toAdd">添加文档</el-button>
        </li>
        <li id="m_edit">
          <el-button class="m_button" size="mini" @click="toEdit">编辑</el-button>
        </li>
        <li id="m_del">
          <el-button class="m_button" size="mini" @click="deleted">删除</el-button>
        </li>
      </ul>
    </div>

    <el-dialog :visible.sync="contentDialogFormVisible" :title="contentTitle" width="35%">
      <el-form :model="contentForm" label-width="80px">
        <el-form-item label="文档名称">
          <el-input v-model="contentForm.title" />
        </el-form-item>
        <el-form-item label="文档类型">
          <el-select v-if="contentFormStatus === 'edit'" v-model="contentForm.docType" disabled="true" placeholder="请选择" style="width:100%">
            <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select v-else v-model="contentForm.docType" placeholder="请选择" style="width:100%">
            <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="文档排序">
          <el-input id="tentacles" v-model="contentForm.sequence" type="number" min="10" max="100" @keyup.enter.native="addOrUpdate" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="contentDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { all, addOrUpdate, deletedById } from '@/api/doc/content'
import $ from 'jquery'
import bus from '@/assets/js/eventbus'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    }
  },
  data() {
    return {
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
      contentDialogFormVisible: false,
      contentTitle: '新增文档',
      contentFormStatus: 'add',
      contentForm: this.initConentForm(),
      checkContentId: null,
      typeOptions: [
        {
          value: 'A',
          label: '文章'
        },
        {
          value: 'D',
          label: '目录'
        }
      ]
    }
  },
  watch: {
    docStatus: {
      immediate: true,
      handler: function(val, oldVal) {
        // 如果状态发生变化,需要修改树的状态
        this.tree()
      }
    },
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
          onClick: this.onClickMethod,
          onRightClick: this.onRightClickMethod
        }
      }
      return set
    },
    tree(conId) {
      all(this.docId).then(res => {
        this.zNodes = res.data
        $.fn.zTree.init($('#treeContent'), this.initSetting(), res.data)
        const zTree_Menu = $.fn.zTree.getZTreeObj('treeContent')
        var treeNode
        if (conId) {
          // 根据id进行选中
          treeNode = zTree_Menu.getNodeByParam('id', conId)
        } else {
          // 首次加载数据,或者没有制定选择时候,默认选择第一个
          treeNode = zTree_Menu.getNodes()[0]
        }
        zTree_Menu.selectNode(treeNode)
        this.selectContentTree(treeNode)
      })
    },
    toAdd() {
      // 初始化form表单
      this.contentForm = this.initConentForm()
      if (this.checkContentId) {
        this.contentForm.parentId = this.checkContentId
      }
      this.contentDialogFormVisible = true
      this.contentFormStatus = 'add'
      this.cancelRMenu()
    },
    addOrUpdate() {
      addOrUpdate(this.contentForm).then(res => {
        this.contentDialogFormVisible = false
        this.checkContentId = null
        this.tree(res.data)
      })
    },
    // 取消树拖拽
    beforeDragMethod(treeId, treeNodes) {
      return false
    },
    deleted() {
      this.$confirm(
        '此操作将永久删除[' + this.contentForm.title + ']文档?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          typr: 'warning'
        }
      ).then(() => {
        deletedById(this.checkContentId).then(() => {
          this.tree() // 刷新树
          this.$notify({
            title: '成功',
            message: '文档[' + this.contentForm.title + ']删除成功!',
            type: 'success'
          })
        })
      })
      return false
    },
    toEdit(treeNode) {
      this.contentDialogFormVisible = true
      this.contentFormStatus = 'edit'
      this.cancelRMenu()
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
    selectContentTree(treeNode) {
      bus.$emit('clickContentTree', treeNode)
    },
    onRightClickMethod(event, treeId, treeNode) {
      if (this.docStatus !== 'E') return // 只有进入编辑模式,才能右击操作
      if (
        !treeNode &&
        event.target.tagName.toLowerCase() !== 'button' &&
        $(event.target).parents('a').length === 0
      ) {
        // 没有选中node时候,默认是添加最高级node节点
        this.checkContentId = null
        $.fn.zTree.getZTreeObj('treeContent').cancelSelectedNode() // 取消节点的选中状态。
        this.showRMenu(treeNode, 'root', event.clientX, event.clientY)
      } else if (treeNode && !treeNode.noR) {
        // 避免编辑的时候,进入后台查询数据进行回现
        this.contentForm.id = treeNode.id
        this.contentForm.title = treeNode.title
        this.contentForm.sequence = treeNode.sequence
        this.contentForm.parentId = treeNode.parentId

        // 选中节点节点添加node
        this.checkContentId = treeNode.id
        $.fn.zTree.getZTreeObj('treeContent').selectNode(treeNode)
        this.showRMenu(treeNode, 'node', event.clientX, event.clientY)
      }
    },
    showRMenu(treeNode, type, x, y) {
      // 选择需要展示的按钮
      this.checkShowRMenu(treeNode, type)
      y += document.body.scrollTop
      x += document.body.scrollLeft
      $('#rMenu').css({ top: y + 'px', left: x + 'px', visibility: 'visible' })

      $('body').bind('mousedown', this.onBodyMouseDown)
    },
    checkShowRMenu(treeNode, type) {
      // 默认展示所有
      $('#rMenu ul').show()
      // 如果没有选择节点
      if (type === 'root') {
        $('#m_add').show()
        $('#m_del').hide()
        $('#m_edit').hide()
        return
      }
      // 如果不是目录,不允许新增子集
      if (!treeNode.parent) {
        $('#m_add').hide()
        $('#m_del').show()
        $('#m_edit').show()
        return
      } else {
        // 目录
        $('#m_add').show()
        $('#m_edit').show()
        // 判断目录下面是否有文档存在,如果没有可以删除
        if (!treeNode.children) {
          $('#m_del').show()
        } else {
          $('#m_del').hide()
        }
      }
    },
    onBodyMouseDown(event) {
      if (
        !(
          event.target.id === 'rMenu' ||
          $(event.target).parents('#rMenu').length > 0
        )
      ) {
        this.cancelRMenu()
      }
    },
    cancelRMenu() {
      $('#rMenu').css({ visibility: 'hidden' })
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
  // .ztree {
  //   width: 280px;
  //   min-height: calc(100vh - 100px);
  //   overflow-y: scroll;
  //   overflow-x: auto;
  // }
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
#rMenu {
  position: absolute;
  visibility: hidden;
  z-index: 9999;
  top: 0;
  text-align: left;
  padding: 2px;
}
#rMenu ul {
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  font-weight: inherit;
  font-style: inherit;
  font-size: 100%;
  font-family: inherit;
  vertical-align: baseline;
  li {
    margin: 0px;
    padding: 0px;
    cursor: pointer;
    list-style: none outside none;
    background-color: #fff;
  }
  .m_button {
    width: 80px;
    border-radius: 0px;
  }
}
</style>

