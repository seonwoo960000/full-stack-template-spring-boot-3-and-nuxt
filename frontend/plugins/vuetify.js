import Vue from 'vue';
import Vuetify from 'vuetify'

Vue.use(Vuetify);

export default ctx => {
  const vuetify = new Vuetify({
    // set default theme
    theme: {
      options: {
        customProperties: true
      },
      themes: {
        // Define custom themes
        // light: {
        //   // used
        //   primary: "#EE4076",
        //   FrontendGreyColor: "#797979",
        //   error: '#FB8B00',
        //   danger: colors.red.accent4
        // },
        // dark: {
        //   primary: colors.blue.darken2,
        //   accent: colors.grey.darken3,
        //   secondary: colors.amber.darken3,
        //   info: colors.teal.lighten1,
        //   warning: colors.amber.base,
        //   error: colors.deepOrange.accent4,
        //   success: colors.green.accent3,
        //   danger: colors.red.accent4,
        // },
      }
    },
    // Define custom icons <v-icon/>
    icons: {
      values: {
        // customIcon: {
        //   component: customIconComponent
        // },
      }
    }
  })
  ctx.app.vuetify = vuetify
  ctx.$vuetify = vuetify.framework
}
