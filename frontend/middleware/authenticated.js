import constant from "@/constants/common";

export default async function ({store, redirect}) {
  if (!store.state.auth.authenticated) {
    try {
      // Define your authentication logic
      // ex) await store.dispatch(`auth/authenticate`, localStorage.getItem(constant.token))
    } catch (e) {
      // Define how application should act when authentication fails
      // ex) await redirect('/auth/sign-in')
    }
  }
}
