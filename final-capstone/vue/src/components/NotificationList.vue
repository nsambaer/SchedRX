<template>
  <div>
    Notification List
    <ul>
      <li v-for="notification in notifications" v-bind:key="notification.notificationId">
        <div v-bind:class="{'read' : !notification.read}">{{notification.message}}</div>
        <div>
          <button v-on:click="markRead(notification.notificationId)">Mark Read</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import notifService from "@/services/NotificationService";

export default {
  data() {
    return {
      notifications: [],
    };
  },

  created() {
    notifService
      .getRecentNotifications(this.$store.state.user.id)
      .then((response) => {
        this.notifications = response.data;
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },

  methods: {
    markRead(notificationId) {
      console.log(notificationId);
      notifService
        .markNotificationRead(notificationId)
        .then(() => {
          notifService
            .getRecentNotifications(this.$store.state.user.id)
            .then((response) => {
              this.notifications = response.data;
            })
            .catch((error) => {
              const response = error.response;
              this.errors = true;
              if (response.status === 400) {
                this.errorMsg = "Bad Request: Validation Errors";
              }
            });
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },
  },
};
</script>

<style>
.read {
  font-weight: bold;
}
</style>