'use strict';
angular.module('App.App')
.config(function($stateProvider, $urlRouterProvider, $sceDelegateProvider) {

    // For any unmatched url, redirect to /home
    $urlRouterProvider.otherwise("/home");

    // Now set up the states
    $stateProvider
        .state('home', {
            url: "/home",
            controller: 'HomeCtrl',
            templateUrl: "app/home/home.html",
        });
});