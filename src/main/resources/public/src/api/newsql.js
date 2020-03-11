import request from '@/utils/request'

export function getNewsqlListByAppId(data) {
  return request({
    url: '/newsql/api/getNewsqlListByAppId',
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

export function getTemplateSqlDetail(uid) {
  return request({
    url: '/newsql/api/getTemplateSqlDetail',
    method: 'get',
    params: { 'uid': uid }
  })
}
export function getSqlLevelsByUid(uid) {
  return request({
    url: '/newsql/api/getSqlLevelsByUid',
    method: 'get',
    params: { 'uid': uid }
  })
}
export function getNewSqlByUid(uid) {
  return request({
    url: '/newsql/api/getNewSqlByUid',
    method: 'get',
    params: { 'uid': uid }
  })
}

export function getExplainByUid(uid) {
  return request({
    url: '/newsql/api/getExplainByUid',
    method: 'get',
    params: { 'uid': uid }
  })
}

export function getTrace(id) {
  return request({
    url: '/newsql/api/getTrace',
    method: 'get',
    params: { 'id': id }
  })
}
