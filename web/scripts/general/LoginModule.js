
var Login = angular.module('LoginModule', []);
Login.controller('Logincontroller', ['$http','$scope', function($http, $scope) {


// User  Login Module
$scope.Login = function(UserData) {
	/*alert(UserData.username + ' Sending Successful');*/
	$http.post('/librarydata/Login/add', UserData).then(function(response) {
		$scope.data = response.data;
		if ($scope.data.successful) {
			location.reload(); 
			/*alert("User Data Inserted Successfully");*/
			} 
				else
				{
					alert("Data not inserted");
					}
		}, function(errResponse) 
			{
				console.error('Error while fetching notes');
				});
	};
	}]);
