<template>
  <div class="half-width half-width full-center">
    <error-404 v-if="error.statusCode === 404"/>
    <error-401 v-else-if="error.statusCode === 401"/>
    <error-default v-else />
  </div>
</template>

<script>
import Error404 from "@/components/error/error404.vue";
import ErrorDefault from "@/components/error/errorDefault.vue";
import Error401 from "@/components/error/error401.vue";

export default {
  name: 'error',
  components: {
    Error404,
    Error401,
    ErrorDefault
  },
  props: {
    error: {
      type: Object,
      default: null,
    },
  },
}
</script>

<style scoped>

</style>
