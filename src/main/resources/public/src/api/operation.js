import request from '@/utils/request'

export function change(data) {
  return request({
    url: '/newsql/api/change',
    method: 'post',
    data
  })
}

export function handle(data) {
  return request({
    url: '/newsql/api/handle',
    method: 'post',
    data
  })
}

export function getOpRecord(uid) {
  return request({
    url: '/newsql/api/getOperationRecord',
    method: 'get',
    params: { 'uid': uid }
  })
}
