const sci = "sciencenature";
const geo = "geography";
const math = "mathematics";
const gen = "general";
const lang = "language";

const searchBar = document.getElementById("search-Dictionary");

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



function fetchAPIDictionary(word) {
    fetch('https://api.api-ninjas.com/v1/dictionary?word=' + word, {
        method: 'GET',
        headers: {'X-Api-Key': 'cIeQtdVErFokcXlopISSOA==kOfToXDSRPOshWVp'},
        contentType: 'application/json'
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            // let definition = data[0].definition;
            // console.log(data)
            $('.definition').text(data.definition);
        })
}
$('#searchDictionarybtn').click((e) => {
    e.preventDefault();
    console.log(document.querySelector('#search-Dictionary').value)
    fetchAPIDictionary(document.querySelector('#search-Dictionary').value)
})

searchBar.addEventListener("submit", e => {
    fetchAPIDictionary($('#search-Dictionary').value)
});

