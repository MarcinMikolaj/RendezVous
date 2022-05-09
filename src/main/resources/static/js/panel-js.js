import Swiper from 'https://unpkg.com/swiper@8/swiper-bundle.esm.browser.min.js';

let panelWrapper;
let leftMessageBox;
let rightMessageBox;

// menu btns
let menuBtnBrowseCandidates;
let menuBtnPreferences;
let menuBtnMessage;
let menuBtnMatches;
let menuBtnSettings;
let menuBtnLogout;
let sidebarUserImg;
let sidebarUserName;

// mobile Menu
let mobileMenuBtnBrowseCandidates;
let mobileMenuBtnPreferences;
let mobileMenuBtnMessage;
let mobileMenuBtnMatches;
let mobileMenuBtnSettings;
let mobileMenuBtnLogout;
let openMobileMenuBtn;
let closeMobileMenuBtn;
let mobileMenu; //mobile menu box
let showRecipientsInMobileBtn;

let startVideoTalker // start video conversation between users


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
let viewedAge;
let viewedAboutMe;
let viewedKilometersAway;
let viewedUniversity;
let viewedCity;
let viewedWork;
let viewedHaveChildren;
let viewedAmISmoking;
let viewedDoIDrinkAlcohol;
let viewedHowTallAmI;
let viewedHowMuchDoIWeight;
let viewedDoIPlaySports;
let viewedSexualOrientation;
let viewedGender;
let viewedInterested;


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
let descriptionAmISmoking;
let descriptionHaveChildren;
let descriptionDoIDrinkAlcohol;
let descriptionHowTallAmI;
let descriptionHowMuchDoIWeight
let descriptionDoIPlaySports;
let descriptionInterested;
let descriptionWork;
let descriptionUniversity;
let descriptionCity;
let saveDescriptionBtn;
let descriptionMobileTopImg;

// User Description Image
let saveDescriptionImageBtn;
let descriptionImageInput;
let singleImgInBase64;

// Recipient preview
let previewImg;
let previewName;
let previewAge;
let previewUniversity;
let previewCity;
let previewWork;
let previewKilometersAway;

// User Localization
let latitude; // Szerokość geograficzna
let longitude; // długość geograficzna

// Another
let swiperWrapper;

let myCredentials; // My credentials, information about the currently logged in user
let myUserDescription; // Object describing my profile, this profile is displayed to project.rendezvous.other users
let myUserPreferences; // My user search preferences

let viewedUser = {
	email: 'no email',
	aboutMeDescription: 'no description',
}; // Information about the currently viewed user


// ------------ Video Talker -------------
let webSocketClient;

let videoChatWrapper;
const localVideo = document.querySelector('.local-video');
const remoteVideo = document.querySelector('.remote-video');

// WebRTC
let pc; // WebRTC peer connection
let dataChannel; // WebRTC data channel
let localStream;

let sendMessageInputCHATROOM;
let sendMessageButtonCHATROOM;
let messageListCHATROOM;


// menu buttons for video-chat
let startVideoButton;
let stopVideoButton;
let mutedMicrophoneButton;
let unmutedMicrophoneButton;
let showChatButton;
let closeChatButton;
let changeVideoChatColorButton;
let closeVideoChatButton;

let incomingCallWrapper;
let incomingCallOfferAccept;
let incomingCallOfferReject;

// set the time that must occur between sending the message for the time of sending the message to be shown
let tostbstm = 10000; // milliseconds

