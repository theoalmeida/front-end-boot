"use strict";

app.controller('userController', [
    '$scope',
    'vcRecaptchaService',
    'userService',
    '$timeout',
     function ($scope, recaptcha, userService, $timeout) {

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
                        if(message.status >= 200 || message.status <= 300) {
                            showMessage(message);
                            window.location.href = 'index.html';
                            //$timeout(window.location.href = 'index.html', 3000);

                        }else{
                            showMessage(message);
                        }

                      });
                    }

                    if (true) {

                    } else {
                        console.log('Failed validation');
                        vcRecaptchaService.reload($scope.widgetId);
                    }
                };

                function showMessage(response) {
                	$scope.messages=response.message;
                }



}]);

