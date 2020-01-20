import request from '@/utils/request'

export function showAll() {
  return request({
    url: '/api/wiki/show/tags/all',
    method: 'get'
  })
}
