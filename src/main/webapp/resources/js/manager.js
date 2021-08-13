

window.onload = function(e) {
  retrieveSesstion(e);
};

async function retrieveSesstion(e){
	e.preventDefault();
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/getsession', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
		});
		var res = await req.json();
		selectListEmployees();
			
	}catch (e){
		location.reload();
		return
	}
	
}
async function selectListEmployees(){
let res = await fetch('http://localhost:8080/Project1/api/manager/allemployees');
	let data = await res.json();
	console.log(data);
	populateAllEmoloyeeSelectList(data);
}

let container = document.getElementById('ADTable');

async function retrievePendingRequest(){
	let res = await fetch('http://localhost:8080/Project1/api/manager/pending');
	let data = await res.json();
	console.log(data);
	populateRequest(data);
}

function populateRequest(data){
	$("td").empty();
    $("tr").empty();
    $("#pending").empty();
    $("#resolved").empty();
    $("#header").empty();
    $("#allEmployees").empty();

	$("#pending").append($('<h2>All Pending Requests of All Employees</h2>'));
	$("#specificEmployeeH2").empty();
    $('#header').append($('<th>Amount</th>'));
    $('#header').append($('<th>Description</th>'));
    $('#header').append($('<th>Status</th>'));
    $('#header').append($('<th>Submit By</th>'));
    $('#header').append($('<th>Submitted On</th>'));
    $('#header').append($('<th>Type</th>'));
	

	for (rbObj of data) {
		let date = rbObj.submitted.month + ` ` +rbObj.submitted.dayOfMonth +`, ` + rbObj.submitted.year;
        $('#ADTable').append($('<tr><td>'+ rbObj.amount +'</td><td>' + rbObj.description + '</td><td>' 
        + rbObj.status.reimBStatus + '</td><td>' + rbObj.submittedBy.firstName + ' ' + rbObj.submittedBy.lastName 
        + '</td><td>'+ date + '</td><td>' + rbObj.type.reimBType 
        + '</td><td>' + '<button type="button" onclick="return approve(' + rbObj.reimbId + ')">Approve</button>'
        + '</td><td>' + '<button type="button" onclick="return deny(event,' + rbObj.reimbId + ')">Deny</button>' + '</td></tr>'));                               
    }

}
async function retrieveResolvedRequest(){
	let res = await fetch('http://localhost:8080/Project1/api/manager/resolved');
	let data = await res.json();
	console.log(data);
	populateResolvedRequest(data);
}

function populateResolvedRequest(data){
	$("td").empty();
    $("tr").empty();
    $("#resolved").empty();
    $("#pending").empty();
    $("#headerResolved").empty();
    $("#allEmployees").empty();
    
    $("#specificEmployeeH2").empty();
	$("#resolved").append($('<h2>All Resolved Requests of All Employees</h2>'));
	
    $('#headerResolved').append($('<th>Amount</th>'));
    $('#headerResolved').append($('<th>Description</th>'));
    $('#headerResolved').append($('<th>Status</th>'));
    $('#headerResolved').append($('<th>Submit By</th>'));
    $('#headerResolved').append($('<th>Submitted On</th>'));
    $('#headerResolved').append($('<th>Type</th>'));
	

	for (rbObj of data) {
		let date = rbObj.submitted.month + ` ` +rbObj.submitted.dayOfMonth +`, ` + rbObj.submitted.year;
        $('#Resolved').append($('<tr><td>'+ rbObj.amount +'</td><td>' + rbObj.description + '</td><td>' 
        + rbObj.status.reimBStatus + '</td><td>' + rbObj.submittedBy.firstName + ' ' + rbObj.submittedBy.lastName 
        + '</td><td>'+ date + '</td><td>' + rbObj.type.reimBType + '</td></tr>'));                               
    }

}



async function approve(rbidIn){
	
	
	let option = 1;
	let rbId = rbidIn;
	
	
	let update ={
		option,
		rbId
	}
	console.log(update);
	
		let req = await fetch ('http://localhost:8080/Project1/api/manager/approve', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
			body: JSON.stringify(update)
		});
		var res = await req.json();
		console.log(res);
}

async function deny(e, rbidIn){
	e.preventDefault();
	
	let option = 2;
	let rbId = rbidIn;
	
	
	let update ={
		option,
		rbId
	}
	console.log(update);
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/manager/approve', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
			body: JSON.stringify(update)
		});
		var res = await req.json();
		
		
	}catch (e){
		console.log('Username or password was incorrect.')
		return
	}
	console.log(res);	
}


