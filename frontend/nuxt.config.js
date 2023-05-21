import English from './i18n/english'
import Korean from './i18n/korean'

function isDevelopment() {
  return process.env.NODE_ENV === 'development'
}

export default {
  publicRuntimeConfig: {},
  privateRuntimeConfig: {
    // not applicable
  },
  env: {
    backendUrl: isDevelopment() ? "http://localhost:8080" : "https://your-backend-url",
  },

  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    titleTemplate: '<Frontend>',
    title: '<Frontend Template>',
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
      {hid: 'description', name: '<Name>', content: '<Content>'},
      {name: 'keywords', content: 'keyword1,keyword2,keyword3'},
      {name: 'format-detection', content: 'telephone=no'},
      {name: 'google-site-verification', content: '<your-verification-number>'},
      {property: 'og:url', content: 'https://your-frontend-url'},
      {property: 'og:type', content: 'website'},
      {property: 'og:title', content: 'Frontend'},
      {property: 'og:image', content: 'https://your-logo-image'},
      {property: 'og:description', content: 'Description'},
    ],
    link: [
      {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'},
      {rel: "apple-touch-icon", href: '/favicon.ico'},
      {rel: "shortcut icon", href: '/favicon.ico'}
    ],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: ['~/assets/main.css'],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '~/plugins/axiosWrap',
    '~/plugins/vuetify',
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
    // https://go.nuxtjs.dev/vuetify
    '@nuxtjs/vuetify',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    // https://go.nuxtjs.dev/pwa
    // '@nuxtjs/pwa',
    // https://i18n.nuxtjs.org/
    '@nuxtjs/i18n',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: isDevelopment() ? {
    // proxy: true,
    proxy: false,
    baseUrl: "http://localhost:20100"
  } : {
    proxy: false,
    baseURL: "https://internal.elFrontendmenu.com",
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    cache: false
  },

  // i18n module options
  i18n: {
    locales: [
      {
        code: 'en',
        name: 'English'
      },
      {
        code: 'ko',
        name: '한국어'
      }
    ],
    vueI18n: {
      defaultLocale: 'en',
      fallbackLocale: 'en',
      messages: {
        en: English,
        ko: Korean
      }
    },
    detectBrowserLanguage: {
      useCookie: true,
      cookieKey: 'i18n_redirected',
      alwaysRedirect: true,
      redirectOn: 'root'  // recommended
    }
  }
}
