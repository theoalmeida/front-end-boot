"use strict";

app.controller('userController', ['$scope', 'vcRecaptchaService', 'userService', function ($scope, recaptcha, userService) {
                console.log("this is your app's controller");

                $scope.response = null;
                $scope.widgetId = null;
                $scope.model = {
                    key: '6LcmXhATAAAAAHHwTLEMQi5psXPEicjBuu0vDZxo'
                };
                $scope.setResponse = function (response) {
                    $scope.captcha = response;
                };
                $scope.setWidgetId = function (widgetId) {
                    $scope.widgetId = widgetId;
                };
                $scope.cbExpiration = function() {
                    $scope.response = null;
                 };
                $scope.submit = function (form, user) {

                    if(form.$valid) {

                      userService.addUser($scope).then(function(message) {
                         showMessage(message);
                      });
                    }

                    if (true) {
                        console.log('Success');
                    } else {
                        console.log('Failed validation');
                        // In case of a failed validation you need to reload the captcha
                        // because each response can be checked just once
                        vcRecaptchaService.reload($scope.widgetId);
                    }
                };

                function showMessage(response) {
                	$scope.messages=response;
                }



}]);

