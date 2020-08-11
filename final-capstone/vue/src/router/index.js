import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Redirect from '@/views/Redirect'
import UpdatePassword from '../views/UpdatePassword.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
    path: "/patient",
    name: "patient",
    component: () => {
      if (store.state.userRole === "ROLE_PATIENT") {
      return import('../views/Patient.vue');
      } else {
        return import('../views/Redirect.vue');
      }
    },
    meta: {
      requiresAuth: true
    }
    },
    {
      path: "/doctor",
      name: "doctor",
      component: () => {
        if (store.state.userRole === "ROLE_DOCTOR") {
          return import('../views/Doctor.vue');
        } else {
          return import('../views/Redirect.vue');
        }
      },
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/admin",
      name: "admin",
      component: () => {
        if (store.state.userRole === "ROLE_ADMIN") {
          return import('../views/Admin.vue');
        } else {
          return import ('../views/Redirect.vue');
        }
      }
    },
    {
      path: "/redirect",
      name: "redirect",
      component: Redirect
    },
    {
      path: "/update-password",
      name: "update-password",
      component: UpdatePassword
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
