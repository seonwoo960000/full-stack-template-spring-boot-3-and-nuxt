export default {
  created() {
    setI18n()
  },
}

function setI18n() {
  let locale = localStorage.getItem('frontend-i18n-locale')
  if (!locale) locale = 'en'
  this.$i18n.locale = locale
}
