'use strict';

angular.module('myApp.exchangeRateApp', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {

  $routeProvider.when('/exchangeRateInfo', {
    templateUrl: 'app/exchangeRateView/exchangeRateView.html'
  });
}]);