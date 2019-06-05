<template>
  <div class="space">
    <div class="header">
      <!-- <el-input v-model="listQuery.name" style="width:300px" placeholder="请选择用户" class="input-with-select" @keyup.enter.native="page"/> -->
      <el-autocomplete
        v-model="userSpaceForm.userId"
        :fetch-suggestions="querySearchUser"
        style="width:270px"
        class="inline-input"
        placeholder="请选择人员"
        @select="handleSelect"
      />
      <el-select v-model="userSpaceForm.privilege" style="width:160px">
        <el-option
          v-for="item in privilegeData"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-date-picker
        v-model="userSpaceForm.expireTime"
        type="date"
        style="width:160px;"
        placeholder="选择过期时间"/>

      <el-button style="float:right;margin-right:30px;" type="success" @click="spaceToAdd">加入空间</el-button>
    </div>
    <el-table
      :data="userLists"
      style="width: 100%">
      <el-table-column
        label="用户名称">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.fullName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加入时间" width="170">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作权限" width="130">
        <template slot-scope="scope">
          <el-select v-model="scope.row.privilege" disabled placeholder="请选择">
            <el-option
              v-for="item in privilegeData"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" width="110">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.expireTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="spaceDelete(scope.$index, scope.row)">
            <i class="el-icon-delete"/>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin:10px 0 0 0;">
      <el-pagination
        :current-page="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :total="total"
        layout="total,  prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
