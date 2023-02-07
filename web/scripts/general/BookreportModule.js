var Book = angular.module('BookreportModule', []);
Book.controller('BookController', ['$http','$scope', function($http, $scope) {


$http.post('/librarydata/Book1/getAll').then(function(response) {
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