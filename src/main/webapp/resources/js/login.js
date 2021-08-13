let form = document.getElementById('login').addEventListener('submit', login);

async function login(e){
	e.preventDefault();
	
	let username = document.getElementById('username').value;
	let password= document.getElementById('password').value;
	
	let user ={
		username,
		password
	}
	console.log(user);
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/login', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
			body: JSON.stringify(user)
		});
		
		var res = await req.json();
		
		
	}catch (e){
		alert('Username or password was incorrect.');
		console.log('Username or password was incorrect.')
		return
	}
	
	//This is how we can Redirect to other page
	location.href='http://localhost:8080/Project1/home';
	location.reload();
	
	
}

let form2 = document.getElementById('signup').addEventListener('submit', signup);

async function signup(e){
	e.preventDefault();
	
	let first = document.getElementById('first').value;
	let last = document.getElementById('last').value;
	let email = document.getElementById('email').value;
	let password= document.getElementById('password').value;
	
	let user ={
		first,
		last,
		email,
		password
	}
	console.log(user);
	try{
		let req = await fetch ('http://localhost:8080/Project1/api/signup', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
				
			},
			body: JSON.stringify(user)
		});
		var res = await req.json();
		
		
	}catch (e){
		console.log('Signup fails')
		return
	}
	//console.log(res);
	//This is how we can Redirect to other page
	location.href='http://localhost:8080/Project1/home';
	location.reload();
	
}
function showregister(e){
	var loginformid = document.getElementById('loginDiv');
	var registerformid = document.getElementById('signupDiv');
	
	if (registerformid.style.display == "none"){
		registerformid.style.display = "block";
		loginformid.style.display = "none";
	}
}
function showlogin(e){
	
	var loginformid = document.getElementById('loginDiv');
	var registerformid = document.getElementById('signupDiv');
	
	if (loginformid.style.display == "none"){
		loginformid.style.display = "block";
		registerformid.style.display = "none";
	}
	
}











