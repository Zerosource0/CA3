'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'AppNewUserCtrl'
                });
            }])

        .controller('AppNewUserCtrl', ['$http', '$scope', function ($http, $scope)
            {
                $scope.name = "adam";

                $scope.submitNewUser = function()
                {
                    $http.post('api/newuser', {username: $scope.newUser.username, 
                        password: $scope.newUser.password, role: $scope.newUser.role})
                            .success(function (data)
                            {
                                console.log(data);
                                $scope.success = "Very Nice! You made a new User Account!";
                            })
                            .error(function (data, status)
                            {
                                console.log("ERROR: " + data, status);
                                $scope.success = "You did something wrong, error: " + status;
                            });
                }
                ;
            }]);