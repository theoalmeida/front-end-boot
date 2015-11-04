"use strict";

cupcake.controller("userController", function($scope, userService) {

	$scope.addUser = function() {
		
		$scope.submit = function(form, user) {
			  if(form.$valid) {
				  userService.addUser($scope).then(function(message) {
						loadRemoteData(message);
					});
			  }
		};
		
		
	};

	$scope.findUser = function() {
		userService.findUser().then(function(message) {
			loadRemoteData(message);
		});
	};
	
	function loadRemoteData(response) {
		$scope.hasError=response.hasError;
		$scope.message=response.message;
	}

});
