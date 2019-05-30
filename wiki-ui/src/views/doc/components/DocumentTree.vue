<template>
  <div class="doc-tree" style=" min-height: calc(100vh - 60px)">
    {{ docStatus }} --  {{ docId }}
    <el-input
      v-if="docStatus == 'R'"
      v-model="filterText"
      placeholder="输入关键字进行过滤"/>
    <div v-else>
      <div style="text-align:center;margin:5px 0px">
        文档标题
        <i class="el-icon-plus" @click="toAdd"/>
      </div>
      <hr>
    </div>

    <el-tree
      ref="catalogTree"
      :data="catalogData"
      :props="defaultProps"
      :filter-node-method="filterNode"
      :expand-on-click-node="false"
      node-key="id"
      class="filter-tree"
      default-expand-all
      style="min-height: calc(100vh - 100px)"
      @node-click="treeClick">
      <div slot-scope="{ node, data }" class="custom-tree-node">
        <div>
          <span>
            {{ node.label }}
          </span>
          <el-dropdown
            v-if="docStatus == 'E'"
            trigger="click"
            style="position: absolute;right: 5px;"
            @command="setCatalog($event,data)">
            <span class="el-dropdown-link">
              <svg-icon icon-class="setting" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="add" >添加文档</el-dropdown-item>
              <el-dropdown-item command="edit">编辑</el-dropdown-item>
              <el-dropdown-item command="delete">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </el-tree>
    <!--
    <el-tree
      v-if="docStatus == 'E'"
      ref="editDocTree"
      :data="catalogData"
      :props="defaultProps"
      :filter-node-method="filterNode"
      node-key="id"
      class="filter-tree"
      default-expand-all
      style="min-height:calc(100vh - 100px)"
      @node-click="treeClick">
      <div slot-scope="{ node }" class="custom-tree-node " >
        <div>
          <span>
            {{ node.label }} --
          </span>
          <span style="position: absolute;right: 5px;">
            <svg-icon icon-class="setting" />
          </span>
        </div>
      </div>
    </el-tree>
-->
    <el-dialog :visible.sync="catalogDialogFormVisible" :title="cataLogTitle" width="35%">
      <el-form :model="cataLogForm" label-width="80px">
        <el-form-item label="文档名称">
          <el-input v-model="cataLogForm.name" />
        </el-form-item>
        <el-form-item label="文档排序">
          <el-input
            id="tentacles"
            v-model="cataLogForm.sequence"
            type="number"
            min="10"
            max="100"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="catalogDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { tree, catalogPage, addOrUpdate, deletedById } from '@/api/doc/catalog'
import { mapGetters } from 'vuex'
import { delay } from 'q'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    },
    docId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      filterText: '',
      treeVisible: false,
      treeId: '',
      catalogData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      catalogDialogFormVisible: false,
      cataLogTitle: '新增文档',
      cataLogForm: {
        name: '',
        sequence: 0,
        parentId: -1, // 表示顶级菜单
        docId: this.docId
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  created() {
    this.tree()
  },
  methods: {
    tree() {
      tree(this.docId).then(res => {
        this.catalogData = res.data
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    toAdd() {
      this.catalogDialogFormVisible = true
    },
    addOrUpdate() {
      addOrUpdate(this.cataLogForm).then(() => {
        this.catalogDialogFormVisible = false
        this.tree()
      })
    },
    treeClick(data) {
    },
    setCatalog(command, data) {
      // 新增自己文档操作
      if (command === 'add') {
        this.cataLogForm.parentId = data.id // 设置当前新增文档的父id
        this.toAdd()
      }

      // 修改操作
      if (command === 'edit') {
        alert('edit')
      }

      // 删除操作
      if (command === 'delete') {
        if (data.children.length === 0) {
          this.$confirm('此操作将永久删除[' + data.name + ']文档?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            typr: 'warning'
          }).then(() => {
            deletedById(data.id).then(() => {
              this.$notify({
                title: '成功',
                message: '文档[' + data.name + ']删除成功!',
                type: 'success'
              })
              this.page()
            })
          })
        } else {
          this.$notify({
            title: '错误',
            message: '该文档包含子级文档,不允许删除!',
            type: 'error'
          })
        }
      }
    }
  }
}
</script>
<style>
.custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>

