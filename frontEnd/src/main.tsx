import ReactDOM from "react-dom/client";
import App from "@/App.tsx";
import { RecoilRoot } from "recoil";
import "@/index.scss";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import worker from "./mocks/browser";

if (process.env.NODE_ENV === "development") {
  worker.start({
    serviceWorker: { url: "/mockServiceWorker.js" },
    onUnhandledRequest: "bypass",
  });
}

localStorage.setItem("MSW_DEBUG", "true");

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 0,
    },
  },
});

ReactDOM.createRoot(document.getElementById("root")!).render(
  <QueryClientProvider client={queryClient}>
    <RecoilRoot>
      <App />
    </RecoilRoot>
  </QueryClientProvider>
);