async function retrieveAllEmployee(){
	let res = await fetch('http://localhost:8080/Project1/api/manager/allemployees');
	let data = await res.json();
	console.log(data);
	populateAllEmoloyee(data);
}




function populateAllEmoloyee(data){
	$("tr").empty();
    $("td").empty();
    $("#resolved").empty();
    $("#pending").empty();
    $("#allEmployees").empty();
    $("#headerEmployees").empty();
    
    $("#specificEmployeeH2").empty();
	$("#allEmployees").append($('<h2>All Employees</h2>'));
	
    $('#headerEmployees').append($('<th>First Name</th>'));
    $('#headerEmployees').append($('<th>Last Name</th>'));
    $('#headerEmployees').append($('<th>Email</th>'));
    $('#headerEmployees').append($('<th>Username</th>'));
	

	for (eObj of data) {
		
		
        $('#employees').append($('<tr><td>'+ eObj.firstName +'</td><td>' + eObj.lastName + '</td><td>' 
        + eObj.email + '</td><td>' + eObj.username + '</td></tr>'));                               
    }

}


function populateAllEmoloyeeSelectList(data){
	var i = 1 ;
	$('#employee').empty();
	for (user of data) {
		
        $('#employee').append($('<option value="'+ user.id +'">'+ i +'. '+ user.firstName +' ' +user.lastName + '</option>' ));    
        i++;                           
    }

}

let form12 = document.getElementById('allemployees').addEventListener('submit', view);

async function view(e){
	e.preventDefault();
	let select = document.getElementById('employee');
	let employeeId = select.options[select.selectedIndex].value;
	let username = select.options[select.selectedIndex].text;
	
	
	let user ={
		employeeId
	}
	console.log(user);
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/manager/specific', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
			body: JSON.stringify(user)
		});
		var res = await req.json();
		
		
	}catch (e){
		console.log('Username or password was incorrect.')
		return
	}
	populateRBEmployee(res, username);
}

function populateRBEmployee(data, name){
	console.log(name);
	if (data != null) {
		$("td").empty();
	    $("tr").empty();
	    $("#specificEmployeeH2").empty();
	    $("#pending").empty();
	    $("#rowHeaderRB").empty();
	    $("#allEmployees").empty();
	    
	    
		$("#specificEmployeeH2").append($('<h2>'+ data[0].submittedBy.firstName +' ' + data[0].submittedBy.lastName+' Reimbursements</h2>'));
		
	    $('#rowHeaderRB').append($('<th>Amount</th>'));
	    $('#rowHeaderRB').append($('<th>Description</th>'));
	    $('#rowHeaderRB').append($('<th>Status</th>'));
	    $('#rowHeaderRB').append($('<th>Submit By</th>'));
	    $('#rowHeaderRB').append($('<th>Submitted On</th>'));
	    $('#rowHeaderRB').append($('<th>Type</th>'));
		
	
		for (rbObj of data) {
			let date = rbObj.submitted.month + ` ` +rbObj.submitted.dayOfMonth +`, ` + rbObj.submitted.year;
	        $('#specificEmployeeRBTable').append($('<tr><td>'+ rbObj.amount +'</td><td>' + rbObj.description + '</td><td>' 
	        + rbObj.status.reimBStatus + '</td><td>' + rbObj.submittedBy.firstName + ' ' + rbObj.submittedBy.lastName 
	        + '</td><td>'+ date + '</td><td>' + rbObj.type.reimBType + '</td></tr>'));                               
	    }
    } else {
	$("td").empty();
    $("tr").empty();
    
    $("#pending").empty();
    $("#rowHeaderRB").empty();
    $("#allEmployees").empty();
	$("#specificEmployeeH2").append($('<h2>'+name +' dose not have any requests</h2>'));
	
	}
}

function showForm(e){
	
	var formid = document.getElementById('allemployees');
	if (formid.style.display == "none"){
		formid.style.display = "block";
	}
}

async function logout(e){
	location.href='http://localhost:8080/Project1/logout';
	e.preventDefault();
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/logout', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
		});
		var res = await req.json();		
	}catch (e){
		location.href='http://localhost:8080/Project1/logout';
		return
	}
	location.href='http://localhost:8080/Project1/logout';
}

