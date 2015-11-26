'use strict';

angular.module('myApp.allUsers', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/allUsers', {
    templateUrl: 'app/allUsers/AllUsersView.html',
    controller: 'allUsersCtrl'
  });
}])

.controller('allUsersCtrl', function($http,$scope) {
  $http.get('api/demoadmin')
            .success(function (data, status, headers, config) {
              $scope.data = data;
            })
            .error(function (data, status, headers, config) {
              
             });
 
});