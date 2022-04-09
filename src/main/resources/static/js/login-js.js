let loginPageForm = document.querySelector('.login-page-form');
let signInBtn = document.querySelector('.signIn-btn');
let homeLink = document.querySelector('.home-link');
let createAccountQuestion = document.querySelector('.create-account-question');

const sendForm = () => {
	console.log('Authorization');
	loginPageForm.submit();
};

const redirectToHomePage = () => {
	window.location.href="http://localhost:8080/home";
}

// Redirect to register form
const redirectToRegisterSite = () => {
	window.location.href="http://localhost:8080/register";
}

createAccountQuestion.addEventListener('click', redirectToRegisterSite);
homeLink.addEventListener('click', redirectToHomePage);
signInBtn.addEventListener('click', sendForm);
