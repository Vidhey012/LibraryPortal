
var Registration = angular.module('RegistrationModule', []);
Registration.controller('RegController', ['$http','$scope', function($http, $scope) {
	
	
			// User Registration Module
			$scope.saveDetails = function(UserData) {
				/*alert(UserData.firstname + ' Registration Successful');*/
				$http.post('/librarydata/register1/saveDetails', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								location.reload();
								/*alert("User Data Inserted Successfully");  */
							} else {
								alert("Data not inserted");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};
			
		/*	//Get Data from Database based on Name
			$scope.getById = function(firstname){
				alert(firstname);
				$http.post('/librarydata/user/getById', firstname).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								$scope.data={object:$scope.data.responseObject};
							} else {
								alert("Error while getting data");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};*/
			
		/*	//Update User Details
			$scope.changeData = function(UserData){
				alert(UserData.username);
				$http.post('/librarydata/register1/changeData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								alert("User password is updated");
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};

			$scope.updateData = function(UserData){
				alert(UserData.username);
				$http.post('/librarydata/register1/updateData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								alert("User profile is updated");
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};
		
			*/
			
		
		} ]);
