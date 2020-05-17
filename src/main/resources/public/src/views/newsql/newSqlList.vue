<template>
  <div class="app-container">
    <div class="panel">
      <div
        class="module"
        style="padding:0;"
      >
        <el-form
          ref="sform"
          :model="sform"
          :inline="true"
          label-width="60px"
        >
          <el-form-item label="表名">
            <el-select
              v-model="sform.tablename"
              filterable
              clearable
              placeholder="请选择表"
            >
              <el-option
                v-for="(item, index) in sform.tablenames"
                :value="item"
                :label="item"
                :key="index"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="标识符">
            <el-select
              v-model="sform.label"
              filterable
              clearable
              placeholder="请选择标识符"
            >
              <el-option
                v-for="(item, index) in sform.labels"
                :value="item"
                :label="item"
                :key="index"
              />
            </el-select>
          </el-form-item>
          <el-date-picker
            v-model="sctime"
            type="date"
            placeholder="开始日期-默认15天前"
          />
          <el-date-picker
            v-model="ectime"
            type="date"
            placeholder="选择结束日期"
          />
          <el-form-item>
            <el-button
              type="primary"
              @click="onSubmit"
            >搜索</el-button>
          </el-form-item>
        </el-form>

      </div>
      <div
        class="module"
        style="padding: 0;"
      >

        <el-table
          :data="items"
          :loading="loading"
          element-loading-text="慢慢等着吧.."
          style="width: 100%"
        >
          <el-table-column
            prop="label"
            label="标识符"
            width="110px"
          />
          <el-table-column
            prop="tablename"
            label="表名"
            width="150px"
          />
          <el-table-column
            prop="templateSql"
            label="模板化SQL"
            width="450px"
          />
          <el-table-column
            :formatter="dateForMatter"
            prop="ctime"
            label="生成时间"
            width="185px"
          > >
          </el-table-column>
          <el-table-column
            prop="handleStatus"
            label="状态"
            width="100px"
          >
            <template slot-scope="scope">
              <el-tag :type="convertStatusCss(scope.row.handleStatus)">{{ convertStatusText(scope.row.handleStatus) }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="finalLevels"
            label="严重等级"
            width="100px"
          >
            <template slot-scope="scope">
              <el-tag :type="convertLevel(scope.row.finalLevels)">{{ convertLevelType(scope.row.finalLevels) }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            class-name="uae-action-column"
            width="160px"
          >
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.status == 1"
                size="small"
                type="warning"
                @click="showOptimizeDialog(scope.row, 'ignored')"
              >
                忽略
              </el-button>
              <el-button
                size="small"
                type="primary"
                @click="info(scope.row)"
              >
                查看
              </el-button>

            </template>
          </el-table-column>

        </el-table>

      </div>
      <div class="block">
        <el-pagination
          :current-page="currentPage"
          :page-sizes="[10, 50, 200, 500]"
          :page-size="50"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
      </div>

      <el-dialog
        :visible.sync="optimizing"
        title="提示"
        width="30%"
      >
        <span v-if="action=='ignored'">确定要忽略吗?</span>
        <span
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="optimizing = false">取 消</el-button>
          <el-button
            type="primary"
            @click="change()"
          >确 定</el-button>
        </span>

      </el-dialog>

    </div>
  </div>
</template>

<script>
import { getNewsqlListByAppId, getTablesByAppId, getLablesByAppId } from '@/api/newsql'
export default {
  data() {
    return {
      items: [],
      loading: false,
      optimizing: false,
      updateAppForm: {
        msg: '',
        id: 0,
        parameter: '',
        status: 0
      },
      appName: 'uae2.0',
      appId: this.$route.query.appId,
      tabName: 'info',
      sql: null,
      sform: {
        tablenames: [],
        tablename: '',
        labels: [],
        label: ''
      },
      action: 'ignored',
      sctime: null,
      ectime: null,
      currentPage: 1,
      total: 0,
      pageSize: 10
    }
  },
  created() {
    this.init(this.appId)
  },
  methods: {
    init(appId) {
      this.loading = true
      getTablesByAppId({ 'appId': appId }).then(info => {
        if (info.success) {
          this.sform.tablenames = info.data || []
          getLablesByAppId({ appId: appId }).then(info => {
            this.sform.labels = info.data || []
          })
          getNewsqlListByAppId({ 'appId': this.appId, 'tablename': this.sform.tablename, 'label': this.label, 'page': this.currentPage, 'size': this.pageSize, 'sctime': this.getSctime(), 'ectime': this.getEctime() }).then(info => {
            if (info.success && info.data.sqls) {
              this.items = info.data.sqls || []
              this.total = info.data.total
              this.loading = false
            }
          })
        }
      })
    },
    getSctime() {
      var st = new Date(this.sctime)
      console.log(st.getTime())
      return st.getTime() / 1000
    },
    getEctime() {
      var et = new Date(this.ectime)
      return et.getTime() / 1000
    },
    onSubmit() {
      getNewsqlListByAppId({ 'appId': this.appId, 'tablename': this.sform.tablename, 'label': this.sform.label, 'page': this.currentPage, 'size': this.pageSize, 'sctime': this.getSctime(), 'ectime': this.getEctime() }).then(info => {
        if (info.success && info.data.sqls) {
          this.items = info.data.sqls || []
          this.currentPage = 1
          this.total = info.data.total
          this.loading = false
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.currentPage = 1
      getNewsqlListByAppId({ 'appId': this.appId, 'tablename': this.sform.tablename, 'label': this.sform.label, 'page': this.currentPage, 'size': this.pageSize, 'sctime': this.getSctime(), 'ectime': this.getEctime() }).then(info => {
        if (info.success && info.data.sqls) {
          this.items = info.data.sqls || []
          this.total = info.data.total
          this.loading = false
        }
      })
    },
    handleCurrentChange(pageNo) {
      this.currentPage = pageNo
      getNewsqlListByAppId({ 'appId': this.appId, 'tablename': this.sform.tablename, 'label': this.sform.label, 'page': this.currentPage, 'size': this.pageSize, 'sctime': this.getSctime(), 'ectime': this.getEctime() }).then(info => {
        if (info.success && info.data.sqls) {
          this.items = info.data.sqls || []
          this.total = info.data.total
          this.loading = false
        }
      })
    },
    change() {
      this.$api.change(this.sql.id, this.action).then(info => {
        if (info.success && info.data) {
          if (this.action === 'ignored') {
            this.sql.status = 2
          }
          this.$notify({
            message: '操作成功',
            type: 'success'
          })
        } else {
          this.$notify({
            message: '操作失败',
            type: 'error'
          })
        }
      })
      this.optimizing = false
    },
    approval(status) {
      this.updatingApp = false
    },
    showOptimizeDialog(sql, action) {
      this.optimizing = true
      this.sql = sql
      this.action = action
    },
    dateForMatter(row, column, value) {
      return this.$helpers.parseTime(value)
    },
    convertStatusText(status) {
      const map = {
        1: '未处理',
        2: '已忽略',
        3: '已优化',
        4: '待确认',
        5: '误报的',
        6: '跟进中'
      }
      if (map[status]) {
        return map[status]
      } else {
        return status
      }
    },
    convertSqlType(status) {
      const map = {
        1: '参数化',
        2: '非参数化'
      }
      if (map[status]) {
        return map[status]
      } else {
        return status
      }
    },
    info(data) {
      const { href } = this.$router.resolve({
        name: 'SqlDetail',
        query: {
          uid: data.id
        }
      })
      window.open(href, '_blank')
    },
    convertStatusCss(status) {
      if (status === 1) {
        return 'danger'
      } else if (status === 3) {
        return 'success'
      } else if (status === 4) {
        return 'warning'
      } else {
        return 'info'
      }
    },
    convertLevel(status) {
      if (status === '1') {
        return 'success'
      } else if (status === '2') {
        return 'info'
      } else if (status === '3') {
        return 'warning'
      } else if (status === '4') {
        return 'danger'
      } else {
        return ''
      }
    },
    convertLevelType(status) {
      const map = {
        1: '还不错',
        2: '需要关注',
        3: '需要关注',
        4: '严重'
      }
      if (map[status]) {
        return map[status]
      } else {
        return '未知'
      }
    },
    convertSqlTypeCss(status) {
      if (status === 1) {
        return 'success'
      } else if (status === 2) {
        return 'danger'
      }
    }
  }
}
</script>
<style>
.el-pagination {
    text-align: center;
}
</style>
