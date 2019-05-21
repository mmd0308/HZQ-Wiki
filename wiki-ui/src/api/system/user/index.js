import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/user/page',
    method: 'get',
    params: query
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/user/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function registerUser(params) {
  return request({
    url: '/api/user/register',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/user/deleted/' + id,
    method: 'delete'
  })
}
