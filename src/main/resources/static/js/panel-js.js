import Swiper from 'https://unpkg.com/swiper@8/swiper-bundle.esm.browser.min.js';

let menuBtnBrowseCandidates;
let menuBtnPreferences;
let menuBtnMessage;
let menuBtnMatches;
let menuBtnSettings;

// box in right wrapper
let browseCandidatesBox;
let preferencesBox;
let messageBox;
let matchesBox;
let profileSettingsBox;

// Like or reject user
let selectUser;
let likeBtn;
let rejectBtn;

// Selected User description
// let viewedEmail;
let viewedName;
let viewedAge
let viewedAboutMe;

// User Preferences inputs
let selectGender;
let sliderMinAgeInput;
let sliderMaxAgeInput;
let sliderDistanceInput;
let selectRelationshipStatus;
let selectSexualOrientation;
let savePreferencesBtn;

// User Description
let descriptionName;
let descriptionGender;
let descriptionAge;
let descriptionAboutMe;
let descriptionSexualOrientation;
let descriptionRelationshipStatus;
let saveDescriptionBtn;

// User Description Image
let saveDescriptionImageBtn
let descriptionImageInput;
let singleImgInBase64;

// User Localization
let latitude; // Szerokość geograficzna
let longitude; // długość geograficzna

// Another
let swiperWrapper;

let myCredentials; // My credentials, information about the currently logged in user
let myUserDescription; // Object describing my profile, this profile is displayed to other users
let myUserPreferences; // My user search preferences

let viewedUser = {
	email: 'no email',
	aboutMeDescription: 'no description',
}; // Information about the currently viewed user

const prepareDOMElementns = () => {
	// Menu buttons
	menuBtnBrowseCandidates = document.querySelector(
		'.menu-btn-browse-candidates'
	);
	menuBtnPreferences = document.querySelector('.menu-btn-preferences');
	menuBtnMessage = document.querySelector('.menu-btn-message');
	menuBtnMatches = document.querySelector('.menu-btn-matches');
	menuBtnSettings = document.querySelector('.menu-btn-settings');

	// box
	browseCandidatesBox = document.querySelector('.browse-candidates-box');
	preferencesBox = document.querySelector('.preferences-box');
	messageBox = document.querySelector('.message-box');
	matchesBox = document.querySelector('.matches-box');
	profileSettingsBox = document.querySelector('.profile-settings-box');

	// Like or reject user
	selectUser = document.querySelector('.selected-user');
	likeBtn = document.querySelector('.like-btn');
	rejectBtn = document.querySelector('.reject-btn');

	// User Preferences inputs
	selectGender = document.querySelector('.select-gender');
	sliderMinAgeInput = document.querySelector('.slider-min-age-input');
	sliderMaxAgeInput = document.querySelector('.slider-max-age-input');
	sliderDistanceInput = document.querySelector('.slider-distance-input');
	selectRelationshipStatus = document.querySelector(
		'.select-relationship-status'
	);
	selectSexualOrientation = document.querySelector(
		'.select-sexual-orientation'
	);
	savePreferencesBtn = document.querySelector('.save-preferences-btn');

	// User Description
	descriptionName = document.querySelector('.description-name');
	descriptionGender = document.querySelector('.description-gender');
	descriptionAge = document.querySelector('.description-age');
	descriptionAboutMe = document.querySelector('.description-aboutMe');
	descriptionSexualOrientation = document.querySelector(
		'.description-sexual-orientation'
	);
	descriptionRelationshipStatus = document.querySelector(
		'.description-relationship-status'
	);
	saveDescriptionBtn = document.querySelector('.save-description-btn');

	// User Description Image
	descriptionImageInput = document.querySelector('.description-image-input');
	saveDescriptionImageBtn = document.querySelector('.save-description-image-btn');

	// Another
	swiperWrapper = document.querySelector('.swiper-wrapper');

	// Viewed Description
	// viewedEmail = document.querySelector('.viewed-email');
	viewedName = document.querySelector('.viewed-name');
	viewedAge = document.querySelector('.viewed-age');
	viewedAboutMe = document.querySelector('.viewed-aboutMe');
};