const prepareDOMElementns = () => {
	panelWrapper = document.querySelector('.panel-wrapper');
	leftMessageBox = document.querySelector('.left-message-box');
	rightMessageBox = document.querySelector('.right-message-box');

	// Menu buttons
	menuBtnBrowseCandidates = document.querySelector('.menu-btn-browse-candidates');
	menuBtnPreferences = document.querySelector('.menu-btn-preferences');
	menuBtnMessage = document.querySelector('.menu-btn-message');
	menuBtnMatches = document.querySelector('.menu-btn-matches');
	menuBtnSettings = document.querySelector('.menu-btn-settings');
	menuBtnLogout = document.querySelector('.menu-btn-logout');
	sidebarUserImg = document.querySelector('.sidebar-user-img');
	sidebarUserName = document.querySelector('.sidebar-user-name');

	// Mobile menu
	 mobileMenuBtnBrowseCandidates = document.querySelector('.mobile-menu-btn-browse-candidates');
	 mobileMenuBtnPreferences= document.querySelector('.mobile-menu-btn-preferences');
	 mobileMenuBtnMessage= document.querySelector('.mobile-menu-btn-message');
	 mobileMenuBtnMatches= document.querySelector('.mobile-menu-btn-matches');
	 mobileMenuBtnSettings= document.querySelector('.mobile-menu-btn-settings');
	 mobileMenuBtnLogout= document.querySelector('.mobile-menu-btn-logout');

	 openMobileMenuBtn = document.querySelector('.open-mobile-menu-btn');
	 closeMobileMenuBtn = document.querySelector('.close-mobile-menu-btn');
	 mobileMenu = document.querySelector('.mobile-menu');
	 showRecipientsInMobileBtn = document.querySelector('.show-recipient-btn');

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
	descriptionAmISmoking = document.querySelector('.description-am-i-smoking');
	descriptionHaveChildren = document.querySelector('.description-have-children');
	descriptionDoIDrinkAlcohol = document.querySelector('.description-do-i-drink-alcohol');
	descriptionHowTallAmI = document.querySelector('.description-how-tall-am-i');
	descriptionHowMuchDoIWeight = document.querySelector('.description-how-much-do-i-weight');
	descriptionDoIPlaySports = document.querySelector('.description-do-i-play-sports');
	descriptionInterested = document.querySelector('.description-interested');
	descriptionWork = document.querySelector('.description-work');
	descriptionUniversity = document.querySelector('.description-university');
	descriptionCity = document.querySelector('.description-city');
	descriptionMobileTopImg = document.querySelector('.description-mobile-top-img');
	descriptionRelationshipStatus = document.querySelector(
		'.description-relationship-status'
	);
	saveDescriptionBtn = document.querySelector('.save-description-btn');

	// Recipient preview
	previewImg = document.querySelector('.preview-img');
	previewName = document.querySelector('.preview-name');
	previewAge = document.querySelector('.preview-age');
	previewUniversity = document.querySelector('.preview-university');
	previewCity = document.querySelector('.preview-city');
	previewWork = document.querySelector('.preview-work');
	previewKilometersAway = document.querySelector('.preview-KilometersAway');

	// User Description Image
	descriptionImageInput = document.querySelector('.description-image-input');
	saveDescriptionImageBtn = document.querySelector(
		'.save-description-image-btn'
	);

	// Another
	swiperWrapper = document.querySelector('.swiper-wrapper');

	// Viewed Description
	// viewedEmail = document.querySelector('.viewed-email');
	viewedName = document.querySelector('.viewed-name');
	viewedAge = document.querySelector('.viewed-age');
	viewedAboutMe = document.querySelector('.viewed-aboutMe');
	viewedKilometersAway = document.querySelector('.viewedKilometersAway');
	viewedUniversity = document.querySelector('.viewed-university');
	viewedCity = document.querySelector('.viewed-city');
	viewedWork = document.querySelector('.viewed-work');
	viewedHaveChildren = document.querySelector('.viewed-have-children');
	viewedAmISmoking = document.querySelector('.viewed-am-i-smoking');
	viewedDoIDrinkAlcohol = document.querySelector('.viewed-do-i-drink-alcohol');
	viewedHowTallAmI = document.querySelector('.viewed-how-tall-am-i');
	viewedHowMuchDoIWeight = document.querySelector('.viewed-how-much-do-i-weight');
	viewedDoIPlaySports = document.querySelector('.viewed-do-i-play-sports');
	viewedSexualOrientation = document.querySelector('.viewed-sexual-orientation');
	viewedGender = document.querySelector('.viewed-gender');
	viewedInterested = document.querySelector('.viewed-interested');


	startVideoTalker = document.querySelector('.start-video-talker');
//	---------- Video Talker -----------

	videoChatWrapper = document.querySelector('.video-chat-wrapper');

	sendMessageButtonCHATROOM = document.querySelector(
		'.sendMessageButtonCHATROOM'
	);
	sendMessageInputCHATROOM = document.querySelector(
		'.sendMessageInputCHATROOM'
	);
	messageListCHATROOM = document.querySelector('.messageListCHATROOM');

	// prepare menu buttons for video-chat
	startVideoButton = document.querySelector('.start-video-button');
	stopVideoButton = document.querySelector('.stop-video-button');
	mutedMicrophoneButton = document.querySelector('.muted-microphone-button');
	unmutedMicrophoneButton = document.querySelector(
		'.unmuted-microphone-button'
	);
	showChatButton = document.querySelector('.show-chat-button');
	closeChatButton = document.querySelector('.close-chat-button');
	changeVideoChatColorButton = document.querySelector(
		'.change-video-chat-color-button'
	);
	closeVideoChatButton = document.querySelector('.close-video-chat-button');

	// incoming call wrapper
	incomingCallWrapper = document.querySelector('.incoming-call-wrapper');
	incomingCallOfferAccept = document.querySelector('.incoming-call-offer-accept');
	incomingCallOfferReject = document.querySelector('.incoming-call-offer-reject');

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
	menuBtnLogout.addEventListener('click', logout);

	// Mobile
	mobileMenuBtnBrowseCandidates.addEventListener('click', menuBtnBrowseCandidatesAction);
	mobileMenuBtnPreferences.addEventListener('click', menuBtnPreferencesAction);
	mobileMenuBtnMessage.addEventListener('click', menuBtnMessageAction);
	mobileMenuBtnMatches.addEventListener('click', menuBtnMatchesAction);
	mobileMenuBtnSettings.addEventListener('click', menuBtnSettingsAction);
	mobileMenuBtnLogout.addEventListener('click', logout);
	openMobileMenuBtn.addEventListener('click', showMobileMenu);
	closeMobileMenuBtn.addEventListener('click', closeMobileMenu);
	showRecipientsInMobileBtn.addEventListener('click', showRecipientsInMobile);

	startVideoTalker.addEventListener('click', () => {
		panelWrapper.style.display = 'none';
		videoChatWrapper.style.display = 'flex';
		startVideoTalkerApplication();
	})

//	---------- Video Talker ------------
	// WebSocket
	// connectWithWebSocketBtn.addEventListener('click', negotiateWebRTCConnectionByWebSocket);

	sendMessageButtonCHATROOM.addEventListener('click', sendAndShowMyMessageToOtherUser);
	sendMessageInputCHATROOM.addEventListener('keypress', (event) => {
		if (event.key === 'Enter') {
			sendAndShowMyMessageToOtherUser();
		}
	});
	closeVideoChatButton.addEventListener('click', hangup);
	closeChatButton.addEventListener('click', closeChat);
	showChatButton.addEventListener('click', showChat);

	incomingCallOfferAccept.addEventListener('click', acceptOrRejectIncomingCall);
	incomingCallOfferReject.addEventListener('click', () => {
		incomingCallWrapper.style.display = 'none';
		videoChatWrapper.style.display = 'none';
	})

};

