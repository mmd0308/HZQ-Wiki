import store from '../store'
import { getToken } from '@/utils/auth'

/**
 * 判断系统是否登陆
 * true 登陆
 * false 没有登陆
 */
export function isLogin() {
  if (
    (getToken() != null && getToken !== '') &&
    (store.getters.userId != null && store.getters.userId !== '')
  ) {
    return true
  }
  return false
}

/**
 * 是否是admin 创建管理员
 * true 是
 * false 不是
 */
export function isAdmin() {
  if (
    isLogin() &&
    (store.getters.username === 'admin')
  ) {
    return true
  }
  return false
}

/**
 * token是否存在
 * true 存在
 * false 不存在
 */
export function isToken() {
  if (
    (getToken() != null && getToken !== '') &&
    (store.getters.token != null && store.getters.token !== '')
  ) {
    return true
  }
  return false
}
