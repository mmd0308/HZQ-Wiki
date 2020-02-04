import request from '@/utils/request'

export function form() {
  return request({
    url: '/api/wiki/configs/form',
    method: 'get'
  })
}

export function update(params) {
  return request({
    url: '/api/wiki/configs',
    method: 'put',
    data: params
  })
}
