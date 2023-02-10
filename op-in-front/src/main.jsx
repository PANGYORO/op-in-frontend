import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

import "./index.css";
import { RecoilRoot } from "recoil";

import Main from "./pages/Main";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  //   </React.StrictMode>
    <RecoilRoot>
      <ToastContainer 
        autoClose={3000} // 자동 off 시간
      />
      <BrowserRouter>
        <Main />
      </BrowserRouter>
    </RecoilRoot>
);