const mainFunction = () => {
	prepareDOMElementns();
	prepareDOMEvents();
	getMyCredentials();
	getMyUserDescription();

	setScrollToBottomForMessageChatRoom();
	videoChatWrapper.style.display = 'none';
	startVideoButton.style.display = 'none';
	unmutedMicrophoneButton.style.display = 'none';
	showChatButton.style.display = 'none';
};

// ********* Przełanczanie boxów ************
const resetAllBox = () => {
	browseCandidatesBox.style.display = 'none';
	preferencesBox.style.display = 'none';
	messageBox.style.display = 'none';
	matchesBox.style.display = 'none';
	profileSettingsBox.style.display = 'none';
	videoChatWrapper.style.display = 'none';
};

const menuBtnBrowseCandidatesAction = () => {
	resetAllBox();
	browseCandidatesBox.style.display = 'flex';
	closeMobileMenu();
};
const menuBtnPreferencesAction = () => {
	resetAllBox();
	preferencesBox.style.display = 'flex';
	closeMobileMenu();
};
const menuBtnMessageAction = () => {
	resetAllBox();
	messageBox.style.display = 'flex';
	closeMobileMenu();
};
const menuBtnMatchesAction = () => {
	resetAllBox();
	matchesBox.style.display = 'flex';
	closeMobileMenu();
};
const menuBtnSettingsAction = () => {
	resetAllBox();
	profileSettingsBox.style.display = 'flex';
	closeMobileMenu();
};

const showMobileMenu = () => {
	mobileMenu.style.display = 'flex';
	panelWrapper.style.display = 'none';
};

const closeMobileMenu = () => {
	mobileMenu.style.display = 'none';
	panelWrapper.style.display = 'flex';
};

const showRecipientsInMobile = () => {
	rightMessageBox.style.display = 'none';
	leftMessageBox.style.display = 'flex';
	leftMessageBox.style['flex-grow'] = '1';
	leftMessageBox.style['max-width'] = '1000px';
};