const prepareDOMEvents = () => {
	likeBtn.addEventListener('click', likeUser);
	rejectBtn.addEventListener('click', rejectUser);
	savePreferencesBtn.addEventListener('click', sendUserPreferences);


	saveDescriptionBtn.addEventListener('click', sendUserDescription);
	saveDescriptionImageBtn.addEventListener('click', sendSingleImage);

	menuBtnBrowseCandidates.addEventListener(
		'click',
		menuBtnBrowseCandidatesAction
	);
	menuBtnPreferences.addEventListener('click', menuBtnPreferencesAction);
	menuBtnMessage.addEventListener('click', menuBtnMessageAction);
	menuBtnMatches.addEventListener('click', menuBtnMatchesAction);
	menuBtnSettings.addEventListener('click', menuBtnSettingsAction);
};

const mainFunction = () => {
	prepareDOMElementns();
	prepareDOMEvents();
	getMyCredentials();
	getMyUserDescription();
};

// ********* Przeanczanie boxów ************
const resetAllBox = () => {
	browseCandidatesBox.style.display = 'none';
	preferencesBox.style.display = 'none';
	messageBox.style.display = 'none';
	matchesBox.style.display = 'none';
	profileSettingsBox.style.display = 'none';
};

const menuBtnBrowseCandidatesAction = () => {
	resetAllBox();
	browseCandidatesBox.style.display = 'flex';
};
const menuBtnPreferencesAction = () => {
	resetAllBox();
	preferencesBox.style.display = 'flex';
};
const menuBtnMessageAction = () => {
	resetAllBox();
	messageBox.style.display = 'flex';
};
const menuBtnMatchesAction = () => {
	resetAllBox();
	matchesBox.style.display = 'flex';
};
const menuBtnSettingsAction = () => {
	resetAllBox();
	profileSettingsBox.style.display = 'flex';
};

// ********** Swipowanie uzytkowników **********

