import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

import { RecoilRoot } from "recoil";

import Main from "./pages/Main";
import { ToastContainer } from "react-toastify";

import "./index.css";

import "react-toastify/dist/ReactToastify.css";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";

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