// --------------------- Swipe.js ------------------------

const swiper = new Swiper('.swiper', {
	// Optional parameters
	direction: 'horizontal',
	loop: true,
	speed: 150,
	spaceBetween: 0,

	effect: 'coverflow',
	coverflowEffect: {
		rotate: 0,
		slideShadows: false,
	},

	grabCursor: true,
	slidesPerView: 1,
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

// -------------------------------------------- Get my credentials and userDescription ------------------------------------

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
		.then(() => connectWebSocket())
		.then(() => getUserPreferencesFromServer())
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

	// User description box
	descriptionName.value = myUserDescription.name;
	descriptionGender.value = myUserDescription.gender;
	descriptionAge.value = myUserDescription.age;
	descriptionAboutMe.value = myUserDescription.aboutMeDescription;
	descriptionSexualOrientation.value = myUserDescription.sexualOrientation;
	descriptionRelationshipStatus.value = myUserDescription.relationshipStatus;
	descriptionAmISmoking.value = myUserDescription.amISmoking;
	descriptionHaveChildren.value = myUserDescription.haveChildren;
	descriptionDoIDrinkAlcohol.value = myUserDescription.doIDrinkAlcohol;
	descriptionHowTallAmI.value = myUserDescription.howTallAmI;
	descriptionHowMuchDoIWeight.value = myUserDescription.howMuchDoIWeight;
	descriptionDoIPlaySports.value = myUserDescription.doIPlaySports;
	descriptionInterested.value = myUserDescription.interested;
	descriptionWork.value = myUserDescription.work;
	descriptionUniversity.value = myUserDescription.university;
	descriptionCity.value = myUserDescription.city;

	if(myUserDescription.pictures[0].bytes){
		descriptionMobileTopImg.src = myUserDescription.pictures[0].bytes;
	}

    // Sidebar user name and img
	sidebarUserName.innerHTML = `${myUserDescription.name}`;
	if(myUserDescription.pictures[0].bytes){
		sidebarUserImg.src = myUserDescription.pictures[0].bytes;
	}
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
				haveChildren: descriptionHaveChildren.value,
				amISmoking: descriptionAmISmoking.value,
				doIDrinkAlcohol: descriptionDoIDrinkAlcohol.value,
				howTallAmI: descriptionHowTallAmI.value,
				howMuchDoIWeight: descriptionHowMuchDoIWeight.value,
				doIPlaySports: descriptionDoIPlaySports.value,
				interested: descriptionInterested.value,
				work: descriptionWork.value,
				university: descriptionUniversity.value,
				city: descriptionCity.value,
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
	};

	reader.onerror = function (error) {
		console.log('Error: ', error);
	};

	reader.readAsDataURL(descriptionImageInput.files[0]);
};

// It allows you to send single img(base64) to server
const sendImgToServer = (imgInBase64) => {
	fetch('http://localhost:8080/panel/api/picture/update', {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		'Access-Control-Allow-Origin': '*',
		body: JSON.stringify({
			bytes: imgInBase64,
		}),
	}).then((res) => console.log(res));
};

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
		.then((response) => {viewedUser = response})
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

const setViewedUserInView = (viewedUser) => {
	swiper.removeAllSlides();

	viewedUser.pictures.forEach(function (picture, index) {
		let newDiv = document.createElement('div');
		let pictureAddedToSwiper = new Image();

		newDiv.classList.add('swiper-slide');
		pictureAddedToSwiper.classList.add('swiper-slide-img');
		pictureAddedToSwiper.src = picture.bytes;

		newDiv.appendChild(pictureAddedToSwiper);
		swiper.appendSlide(newDiv);
	});

	swiper.update();

	viewedName.textContent = viewedUser.name;
	viewedAge.textContent = viewedUser.age;
	viewedAboutMe.textContent = viewedUser.aboutMeDescription;
	viewedKilometersAway.textContent = viewedUser.kilometersAway + ' km';
	viewedCity.textContent = viewedUser.city;
	viewedUniversity.textContent = viewedUser.university;
	viewedWork.textContent = viewedUser.work;
	viewedHaveChildren.textContent = viewedUser.haveChildren;
	viewedAmISmoking.textContent = viewedUser.amISmoking;
	viewedDoIDrinkAlcohol.textContent = viewedUser.doIDrinkAlcohol;
	viewedHowTallAmI.textContent = viewedUser.howTallAmI + ' cm';
	viewedHowMuchDoIWeight.textContent = viewedUser.howMuchDoIWeight + ' kg';
	viewedDoIPlaySports.textContent = viewedUser.doIPlaySports;
	viewedSexualOrientation.textContent = viewedUser.sexualOrientation;
	viewedGender.textContent = viewedUser.relationshipStatus;
	viewedInterested.textContent = viewedUser.interested;

};

