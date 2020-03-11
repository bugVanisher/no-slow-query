<template>
  <div class="app-container">
    <div class="panel">
      <el-row>
        <div class="panel-title clearfix">
          SQL详情
          <el-button
            type="info"
            size="small"
            class="pull-right"
            style="margin-left: 10px;"
            @click="getOperationRecord()"
          >
            <i class="icon-slim-pen" /> 操作记录
          </el-button>
          <el-button
            v-if="showReportBtn"
            type="warning"
            size="small"
            class="pull-right"
            @click="showReportDialog"
          >
            <i class="icon-slim-plus" /> 报告问题
          </el-button>
          <el-button
            type="primary"
            size="small"
            class="pull-right"
            style="margin-right: 2px;"
            @click="showReRunDialog"
          >
            <i class="icon-slim-refresh-2" /> 重跑查询
          </el-button>
          <el-button
            type="primary"
            size="small"
            @click="showCTableDialog"
          >
            <i class="icon-slim-hammer" /> 查表结构
          </el-button>

        </div>
        <div
          class="module"
          style="padding: 0;"
        >
          <el-table
            :data="templateSqls"
            style="width: 100%"
          >
            <el-table-column
              prop="appName"
              label="应用名"
            />
            <el-table-column
              prop="tablename"
              label="表名"
              width="200"
            />
            <el-table-column
              prop="label"
              label="标签"
              width="100"
            />
            <el-table-column
              prop="templateSql"
              label="sql语句"
              width="450"
            />
            <el-table-column
              prop="sqlType"
              label="sql语句类型"
            >
              <template slot-scope="scope">
                <el-tag :type="convertSqlTypeCss(scope.row.sqlType)">{{ convertSqlType(scope.row.sqlType) }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              :formatter="dateForMatter"
              prop="ctime"
              label="生成时间"
              width="180"
            />
            <el-table-column
              prop="handleStatus"
              label="状态"
            >
              <template slot-scope="scope">
                <el-tag :type="convertStatusCss(scope.row.handleStatus)">{{ convertStatusText(scope.row.handleStatus) }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              class-name="uae-action-column"
            >
              <template slot-scope="scope">
                <el-button
                  v-if="scope.row.handleStatus == 4"
                  size="small"
                  type="info"
                  @click="showConfirmDialog(scope.row, 'mistaken')"
                >
                  误报
                </el-button>
                <el-button
                  v-if="scope.row.handleStatus == 4 || scope.row.handleStatus == 1 "
                  size="small"
                  type="danger"
                  @click="showConfirmDialog(scope.row, 'following')"
                >
                  跟进
                </el-button>
                <el-button
                  v-if="scope.row.handleStatus == 1 || scope.row.handleStatus == 4"
                  size="small"
                  type="warning"
                  @click="showConfirmDialog(scope.row, 'ignored')"
                >
                  可忽略
                </el-button>
                <el-button
                  v-if="scope.row.handleStatus == 6"
                  size="small"
                  type="primary"
                  @click="showHandleDialog(scope.row)"
                >
                  已处理
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-row>
      <el-row>
        <div
          class="panel"
          style="padding: 0;"
        >
          <el-tag
            v-for="tag in tags"
            :key="tag.name"
            :type="tag.type"
            closable
            style="margin-right: 10px"
          >
            {{ tag.name }}
          </el-tag>
        </div>
      </el-row>
      <el-row>
        <div
          class="panel"
          style="padding: 0;"
        >
          <el-table
            :data="newsqls"
            :loading="loading"
            element-loading-text="慢慢等着吧.."
          >
            <!-- <el-table-column prop="appName" label="应用名"></el-table-column> -->
            <el-table-column
              prop="tablename"
              label="表名"
              width="200"
            />
            <el-table-column
              prop="dbName"
              label="执行数据库"
              width="150"
            />
            <el-table-column
              prop="newSql"
              label="sql语句"
              width="500"
            />
            <el-table-column
              prop="num"
              label="历史查询次数"
            />
            <el-table-column
              label="操作"
              class-name="uae-action-column"
            >
              <template slot-scope="scope">
                <el-button
                  size="small"
                  type="primary"
                  @click="showTraceDialog(scope.row)"
                >
                  查看堆栈
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-row>
      <el-row>
        <div
          class="module"
          style="padding: 0;"
        >
          <el-table
            :data="explains"
            :loading="loading"
            element-loading-text="慢慢等着吧.."
          >
            <el-table-column
              prop="selectType"
              label="selectType"
              width="120"
            />
            <el-table-column
              prop="tablename"
              label="tablename"
              width="200"
            />
            <!-- <el-table-column prop="partitions" label="partitions" width="110"></el-table-column> -->
            <el-table-column
              prop="type"
              label="type"
            />
            <el-table-column
              prop="possibleKeys"
              label="possibleKeys"
              width="130"
            />
            <el-table-column
              prop="key"
              label="key"
            />
            <el-table-column
              prop="keyLen"
              label="keyLen"
            />
            <el-table-column
              prop="ref"
              label="ref"
            />
            <el-table-column
              prop="rows"
              label="rows"
            />
            <el-table-column
              prop="filtered"
              label="filtered"
            />
            <el-table-column
              prop="extra"
              label="extra"
            />
            <el-table-column
              prop="newSql"
              label="sql"
              width="500"
            />
          </el-table>
        </div>
      </el-row>

      <el-dialog
        :visible.sync="confirming"
        title="提示"
        width="30%"
      >
        <span v-if="action=='ignored'">确定要忽略吗?</span>
        <span v-if="action=='mistaken'">确定是误报吗?</span>
        <span v-if="action=='following'">确定要跟进吗?</span>
        <span
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="confirming = false">取 消</el-button>
          <el-button
            type="primary"
            @click="change()"
          >确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog
        v-if="sql"
        :visible.sync="traceShow"
        title="堆栈信息"
        width="100%"
      >
        <el-form
          ref="form"
          v-model="form"
          label-width="50px"
        >
          <el-form-item label="SQL: ">
            <span>{{ sql.newSql }}</span>
          </el-form-item>
          <el-form-item label-width="30px">
            <ace-editor
              v-model="trace"
              lang="sh"
              height="500"
            />
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-dialog
        :visible.sync="handling"
        title="处理对话框"
        width="30%"
      >
        <el-form
          ref="handleForm"
          :model="handleForm"
          :rules="rules"
          label-width="60px"
        >
          <el-form-item
            v-if="sql"
            label="SQL: "
          >
            <span>{{ sql.templateSql }}</span>
          </el-form-item>
          <el-form-item
            label="问题描述"
            prop="desc"
          >
            <el-input
              v-model="handleForm.desc"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="解决方式"
            prop="solution"
          >
            <el-input
              v-model="handleForm.solution"
              type="textarea"
            />
          </el-form-item>
        </el-form>
        <div
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="handling = false">取 消</el-button>
          <el-button
            type="primary"
            @click="handle('handleForm')"
          >确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog
        :visible.sync="addReportDialog"
        title="您正在报告Bug哦！"
      >
        <span>确定要报Bug吗?</span>
        <span
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="addReportDialog = false">取 消</el-button>
          <el-button
            type="primary"
            @click="reportBug()"
          >确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog
        :visible.sync="reRunDialog"
        title="重跑查询计划"
      >
        <div>
          <span>功能正在实现中哦.</span>
        </div>
      </el-dialog>

      <el-dialog
        v-model="cTableDialog"
        title="表结构"
      >
        <div>
          <el-form label-width="50px">
            <el-form-item label="SQL: ">
              <span>{{ createTable.templateSql }}</span>
            </el-form-item>
            <el-form-item label-width="30px">
              <br>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>

      <el-dialog
        :visible.sync="dialogTableVisible"
        title="操作记录"
        width="100%"
      >
        <el-table :data="operationRecord">
          <el-table-column
            prop="handleStatus"
            label="操作行为"
            width="120px"
          >
            <template slot-scope="scope">
              {{ convertOperationStatusText(scope.row.handleStatus) }}
            </template>
          </el-table-column>
          <el-table-column
            prop="comment"
            label="备注"
            width="300px"
          >
            <template slot-scope="scope">
              <p v-html="scope.row.comment" />
              <p v-if="scope.row.comment == ''">无</p>
            </template>
          </el-table-column>
          <el-table-column
            prop="operatorName"
            label="操作人"
            width="120px"
          />

          <el-table-column
            :formatter="dateForMatter"
            prop="ctime"
            label="操作时间"
            width="180px"
          > >
          </el-table-column>
        </el-table>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getTemplateSqlDetail, getSqlLevelsByUid, getNewSqlByUid, getExplainByUid, getTrace } from '@/api/newsql'
import { change, handle, getOpRecord } from '@/api/operation'
export default {
  components: {
    'ace-editor': require('@/components/AceEditor').default
  },
  data() {
    return {
      templateSqls: [],
      newsqls: [],
      explains: [],
      loading: false,
      pagination: {
        currentPage: 1,
        pageSize: 50
      },
      optimizing: false,
      confirming: false,
      handling: false,
      appName: 'uae2.0',
      tabName: 'info',
      sql: null,
      dialogTableVisible: false,
      form: {
        tag: '',
        projectId: this.$route.params.id,
        envs: []
      },
      sform: {
        tablenames: [],
        tablename: ''
      },
      templateSqlId: this.$route.query.uid,
      trace: '',
      traceShow: false,
      action: 'ignored',
      addReportDialog: false,
      reRunDialog: false,
      cTableDialog: false,
      tags: [],
      handleForm: {
        desc: '',
        solution: ''
      },
      operationRecord: [],
      showReportBtn: true,
      rules: {
        desc: [
          { required: true, message: '请输入问题描述', trigger: 'blur' }
        ],
        solution: [
          { required: true, message: '请输入解决方式', trigger: 'blur' }
        ]
      },
      createTable: {
        createTableContent: 'loading...',
        templateSql: 'loading...'
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.loading = true
      getTemplateSqlDetail(this.templateSqlId).then(info => {
        if (info.success) {
          const tsqlList = []
          tsqlList.push(info.data)
          this.templateSqls = tsqlList || []
          this.loading = false
        }
      })
      getSqlLevelsByUid(this.templateSqlId).then(info => {
        if (info.success) {
          this.tags = info.data || []
        }
      })
      getNewSqlByUid(this.templateSqlId).then(info => {
        if (info.success) {
          this.newsqls = info.data || []
        }
      })
      getExplainByUid(this.templateSqlId).then(info => {
        if (info.success) {
          this.explains = info.data || []
        }
      })
    },
    onSubmit() {
      this.$api.getSearchData(this.sform.tablename, this.pagination.currentPage, this.pagination.pageSize).then(info => {
        if (info.success) {
          this.items = info.data || []
          this.loading = false
        }
      })
    },
    getOperationRecord() {
      getOpRecord(this.templateSqlId).then(info => {
        if (info.success) {
          this.operationRecord = info.data || []
          this.loading = false
          this.dialogTableVisible = true
        }
      })
    },
    change() {
      change({ 'uid': this.sql.id, 'action': this.action }).then(info => {
        if (info.success && info.data) {
          if (this.action === 'mistaken') {
            this.sql.handleStatus = 5
          }
          if (this.action === 'following') {
            this.sql.handleStatus = 6
          }
          if (this.action === 'ignored') {
            this.sql.handleStatus = 2
          }
          this.$notify({
            message: '操作成功',
            type: 'success'
          })
        } else {
          this.$notify.error({
            message: '失败失败'
          })
        }
      })
      this.confirming = false
    },
    handle(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          handle({ 'uid': this.sql.id, 'problemDesc': this.handleForm.desc, 'solution': this.handleForm.solution }).then(info => {
            if (info.success && info.data) {
              this.sql.handleStatus = 3
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
          this.handling = false
        }
      })
    },
    showOperationRecord(data) {
      const { href } = this.$router.resolve({
        name: 'operationRecord',
        params: {
          id: this.$route.params.id
        },
        query: {
          uid: this.$route.query.uid
        }
      })
      window.open(href, '_blank')
    },
    showHandleDialog(sql) {
      this.handling = true
      this.sql = sql
    },
    showConfirmDialog(sql, action) {
      this.confirming = true
      this.sql = sql
      this.action = action
    },
    showTraceDialog(sql) {
      this.traceShow = true
      getTrace(sql.id).then(info => {
        if (info.success) {
          this.trace = info.data.trace || ''
          this.sql = sql
        }
      })
    },
    reportBug() {
      this.addReportDialog = false
      this.$api.reportBug(this.templateSqlId).then(info => {
        if (info.success && info.data) {
          this.$notify({
            message: '操作成功',
            type: 'success'
          })
          this.showReportBtn = false
        } else {
          this.$notify({
            message: '操作失败',
            type: 'error'
          })
        }
      })
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
    convertOperationStatusText(status) {
      const map = {
        2: '点击忽略',
        3: '点击处理',
        5: '点击误报',
        6: '点击跟进',
        99: '报bug'
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
    convertSqlTypeCss(status) {
      if (status === 1) {
        return 'success'
      } else if (status === 2) {
        return 'danger'
      }
    },
    showReportDialog() {
      this.addReportDialog = true
    },
    showReRunDialog() {
      this.reRunDialog = true
    },
    showCTableDialog() {
      this.cTableDialog = true
      this.$api.getCreateTable(this.templateSqlId).then(info => {
        if (info.success) {
          this.createTable = info.data || ''
        }
      })
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
