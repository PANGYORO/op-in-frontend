import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";

import Main from "./pages/Main";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    {/* <RouterProvider router={pages} /> */}
    <Main />
  </React.StrictMode>
);
