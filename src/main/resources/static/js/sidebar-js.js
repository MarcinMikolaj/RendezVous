let sidebar; // Sidebar (this is main warpper )
let sidebarBox; // Sidebar boxes
let sidebarBoxSpanList; // All span elements (in sidebarBox)
let sidebarBoxIcones; // All i elements (in sidebarBox)

let sidebarSearchInput; // Search Input
let sidebarChangeColorInput; // Change Color button

// minimalize and maxymalize sidebaer buttons
let minimizeSidebarButton;
let maximizeSidebarButton;

// other elements
let sidebarLogo;

// Change color
let sidebarChangeColorBox;
let changeColorSunIcone;
let changeColorMoonIcone;
let lightDarkModeText;

let isMinimalize = false;
key = false; // checks in what color the sidebar should be displayed

// Colors
// Specyfi, replace sidebars light mode and dark mode colors below
// example:
// light mode
// let sidebarBackroundColorFirstMode = '#f3e6e8';
// let sidebarBackroundImageFirstMode =
// 	'linear-gradient(315deg, #f3e6e8 0%, #d5d0e5 74%)';
// let sidebarColorFirstMode = 'black';
// let firstModeTextColor = 'white';
// let firstModeLogoColor = 'white';
// let minimizeSidebarButtonFirstModeColor = 'white';
// let maximizeSidebarButtonFirstModeColor = 'white';

// pink mode
let sidebarBackroundColorFirstMode = '#726cf8';
let sidebarBackroundImageFirstMode =
    'linear-gradient(315deg, #726cf8 0%, #e975a8 74%)';
let sidebarColorFirstMode = 'black';
let firstModeTextColor = 'white';
let firstModeLogoColor = 'white';
let minimizeSidebarButtonFirstModeColor = 'white';
let maximizeSidebarButtonFirstModeColor = 'white';

// dark mode
let sidebarBackroundColorSecondMode = 'rgb(27, 27, 27)';
let sidebarBackroundImageSecondMode = 'none';
let sidebarColorSecondMode = 'rgb(235, 235, 235)';
let secondModeTextColor = 'white';
let secondModeLogoColor = 'white';
let minimizeSidebarButtonSecondModeColor = 'white';
let maximizeSidebarButtonSecondModeColor = 'white';

// Prepare Elements
const prepareDOMElements = () => {
    // sidebar and sidebar box
    sidebar = document.querySelector('.sidebar');
    sidebarBox = document.querySelectorAll('.sidebar-box'); // all boxes
    sidebarBoxSpanList = document.querySelectorAll('.sidebar-box span');
    sidebarBoxIcones = document.querySelectorAll('.sidebar-box i');
    sidebarSearchInput = document.querySelector('.sidebar-search-input');
    sidebarChangeColorInput = document.querySelector(
        '.sidebar-change-color-input'
    );

    // minimalize and maxymalize sidebaer buttons
    minimizeSidebarButton = document.querySelector('.minimize-sidebar-button');
    maximizeSidebarButton = document.querySelector('.maximize-sidebar-button');

    //Change colors icone
    sidebarChangeColorBox = document.querySelector('.sidebar-change-color-box');
    changeColorSunIcone = document.querySelector('.change-color-sun-icone');
    changeColorMoonIcone = document.querySelector('.change-color-moon-icone');
    lightDarkModeText = document.querySelector('.light-dark-mode-text');

    // other elements
    sidebarLogo = document.querySelector('.sidebar-logo');
};

// Prepare Events
const prepareDOMEvents = () => {
    sidebarSearchInput.addEventListener('keyup', searchButtons);
    sidebarChangeColorInput.addEventListener('click', changeColor);
    minimizeSidebarButton.addEventListener('click', minimizeSidebar);
    maximizeSidebarButton.addEventListener('click', maxymalizeSidebar);
};

// Main function
const main = () => {
    prepareDOMElements();
    prepareDOMEvents();
};

// Search buttons in buttons list.
// The buttons are in the list.
const searchButtons = (event) => {
    let text = event.target.value.toLowerCase().trim();
    let buttonsList = sidebar.querySelector('.sidebar-buttons-list');

    for (const iterator of buttonsList.children) {
        if (
            iterator.querySelector('span').innerHTML.toLowerCase().indexOf(text) !==
            -1
        ) {
            iterator.style.display = 'flex';
        } else {
            iterator.style.display = 'none';
        }
    }
};

