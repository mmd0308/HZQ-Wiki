import request from '@/utils/request'

export function userDocPage(query, id) {
  return request({
    url: '/api/udoc/page/' + id,
    method: 'get',
    params: query
  })
}

export function userDocAndSpaceAll(id, spaceId) {
  return request({
    url: '/api/udoc/all/' + id + '/' + spaceId,
    method: 'get'
  })
}

export function spaceNonUserAll(spaceId) {
  return request({
    url: '/api/udoc/spaceNonUserAll/' + spaceId,
    method: 'get'
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/udoc/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/udoc/deleted/' + id,
    method: 'delete'
  })
}
