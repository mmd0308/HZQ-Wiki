import request from '@/utils/request'

export function getListAll(articleId) {
  return request({
    url: '/api/wiki/articles/tags/all/' + articleId,
    method: 'get'
  })
}