// ******************** Get Localisation **********************************

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

let client;
let recipientWebSocketClient;
let conversationWebSocketClient;

const recipientListBox = document.querySelector('.recipient-list-box');
const conversationList = document.querySelector('.conversation-list');
const inputMessageField = document.querySelector('.input-message-field');
const sendMessageBtn = document.querySelector('.send-btn');
let actualRecipientList;
let actualRecipient;
let actualRecipientName = '';

const searchRecipientInput = document.querySelector('.search-recipient-input');
const conversationTopNameP = document.querySelector('.conversation-top-name-p');
// const conversationInfoImg = document.querySelector('.conversation-info-img');

const sendMessage = () => {
	let today = new Date();
	let time =
		today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();

	let quote = {
		text: inputMessageField.value,
		username: myCredentials.email,
		name: actualRecipientName,
		time: time,
	};

	addRightMessageInPanelChatBox(quote);
	client.send('/app/chat', {}, JSON.stringify(quote));
	inputMessageField.value = ''; // clear input
	conversationList.scrollTop = conversationList.scrollHeight; // Scroll set bottom
};

// Allows you to send a request for an updated recipient list from the server
const sendUpdateRequestRecipientList = () => {
	// update after logged
	let quote = { username: myCredentials.email };
	recipientWebSocketClient.send('/app/recipients', {}, JSON.stringify(quote));

	// update periodically
	let updateMessageRecipientInterval = window.setInterval(function () {
		let quote = { username: myCredentials.email };
		recipientWebSocketClient.send('/app/recipients', {}, JSON.stringify(quote));
	}, 60000);
};

const connectWebSocket = () => {
	client = Stomp.client('ws://localhost:8080/chat'); //ws - information abut protocol, localhost.. - chat endpoint
	client.connect({ username: myCredentials.email }, function (frame) {
		client.subscribe('/users/queue/messages', function (message) {
			addLeftMessageInPanelChatBox(JSON.parse(message.body));
			conversationList.scrollTop = conversationList.scrollHeight; // Scroll set bottom
		});
	});

	recipientWebSocketClient = Stomp.client('ws://localhost:8080/recipients');
	recipientWebSocketClient.connect(
		{ username: myCredentials.email },
		function (frame) {
			client.subscribe('/users/queue/recipients', function (message) {
				showRecipientsInPanel(JSON.parse(message.body).recipientList);
				actualRecipientList = JSON.parse(message.body).recipientList;
			});

			sendUpdateRequestRecipientList();
		}
	);

	negotiateWebRTCConnectionByWebSocket();

	conversationWebSocketClient = Stomp.client(
		'ws://localhost:8080/conversation'
	);
	conversationWebSocketClient.connect(
		{ username: myCredentials.email },
		function (frame) {
			client.subscribe('/users/queue/conversation', function (message) {
				setUpConversationInPanel(JSON.parse(message.body));
			});
		}
	);
};

// It allows you to set up a conversation with the selected recipient in the panel
const setUpConversationInPanel = (conversation) => {
	conversationList.innerHTML = ''; // clear conversations
	conversation.chatMessageList.forEach(function (recipient) {
		// add left message
		if (recipient.username === actualRecipientName) {
			addLeftMessageInPanelChatBox(recipient);
		}

		// add right message
		if (recipient.username === myCredentials.email) {
			addRightMessageInPanelChatBox(recipient);
		}
	});

	inputMessageField.value = ''; // clear input
	conversationList.scrollTop = conversationList.scrollHeight; // Scroll set bottom
};

const addLeftMessageInPanelChatBox = (message) => {
	let li = document.createElement('li');
	li.classList.add('conversation-li-left');
	li.innerHTML = `<img class="conversation-message-img" src="${actualRecipient.profileImg.bytes}" alt="">
                                    <div>
                                        <p class="d">${message.text}</p>
                                        <p class="c">${message.time}</p>
                                    </div>`;
	conversationList.appendChild(li);
};

