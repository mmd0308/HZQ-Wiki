<template>
  <div class="doc-tree">
    <el-input
      v-model="filterText"
      placeholder="输入关键字进行过滤"/>
    {{ docStatus }}
    <el-tree
      ref="tree2"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      node-key="id"
      class="filter-tree"
      default-expand-all
      @node-click="treeClick">
      <div slot-scope="{ node }" class="custom-tree-node " >
        <div>
          <span>
            {{ node.label }}
          </span>
          <span style="    position: absolute;right: 5px;">
            <svg-icon icon-class="setting" />
          </span>
        </div>
      </div>
    </el-tree>
  </div>
</template>

<script>
export default {
  name: 'Sidebar',
  props: {
    docStatus: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      filterText: '',
      treeVisible: false,
      treeId: '',
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      data: [{
        id: '1',
        label: '一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11一级 11',
        children: [{
          id: '11',
          label: '二级 1-1',
          children: [{
            id: '111',
            label: '三级 1-1-1'
          }]
        }]
      }, {
        id: '2',
        label: '一级 2',
        children: [{
          id: '22',
          label: '二级 2-1',
          children: [{
            id: '222',
            label: '三级 2-1-1'
          }]
        }]
      }, {
        id: '3',
        label: '一级 3',
        children: [{
          id: '33',
          label: '二级 3-1',
          children: [{
            id: '3333',
            label: '三级 3-1-1-1-1-1-1-1--1-1-1-1-1-'
          }]
        }, {
          id: '333',
          label: '二级 3-2',
          children: [{
            id: '33333',
            label: '三级 3-2-1'
          }]
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    treeClick(data) {
      debugger
    },
    contextTree(event, data, node) {
      this.$refs[`popover-` + node.key].doShow()
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

