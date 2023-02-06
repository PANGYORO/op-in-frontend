import React from "react";
import Prism from "prismjs";
// 여기 css를 수정해서 코드 하이라이팅 커스텀 가능
import "prismjs/themes/prism.css";

import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/react-editor";

import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";
import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";

export default function PostView() {
  return (
    <div className="w-full m-4 p-6 bg-white h-screen shadow-lg rounded-2xl dark:bg-gray-700 ">
      <Viewer
        initialValue="### hellossafy ```const dev = 2```"
        plugins={[[codeSyntaxHighlight, { highlighter: Prism }]]}
      />
    </div>
  );
}
