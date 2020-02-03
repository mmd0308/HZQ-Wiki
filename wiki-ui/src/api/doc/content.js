import request from '@/utils/request'

export function getShowAllByDocId(docId) {
  return request({
    url: '/api/wiki/show/contents/all/' + docId,
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

export function get(id) {
  return request({
    url: '/api/content/get/' + id,
    method: 'get'
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
