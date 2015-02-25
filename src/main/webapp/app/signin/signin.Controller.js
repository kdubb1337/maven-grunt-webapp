'use strict';
angular.module("App.Signin")
.controller("SigninCtrl", function($scope, $http) {

	$scope.login = function() {
		$http({
            url: '/signin',
            method: 'POST',
            params: {
            	username: $scope.username,
            	password: $scope.password
            }
        }).then(function(data) {
        	window.location = "/";
        });
	};
});