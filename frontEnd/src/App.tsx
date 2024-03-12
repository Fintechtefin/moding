import { Suspense, lazy } from "react";
import "@/App.scss";
import { Route, Routes } from "react-router-dom";

const HomePage = lazy(() => import("@pages/HomePage"));

function App() {
  return (
    <Suspense fallback={<div />}>
      <Routes>
        <Route path="/">
          <Route index element={<HomePage />} />
        </Route>
      </Routes>
    </Suspense>
  );
}

export default App;
