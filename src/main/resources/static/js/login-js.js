let loginPageForm = document.querySelector('.login-page-form');
let signInBtn = document.querySelector('.signIn-btn');

const sendForm = () => {
	console.log('Authorization');
	loginPageForm.submit();
};

signInBtn.addEventListener('click', sendForm);
