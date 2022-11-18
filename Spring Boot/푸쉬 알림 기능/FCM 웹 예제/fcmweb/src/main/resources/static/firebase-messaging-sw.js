// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
apiKey: "AIzaSyAQuC4dWSBUz4F1tnVn8MmvfrTbKnbcMWg",
authDomain: "fcmtest-8ea73.firebaseapp.com",
projectId: "fcmtest-8ea73",
storageBucket: "fcmtest-8ea73.appspot.com",
messagingSenderId: "327970754149",
appId: "1:327970754149:web:0330f48916620df3fe408c",
measurementId: "G-EGW7NBQKR8"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
