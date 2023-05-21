<template>
  <v-app>
    <v-app-bar app
               color="white"
               elevatation="0"
               fixed>
      <v-container>
        <v-toolbar-title>
        </v-toolbar-title>
      </v-container>
    </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt/>
      </v-container>
      <error-snack-bar/>
      <primary-snack-bar/>
    </v-main>
  </v-app>
</template>

<script>
import ErrorSnackBar from "@/components/etc/ErrorSnackBar";
import PrimarySnackBar from "@/components/etc/PrimarySnackBar";

export default {
  name: "EmptyLayout",
  components: {
    ErrorSnackBar,
    PrimarySnackBar,
  },
}
</script>

<style scoped>

</style>
