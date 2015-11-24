'use strict';

angular.module('myApp.exchangeRateInfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/exchangeRateInfo', {
    templateUrl: 'app/exchangeRateInfo/ExchangeRateView.html'
  });
}]);