function showPage(path, targetDiv) {
	var jqxhr = jQuery.post(path, function(data) {
		jQuery("#" + targetDiv).html(data);
	}).done(function() {
		// alert("second success");
	}).fail(function() {
		// alert("error");
	}).always(function() {
		// alert("finished");
	});
}

function setSessionValue(key, value) {
	sessionStorage.setItem(key, value);
}

function getSessionValue(key) {
	return sessionStorage.getItem(key);
}

function showLoginHome() {
	var path = "/librarydata/html/login/home.html";
	showPage(path, "homedata");
}

function showErrorPage() {
	var path = "html/general/Error.html";
	showPage(path, "indexDiv");
}

function showGalleryPage() {
	
	var path = "/librarydata/html/gallary.html";
	showPage(path, "replaceDiv");
	
}


function showQuestionReport() {
	var path = "/librarydata/html/interest.html";
	showPage(path, "replaceDiv");
}

function showLatesSingleCollege() {
	var path="/librarydata/html/login/CollegeDistrictTrained.html";               
	showPage(path, "replaceDiv");
}
function showgetlatestStudents() {
	var path="/librarydata/html/login/districtStudent.html";
	showPage(path, "replaceDiv");
}

function showgetlatestBranchdata() {
	var path="/librarydata/html/login/branchRep.html";
	showPage(path, "replaceDiv");
}

function showgetlatestStudentsdata() {
	var path="/librarydata/html/login/collegesstudent.html";
	showPage(path, "replaceDiv");
}



function checkStatus(){
	var urlParameter = getUrlParameter("q");
	Andromeda.setSessionValue("parameter", urlParameter);
	if(Andromeda.getSessionValue("status")== undefined){
		Andromeda.setSessionValue("status", Andromeda.getSessionValue("parameter"));
		return true;
	}
	else{
		if(Andromeda.getSessionValue("status") == Andromeda.getSessionValue("parameter")){
			return true;
		}
		else{
			return false;
		}
	}
}



/*---------------District wise dashboard----------------------*/

function getDistrictwiseNewPrograms() {
	var responseData = Andromeda.post("/librarydata/report/district");
	if (responseData.successful) {
		var count = 0;
		$("#title").html("<center><h3>District - wise report</h3></center>");
		$("#subTitle").html("");
		$("#childTitle").html("");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
				.html(
						"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
		$("#back")
				.html(
						"<button type='button' class='abn abn-link pull-right' onclick='window.location.reload()'><a><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></a></button>");
		$("#tableHead")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>District</th><th>Colleges</th><th>Trained</th></tr>");
		for (i = 0; i < responseData.responseObject.length; i++) {
			count = count + responseData.responseObject[i].count;
			$("#tableBody")
					.append(
							"<tr>"
									+ "<td align='right'>"
									+ (i + 1)
									+ "</td>"
									+ "<td class='nav cursor-pointer' onClick=getlatestSingleCollege("
									+ responseData.responseObject[i].districtId
									+ ",'"
									+ encodeURI(responseData.responseObject[i].districtName)
									+ "');><a>"
									+ responseData.responseObject[i].districtName
									+ "</a></td>"
									+ "<td align='right'>"
									+ responseData.responseObject[i].collegecount
									+ "</td>" + "<td align='right'>"
									+ responseData.responseObject[i].count
									+ "</td>"
									// +"<td>"+responseData.responseObject[i].traineeCount+"</td>"
									+ "</tr>");
		}
		$("#tableBody")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><td></td><td>Total</td><td></td><td align='right'>"
								+ count + "</td></tr>");

	} else {
		Andromeda.showError(responseData.errorMessage);
	}
}

/*---------------College wise dashboard----------------------*/

