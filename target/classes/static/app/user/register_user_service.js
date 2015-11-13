"use strict";

app.service("userService", function($http, $q) {

	var self;
	
	return ({
		addUser : addUser,
		findUser: findUser
	});

	function addUser(scope) {
		self = scope;
		var captcha = grecaptcha.getResponse()
		var request = $http({
			method : "post",
			url : "/user/save",
			data : {
				email : scope.user.email,
				name : scope.user.completename,
				username : scope.user.username,
				password : scope.user.password,
				captchaValue : captcha
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
			resp.message = response.data;
			resp.hasError = true;
			return resp;
		}
		response.data.status = response.status;
		return response.data;

	}
	function handleSuccess(response) {
		response.data.status = response.status;
		return response.data;
	}
});
