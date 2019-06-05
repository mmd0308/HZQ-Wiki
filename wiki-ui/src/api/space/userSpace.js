import request from '@/utils/request'

export function userSpacePage(query, spaceId) {
  return request({
    url: '/api/uspace/page/' + spaceId,
    method: 'get',
    params: query
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/space/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/space/deleted/' + id,
    method: 'delete'
  })
}
