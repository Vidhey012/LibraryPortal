var validModle = angular.module('valid', []);
            
validModle.controller('mainController', ['$scope', '$http', function ($scope, $http ) {  
	
	  $scope.save=function(formData){
	///console.log(formData.username);
	 if(formData==null || formData=='' || formData==undefined)
	  {
	  	swal("Enter all details!");
	  }
	
	 else if(formData.firstname == null || formData.firstname == '' || formData.firstname == undefined)
	  {
	  	swal("Enter First Name!");
	  }
	 else if(formData.username == null || formData.username == '' || formData.username == undefined)
	  {
	  	swal("Enter User Name!");
	  }
	
		   alert(formData.username + ' Registration Successful');
			$http.post('/librarydata/register1/saveDetails', formData).then(
					function(response) {
						$scope.data = response.data;
						if ($scope.data.successful) {
							location.reload();
							/*alert("User Data Inserted Successfully");*/
						} else {
							alert("Data not inserted");
						}
					}, function(errResponse) {
						console.error('Error while fetching notes');
					});
	  }   }]);
	 