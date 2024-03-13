import ReactDOM from "react-dom/client";
import App from "@/App.tsx";
import { RecoilRoot } from "recoil";
import { BrowserRouter } from "react-router-dom";
import "@/index.scss";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <RecoilRoot>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </RecoilRoot>
);
