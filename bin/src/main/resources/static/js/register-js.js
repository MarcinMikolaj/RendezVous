import Swiper from 'https://unpkg.com/swiper@8/swiper-bundle.esm.browser.min.js';

let registerForm;
let registerBtn;
let signInLink;

const prepareDOMElements = () => {
	registerForm = document.querySelector('.register-form');
	registerBtn = document.querySelector('.register-btn');
	signInLink = document.querySelector('.sign-in-link');
};

const prepareDOMEvents = () => {
	registerBtn.addEventListener('click', sendRegisterForm);
	signInLink.addEventListener('click', redirectToSignInSite);
};

const main = () => {
	prepareDOMElements();
	prepareDOMEvents();
};

const swiper = new Swiper('.swiper', {
	// Optional parameters
	direction: 'horizontal',
	loop: true,
	speed: 1200,

	autoplay: {
		delay: 5000,
		disableOnInteraction: false,
	},

	effect: 'cube',
	cubeEffect: {
		slideShadows: false,
		shadow: false,
	},

	// Navigation arrows
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	},

	// And if we need scrollbar
	scrollbar: {
		el: '.swiper-scrollbar',
	},

	grabCursor: true,
});

const sendRegisterForm = () => {
	registerForm.submit();
};

const redirectToSignInSite = () => {
	window.location.href="http://localhost:8080/panel";
}

document.addEventListener('DOMContentLoaded', main);
