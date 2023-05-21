<template>
  <div class="text-center">
    <v-snackbar
      v-model="snackbar"
      timeout="2000"
    >
      {{ $t(text) }}
      <template #action="{ attrs }">
        <v-btn
          color="blue"
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
import {EventBus} from "@/plugins/event-bus";
import behavior from "@/constants/behavior";

export default {
  name: "PrimarySnackBar",
  data: () => ({
    snackbar: false,
    text: '',
  }),
  created() {
    EventBus.$on(behavior.OPEN_SNACK_BAR_PRIMARY, text => {
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

</style>
