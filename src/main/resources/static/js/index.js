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
			// maxSize: 1024,
			// maxFiles: 4,
			// failOverMaxFiles: false,
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

// edit account button
$('.edit-account-btn').click(function () {
	$('.update-account-form').toggleClass('hidden')
})