const addRightMessageInPanelChatBox = (message) => {
	let li = document.createElement('li');
	li.classList.add('conversation-li-right');
	li.innerHTML = `<div>
                       <p class="g">${message.text}</p>
                    </div>
                    <p class="l">${message.time}</p>`;
	conversationList.appendChild(li);
};

// Allows you to display recipients in the user panel
// example item object
// ({
// active: false,
// email: 'marcin3246a51@o2.pl',
// lastMessage: null,
// nick: 'marcin3246',
// profileImg: Object { bytes: 'imgInBase64'}
// })
const showRecipientsInPanel = (recipients) => {
	recipientListBox.innerHTML = '';
	recipients.forEach(function (item) {
		let newLi = document.createElement('li');
		newLi.innerHTML = `<li id="${item.email}" class="recipient-box">
                            <div class="recipient-status"></div>
                            <img class="recipient-img" src="${item.profileImg.bytes}" alt="">
                            <div class="recipient-box-center">
                                <p class="recipient-nick">${item.nick}</p>
                                <p class="recipient-last-message">${item.lastMessage.text}</p>
                            </div>
                            <p class="recipient-last-message-time">${item.lastMessage.time}</p>
                        </li>`;
		newLi.classList.add('recipient-box');
		recipientListBox.appendChild(newLi);
	});
};

// It allows you display recipient in 'preview panel'
const showRecipientInPreviewPanel = (actualRecipient) => {

	previewImg.src = actualRecipient.profileImg.bytes;
	previewName.textContent = actualRecipient.name + ' ';
	previewAge.textContent = actualRecipient.age;
	previewCity.textContent = actualRecipient.city;
	previewWork.textContent = actualRecipient.work;
	previewUniversity.textContent = actualRecipient.university;

	// previewKilometersAway.textContent = actualRecipient.kilometersAway + ' km';

}

const setActualRecipientConversation = (event) => {
	actualRecipientName = event.target.closest('.recipient-box').id;
	getActualChosenRecipientByEmail(actualRecipientList, actualRecipientName);
	conversationTopNameP.textContent = actualRecipient.nick;
	// conversationInfoImg.src = actualRecipient.profileImg.bytes;
	showRecipientInPreviewPanel(actualRecipient);
	let quote = {
		ownerEmail: myCredentials.email,
		recipientEmail: actualRecipientName,
	};
	recipientWebSocketClient.send('/app/conversation', {}, JSON.stringify(quote));

	if (window.innerWidth < 800) {
		rightMessageBox.style.display = 'flex';
		leftMessageBox.style.display = 'none';
	}
};

const getActualChosenRecipientByEmail = (recipientList, email) => {
	actualRecipientList.forEach(function (item) {
		if (item.email === email) {
			actualRecipient = item;
		}
	});
};

// redirect and logout
const logout = () => {
	window.location.href = 'http://localhost:8080/logout';
};

// Search recipient
const searchRecipient = (e) => {
	let text = e.target.value.toLowerCase().trim();

	for (const iterator of recipientListBox.children) {
		if (iterator.textContent.toLowerCase().indexOf(text) !== -1) {
			iterator.style.display = 'block';
		} else {
			iterator.style.display = 'none';
		}
	}
};

sendMessageBtn.addEventListener('click', sendMessage);
inputMessageField.addEventListener('keyup', function (event) {
	if (event.keyCode === 13) {
		sendMessage();
	}
});
recipientListBox.addEventListener('click', setActualRecipientConversation);
searchRecipientInput.addEventListener('keyup', searchRecipient);



// ----------------------------------------------------- Video Talker Section -----------------------------------------------------


const negotiateWebRTCConnectionByWebSocket = () => {

	webSocketClient = Stomp.client('ws://localhost:8080/videochat')
	webSocketClient.connect({username: myCredentials.email,}, function (frame){

		webSocketClient.subscribe('/users/queue/videochat', function(message){

			let rtc = JSON.parse(message.body).rtc;

			switch (rtc.type) {
				case 'offer':
					if(localStream){
						handleOffer(rtc);
					}
					break;
				case 'answer':
					if(localStream){
						handleAnswer(rtc);
					}
					break;
				case 'candidate':
					if(localStream) {
						handleCandidate(rtc);
					}
					break;
				case 'ready':
					// A second tab joined. This tab will initiate a call unless in a call already.
					if (pc) {
						console.log('already in call, ignoring');
						return;
					}
					panelWrapper.style.display = 'none';
					videoChatWrapper.style.display = 'none';
					incomingCallWrapper.style.display = 'flex';
					break;
				case 'bye':
					if (pc) {
						hangup();
					}
					break;
				default:
					console.log('unhandled', rtc);
					break;
			}
		})
	})
}



