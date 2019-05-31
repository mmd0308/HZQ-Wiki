import request from '@/utils/request'

export function all(docId) {
  return request({
    url: '/api/content/all/' + docId,
    method: 'get'
  })
}

export function catalogPage(query) {
  return request({
    url: '/api/content/page',
    method: 'get',
    params: query
  })
}

export function addOrUpdate(content) {
  return request({
    url: '/api/content/addOrUpdate',
    method: 'post',
    data: content
  })
}

export function deletedById(id) {
  return request({
    url: '/api/content/deleted/' + id,
    method: 'delete'
  })
}
