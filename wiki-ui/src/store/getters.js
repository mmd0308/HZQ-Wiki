const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  username: state => state.user.username,
  userId: state => state.user.userId,
  roles: state => state.user.roles,

  title: state => state.config.title,
  keepToRecord: state => state.config.keepToRecord
}
export default getters
