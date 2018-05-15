var myapp = angular.module('myapp', []);
myapp.controller('newsController', function ($scope, $http) {
    $http.get('http://localhost:8012/news/newsForAll')
        .success(function (response) {
            $scope.listNews = response;
        })
});