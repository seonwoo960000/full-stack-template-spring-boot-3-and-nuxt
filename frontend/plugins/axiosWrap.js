import constant from "~/constants/common";

export default function ({$axios, redirect}, inject) {
  const api = $axios.create({
    withCredentials: true,
    headers: {
      common: {
        Authorization: getAuthorizationHeader()
      }
    },
  })

  // Console log axios information when in development environment
  if (process.env.NODE_ENV === 'development') {
    api.interceptors.request.use(config => {
      console.table({
        url: config.url,
        method: config.method,
        baseUrl: process.env.backendUrl
      })

      return config
    }, error => Promise.reject(error))
  }

  // When backend responses with constant.token header, store it in localStorage
  api.interceptors.response.use(response => {
    const token = response.headers[constant.token]
    if (token) {
      localStorage.setItem(constant.token, token)
      $axios.setToken(token, 'Bearer')
    }
    return response
  }, error => Promise.reject(error))

  inject('api', api)
}

function getAuthorizationHeader() {
  const token = localStorage.getItem(constant.token)
  return token ? `Bearer ${token}` : null
}