function getCollegewiseNewPrograms() {
	var responseData = Andromeda.post("/librarydata/report/college");
	if (responseData.successful) {
		var count = 0;
		$("#title").html("<center><h3>College - wise report</h3></center>");
		$("#subTitle").html("");
		$("#childTitle").html("");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
				.html(
						"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
		$("#back")
				.html(
						"<button type='button' class='abn abn-link pull-right' onclick='window.location.reload()'><a><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></a></button>");
		$("#tableHead")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>District</th><th>Colleges</th><th>Trained</th></tr>");
		for (i = 0; i < responseData.responseObject.length; i++) {
			count = count + responseData.responseObject[i].count;
			$("#tableBody")
					.append(
							"<tr>"
									+ "<td align='right'>"
									+ (i + 1)
									+ "</td>"
									+ "<td class='nav cursor-pointer' onClick=getlatestSingleCollege("
									+ responseData.responseObject[i].collegeId
									+ ",'"
									+ encodeURI(responseData.responseObject[i].collegeName)
									+ "');><a>"
									+ responseData.responseObject[i].collegeName
									+ "</a></td>"
									+ "<td align='right'>"
									+ responseData.responseObject[i].branchcount
									+ "</td>" + "<td align='right'>"
									+ responseData.responseObject[i].count
									+ "</td>"
									// +"<td>"+responseData.responseObject[i].traineeCount+"</td>"
									+ "</tr>");
		}
		$("#tableBody")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><td></td><td>Total</td><td></td><td align='right'>"
								+ count + "</td></tr>");

	} else {
		Andromeda.showError(responseData.errorMessage);
	}
}


