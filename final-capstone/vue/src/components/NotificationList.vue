<template>
  <div class = "standard-component-container">
    <h3 class="standard-component-header">Notifications</h3>
    <label for="notification-view">View:</label>
    <select id="notification-view" v-model="notifView">
      <option value="unread">View Unread Notifications</option>
      <option value="all">View All Notifications</option>
    </select>
    <br />
    <button class="standard-button" v-on:click="markAllRead">Mark All Read</button>
    <ul>
      <div v-for="notification in filteredNotifications" v-bind:key="notification.notificationId" class="standard-display-slot" >
        <div v-bind:class="{'read' : !notification.read}">{{notification.message}}</div>
        <div>
          <button class="standard-button" v-show="!notification.read" v-on:click="markRead(notification.notificationId)">Mark Read</button>
        </div>
      </div>
      <p v-show="filteredNotifications.length === 0">No Notifications</p>
    </ul>
  </div>
</template>

<script>
import notifService from "@/services/NotificationService";

export default {
  data() {
    return {
      notifications: [],
      notifView: "unread",
    };
  },

  computed: {
    filteredNotifications() {
      if (this.notifView == "unread") {
        return this.notifications.filter((notification) => {
          return notification.read === false;
        });
      } else {
        return this.notifications;
      }
    },
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

    markAllRead() {
      notifService
        .markAllRead(this.$store.state.user.id)
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

    notificationEmpty() {
      return this.filteredNotifications === [];
    }

  },
};
</script>

<style>
.read {
  font-weight: bold;
}

ul{
  padding-inline-start: 0px;
}
</style>