var myapp = angular.module('myapp', []);
myapp.controller('newsController', function ($scope, $http) {

    $scope.createCard = function () {
        if ($scope.accountForCard != undefined) {
            $http.post("/accounts/cards?id=" + $scope.accountForCard)
                .success(function (response) {
                    $scope.cardsFunc();
                });
        }
    };
    $scope.lockCard = function () {


        if ($scope.selectedCard != undefined && $scope.selectedStateCard != undefined) {
            $http.post('cards/state?id=' + $scope.selectedCard + '&state=' + $scope.selectedStateCard)
                .success(function (response) {
                    $scope.cardsFunc();
                });
        }
    };

    $scope.cardsFunc = function () {
        $scope.notShowCards = false;
        $scope.notShowAccounts = true;
        $scope.notShowTransactions = true;
        $scope.headers = null;
        $scope.headersCard = [
            'card number', 'balance', 'state'];
        $scope.listNews = null;
        $scope.listAccounts = null;
        $http.get('http://localhost:8012/users/cards')
            .success(function (response) {
                $scope.listCards = response;
            });

        $http.get('http://localhost:8012/users/accounts')
            .success(function (response) {

                $scope.headers = [
                    'account number', 'balance', 'state'];
                $scope.listAccounts = response;
            })

    };


    $scope.accountsFunc = function () {
        $scope.notShowAccounts = false;
        $scope.notShowCards = true;
        $scope.notShowTransactions = true;
        $scope.listNews = null;
        $scope.listCards = null;
        $http.get('http://localhost:8012/users/accounts')
            .success(function (response) {

                $scope.headers = [
                    'account number', 'balance', 'state'];
                $scope.listAccounts = response;
            })
    };

    $scope.createAccount = function () {
        $http.post('http://localhost:8012/users/accounts')
            .success(function (response) {
                $scope.accountsFunc();
            })
    };

    $scope.lockAccount = function () {
        if($scope.myOption != undefined && $scope.selectedStateAccount != undefined)
        $http.post('http://localhost:8012/accounts/state' + '?id=' + $scope.myOption + '&state='+$scope.selectedStateAccount)
            .success(function (response) {
                $scope.accountsFunc()
            })
    };

    $scope.transactionsFunc = function () {
        $scope.notShowCards = true;
        $scope.notShowAccounts = true;
        $scope.notShowTransactions = false;
        $scope.headersTransactions = [
            'title', 'name of recipient', 'card number of the sender', 'date', 'sum'];
        $scope.listNews = null;
        $scope.listAccounts = null;
        $http.get('http://localhost:8012/users/cards')
            .success(function (response) {
                $scope.listCards = response;
            });
        $http.get('http://localhost:8012/users/transactions')
            .success(function (response) {
                $scope.listTransactions = response;
            });
    };


    $scope.makeTransaction = function () {

        if ($scope.mySelectedCard != undefined) {
            if ($scope.mySelectedOperation == 'Card') {
                var data = {
                    'idCard': $scope.mySelectedCard,
                    'recipientNumber': $scope.recipient,
                    'summa': $scope.sum
                };
                $http.post('http://localhost:8012/transactions/card', data)
                    .success(function (response) {
                        $scope.transactionsFunc();
                        $scope.transaction = response;
                        $scope.transactionMessage = 'transaction committed'
                    }).error(function (response) {
                    console.log(response);
                    $scope.transactionMessage = 'transaction not committed'
                })

            } else if ($scope.mySelectedOperation == 'Account') {
                var data = {
                    'idCard': $scope.mySelectedCard,
                    'recipientNumber': $scope.recipient,
                    'summa': $scope.sum
                };
                $http.post('http://localhost:8012/transactions/account', data)
                    .success(function (response) {
                        $scope.transactionsFunc();
                        $scope.transaction = response;
                        $scope.transactionMessage = 'transaction committed'
                    }).error(function (response) {
                    console.log(response);
                    $scope.transactionMessage = 'transaction not committed'
                })
            }
        }
    }


    $scope.notShowAccounts = true;
    $scope.notShowCards = true;
    $scope.notShowTransactions = true;
    $http.get('http://localhost:8012/users/newsForUser')
        .success(function (response) {
            $scope.listNews = response;
        })


    function saveTextAsFile(data, filename) {

        if (!data) {
            console.error('Console.save: No data')
            return;
        }

        if (!filename) filename = 'console.json'

        var blob = new Blob([data], {type: 'text/plain'}),
            e = document.createEvent('MouseEvents'),
            a = document.createElement('a')
// FOR IE:

        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveOrOpenBlob(blob, filename);
        }
        else {
            var e = document.createEvent('MouseEvents'),
                a = document.createElement('a');

            a.download = filename;
            a.href = window.URL.createObjectURL(blob);
            a.dataset.downloadurl = ['text/plain', a.download, a.href].join(':');
            e.initEvent('click', true, false, window,
                0, 0, 0, 0, 0, false, false, false, false, 0, null);
            a.dispatchEvent(e);
        }
    }

    $scope.saveTransactions = function () {
        $http.get('http://localhost:8012/users/transactions')
            .success(function (response) {
                saveTextAsFile(angular.toJson(response), 'my transactions');
            });
        var fileName = "myTransactions.json"

    }

});