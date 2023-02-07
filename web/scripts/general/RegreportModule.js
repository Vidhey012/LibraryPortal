var Registration = angular.module('RegreportModule', []);
Registration.controller('RegController', ['$http','$scope', function($http, $scope) {


$http.post('/librarydata/register1/getAll').then(function(response) {
$scope.data = response.data;
if ($scope.data.successful) {
$scope.ussdata = $scope.data.responseObject;
} else {
alert("Can't view the Data");
}
}, function(errResponse) {
console.error('Error while viewing notes');
});
} ]);            