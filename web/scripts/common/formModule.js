
var formModule = angular.module('formModule', [])
.controller('FormController', [ '$http', '$scope', function($http, $scope) {
		var self = this;
		$scope.login = function(user) {
			 $("#glyphiconid").show();
			
	        $http.post('/librarydata/login/login',user).then(function(response) {  
				$scope.data = response.data;
				if($scope.data.successful) {
					Andromeda.setSessionValue("email", $scope.data.responseObject.email.replace(/\s+/g, ''));
					Andromeda.setSessionValue("password", $scope.data.responseObject.password);
					Andromeda.setSessionValue("type", $scope.data.responseObject.type);
					// $("#glyphiconid").hide();  
					console.log(Andromeda.getSessionValue("type"));
					if(Andromeda.getSessionValue("type")=="Student")
						{
//						 showMainPage();
					Andromeda.showUserLoginPage();
						}
					else if(Andromeda.getSessionValue("type")=="Admin"){           
						Andromeda.showAdminLoginPage();
						
					}
					else if(Andromeda.getSessionValue("type")=="Faculty"){           
						Andromeda.showFacultyLoginPage();
						
					}
					else{
						showHomePage();
					}
				} else {
					$("#glyphiconid").hide();
					/*alert($scope.data.errorMessage);
					showError($scope.data.errorMessage);
					var message = "<div class=\"alert alert-danger\"><strong>Error: </strong>"+ $scope.data.errorMessage + "</div>";
					jQuery("#errorDiv").html(message);*/
					$("#errorDiv").show();
					$("#errorDiv").html("<b style='margin-left: 28%;font-size: medium;'>Note:</b> Invalid login credentials");
					console.log("Error while validating user");
				}
			}, function(errResponse) {
				console.error('Error while fetching notes');
			});
	    };
	    $scope.forgotPassword = function(email)
		{
			if(email != null)   
			{
				jQuery("#spinner").addClass("glyphicon glyphicon-refresh glyphicon-refresh-animate");
				$http.post('/librarydata/register1/forgot', email).then(function(response) 
				{  
					$scope.data = response.data;
					if($scope.data.successful) 
					{
						jQuery("#spinner").removeClass("glyphicon glyphicon-refresh glyphicon-refresh-animate");
						alert("A mail has been sent!");
						
						Andromeda.showHomePage1();
					}
					else 
					{
						jQuery("#spinner").removeClass("glyphicon glyphicon-refresh glyphicon-refresh-animate");
						alert($scope.data.errorMessage);
					}
				},
				function(errResponse) 
				{
					console.error('Error while fetching notes');
				});
			}
			else
			{
				alert("Please enter email-Id");
			}
	    };
} ]);