/*------------------single college-----------------*/
function getlatestSingleCollege(districtId, districtName) {
	var districtModel = {
		districtId : districtId  
	};
	var responseData = Andromeda.post(
			"/librarydata/report/singlelatestCollegesinapssdc", districtModel);
	if (responseData.successful) {
		var count = 0;
		$("#title").html("<center><h3>District - wise report</h3></center>");
		$("#subTitle").html(
				"<h4>District: " + decodeURI(districtName) + "</h4>");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
		.html(
				"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
$("#back")
		.html(
				"<a class='pull-right' style='cursor: pointer;' onclick='getDistrictwiseNewPrograms();'><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></a>");
$("#tableHead")
		.append(
				"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>Program</th><th>Trainee</th></tr>");
for (i = 0; i < responseData.responseObject.length; i++) {
	count = count + responseData.responseObject[i].trainees;
	$("#tableBody")
			.append(
					"<tr>"
							+ "<td align='right'>"
							+ (i + 1)
							+ "</td>"
							+ "<td class='nav cursor-pointer' onClick=getlatestSingleCollege("
							+ responseData.responseObject[i].districtId
							+ ","
							+ responseData.responseObject[i].collegeId
							+ ",'"
							+ districtName
							+ "','"
							+ encodeURI(responseData.responseObject[i].collegeName)
							+ "');><a>"
							+ responseData.responseObject[i].collegeName
							+ "</a></td>" + "<td align='right'>"
							+ responseData.responseObject[i].trainees
							+ "</td>" + "</tr>");
}
$("#tableBody")
		.append(
				"<tr style='color:#333; background-color:#f5f5f5;'><td></td><td>Total</td><td align='right'>"
						+ count + "</td></tr>");

} else {
Andromeda.showError(responseData.errorMessage);
}
}

function getlatestStudents(districtId, districtName,collegeId, collegeName) {
	var districtModel = {
		districtId : districtId,
		collegeId : collegeId
		
	};
	var responseData = Andromeda.post("/librarydata/report/latestStudents",
			districtModel);
	if (responseData.successful) {
		$("#title").html("<center><h3>District - wise report</h3></center>");
		$("#subTitle").html(
				"<h4>District: " + decodeURI(districtName) + "</h4>");
		$("#childTitle").html(
				"<h4>College:"
						+ decodeURI(collegeName) + "</h4>");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
				.html(
						"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
		$("#back")
				.html(
						"<button type='button' class='abn abn-link pull-right' onclick=getlatestSingleCollege("
								+ districtId
								
								+ ",'"
								+ districtName
								
								+ "');><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></button>");
		$("#tableHead")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>Trainee id</th><th>Student</th></tr>");
		for (i = 0; i < responseData.responseObject.length; i++) {
			$("#tableBody")
					.append(
							"<tr>"
									+ "<td align='right'>"
									+ (i + 1)
									+ "</td>"
									+ "<td class='nav cursor-pointer'>"
									+ responseData.responseObject[i].registrationId
									+ "</td><td class='nav cursor-pointer' onclick=popup('"
									+ responseData.responseObject[i].registrationId
									+ "')><a>"
									+ responseData.responseObject[i].traineeName
									+ "</a></td>" + "</tr>");
		}
	} else {
		Andromeda.showError(responseData.errorMessage);
	}

	getUpdatedReport();  

}


function getlatestBranchdata(collegeId, collegeName) {
	var districtModel = {
			collegeId : collegeId
		
	};
	var responseData = Andromeda.post(
			"/librarydata/report/branch", districtModel);
	if (responseData.successful) {
		var count = 0;
		$("#title").html("<center><h3>District - wise report</h3></center>");
		$("#subTitle").html(
				"<h4>College: " + decodeURI(collegeName) + "</h4>");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
		.html(
				"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
$("#back")
		.html(
				"<a class='pull-right' style='cursor: pointer;' onclick='getDistrictwiseNewPrograms();'><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></a>");
$("#tableHead")
		.append(
				"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>Program</th><th>Trainee</th></tr>");
for (i = 0; i < responseData.responseObject.length; i++) {
	count = count + responseData.responseObject[i].trainees;
	$("#tableBody")
			.append(
					"<tr>"
							+ "<td align='right'>"
							+ (i + 1)
							+ "</td>"
							+ "<td class='nav cursor-pointer' onClick=getlatestSingleCollege("
							+ responseData.responseObject[i].collegeId
							+ ","
							+ responseData.responseObject[i].branchId
							+ ",'"
							+ collegeName
							+ "','"
							+ encodeURI(responseData.responseObject[i].branchName)
							+ "');><a>"
							+ responseData.responseObject[i].branchName
							+ "</a></td>" + "<td align='right'>"
							+ responseData.responseObject[i].trainees
							+ "</td>" + "</tr>");
}
$("#tableBody")
		.append(
				"<tr style='color:#333; background-color:#f5f5f5;'><td></td><td>Total</td><td align='right'>"
						+ count + "</td></tr>");

} else {
Andromeda.showError(responseData.errorMessage);
}
}



function getlatestStudentsdata(collegeId, collegeName,branchId, branchName,registrationId) {
	var districtModel = {
			
		collegeId : collegeId,
		branchId : branchId,
		registrationId : registrationId
		
	};
	var responseData = Andromeda.post("/librarydata/report/latestStudentsdata",
			districtModel);
	if (responseData.successful) {
		$("#title").html("<center><h3>District - wise report</h3></center>");
		$("#subTitle").html(
				"<h4>College: " + decodeURI(collegeName) + "</h4>");
		$("#childTitle").html(
				"<h4>Branch:"
						+ decodeURI(branchName) + "</h4>");
		$("#tableDiv thead").empty();
		$("#tableDiv tbody").empty();
		$("#excel")
				.html(         
						"<button type='button' class='abn abn-link pull-right' onClick='exportData();'><img src='/andromeda/images/icons/excel-icon.png' alt='Excel' width='30' height='30'></button>");
		$("#back")
				.html(
						"<button type='button' class='abn abn-link pull-right' onclick=getlatestSingleCollege("
								+ districtId
								
								+ ",'"
								+ districtName
								
								+ "');><img src='/andromeda/images/icons/back.png' alt=''width='30' height='30'/></button>");
		$("#tableHead")
				.append(
						"<tr style='color:#333; background-color:#f5f5f5;'><th>S.No.</th><th>Trainee id</th><th>Student</th></tr>");
		for (i = 0; i < responseData.responseObject.length; i++) {
			$("#tableBody")
					.append(
							"<tr>"
									+ "<td align='right'>"
									+ (i + 1)
									+ "</td>"
									+ "<td class='nav cursor-pointer'>"
									+ responseData.responseObject[i].registrationId
									+ "</td><td class='nav cursor-pointer' onclick=popup('"
									+ responseData.responseObject[i].registrationId
									+ "')><a>"
									+ responseData.responseObject[i].traineeName
									+ "</a></td>" + "</tr>");
		}
	} else {
		Andromeda.showError(responseData.errorMessage);
	}
	getUpdatedReport();  

}







