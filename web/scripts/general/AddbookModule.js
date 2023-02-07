var Book = angular.module('AddbookModule', []);
Book.controller('BookController', ['$http','$scope', function($http, $scope) {


// Book information Module
$scope.saveDetails = function(BookData) {
/*alert(BookData.bookname + ' inserted Successful');*/
$http.post('/librarydata/Book1/add', BookData).then(
function(response) {
$scope.data = response.data;
if ($scope.data.successful) {
/*location.reload();*/

/*alert("Book Data Inserted Successfully");*/
} else {
alert("Data not inserted");
}
}, function(errResponse) {
console.error('Error while fetching notes');

});
};
 
} ]);