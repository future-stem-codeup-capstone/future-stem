// references to the DOM elements
const form = document.getElementById('upload');
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');
const quizContainer = document.getElementById('quiz');
const resultsContainer = document.getElementById('results');
const submitQuiz = document.getElementById('submit');

// const category = 'sciencenature';
//
// fetch('https://api.api-ninjas.com/v1/trivia?category=' + category, {
// 	method: 'GET',
// 	headers: { 'X-Api-Key': 'cIeQtdVErFokcXlopISSOA==kOfToXDSRPOshWVp'},
// 	contentType: 'application/json'})
// 	.then(function (response) {
// 		return response.json();
// 	})
// 	.then(function (data) {
// 		console.log(data);
// 		let question = data[0].question;
// 		let answer = data[0].answer;
// 		console.log(question)
// 		console.log(answer)
// 		$('.question').append(question);
// 		$('.answer').append(answer);
//
// 	})



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

const scienceQuestions = [
	{
		question: 'What is the name of the molten rock that pours from volcanoes?',
		answers: {
			a: 'Lava',
			b: 'Fire',
			c: 'Sand'
		},
		correctAnswer: 'a'
	},
	{
		question: 'Which is not owned or created by Microsoft:',
		answers: {
			a: 'Minecraft',
			b: 'Xbox',
			c: 'Fortnite'
		},
		correctAnswer: 'c'
	},
	{
		question: 'The human body is what percentage of water?',
		answers: {
			a: '25%',
			b: '60%',
			c: '2%'
		},
		correctAnswer: 'b'
	},
	{
		question: 'What kind of animals can live on both water and land?',
		answers: {
			a: '45%',
			b: '60%',
			c: '2%'
		},
		correctAnswer: 'b'
	},
	{
		question: 'At what temperature does water freeze?',
		answers: {
			a: '0 degrees Fahrenheit',
			b: '20 degrees Fahrenheit',
			c: '32 degrees Fahrenheit'
		},
		correctAnswer: 'c'
	}
]



function buildQuiz() {
	const output = [];
	
	scienceQuestions.forEach((currentQuestion, questionNumber) => {
		const answers = [];
		
		// let label = `<label>
        //     <input type="radio" name="question${questionNumber}" value="${letter}">
        //     ${letter} :
        //     ${currentQuestion.answers[letter]}
        //   </label>`;
		
		for (let letter in currentQuestion.answers) {
			answers.push(`<label>
            <input type="radio" name="question${questionNumber}" value="${letter}">
            ${letter} :
            ${currentQuestion.answers[letter]}
          </label>`);
		}
		
		// let output = `<div class="question"> ${currentQuestion.question} </div>
        // <div class="answers"> ${answers.join('')} </div>`;
		
		output.push(`<div class="question"> ${currentQuestion.question} </div>
        <div class="answers"> ${answers.join('')} </div>`);
	})
	quizContainer.innerHTML = output.join('');
}
buildQuiz();

function showResults() {

}

submitQuiz.addEventListener('click', showResults);





