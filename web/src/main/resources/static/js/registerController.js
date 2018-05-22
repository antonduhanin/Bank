var myapp = angular.module('myapp', []);
myapp.controller('registerController', function ($scope, $http) {

    $scope.registration = function () {
        if ( validateEmail($scope.myemail) && validateName($scope.name) && $scope.myemail != undefined && $scope.pass != undefined && $scope.name != undefined && $scope.nam != "" && $scope.emai != '' && $scope.pass != '') {
            if ($scope.myemail == $scope.emailRepeat && $scope.pass == $scope.passRepeat) {
                var data = {
                    'name': $scope.name,
                    'email': $scope.myemail,
                    'password': $scope.pass
                };


                $http.post('/new', data)
                    .success(function (response) {
                        $scope.notShow = true;
                        $scope.messageError = 'registration success'
                    }).error(function (response) {
                    $scope.messageError = 'not register';

                })
            } else {
                $scope.messageError = 'not same';
            }
        } else {
            $scope.messageError = 'enter the data';

        }
    }
});
