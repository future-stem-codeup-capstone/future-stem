const sci = "sciencenature";
const geo = "geography";
const math = "mathematics";
const gen = "general";
const lang = "language";

function fetchAPI(category) {

    fetch('https://api.api-ninjas.com/v1/trivia?category=' + category, {
        method: 'GET',
        headers: {'X-Api-Key': 'cIeQtdVErFokcXlopISSOA==kOfToXDSRPOshWVp'},
        contentType: 'application/json'
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            let question = data[0].question;
            let answer = data[0].answer;
            console.log(question)
            console.log(answer)
            $('.question').text(question);
            $('.answer').text(answer);
        })
}

$('.sciButton').click((e) => {
    e.preventDefault();
    fetchAPI(sci)
})
$('.geoButton').click((e) => {
    e.preventDefault();
    fetchAPI(geo)
})
$('.mathButton').click((e) => {
    e.preventDefault();
    fetchAPI(math)
})
$('.genButton').click((e) => {
    e.preventDefault();
    fetchAPI(gen)
})
$('.langButton').click((e) => {
    e.preventDefault();
    fetchAPI(lang)

})
fetchAPI()