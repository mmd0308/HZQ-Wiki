import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/api/login',
    method: 'post',
    params: {
      username,
      password
    }
  })
}

export function getInfo() {
  return request({
    url: '/api/wiki/auth/user',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
