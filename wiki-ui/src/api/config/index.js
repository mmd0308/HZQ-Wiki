import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/config/page',
    method: 'get',
    params: query
  })
}

export function checkKey(key) {
  return request({
    url: '/api/config/checkKey/' + key,
    method: 'get'
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/config/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/config/deleted/' + id,
    method: 'delete'
  })
}
