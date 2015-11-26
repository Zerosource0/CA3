'use strict';

angular.module('myApp.exchangeRateApp', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {

  $routeProvider.when('/exchangeRateInfo', {
    templateUrl: 'app/exchangeRateView/exchangeRateView.html',
    controller: 'exchangeRateViewCtrl',
    controllerAs: 'ctrl'
  });
}]).controller('exchangeRateViewCtrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });

          $http.get('api/dailyrates')
                    .success(function (data, status, headers, config) {
                        $scope.rates = data;
                    })
                    .error(function (data, status, headers, config) {
                        console.log(status + " " + headers + " " + data);
                    });

        });