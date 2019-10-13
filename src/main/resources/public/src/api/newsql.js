import request from '@/utils/request'

export function getNewsqlByAppId(data) {
  return request({
    url: '/newsql/api/getNewsqlByAppId',
    method: 'post',
    data
  })
}

export function getTablesByAppId(params) {
  return request({
    url: '/newsql/api/getTablesByAppId',
    method: 'get',
    params
  })
}

export function getLablesByAppId(params) {
  return request({
    url: '/newsql/api/getLabelsByAppId',
    method: 'get',
    params
  })
}
