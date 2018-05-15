var myapp = angular.module('myapp', []);
myapp.controller('registerController', function ($scope, $http) {

  /*  $scope.notShow = false;
    $scope.registration = function () {
        if ($scope.email != undefined && $scope.pass != undefined && $scope.name != undefined && $scope.name != "" && $scope.email != '' && $scope.pass != '') {
            if ($scope.email == $scope.emailRepeat && $scope.pass == $scope.passRepeat) {
                var data = {
                    'name': $scope.name,
                    'email': $scope.email,
                    'password': $scope.pass
                };
                $http.post('/users', data)
                    .success(function (response) {
                        $scope.notShow = true;
                        $scope.messageError = 'registration success'
                    }).error(function (response) {
                    $scope.messageError = 'not register';
                })
            }
        }
    }*/
});