// Default WebSocket send method
const postMessage = (message) => {
	console.log('GGGGGGGGGGGGGGGGGGGGGGG from:' + myCredentials.email + '. to: ' + actualRecipient.email);
	let quote = {'from' : myCredentials.email, 'to' : actualRecipient.email, 'content': 'negotiate webRTC connect', 'rtc': message};
	webSocketClient.send("/app/videochat", {}, JSON.stringify(quote));
}

async function acceptOrRejectIncomingCall() {
	localStream = await navigator.mediaDevices.getUserMedia({
		audio: true,
		video: true,
	});

	if(localStream){
		videoChatWrapper.style.display = 'flex';
		localVideo.srcObject = localStream;
		await makeCall();
	}else {
		// back to menu
		console.log('you must share the video before you can start the conversation');
	}
}

async function startVideoTalkerApplication()  {
	localStream = await navigator.mediaDevices.getUserMedia({
		audio: true,
		video: true,
	});
	localVideo.srcObject = localStream;
	startButton.disabled = true;
	hangupButton.disabled = false;
	postMessage({type: 'ready'});
};

// startButton.onclick = async () => {
// 	localStream = await navigator.mediaDevices.getUserMedia({
// 		audio: true,
// 		video: true,
// 	});
// 	localVideo.srcObject = localStream;
// 	startButton.disabled = true;
// 	hangupButton.disabled = false;
// 	postMessage({type: 'ready'});
// };

// hangupButton.onclick = async () => {
// 	hangup();
// 	postMessage({type: 'bye'});
// };

// Checking message is correct and calls the method responsible for send and display in chat panel this message.
const sendAndShowMyMessageToOtherUser = () => {
	const message = sendMessageInputCHATROOM.value;
	if (isEmptyOrSpaces(message)) {
		sendMessageInputCHATROOM.value = '';
		return;
	}
	dataChannel.send(message); // send message via WebRTC data channel
	addNewRightMessageInChatPanel(message);
	sendMessageInputCHATROOM.value = ''; // clear message input
}

async function hangup() {
	if (pc) {
		pc.close();
		pc = null;
	}

	if(localStream){
		localStream.getTracks().forEach(track => track.stop());
		localStream = null;
	}

	localVideo.pause();
	localVideo.removeAttribute('src');
	localVideo.load();

	remoteVideo.pause();
	remoteVideo.removeAttribute('src');
	remoteVideo.load();

	startButton.disabled = false;
	hangupButton.disabled = true;

	videoChatWrapper.style.display = 'none';
	panelWrapper.style.display = 'flex';

};

function createPeerConnection() {
	pc = new RTCPeerConnection();

	dataChannel = pc.createDataChannel("chat"); // create WebRTC data channel

	pc.ondatachannel = function (event) {
		dataChannel = event.channel;
	};

	// listen message on data channel, (webRTC data channel)
	dataChannel.onmessage = function(event) {
		addNewLeftMessageInChatPanel(event.data);
	};

	pc.onicecandidate = e => {
		const message = {
			type: 'candidate',
			candidate: null,
		};
		if (e.candidate) {
			message.candidate = e.candidate.candidate;
			message.sdpMid = e.candidate.sdpMid;
			message.sdpMLineIndex = e.candidate.sdpMLineIndex;
		}
		postMessage(message);
	};

	pc.ontrack = e => remoteVideo.srcObject = e.streams[0];
	localStream.getTracks().forEach(track => pc.addTrack(track, localStream));

}

async function makeCall() {
	await createPeerConnection();

	const offer = await pc.createOffer();
	postMessage({type: 'offer', sdp: offer.sdp});
	await pc.setLocalDescription(offer);

	incomingCallWrapper.style.display = 'none';
}

async function handleOffer(offer) {
	if (pc) {
		console.error('existing peer connection');
		return;
	}
	await createPeerConnection();
	await pc.setRemoteDescription(offer);

	const answer = await pc.createAnswer();
	postMessage({type: 'answer', sdp: answer.sdp});
	await pc.setLocalDescription(answer);
}

async function handleAnswer(answer) {
	if (!pc) {
		console.error('no peer connection');
		return;
	}
	await pc.setRemoteDescription(answer);
}

