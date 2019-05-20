import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/space/page',
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
