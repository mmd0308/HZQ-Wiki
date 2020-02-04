import { form } from '@/api/config/index'

const config = {
  state: {
    title: '',
    keepToRecord: ''
  },

  mutations: {
    SET_TITLE: (state, title) => {
      state.title = title
    },
    SET_RECORD: (state, keepToRecord) => {
      state.keepToRecord = keepToRecord
    }
  },

  actions: {
    // 登出
    HWConfig({ commit, state }) {
      return new Promise((resolve, reject) => {
        form().then(res => {
          commit('SET_TITLE', res.title)
          commit('SET_RECORD', res.keepToRecord)
        })
        resolve()
      })
    }

  }
}

export default config