async function handleCandidate(candidate) {
	if (!pc) {
		console.error('no peer connection');
		return;
	}
	if (!candidate.candidate) {
		await pc.addIceCandidate(null);
	} else {
		await pc.addIceCandidate(candidate);
	}
}

async function sendFile() {

}

// ------ Show, close chat panel ------

const closeChat = () => {
	closeChatButton.style.display = 'none';
	showChatButton.style.display = 'flex';
	document.querySelector('.chat-room').style.display = 'none';
}

const showChat = () => {
	showChatButton.style.display = 'none';
	closeChatButton.style.display = 'flex';
	document.querySelector('.chat-room').style.display = 'flex';
}


// ------ Add messages in chat panel ------

// checks if the message is sent for the first time since session opening
let leftTimeKey = true;
let startTimeBetweenReceivedMessages;
let stopTimeBetweenReceivedMessages;

// This method allows you to display messages in the chat panel, it is intended for incoming messages
const addNewLeftMessageInChatPanel = (message) => {
	let time = 0; // time between received messages

	if(leftTimeKey === true){
		startTimeBetweenReceivedMessages = new Date().getTime();
		leftTimeKey = false;
	}else{
		stopTimeBetweenReceivedMessages = new Date().getTime();
		time = stopTimeBetweenReceivedMessages - startTimeBetweenReceivedMessages;
	}

	if(time <= tostbstm){
		const li = document.createElement('li');
		li.classList.add('leftMessagLiCHATROOM');
		li.innerHTML = `<li class="leftMessagLiCHATROOM">
                        <div class="leftMessagAdditionalBox">
                            <img class="userImgMessageCHATROOM" src="" alt="">
                            <p class="leftMessageContentCHATROOM">
                                ${message}
                            </p>
                        </div>
                    </li>`
		messageListCHATROOM.appendChild(li);

	}else {
		const li = document.createElement('li');
		li.classList.add('leftMessagLiCHATROOM');
		li.innerHTML = `<li class="leftMessagLiCHATROOM">
                        <p class="message-time">${getDateTime()}</p>
                        <div class="leftMessagAdditionalBox margin15">
                            <img class="userImgMessageCHATROOM" src="" alt="">
                            <p class="leftMessageContentCHATROOM">
                                ${message}
                            </p>
                        </div>
                    </li>`
		messageListCHATROOM.appendChild(li);
		startTimeBetweenReceivedMessages = new Date().getTime();
	}

	setScrollToBottomForMessageChatRoom();
}

// checks if the message is sent for the first time since session opening
let rightTimeKey = true;
let startTimeBetweenSendMessages;
let stopTimeBetweenSendMessages;

// This method allows you to display messages in the chat panel, it is intended for sending messages
const addNewRightMessageInChatPanel = (message) => {
	let time = 0; // time between sent messages

	if (rightTimeKey === true) {
		startTimeBetweenSendMessages = new Date().getTime();
		rightTimeKey = false;
	} else {
		stopTimeBetweenSendMessages = new Date().getTime();
		time = stopTimeBetweenSendMessages - startTimeBetweenSendMessages;
	}

	if (time <= tostbstm) {
		const li = document.createElement('li');
		li.classList.add('rightMessagLiCHATROOM');
		li.innerHTML = `<p class="rightMessageContentCHATROOM">${message}</p>`;
		messageListCHATROOM.appendChild(li);
	} else {
		const li = document.createElement('li');
		li.classList.add('rightMessagLiCHATROOM');
		li.innerHTML = `<p class="message-time">${getDateTime()}</p>
        <p class="rightMessageContentCHATROOM margin15">
            ${message}
        </p>`;

		messageListCHATROOM.appendChild(li);
		startTimeBetweenSendMessages = new Date().getTime();
	}

	setScrollToBottomForMessageChatRoom();
};


// *** auxiliary functions ***
const isEmptyOrSpaces = (str) => {
	return str === null || str.match(/^ *$/) !== null;
};

const setScrollToBottomForMessageChatRoom = () => {
	document.querySelector('.chat-room-message-box').scrollTop =
		document.querySelector('.chat-room-message-box').scrollHeight;
};

const getDateTime = () => {
	let today = new Date();
	let date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	let dateTime = date+' '+time;
	return dateTime;
}


// Start, DOMContentLoaded
document.addEventListener('DOMContentLoaded', mainFunction);
