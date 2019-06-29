import request from '@/utils/request'

export function form() {
  return request({
    url: '/api/config/form',
    method: 'get'
  })
}

export function update(params) {
  return request({
    url: '/api/config/update',
    method: 'post',
    data: params
  })
}
