var Andromeda = {

	showPage : function(path, targetDiv) {
		var jqxhr = jQuery.post(path, function(data) {
			jQuery("#" + targetDiv).html(data);
		});
	},
	showHomePage : function() {
		var path = "/librarydata/index.html";
		Andromeda.showPage(path, "name");
	},  
	
	showHomePage1 : function() {
		var path = "/librarydata/html/Home1.html";
		Andromeda.showPage(path, "amdContainerDiv");
	},   
	showContactPage : function() {
		var path = "/librarydata/html/contact.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	
	showGalleryPage : function() {
		var path = "/librarydata/html/gallery.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	showAboutPage : function() {
		var path = "/librarydata/html/aboutus.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	showLoginPage : function(){
		var path = "/librarydata/html/login.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	showRegisterPage : function() {
		var path = "/librarydata/html/register.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	
	
	showAboutPage : function() {
		var path = "/librarydata/html/aboutus.html";
		Andromeda.showPage(path, "replaceDiv");
	},
	
	showUserLoginPage : function() {
 		var path = "/librarydata/html/userLogin.html";
		Andromeda.showPage(path, "name");
	},
	

	showProfilePage : function() {
		var path = "/librarydata/html/profile.html";
		Andromeda.showPage(path, "backDiv");
	},
	 showPasswordPage : function() {
		var path = "/librarydata/html/change.html";
		Andromeda.showPage(path, "backDiv");
	},
	showUserbookPage : function() {
		var path = "/librarydata/html/bookreport.html";
		Andromeda.showPage(path, "backDiv");           
	},
	showAdminLoginPage : function() {
		var path = "/librarydata/html/admin.html";
		Andromeda.showPage(path, "name");
	},
	showFacultyLoginPage : function() {
		var path = "/librarydata/html/faculty.html";
		Andromeda.showPage(path, "name");
	},
	
	
	showRegreportPage : function() {
		var path = "/librarydata/html/Regreport.html";
		Andromeda.showPage(path, "adminid");
	},
	showConreportPage : function() {
		var path = "/librarydata/html/conreport.html";
		Andromeda.showPage(path, "adminid");
	},
	showBookreportPage : function() {
		var path = "/librarydata/html/bookreport.html";
		Andromeda.showPage(path, "adminid");
	},
	showAdminProfilePage : function() {
		var path = "/librarydata/html/profile.html";
		Andromeda.showPage(path, "adminid");
	},
	showAddBookPage : function() {
		var path = "/librarydata/html/addbook.html";
		Andromeda.showPage(path, "adminid");
	},
	
	showFacbookPage : function() {
		var path = "/librarydata/html/bookreport.html";
		Andromeda.showPage(path, "facDiv");
	},
	showFacProfilePage : function() {
		var path = "/librarydata/html/profile.html";
		Andromeda.showPage(path, "facDiv");
	},
	showFacPasswordPage : function() {
		var path = "/librarydata/html/change.html";
		Andromeda.showPage(path, "facDiv");
	},
	home : function() {
		window.location.reload();
	},

	setSessionValue : function(key, value) {
		sessionStorage.setItem(key, value);
	},

	getSessionValue : function(key) {
		return sessionStorage.getItem(key);
	},   

	removeSessionValue : function(key) {
		sessionStorage.removeItem(key);
	},

	showError : function(errorMessage) {
		var message = "<div class=\"alert alert-danger\"><strong>Error: </strong>"
				+ errorMessage + "</div>";
		jQuery("#errorDiv").html(message);
	},

	logout : function() {
		
		var email = Andromeda.getSessionValue("email") || "";
		Andromeda.setSessionValue("email", null);
		Andromeda.setSessionValue("password", null);
		var data = {
			email : email
		};  
		Andromeda.post('/librarydata/login/logout', data);
		Andromeda.showHomePage();
	},
   
	post : function(url, data) {
		var responseData = null;

		jQuery.ajax({
			url : url,
			type : 'post',
			data : JSON.stringify(data), // Stringified Json Object
			dataType : 'json',
			async : false, // Cross-domain requests and dataType: "jsonp"
			// requests do not support synchronous operation
			cache : false, // This will force requested pages not to be cached
			// by the browser
			processData : false, // To avoid making query String instead of
			// JSON
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				responseData = data;
			}
		});

		return responseData;
	},

	isUserLoggedIn : function() {
		/*console.log("userrrrr:"+Andromeda.getSessionValue("userName"));
		console.log("contextttttt:"+Andromeda.getSessionValue("context"));*/
		var email = Andromeda.getSessionValue("email") || "";
		var password = Andromeda.getSessionValue("password") || "";
		var type = Andromeda.getSessionValue("type") || "";

		var login = {
			email : email,
			password : password,
			type : type
			
		};

		return Andromeda.post('/librarydata/login/loggedin', login) || false;
	}  	
};
