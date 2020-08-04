<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <div class="container">
        <form>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <h1>Register</h1>
                    <hr>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input
                                type="text"
                                id="address"
                                class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input
                                type="password"
                                id="password"
                                class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="first name">First Name</label>
                        <input
                                type="text"
                                id="first name"
                                class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="Last Name">Last Name</label>
                        <input
                                type="text"
                                id="last name"
                                class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input
                                type="text"
                                id="Address"
                                class="form-control">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <div class="form-group">
                        <label for="Office 1">
                            <input
                                    type="checkbox"
                                    id="Office 1"
                                    value="Office Name"> Office 1
                        </label>
                        <label for="Office 2">
                            <input
                                    type="checkbox"
                                    id="Office 2"
                                    value="Office Name"> Office 2
                        </label>
                         <label for="Office 3">
                            <input
                                    type="checkbox"
                                    id="Office 3"
                                    value="Office Name"> Office 3
                        </label>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 form-group">
                    <label for="doctor">
                        <input
                                type="radio"
                                id="patient"
                                value="patient"> Patient
                    </label>
                    <label for="doctor">
                        <input
                                type="radio"
                                id="doctor"
                                value="doctor"> Doctor
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 from-group">
                    <label for="authority">Authority</label>
                    <select
                            id="authority"
                            class="form-control">
                        <option></option>
                    </select>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <button
                            class="btn btn-primary">Submit!
                    </button>
                </div>
            </div>
        </form>
        <hr>
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Your Data</h4>
                    </div>
                    <div class="panel-body">
                        <p>First Name:</p>
                        <p>Last Name:</p>
                        <p>Address:</p>
                        <p>Office Location:</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <router-view />
  </div>
</template>
