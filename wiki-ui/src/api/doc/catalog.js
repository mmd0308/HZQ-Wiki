import request from '@/utils/request'

export function tree(docId) {
  return request({
    url: '/api/catalog/tree/' + docId,
    method: 'get'
  })
}

export function catalogPage(query) {
  return request({
    url: '/api/catalog/page',
    method: 'get',
    params: query
  })
}

export function addOrUpdate(catalog) {
  return request({
    url: '/api/catalog/addOrUpdate',
    method: 'post',
    data: catalog
  })
}

export function deletedById(id) {
  return request({
    url: '/api/catalog/deleted/' + id,
    method: 'delete'
  })
}
