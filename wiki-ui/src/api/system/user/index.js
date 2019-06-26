import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/user/page',
    method: 'get',
    params: query
  })
}
export function get(id) {
  return request({
    url: '/api/user/get/' + id,
    method: 'get'
  })
}
export function checkUsername(username) {
  return request({
    url: '/api/user/checkUsername',
    method: 'get',
    params: { username }
  })
}
export function checkPass(params) {
  return request({
    url: '/api/user/checkPass',
    method: 'post',
    data: params
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