// Change color after 'color switch' click
const changeColor = (event) => {
    // when click switch color
    if (key === false) {
        sidebar.style.backgroundImage = sidebarBackroundImageFirstMode;
        sidebar.style.backgroundColor = sidebarBackroundColorFirstMode;
        sidebar.style.color = sidebarColorFirstMode;
        sidebarLogo.style.color = firstModeLogoColor;
        minimizeSidebarButton.style.color = minimizeSidebarButtonFirstModeColor;
        maximizeSidebarButton.style.color = maximizeSidebarButtonFirstModeColor;

        if (isMinimalize === false) {
            changeColorSunIcone.style.display = 'block';
            changeColorMoonIcone.style.display = 'none';
        }
        lightDarkModeText.textContent = 'Light mode';

        sidebarBox.forEach(function (item) {
            item.style.color = secondModeTextColor;
        });

        sidebarBox.forEach(function (item) {
            item.addEventListener('mouseover', function (event) {
                item.style.color = firstModeTextColor;
            });
        });

        sidebarBox.forEach(function (item) {
            item.addEventListener('mouseout', function (event) {
                item.style.color = secondModeTextColor;
            });
        });

        key = true;
        //default switch position
    } else {
        sidebar.style.backgroundImage = sidebarBackroundImageSecondMode;
        sidebar.style.backgroundColor = sidebarBackroundColorSecondMode;
        sidebar.style.color = sidebarColorSecondMode;
        sidebarLogo.style.color = secondModeLogoColor;
        minimizeSidebarButton.style.color = minimizeSidebarButtonSecondModeColor;
        maximizeSidebarButton.style.color = maximizeSidebarButtonSecondModeColor;

        if (isMinimalize === false) {
            changeColorSunIcone.style.display = 'none';
            changeColorMoonIcone.style.display = 'block';
        }
        lightDarkModeText.textContent = 'Dark mode';

        sidebarBox.forEach(function (item) {
            item.style.color = firstModeTextColor;
        });

        sidebarBox.forEach(function (item) {
            e2 = item.addEventListener('mouseout', function (event) {
                item.style.color = firstModeTextColor;
            });
        });

        key = false;
    }
};

// Call when you want to shrink the menu.
const minimizeSidebar = () => {
    isMinimalize = true;

    minimizeSidebarButton.style.display = 'none';
    maximizeSidebarButton.style.display = 'block';
    sidebarSearchInput.style.display = 'none';
    sidebarLogo.style.display = 'none';
    sidebarChangeColorBox.querySelector('.switch').style.marginLeft = '0px';

    // sidebarChangeColorBox.querySelector('i').style.display = 'none';
    changeColorSunIcone.style.display = 'none';
    changeColorMoonIcone.style.display = 'none';

    sidebar.style.width = '120px';
    sidebar.style.minWidth = '120px';
    sidebar.querySelector('.sidebar-top-section').style.justifyContent = 'center';

    sidebarBoxIcones.forEach(function (item) {
        item.style.marginRight = '0px';
    });

    sidebarBox.forEach(function (item) {
        item.style.justifyContent = 'center';
    });

    sidebarBoxSpanList.forEach(function (item) {
        item.style.display = 'none';
    });
};

// Call when you want to grow the menu.
const maxymalizeSidebar = () => {
    isMinimalize = false;

    maximizeSidebarButton.style.display = 'none';
    minimizeSidebarButton.style.display = 'block';
    sidebarSearchInput.style.display = 'block';
    sidebarLogo.style.display = 'flex';
    sidebarChangeColorBox.querySelector('.switch').style.marginLeft = '40px';

    // sidebarChangeColorBox.querySelector('i').style.display = 'block';
    if (key == true) changeColorSunIcone.style.display = 'block';
    else changeColorMoonIcone.style.display = 'block';

    sidebar.querySelector('.sidebar-top-section').style.justifyContent =
        'space-between';

    sidebar.style.width = '280px';
    sidebar.style.minWidth = '280px';

    sidebarBoxIcones.forEach(function (item) {
        item.style.marginRight = '15px';
    });

    sidebarBox.forEach(function (item) {
        item.style.justifyContent = 'flex-start';
    });

    sidebarBoxSpanList.forEach(function (item) {
        item.style.display = 'block';
    });
};

document.addEventListener('DOMContentLoaded', main);