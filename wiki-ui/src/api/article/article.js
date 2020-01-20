import request from '@/utils/request'

export function release(articleRelease) {
  return request({
    url: '/api/wiki/articles/release',
    method: 'post',
    data: articleRelease
  })
}
export function showPage(query) {
  return request({
    url: '/api/wiki/show/articles/page/' + query.pageNum + '/' + query.pageSize,
    method: 'get',
    params: query
  })
}

export function page(query) {
  return request({
    url: '/api/article/page',
    method: 'get',
    params: query
  })
}

export function get(id) {
  return request({
    url: '/api/article/get/' + id,
    method: 'get'
  })
}

export function addOrUpdate(article) {
  return request({
    url: '/api/article/addOrUpdate',
    method: 'post',
    data: article
  })
}

export function deletedById(id) {
  return request({
    url: '/api/article/deleted/' + id,
    method: 'delete'
  })
}
