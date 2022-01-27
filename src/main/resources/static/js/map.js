mapboxgl.accessToken = 'pk.eyJ1IjoiamFja2llZGFsbGFzIiwiYSI6ImNreXc0eGI3ZTAzODYyeG1zbnhyc21wOXYifQ.bl5vqXwUq_SIoY62-Bscvw';
const mapboxKey = 'pk.eyJ1IjoiamFja2llZGFsbGFzIiwiYSI6ImNreXc0eGI3ZTAzODYyeG1zbnhyc21wOXYifQ.bl5vqXwUq_SIoY62-Bscvw';
const arcGisKey = 'AAPKc6f35ea946a144539423779db38681abVoLuBW1wawtaCGQDc5IW7bvp-Zvckhhb5dZoncVuR9_QSXG7LEQLdCb8qipLOT_F';
const basemapEnum = 'ArcGIS:Navigation'
const map = new mapboxgl.Map({
	container: 'map', // container id
	style: `https://basemaps-api.arcgis.com/arcgis/rest/services/styles/${basemapEnum}?type=style&token=${arcGisKey}`, // style URL
	center: [-98.4861, 29.4252], // starting position [lng, lat]
	zoom: 9
});



map.once('load', () => {
	map.addSource('places', {
		type: 'geojson',
		data: {
			type: 'FeatureCollection',
			features: []
		}
	});
	
	map.addLayer({
		id: 'places-circle',
		source: 'places',
		type: 'circle',
		
		paint: {
			"circle-color": "hsla(200, 80%, 80%, 0.7)",
			"circle-stroke-color": "hsl(200, 80%, 40%)",
			"circle-stroke-width": 1,
			"circle-radius": 3
		}
	});
	
	map.addLayer({
		id: "places-text",
		source: "places",
		type: "symbol",
		layout: {
			"text-field": ["get", "PlaceName"],
			"text-font": ["Arial Bold"],
			"text-variable-anchor": ["left","right"],
			"text-justify": "left",
			"text-radial-offset": 0.5
		},
		paint: {
			"text-color": "hsl(200, 80%,40%)",
			"text-halo-color": "white",
			"text-halo-width": 2
		}
	})
	
	map.on('click', 'places-text', (e) => {
		const places = e.features[0];
		console.log(places);
		
		new mapboxgl.Popup()
			.setHTML(`<b>${places.properties.PlaceName}</b><br>${places.properties.Place_addr}`)
			.setLngLat(places.geometry.coordinates)
			.addTo(map);
	})
	
	map.on('mouseenter', 'places-text', () => {
		map.getCanvas().style.cursor = 'pointer';
	})
	
	map.on('mouseleave', 'places-text', () => {
		map.getCanvas().style.cursor = '';
	})
	
	showPlaces();
	
	
});

function showPlaces() {
	const authentication = new arcgisRest.ApiKey({
		key: arcGisKey
	});
	const category = document.getElementById('places-select').value;
	if(!category) {return;}
	arcgisRest
		.geocode({
			authentication,
			outFields: 'Place_addr,PlaceName',
			params: {
				category,
				location: map.getCenter().toArray().join(","),
				maxLocations: 40
			}
		})
		.then((response) => {
			map.getSource('places').setData(response.geoJson);
		})
		.catch((error) => {
			alert('There was a problem with the geocoder, see console for details.');
			console.error(error);
		})
}
function geocode(search, token) {
	const baseUrl = 'https://api.mapbox.com';
	const endPoint = '/geocoding/v5/mapbox.places/';
	return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
		.then(function(res) {
			return res.json();
			// to get all the data from the request, comment out the following three lines...
		}).then(function(data) {
			return data.features[0].center;
		});
}


// document.getElementById('places-select').addEventListener('change', showPlaces);
$('#places-select').change(() => {showPlaces()});
$('#location-button').click((e) => {
	e.preventDefault();
	geocode($('#location-input').val(), mapboxKey)
		.then((results) => {
			map.flyTo({
				center: results,
				essential: true,
				speed: 0.5,
				zoom: 9,
				bearing: 0
			})
		})
	
})