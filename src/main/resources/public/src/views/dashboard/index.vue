<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name:{{ name }}</div>
    <div class="dashboard-text">roles:<span v-for="role in roles" :key="role">{{ role }}</span></div>
    <div class="dashboard-text">tables:<span v-for="tableName in tableNames" :key="tableName">{{ tableName }}</span></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getTableList } from '@/api/table'

export default {
  name: 'Dashboard',
  data() {
    return {
      tableNames: []
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  created: function() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getTableList({ appName: 'agent-test' }).then(response => {
        this.tableNames = response.data.data
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
