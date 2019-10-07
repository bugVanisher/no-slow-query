import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/table/list',
    method: 'get',
    params
  })
}

export function getTableList(params) {
  return request({
    url: '/newsql/api/getTables',
    method: 'get',
    params
  })
}
