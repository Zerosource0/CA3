'use strict';

angular.module('myApp.view5', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view5', {
    templateUrl: 'app/view5/view5.html',
    controller: 'AppNewUserCtrl'
  });
}])

.controller('AppNewUserCtrl', ['$http','$scope', function($http, $scope)
{
	$http.post('api/newuser', $scope.newUser)
	.success(function(data)
	{
		console.log(data);
	})
	.error(function(data, status)
	{
		console.log("ERROR: "+data, status);
	});
}]);