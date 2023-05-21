import {EventBus} from "@/plugins/event-bus";
import behavior from "@/constants/behavior";

export function openErrorSnackBar(message) {
  EventBus.$emit(behavior.OPEN_SNACK_BAR_ERROR, message)
}

export function openPrimarySnackBar(message) {
  EventBus.$emit(behavior.OPEN_SNACK_BAR_PRIMARY, message)
}

export function openOrCloseProgressCircular() {
  EventBus.$emit(behavior.OPEN_OR_CLOSE_PROGRESS_CIRCULAR)
}
