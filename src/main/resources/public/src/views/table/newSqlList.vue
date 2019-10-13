<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="AppName" width="200">
        <template slot-scope="scope">
          {{ scope.row.appName }}
        </template>
      </el-table-column>
      <el-table-column label="Group" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.groupId }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status | mapFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="CreatedTtime" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ formatTime(scope.row.ctime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="link">
        <template slot-scope="scope">
          <el-button @click="watchDetail(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getApps } from '@/api/user'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        2: 'danger'
      }
      return statusMap[status]
    },
    mapFilter(status) {
      const descMap = {
        1: '生效',
        2: '失效'
      }
      return descMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getApps().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    formatTime(timestamp) {
      return this.$helpers.parseTime(timestamp, '')
    },
    watchDetail(row) {
      const { href } = this.$router.resolve({ path: '/example/newSqlList', query: { appName: row.appName }})
      window.open(href, '_blank')
    }
  }
}
</script>
