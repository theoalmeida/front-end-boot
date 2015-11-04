"use strict";

cupcake.service("userService", function($http, $q) {

	var self;
	
	return ({
		addUser : addUser,
		findUser: findUser
	});

	function addUser(scope) {
		self = scope;
		var request = $http({
			method : "post",
			url : "/user/save",
			data : {
				email : scope.user.email,
				firstName : scope.user.firstName,
				lastName : scope.user.lastName,
				password : scope.user.password,
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function findUser() {
		var request = $http({
			method : "get",
			url : "/user/find"
		});
		return (request.then(handleSuccess, handleError));
	}

	function handleError(response) {
		var resp = {};
		if (typeof response.data.error !== 'undefined' && response.data.error !== null) {
			resp.message = response.data.message;
			resp.hasError = true;
			return resp;
		}
		return response.data;

	}
	function handleSuccess(response) {
		return response.data;
	}
});
