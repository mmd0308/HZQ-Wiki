import request from '@/utils/request'

const path = '/api/wiki/'

export function list(moudle, params) {
  return request({
    url: path + moudle,
    method: 'get',
    params: params
  })
}

export function page(moudle, params) {
  return request({
    url: path + moudle + '/page/' + params.pageNum + '/' + params.pageSize,
    method: 'get',
    params: params
  })
}

export function get(moudle, id) {
  return request({
    url: path + moudle + '/' + id,
    method: 'get'
  })
}

export function getAll(moudle) {
  return request({
    url: path + moudle + '/all',
    method: 'get'
  })
}

export function create(moudle, params) {
  return request({
    url: path + moudle,
    method: 'post',
    data: params
  })
}

export function updateById(moudle, params) {
  return request({
    url: path + moudle + '/' + params.id,
    method: 'put',
    data: params
  })
}

export function deleteById(moudle, id) {
  return request({
    url: path + moudle + '/' + id,
    method: 'DELETE'
  })
}
