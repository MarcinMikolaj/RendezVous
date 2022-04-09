let signInBtn;
let registerBtn;

const prepareDOMElements = () => {
    signInBtn = document.querySelector('.sign-in-btn');
    registerBtn = document.querySelector('.register-btn');
}
const prepareDOMEvents = () => {
    signInBtn.addEventListener('click', redirectToPanelSite);
    registerBtn.addEventListener('click', redirectToRegisterSite);
}
const main = () => {
    prepareDOMElements();
    prepareDOMEvents();
}

// Redirect to panel after authentication
const redirectToPanelSite = () => {
    window.location.href="http://localhost:8080/panel";
}

// Redirect to register form
const redirectToRegisterSite = () => {
    window.location.href="http://localhost:8080/register";
}

document.addEventListener('DOMContentLoaded', main);

