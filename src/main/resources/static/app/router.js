"use strict";

    app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {

        $routeProvider
            .when('/homepage', {
                templateUrl : 'user',
                controller  : 'homeController'
            })
            .when('/users', {
                templateUrl : 'contacts',
                controller  : 'userController'
            });
    }]);