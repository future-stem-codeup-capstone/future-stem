
const client = filestack.init('AQ87ePcflS1GG7yCbmaMoz');
const options = {
    onUploadDone: updateForm,
    accept: ["image/*"],
};

const picker = client.picker(options);

// const client = filestack.init('AQ87ePcflS1GG7yCbmaMoz');
// const options = {
//     onUploadDone: updateForm,
//     maxSize: 10 * 1024 * 1024,
//     accept: 'image/*',
//     uploadInBackground: false,
// };
// const picker = client.picker(options);

// references to the DOM elements
const form = document.getElementById('pick-form');
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');

// our event listeners
btn.addEventListener('click', function (event) {
    event.preventDefault();
    picker.open();
});

form.addEventListener('submit', function (event) {
    event.preventDefault();
    alert('Submitting: ' + fileInput.value);
});
// overwrite the field input value

// function updateForm(result) {
//     const fileData = result.filesUploaded[0];
//     fileInput.value = fileData.url;

//     DOM code to show some data.
//     const name = document.createTextNode('Selected: ' + fileData.filename);
//     const url = document.createElement('a');
//     url.href = fileData.url;
//     url.appendChild(document.createTextNode(fileData.url));
//     nameBox.appendChild(name);
//     urlBox.appendChild(document.createTextNode('Uploaded to: '));
//     urlBox.appendChild(url);
function updateForm (result) {
    const fileData = result.filesUploaded[0];
    fileInput.value = fileData.url;

    // thumbnail preview
    if (['jpeg', 'png'].indexOf(fileData.mimetype.split('/')[1]) !== -1) {
        const container = document.getElementById('thumbnail-container');
        const thumbnail = document.getElementById('thumbnail') || new Image();
        thumbnail.id = 'thumbnail';
        thumbnail.src = client.transform(fileData.handle, {
            resize: {
                width: 200
            }
        });

        if (!container.contains(thumbnail)) {
            container.appendChild(thumbnail);
        }
    }
}
