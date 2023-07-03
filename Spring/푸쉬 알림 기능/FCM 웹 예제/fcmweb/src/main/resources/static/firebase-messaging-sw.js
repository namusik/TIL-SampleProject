// Import the functions you need from the SDKs you need
// importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js');
// importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging.js');
importScripts("https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.10.0/firebase-messaging.js");
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
firebase.initializeApp({
    apiKey: "AIzaSyAQuC4dWSBUz4F1tnVn8MmvfrTbKnbcMWg",
    authDomain: "fcmtest-8ea73.firebaseapp.com",
    projectId: "fcmtest-8ea73",
    storageBucket: "fcmtest-8ea73.appspot.com",
    messagingSenderId: "327970754149",
    appId: "1:327970754149:web:0330f48916620df3fe408c",
    measurementId: "G-EGW7NBQKR8"
});

const messaging = firebase.messaging();

//백그라운드 메세지 개인화 설정시 사용
// messaging.onBackgroundMessage((payload) => {
//     console.log('[firebase-messaging-sw.js] Received background message ', payload);
//     // Customize notification here
//     const notificationTitle = 'Background Message Title';
//     const notificationOptions = {
//         body: 'Background Message body.',
//         icon: '/firebase-logo.png'
//     };
//
//     self.registration.showNotification(notificationTitle,
//         notificationOptions);
// });
