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