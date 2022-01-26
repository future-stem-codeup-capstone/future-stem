mapboxgl.accessToken = 'pk.eyJ1IjoiamFja2llZGFsbGFzIiwiYSI6ImNreXc0eGI3ZTAzODYyeG1zbnhyc21wOXYifQ.bl5vqXwUq_SIoY62-Bscvw';
const map = new mapboxgl.Map({
	container: 'map', // container id
	style: 'mapbox://styles/mapbox/streets-v11', // style URL
	center: [-98.4861, 29.4252], // starting position [lng, lat]
	zoom: 9
})