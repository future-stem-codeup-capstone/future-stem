// // FileStack code for only uploading images
// const client = filestack.init('AQ87ePcflS1GG7yCbmaMoz');
// const options = {
//     accept: ["image/*"],
// };
//
// client.picker(options).open();


// window.addEventListener('DOMContentLoaded', function () {
//     const apikey = 'AQ87ePcflS1GG7yCbmaMoz';
//     const client = filestack.init(apikey);
//     const options = {
//         maxFiles: 20,
//         uploadInBackground: false,
//         onOpen: () => console.log('opened!'),
//         onUploadDone: (res) => console.log(res),
//     };
//     client.picker(options).open();
// });


// initialization with Options (only image, max size: 1024, max files: 2):
// const config = {
//     accept: ['image/*'], // default empty array - all files
//     maxSize: 1024, // default 0 - no limit
//     maxFiles: 2, // default 0 - no limit
//     failOverMaxFiles: false,
// }
//
// const filestackDnD = new filestackDnD.init('API_KEY', document.querySelector('.drop-container'), config);

// references to the DOM elements
const form = document.getElementById('upload');
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');

const getUserId = () => {
	return new Promise((resolve) => {
		resolve({
			userId: 1
		})
	});
};

//  function for saving user data
const saveUserData = (data) => {
	return new Promise((resolve) => {
		console.log(data);
		resolve({
			success: true
		})
	});
};
window.addEventListener('DOMContentLoaded', function () {
	getUserId().then((response) => {
		const userId = response.userId;
		const apikey = 'AQ87ePcflS1GG7yCbmaMoz';
		const client = filestack.init(apikey);
		const options = {
			onUploadDone: updateForm,
			accept: ["image/*"],
			uploadInBackground: false,
			onFileUploadFinished: (response) => {
				saveUserData({
					userId,
					fileHandle: response.handle,
					url: response.url
				}).then((response) => {
					console.log('User data has been saved', response);
					form.addEventListener('submit', function (event) {
						
						$('#fileupload').val(response.url);
						event.preventDefault();
						alert('Submitting: ' + fileInput.value);
					});
				})
			}
		};
		const picker = client.picker(options);
// const client = filestack.init('AQ87ePcflS1GG7yCbmaMoz');
// const options = {
//     onUploadDone: updateForm,
//     accept: ["image/*"],
// };
//
// const picker = client.picker(options);

// const client = filestack.init('AQ87ePcflS1GG7yCbmaMoz');
// const options = {
//     onUploadDone: updateForm,
//     maxSize: 10 * 1024 * 1024,
//     accept: 'image/*',
//     uploadInBackground: false,
// };
// const picker = client.picker(options);


// our event listeners
		btn.addEventListener('click', function (event) {
			event.preventDefault();
			picker.open();
		});

// overwrite the field input value
		function updateForm(result) {
			const fileData = result.filesUploaded[0];
			fileInput.value = fileData.url;

//     DOM code to show some data.
			const name = document.createTextNode('Selected: ' + fileData.filename);
			const url = document.createElement('a');
			url.href = fileData.url;
			url.appendChild(document.createTextNode(fileData.url));
			nameBox.appendChild(name);
			urlBox.appendChild(document.createTextNode('Uploaded to: '));
			urlBox.appendChild(url);
		}
	});
});
//update form and thumbnail preview lines below

// function updateForm(result) {
//     const fileData = result.filesUploaded[0];
//     fileInput.value = fileData.url;

// thumbnail preview
//             if (['jpeg', 'png'].indexOf(fileData.mimetype.split('/')[1]) !== -1) {
//                 const container = document.getElementById('thumbnail-container');
//                 const thumbnail = document.getElementById('thumbnail') || new Image();
//                 thumbnail.id = 'thumbnail';
//                 thumbnail.src = client.transform(fileData.handle, {
//                     resize: {
//                         width: 200
//                     }
//                 });
//
//                 if (!container.contains(thumbnail)) {
//                     container.appendChild(thumbnail);
//                 }
//             }
//         }
//     });
// });
