import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import {RecoilRoot} from "recoil";

import Main from "./pages/Main";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RecoilRoot>
      {/* <RouterProvider router={pages} /> */}
      <Main />
    </RecoilRoot>
  </React.StrictMode>
);
