
var Registration = angular.module('ConReportModule', []);
Registration.controller('ContactController', ['$http','$scope', function($http, $scope) {

$http.post('/librarydata/Contact/getAll').then(function(response) {
$scope.data = response.data;
if ($scope.data.successful) {
$scope.condetails = $scope.data.responseObject;
} else {
alert("Can't view the Data");
}
}, function(errResponse) {
console.error("Error while viewing notes");
});

} ]);  