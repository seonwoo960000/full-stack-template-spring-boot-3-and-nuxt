<template>
  <div>
    <v-menu bottom>
      <template #activator="{ on, attrs }">
        <v-btn
          icon
          fab
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-web</v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item
          v-for="locale in availableLocales"
          :key="locale.code">
          <v-list-item-title style="cursor:pointer;">
            <div @click="changeLocale(locale.code)"
                 class="remove-href-style">
              {{ locale.name }}
            </div>
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>

export default {
  name: "I18nButton",
  data() {
    return {
      langs: {
        'en': 'English',
        'ko': '한국어'
      }
    }
  },
  computed: {
    availableLocales() {
      return this.$i18n.locales.filter(i => i.code !== this.$i18n.locale)
    }
  },
  methods: {
    changeLocale(code) {
      this.$i18n.setLocale(code)
    }
  }
}
</script>

<style scoped>

</style>
