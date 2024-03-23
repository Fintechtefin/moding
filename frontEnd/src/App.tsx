import { RouterProvider } from "react-router-dom";
import { router } from "@util/router";
import "@/firebase-messaging-sw";
import "@/App.scss";

function App() {
  // useEffect(() => {
  //   if ("serviceWorker" in navigator) {
  //     navigator.serviceWorker
  //       .register("../public/firebase-messaging-sw.js")
  //       .then((registration) => {
  //         console.log("Service Worker 등록 성공:", registration);
  //       })
  //       .catch((err) => {
  //         console.log("Service Worker 등록 실패:", err);
  //       });
  //   }
  // }, []);

  return <RouterProvider router={router} />;
}

export default App;
