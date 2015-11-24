'use strict';

angular.module('myApp.companyInfoView', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/companyInfoView', {
              templateUrl: 'app/companyInfoView/companyInfoView.html',
              controller: 'companyInfoViewCtrl',
              controllerAs: 'ctrl'
            });
          }])

        .controller('companyInfoViewCtrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });

        });