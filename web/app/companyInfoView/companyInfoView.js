'use strict';

angular.module('myApp.companyInfoView', ['ngRoute', 'myApp.security'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/companyInfoView', {
              templateUrl: 'app/companyInfoView/companyInfoView.html',
              controller: 'companyInfoViewCtrl',
              controllerAs: 'ctrl'
            });
          }])

        .controller('companyInfoViewCtrl',['$http', '$scope', function ($http, $scope) {
            
            $scope.searchInput;
            $scope.searchClicked = false;
            
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });
          
          $scope.submitSearch = function(){
             //DisableAuthInterceptor.enableLoader = false;
          $http.get('//cvrapi.dk/api?search='+ $scope.searchInput.search + 
                  '&country='+ $scope.searchInput.country)
                  .success(function(data){
                      $scope.result = data;
                      $scope.companyList = data.productionunits;
                      $scope.searchClicked = true;
                     // DisableAuthInterceptor.enableLoader = true;
                  })
                  .error(function(data, status){
                      console.log("Error: data");
                      console.log(data);
                      console.log("status");
                      console.log(status);
                     // DisableAuthInterceptor.enableLoader = true;
                  });
  
                  
          };
        }]);