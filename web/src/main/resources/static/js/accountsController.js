var myapp = angular.module('myapp', []);
myapp.controller('newsController', function ($scope, $http) {
    $http.get('http://localhost:8012/accounts/2')
        .success(function (response) {
            $scope.accounts = response;
        })
});