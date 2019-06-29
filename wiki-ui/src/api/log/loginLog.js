import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/login/log/page',
    method: 'get',
    params: query
  })
}