const swiper = new Swiper('.swiper', {
	// Optional parameters
	direction: 'horizontal',
	loop: true,
	speed: 550,
	spaceBetween: 100,
	effect: 'coverflow',
	coverflowEffect: {
		depth: 800,
		modifier: 1,
		rotate: 30,
		slideshadow: true,
	},

	grabCursor: false,

	slidesPerView: 1.4,
	centeredSlides: true,

	autoplay: {
		delay: 3000,
		disableOnInteraction: false,
		pauseOnMouseEnter: true,
		reverseDirection: false,
		stopOnLastSlide: false,
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
});

// ********************************************* Get my credentials and userDescription ***************************************

// Enabling the authentication of the currently logged in user to be retrieved from the server
const getMyCredentials = () => {
	fetch('http://localhost:8080/panel/api/credentials/send', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((response) => response.json())
		.then((response) => {
			myCredentials = response;
			console.log(response);
		})
		.then(() => {
			connectWebSocket();
			getUserPreferencesFromServer();
		})
		.then(() => getUserLocationAndSendToServer())
		.then(() => getNextUserFromServer()) // Get first viewedUser
		.catch((error) => console.log('err: ', error));
};

// ******************************************************* User description **********************************************

const getMyUserDescription = () => {
	fetch('http://localhost:8080/panel/api/userDescription/send', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((response) => response.json())
		.then((response) => {
			myUserDescription = response;
			console.log(response);
		})
		.then(() => setMyUserDescription(myUserDescription))
		.catch((error) => console.log('err: ', error));
};

const setMyUserDescription = (myUserDescription) => {
	descriptionName.value = myUserDescription.name;
	descriptionGender.value = myUserDescription.gender;
	descriptionAge.value = myUserDescription.age;
	descriptionAboutMe.value = myUserDescription.aboutMeDescription;
	descriptionSexualOrientation.value = myUserDescription.sexualOrientation;
	descriptionRelationshipStatus.value = myUserDescription.relationshipStatus;
};

// Send UserDescription object to server
const sendUserDescription = () => {
	const response = fetch(
		'http://localhost:8080/panel/api/userDescription/update',
		{
			method: 'POST',
			headers: {
				Accept: 'application/json',
				'Content-Type': 'application/json',
			},
			'Access-Control-Allow-Origin': '*',
			body: JSON.stringify({
				name: descriptionName.value,
				gender: descriptionGender.value,
				age: descriptionAge.value,
				aboutMeDescription: descriptionAboutMe.value,
				sexualOrientation: descriptionSexualOrientation.value,
				relationshipStatus: descriptionRelationshipStatus.value,
			}),
		}
	);
};

// ************************************ User Description Image *******************************************

// img to base64 and callback send to server
const sendSingleImage = () => {
	let reader = new FileReader();

	reader.onload = () => {
		console.log('e2' + reader.result);
		singleImgInBase64 = reader.result;
		sendImgToServer(reader.result);
	}

	reader.onerror = function (error) {
		console.log('Error: ', error);
	};

	reader.readAsDataURL(descriptionImageInput.files[0]);
}

// It allows you to send single img(base64) to server
const sendImgToServer = (imgInBase64) => {
		fetch("http://localhost:8080/panel/api/picture/update", {
			method: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			'Access-Control-Allow-Origin': "*",
			body: JSON.stringify({
				"bytes": imgInBase64,
			}),
		})
			.then(res => console.log(res))
}

// ************************************ Next User *****************************************

// It allows you to download another user from the server for viewing
const getNextUserFromServer = () => {
	fetch('http://localhost:8080/panel/api/userDescription/sendNext', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((response) => response.json())
		.then((response) => {
			console.log(response);
			viewedUser = response;
		})
		.then(() => setViewedUserInView(viewedUser))
		.catch((error) => console.log('err: ', error));
};

// Allows you to update likes and disapproved users and get next user
const updateUserLikesAndGetNext = (choice = '') => {
	const response = fetch('http://localhost:8080/panel/api/userFriends/update', {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		'Access-Control-Allow-Origin': '*',
		body: JSON.stringify({
			selectingUserEmail: myCredentials.email,
			selectedUserEmail: viewedUser.email,
			choice: choice,
		}),
	})
		.then((res) => console.log(res))
		.then(() => {
			getNextUserFromServer();
		});
};

const likeUser = () => {
	updateUserLikesAndGetNext('like');
};

const rejectUser = () => {
	updateUserLikesAndGetNext('reject');
};

//viewedUser.pictures.forEach( function (item, index){
// 			let newDiv = document.createElement("div");
// 			let pictureAddedToSwiper = new Image();
//
// 			newDiv.classList.add("swiper-slide");
// 			pictureAddedToSwiper.classList.add("swiper-slide-img");
// 			pictureAddedToSwiper.src = viewedUser.pictures[0].bytes;
//          console.log('NNNNNNNNNNNNN   ' + viewedUser.pictures[0].bytes)
// 			newDiv.appendChild(pictureAddedToSwiper);
// 			swiper.appendSlide(newDiv);

const setViewedUserInView = (viewedUser) => {

	swiper.removeAllSlides();

		viewedUser.pictures.forEach( function (picture, index){
			let newDiv = document.createElement("div");
			let pictureAddedToSwiper = new Image();

			newDiv.classList.add("swiper-slide");
			pictureAddedToSwiper.classList.add("swiper-slide-img");
			pictureAddedToSwiper.src = picture.bytes;

			newDiv.appendChild(pictureAddedToSwiper);
			swiper.appendSlide(newDiv);
		});

	swiper.update();

	viewedName.textContent = viewedUser.name;
	viewedAge.textContent = viewedUser.age;
	viewedAboutMe.textContent = viewedUser.aboutMeDescription;

};

// ******************** GetLocalisation **********************************

// Allows you to retrieve the user's location and send to server
const getUserLocationAndSendToServer = () => {
	let geo = navigator.geolocation;

	if (geo) {
		geo.getCurrentPosition(function (location) {
			latitude = location.coords.latitude;
			longitude = location.coords.longitude;

			sendGeoLocalizationToServer();

			const geoLocalizationInterval = window.setInterval(() => {
				sendGeoLocalizationToServer();
			}, 40000); // 40s
		});
	} else {
		console.log('The location is not available ');
	}
};

// Send localization to Server
const sendGeoLocalizationToServer = () => {
	const response = fetch(
		'http://localhost:8080/panel/api/geoLocalization/update',
		{
			method: 'POST',
			headers: {
				Accept: 'application/json',
				'Content-Type': 'application/json',
			},
			'Access-Control-Allow-Origin': '*',
			body: JSON.stringify({
				email: myCredentials.email,
				latitude: latitude,
				longitude: longitude,
			}),
		}
	);
};

// *****************  Filters **************************

// It allows you to upload your currently saved userPreferences to the server
const sendUserPreferences = () => {
	const response = fetch(
		'http://localhost:8080/panel/api/userPreferences/update',
		{
			method: 'POST',
			headers: {
				Accept: 'application/json',
				'Content-Type': 'application/json',
			},
			'Access-Control-Allow-Origin': '*',
			body: JSON.stringify({
				email: myCredentials.email,
				gender: selectGender.value,
				minAge: sliderMinAgeInput.value,
				maxAge: sliderMaxAgeInput.value,
				distance: sliderDistanceInput.value,
				relationshipStatus: selectRelationshipStatus.value,
				sexualOrientation: selectSexualOrientation.value,
			}),
		}
	).then((res) => console.log(res));
	// .then(() => getUserPreferencesFromServer()) Czy jest nam to potrzebne
};

const getUserPreferencesFromServer = () => {
	fetch('http://localhost:8080/panel/api/userPreferences/send', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((response) => response.json())
		.then((response) => {
			console.log(response);
			myUserPreferences = response;
		})
		.then((response) => setMyUserPreferences(myUserPreferences))
		.catch((error) => console.log('err: ', error));
};

// Sets the inputs to the appropriate values according to the passed argument to the function
const setMyUserPreferences = (preferences) => {
	selectGender.value = preferences.gender;
	sliderMinAgeInput.value = preferences.minAge;
	sliderMaxAgeInput.value = preferences.maxAge;
	sliderDistanceInput.value = preferences.distance;
	selectRelationshipStatus.value = preferences.relationshipStatus;
	selectSexualOrientation.value = preferences.sexualOrientation;
};

// ********************************* Communicator *****************************



const inputField = document.querySelector('.input-field');
const sendBtn = document.querySelector('.send-btn');
const response = document.querySelector('.response');

const updateRecipientBtn = document.querySelector('.up');

const recipientNameInput = document.querySelector('.recipient-name-input');

let client;

const send = () => {
	let text = inputField.value;
	let username = myCredentials.email;
	let name = recipientNameInput.value;
	let quote = {'text':text, 'username':username, 'name': name};
	client.send("/app/chat", {}, JSON.stringify(quote));
}


const updateMessageRecipient = () => {
	let quote = {'username': myCredentials.email,};
	client.send("/recipients/update", {}, JSON.stringify(quote));
}

const connectWebSocket = () => {
	client = Stomp.client('ws://localhost:8080/chat') //ws - information abut protocol, localhost.. - chat endpoint
	client.connect({ username: myCredentials.email, }, function (frame){
		console.log('Web Socket is connected');

		client.subscribe('/users/queue/recipients', function(message){
			// console.log('DOstałem liste polubionych za pomocą webSocket')
			// let c = JSON.parse(message.body).email;
			console.log("kawa")
			console.log(JSON.parse(message.body));
			console.log(JSON.parse(message.body).recipientList);
		})

		client.subscribe('/users/queue/messages', function(message){
			showMessage(JSON.parse(message.body).username, JSON.parse(message.body).text, JSON.parse(message.body).time);

		})
	})
}

const showMessage = (username, text, time) => {
	let newP = document.createElement('p');
	newP.textContent = text;
	response.appendChild(newP);
	console.log('Otrzymałem wiadomość: ' + username + ' ' + text + ' ' + time);
}

sendBtn.addEventListener('click', send);
updateRecipientBtn.addEventListener('click', updateMessageRecipient);
// connectWebsocketBtn.addEventListener('click', connectWebSocket);


// Start, DOMContentLoaded
document.addEventListener('DOMContentLoaded', mainFunction);
