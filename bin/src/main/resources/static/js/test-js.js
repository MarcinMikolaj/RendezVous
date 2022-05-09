const savePictureBtn = document.querySelector('.save-picture-btn');
const imgBox = document.querySelector('.img-box');
const fileInput = document.querySelector('.file-input');
let imgInBase64;

const getPictureBtn = document.querySelector('.get-picture-btn');
let getPictureImg = document.querySelector('.get-picture-img');

let actualImg;

const toBase64 = (file) => {
    let reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = function () {
        // console.log(reader.result);
        imgInBase64 = reader.result;
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };
}

const sendPictureToServer = () => {

    // console.log('Picture send to server');
    // // console.log(fileInput.files[0]);

    const result = toBase64(fileInput.files[0]);

    // console.log(`result ${result}`);

    if(imgInBase64){
        const response = fetch("http://localhost:8080/panel/api/picture/update",{
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'},
            'Access-Control-Allow-Origin': "*",
            body: JSON.stringify({
                "bytes": imgInBase64,
            }),
        })
            .then(res => console.log(res))
    }

}

const getPictureFromServer = () => {

    fetch('http://localhost:8080/panel/api/picture/send', {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then((response) => response.json())
        .then((response) => {
            actualImg = response
        })
        .then((response) => setImg(response))
        .catch(error => console.log("err: ", error));
}

const setImg = (base64) => {

    // let image = new Image();
    // image.src = base64;
    // document.body.appendChild(image);

    console.log(getPictureImg);
    console.log(actualImg);

    // getPictureImg = new Image();
    getPictureImg.src = actualImg.bytes;
}

savePictureBtn.addEventListener('click', sendPictureToServer);
getPictureBtn.addEventListener('click', getPictureFromServer);