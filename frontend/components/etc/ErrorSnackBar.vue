<template>
  <div class="text-center">
    <v-snackbar
      v-model="snackbar"
      timeout="2000"
    >
      {{ $t(text) }}
      <template #action="{ attrs }">
        <v-btn
          color="red"
          text
          v-bind="attrs"
          @click="closeSnackBar"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import behavior from "@/constants/behavior";
import { EventBus } from "@/plugins/event-bus"

export default {
  name: "ErrorSnackBar",
  data: () => ({
    snackbar: false,
    text: '',
  }),
  created() {
    EventBus.$on(behavior.OPEN_SNACK_BAR_ERROR, text => {
      this.snackbar = true
      this.text = text
    })
  },
  methods: {
    closeSnackBar() {
      this.snackbar = false
    }
  },
}
</script>

<style scoped>
h1 {
  font-size: 20px;
}
</style>
