import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";

const firebaseConfig = {
  apiKey: "AIzaSyCs5ruoCYfSnFBfRRXRu7bahcYW32-II3k",
  authDomain: "moding-1a881.firebaseapp.com",
  projectId: "moding-1a881",
  storageBucket: "moding-1a881.appspot.com",
  messagingSenderId: "279539398007",
  appId: "1:279539398007:web:90afb239004f8fe5231d72",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

const requestPermission = async () => {
  console.log("권한 요청 중...");

  const permission = await Notification.requestPermission();
  if (permission === "denied") {
    console.log("알림 권한 허용 안됨");
    return;
  }

  console.log("알림 권한이 허용됨");

  const token = await getToken(messaging, {
    vapidKey: "BM4lDFUAUgOefiD3Xx5v0KH6Cp6DwyLIt6HrX2Vb4rVKeVHjpUBUaMc_Pk_rQ2W-oIm3fSZjQNGoTVufLl_sEjE",
  });

  if (token) console.log("token: ", token);
  else console.log("Can not get Token");

  onMessage(messaging, (payload) => {
    console.log("메시지가 도착했습니다.", payload);
    // ...
  });
};

requestPermission();
