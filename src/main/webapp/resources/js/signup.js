let form = document.getElementById('signup').addEventListener('submit', login);

async function login(e){
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
	location.href='resources/html/employee.html';
	
}









