let barsIcone; // Ikona otwarcia paska nawigacji na wersjach mobilnych (i)
let barsMenu; // Kontener dla navigacji na wersjach mobilnych (div)
let times; // Ikona zamknięcia paska nawigacji na wersjach mobilny (i)
let sendTheApplicationBtn; // Przycisk wysłania formularz (button)
let contactForm; // Formularz kontaktowy (form)

// wejścia (input) i komunikaty błędów (p) dla validacji formularz
let senderName;
let senderNameError;
let email;
let emailError;
let title;
let titleError;
let formContent;
let formContentError;
let agreement;
let agreementError;

// Wyrażenie regulrne dla sprawdzania poprawności maila
const mailRegex =
	/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;

prepareDOMElements = () => {
	barsIcone = document.querySelector('.fa-bars');
	barsMenu = document.querySelector('.bars-menu');
	times = document.querySelector('.fa-times');
	sendTheApplicationBtn = document.querySelector('.send-btn');
	contactForm = document.querySelector('.contact-form');
	senderName = document.querySelector(".contact-form input[name='name']");
	senderNameError = document.querySelector('.name-error');
	email = document.querySelector(".contact-form input[name='email']");
	emailError = document.querySelector('.email-error');
	title = document.querySelector(".contact-form input[name='title']");
	titleError = document.querySelector('.title-error');
	formContent = document.querySelector(".contact-form textarea[name='formContent']")
	formContentError = document.querySelector('.formContent-error');
	agreement = document.querySelector(".contact-form input[name='agreement']");
	agreementError = document.querySelector('.agreement-error');
};

prepareDOMEvents = () => {
	barsIcone.addEventListener('click', showBarsMenu);
	times.addEventListener('click', closeBarsMenu);
	sendTheApplicationBtn.addEventListener('click', sendTheApplicationForm);
};

main = () => {
	prepareDOMElements();
	prepareDOMEvents();
};

showBarsMenu = () => {
	barsMenu.style.display = 'flex';
};

closeBarsMenu = () => {
	barsMenu.style.display = 'none';
};

sendTheApplicationForm = () => {
	let key = true;

	if(!validName() == true){
		key = false;
	}
	if(!validEmailAdress() == true){
		key = false;
	}
	if(!validTitle() == true){
		key = false;
	}
	if(!validFormContent() == true){
		key = false;
	}
	if(!validAgreement() == true){
		key = false;
	}
	if(key === true){
		contactForm.submit();
		console.log("Contact Form sent form client to server, success !");
	}

};

validName = () => {
	if (senderName && senderName.value !== '') {
		senderNameError.textContent = '';
		return true;
	} else {
		senderName.value = '';
		senderNameError.textContent = 'Musisz podać swoje imię';
		return false;
	}
};

validEmailAdress = () => {
	if (email && mailRegex.test(email.value)) {
		emailError.textContent = '';
		return true;
	} else {
		email.value = '';
		emailError.textContent = 'Wprowadzony adres email jest niepoprawny';
		return false;
	}
};

validTitle = () => {
	if (title && title.value !== '') {
		titleError.textContent = '';
		return true;
	} else {
		titleError.textContent = 'Temat wiadomości nie może być pusty';
		return false;
	}
};

validFormContent = () => {

	if (formContent && formContent.value !== '') {
		formContentError.textContent = '';
		return true;
	} else {
		formContentError.textContent = 'Musisz podać treść swojej wiadomości';
		return false;
	}
}

validAgreement = () => {
	if (agreement.checked == true) {
		agreementError.textContent = '';
		return true;
	} else {
		agreementError.textContent = 'Musisz zatwierdzić zgodę';
		return false;
	}
};

// Iniclajlizacja i dodanie mapy
initMap = () => {
	// Lokalizacja ustawianego miejsca
	const myLocation = { lat: 49.57803, lng: 20.67169 };

	// Ustawienie mapy na moją lokalizacje
	const map = new google.maps.Map(document.getElementById('map'), {
		zoom: 15,
		center: myLocation,
	});

	// Ustwienie znacznika na moją lokalizacjie
	const marker = new google.maps.Marker({
		position: myLocation,
		map: map,
	});
};

document.addEventListener('DOMContentLoaded', main);
