
var Contact = angular.module('ContactModule', []);
Contact.controller('Contactcontroller', ['$http','$scope', function($http, $scope) {


// User  Contact Module
$scope.Contact = function(UserData) {
	if(UserData == undefined)               
	{
	alert("Fill All details");
	}
	else if(UserData .name == null || UserData .name == undefined || UserData.name == "" ){
	alert("Name cannot be empty");
	}
	else if( UserData.email == null ||  UserData.email == undefined ||  UserData.email == "" ){
	alert("Email cannot be empty");
	}

	else
		{
/*alert(UserData.name + ' Sending Successful');*/
$http.post('/librarydata/Contact/add', UserData).then(
function(response) {
$scope.data = response.data;          
if ($scope.data.successful) {   
location.reload();
/*alert("User Data Inserted Successfully");    */      
} else {
alert("Data not inserted");
}
}, function(errResponse) {
console.error('Error while fetching notes');                     
});
};
} }]);




