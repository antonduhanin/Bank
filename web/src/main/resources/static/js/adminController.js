var myapp = angular.module('myapp', []);
myapp.controller('adminController', function ($scope, $http) {
    $scope.headersUsers = ['id', 'name', 'email', 'role']
    $scope.isActiveNews = false;
    $scope.isActiveAccounts = false;
    $scope.isActiveCards = false;
    $scope.isActiveTransactions = false;
    $scope.isActiveUsers = true;

    $http.get('/users/allUsers')
        .success(function (response) {
            $scope.listUsers = response;
        });


    $scope.adminNews = function () {
        $scope.listUsers = null;
        $scope.listAccounts = null;
        $scope.listCards = null;
        $scope.isActiveNews = true;
        $scope.isActiveAccounts = false;
        $scope.isActiveCards = false;
        $scope.isActiveTransactions = false;
        $scope.isActiveUsers = false;
        $scope.headersNews = [
            'id', 'title', 'content', 'recipient'];
        $http.get('/news')
            .success(function (response) {
                $scope.listNews = response;
            });
        $http.get('/users/allUsers')
            .success(function (response) {
                $scope.listUsers = response;
            });

    };
    $scope.adminAccounts = function () {
        $scope.listUsers = null;
        $scope.listNews = null;
        $scope.listCards = null;
        $scope.isActiveNews = false;
        $scope.isActiveAccounts = true;
        $scope.isActiveCards = false;
        $scope.isActiveTransactions = false;
        $scope.isActiveUsers = false;
        $scope.headersAccount = [
            'account number', 'balance', 'state'];
        $http.get('/accounts')
            .success(function (response) {
                $scope.listAccounts = response;
            });
    };
    $scope.adminCards = function () {
        $scope.listUsers = null;
        $scope.listNews = null;
        $scope.listCards = null;
        $scope.isActiveNews = false;
        $scope.isActiveAccounts = false;
        $scope.isActiveCards = true;
        $scope.isActiveTransactions = false;
        $scope.isActiveUsers = false;
        $scope.headersCard = [
            'card number', 'balance', 'state'];
        $http.get('/cards')
            .success(function (response) {
                $scope.listCards = response;
            });

    };
    $scope.adminTransactions = function () {
        $scope.isActiveNews = false;
        $scope.isActiveAccounts = false;
        $scope.isActiveCards = false;
        $scope.isActiveTransactions = true;
        $scope.isActiveUsers = false;
        $scope.headersTransactions = [
            'title', 'name of recipient', 'card number of the sender', 'date', 'sum'];
        $http.get('/transactions')
            .success(function (response) {
                $scope.listTransactions = response;
            });
    };

    $scope.addNews = function () {
        var data = {
            'title': $scope.title,
            'content': $scope.content
        };
        if ($scope.mySelectedUser == 'ALL USERS') {
            $http.post('/news', data)
                .success(function (response) {
                    $scope.adminNews()
                });
        } else if ($scope.mySelectedUser != undefined) {
            $http.post('/news/recipient?recipient=' + $scope.mySelectedUser, data)
                .success(function (response) {
                    $scope.adminNews()
                });
        }
    };

    $scope.changeState = function () {
        if ($scope.mySelectedState != undefined && $scope.mySelectedAccount != undefined) {
            $http.post('http://localhost:8012/accounts/state' + '?id=' + $scope.mySelectedAccount + '&state=' + $scope.mySelectedState)
                .success(function (response) {
                    $scope.adminAccounts()
                });
        }
    };
    $scope.deleteAccount = function () {
        if ($scope.mySelectedAccount != undefined) {
            $http.delete('/accounts?id=' + $scope.mySelectedAccount)
                .success(function (response) {
                    $scope.adminAccounts();
                });
        }
    };


    $scope.deleteNews = function () {
        if ($scope.mySelectedNews != undefined) {
            $http.delete('/news?id=' + $scope.mySelectedNews)
                .success(function (response) {
                    $scope.adminNews();
                });
        }
    };

    $scope.deleteCard = function () {
        if ($scope.mySelectedCard != undefined) {
            $http.delete('/cards?id=' + $scope.mySelectedCard)
                .success(function (response) {
                    $scopeSeparator.adminCards();
                });
        }
    };
    $scope.changeStateCard = function () {
        if ($scope.mySelectedCard != undefined && $scope.mySelectedStateOfCard !=undefined) {
            $http.post('/cards/state'+ '?id=' + $scope.mySelectedCard + '&state=' + $scope.mySelectedStateOfCard)
                .success(function (response) {
                    $scope.adminCards();
                });
        }
    };

});