'use strict';

angular.module('myApp.companyInfoView', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/companyInfoView', {
              templateUrl: 'app/companyInfoView/companyInfoView.html',
              controller: 'companyInfoViewCtrl',
              controllerAs: 'ctrl'
            });
          }])

        .controller('companyInfoViewCtrl',['$http', '$scope', function ($http, $scope) {
            
            $scope.searchInput;
            
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });
          
          $scope.submitSearch = function(){
          $http.get('//cvrapi.dk/api?search='+ $scope.searchInput.search + 
                  '&country='+ $scope.searchInput.country)
                  .success(function(data){
                      $scope.result = data;
                  })
                  .error(function(data, status){
                      console.log("Error: data");
                      console.log(data);
                      console.log("status");
                      console.log(status);
                  });
          };
        }]);