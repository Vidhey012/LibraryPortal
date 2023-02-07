
var Registration = angular.module('UpdateModule', []);
Registration.controller('RegController', ['$http','$scope', function($http, $scope) {
	
			
			//Update User Details
			$scope.changeData = function(UserData){
				/*alert(UserData.username);*/
				$http.post('/librarydata/register1/changeData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								/*alert("User password is updated");*/
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};

			$scope.updateData = function(UserData){
				/*alert(UserData.username);*/
				$http.post('/librarydata/register1/updateData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								/*alert("User profile is updated");*/
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};
			// View Data from Database                      
			email=Andromeda.getSessionValue("email");
			$http.post('/librarydata/register1/getById',email).then(function(response) {
				$scope.data = response.data;
				if ($scope.data.successful) {
					$scope.data ={object: $scope.data.responseObject};
				} else {
					alert("Can't view the Data");
				}
			}, function(errResponse) {
				console.error('Error while viewing notes');
			});

			
		
		} ]);