import { page, addOrUpdate, deletedById } from '@/api/system/user/index'
import { userSpacePage } from '@/api/space/userSpace'
export default {
  props: {
    spaceId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      privilegeData: [
        {
          value: 1,
          label: '浏览者'
        },
        {
          value: 2,
          label: '编辑者'
        },
        {
          value: 3,
          label: '管理员'
        }
      ],
      restaurants: this.loadAll(),
      state1: '',
      dialogTitle: '新增用户',
      dialogUserVisible: false,
      status: '',
      userSpaceForm: this.init(),
      userLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入登陆账号', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '联系电话不能为空' }
        ]
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    init() {
      return {
        userId: '',
        spaceId: this.spaceId,
        privilege: 1,
        expireTime: ''
      }
    },
    spaceToAdd() {
      this.status = 'add'
      this.dialogUserVisible = true
    },
    spaceToEdit(index, row) {
      this.dialogTitle = '修改用户'
      this.status = 'edit'
      this.dialogUserVisible = true
      this.userForm = row
    },
    page() {
      userSpacePage(this.listQuery, this.spaceId).then(response => {
        this.userLists = response.data
        this.total = response.total
      })
    },
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          if (this.status === 'edit') { // 修改用户信息 不修改密码
            this.userForm.password = null
          }
          addOrUpdate(this.userForm).then(() => {
            this.dialogUserVisible = false
            this.userForm = this.init()
            this.page()
          })
        } else {
          return false
        }
      })
    },
    spaceDelete(index, row) {
      this.$confirm('此操作将永久删除[' + row.name + ']空间?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deletedById(row.id).then(() => {
          this.$notify({
            title: '成功',
            message: '空间[' + row.name + ']删除成功!',
            type: 'success'
          })
          this.page()
        })
      })
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.page()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.page()
    },
    cancel() {
      this.dialogUserVisible = false
      this.userForm = this.init()
    },
    querySearchUser(queryStr, cb) {
      var restaurants = this.restaurants
      var results = queryStr ? restaurants.filter(this.createFilter(queryStr)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    handleSelect(item) {
      console.log(item)
    },
    loadAll() {
      return [
        { 'value': '三全鲜食（北新泾店）', 'address': '长宁区新渔路144号' },
        { 'value': 'Hot honey 首尔炸鸡（仙霞路）', 'address': '上海市长宁区淞虹路661号' },
        { 'value': '新旺角茶餐厅', 'address': '上海市普陀区真北路988号创邑金沙谷6号楼113' },
        { 'value': '泷千家(天山西路店)', 'address': '天山西路438号' },
        { 'value': '胖仙女纸杯蛋糕（上海凌空店）', 'address': '上海市长宁区金钟路968号1幢18号楼一层商铺18-101' },
        { 'value': '贡茶', 'address': '上海市长宁区金钟路633号' },
        { 'value': '豪大大香鸡排超级奶爸', 'address': '上海市嘉定区曹安公路曹安路1685号' },
        { 'value': '茶芝兰（奶茶，手抓饼）', 'address': '上海市普陀区同普路1435号' },
        { 'value': '十二泷町', 'address': '上海市北翟路1444弄81号B幢-107' },
        { 'value': '星移浓缩咖啡', 'address': '上海市嘉定区新郁路817号' },
        { 'value': '阿姨奶茶/豪大大', 'address': '嘉定区曹安路1611号' },
        { 'value': '新麦甜四季甜品炸鸡', 'address': '嘉定区曹安公路2383弄55号' },
        { 'value': 'Monica摩托主题咖啡店', 'address': '嘉定区江桥镇曹安公路2409号1F，2383弄62号1F' },
        { 'value': '浮生若茶（凌空soho店）', 'address': '上海长宁区金钟路968号9号楼地下一层' },
        { 'value': 'NONO JUICE  鲜榨果汁', 'address': '上海市长宁区天山西路119号' },
        { 'value': 'CoCo都可(北新泾店）', 'address': '上海市长宁区仙霞西路' },
        { 'value': '快乐柠檬（神州智慧店）', 'address': '上海市长宁区天山西路567号1层R117号店铺' },
        { 'value': 'Merci Paul cafe', 'address': '上海市普陀区光复西路丹巴路28弄6号楼819' },
        { 'value': '猫山王（西郊百联店）', 'address': '上海市长宁区仙霞西路88号第一层G05-F01-1-306' },
        { 'value': '枪会山', 'address': '上海市普陀区棕榈路' },
        { 'value': '纵食', 'address': '元丰天山花园(东门) 双流路267号' },
        { 'value': '钱记', 'address': '上海市长宁区天山西路' },
        { 'value': '壹杯加', 'address': '上海市长宁区通协路' },
        { 'value': '唦哇嘀咖', 'address': '上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元' },
        { 'value': '爱茜茜里(西郊百联)', 'address': '长宁区仙霞西路88号1305室' },
        { 'value': '爱茜茜里(近铁广场)', 'address': '上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺' },
        { 'value': '鲜果榨汁（金沙江路和美广店）', 'address': '普陀区金沙江路2239号金沙和美广场B1-10-6' },
        { 'value': '开心丽果（缤谷店）', 'address': '上海市长宁区威宁路天山路341号' },
        { 'value': '超级鸡车（丰庄路店）', 'address': '上海市嘉定区丰庄路240号' },
        { 'value': '妙生活果园（北新泾店）', 'address': '长宁区新渔路144号' },
        { 'value': '香宜度麻辣香锅', 'address': '长宁区淞虹路148号' },
        { 'value': '凡仔汉堡（老真北路店）', 'address': '上海市普陀区老真北路160号' },
        { 'value': '港式小铺', 'address': '上海市长宁区金钟路968号15楼15-105室' },
        { 'value': '蜀香源麻辣香锅（剑河路店）', 'address': '剑河路443-1' },
        { 'value': '北京饺子馆', 'address': '长宁区北新泾街道天山西路490-1号' },
        { 'value': '饭典*新简餐（凌空SOHO店）', 'address': '上海市长宁区金钟路968号9号楼地下一层9-83室' },
        { 'value': '焦耳·川式快餐（金钟路店）', 'address': '上海市金钟路633号地下一层甲部' },
        { 'value': '动力鸡车', 'address': '长宁区仙霞西路299弄3号101B' },
        { 'value': '浏阳蒸菜', 'address': '天山西路430号' },
        { 'value': '四海游龙（天山西路店）', 'address': '上海市长宁区天山西路' },
        { 'value': '樱花食堂（凌空店）', 'address': '上海市长宁区金钟路968号15楼15-105室' },
        { 'value': '壹分米客家传统调制米粉(天山店)', 'address': '天山西路428号' },
        { 'value': '福荣祥烧腊（平溪路店）', 'address': '上海市长宁区协和路福泉路255弄57-73号' },
        { 'value': '速记黄焖鸡米饭', 'address': '上海市长宁区北新泾街道金钟路180号1层01号摊位' },
        { 'value': '红辣椒麻辣烫', 'address': '上海市长宁区天山西路492号' },
        { 'value': '(小杨生煎)西郊百联餐厅', 'address': '长宁区仙霞西路88号百联2楼' },
        { 'value': '阳阳麻辣烫', 'address': '天山西路389号' },
        { 'value': '南拳妈妈龙虾盖浇饭', 'address': '普陀区金沙江路1699号鑫乐惠美食广场A13' }
      ]
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.space {
    .header{
        margin-bottom: 15px;
    }
}
</style>
