import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/doc/page',
    method: 'get',
    params: query
  })
}

export function showDocPage(query, userId) {
  return request({
    url: '/api/doc/page/' + userId,
    method: 'get',
    params: query
  })
}

export function addOrUpdate(doc) {
  return request({
    url: '/api/doc/addOrUpdate',
    method: 'post',
    data: doc
  })
}
