import request from '@/utils/request'

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
