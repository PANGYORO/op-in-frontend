import React, { useState, useEffect } from "react";
import Prism from "prismjs";
import http from "@api/http";
// 여기 css를 수정해서 코드 하이라이팅 커스텀 가능
import "prismjs/themes/prism.css";

import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/react-editor";

import "@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css";
import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";
// import { result } from 'lodash';

export default function PostView() {
  // const [myrepo, setMyInfo] = useState("");

  // async function getRepo() {
  //   await http
  //     .post("repo/", {
  //       email: repoData,
  //     })
  //     .then((response) => {
  //       // alert(response.data.nickname);
  //       console.log(response.code);
  //       setMyInfo(response.code);
  //     })
  //     .catch(() => alert("error"));
  //   // console.log(myinfo);
  // }

  // useEffect(() => {
  //   getRepo();
  // }, []);

  const arr = ['제목1', '제목2', 3, 4, 5];
  
  const arrLoop = () => {
    const newArr = [];
    for (let i = 0; i < arr.length; i++) {
      newArr.push(<h2 key={i}>{ arr[i] }</h2>);
    }
    return newArr;
  };
  
  return (
    <div className="w-full m-4 p-6 bg-white h-screen shadow-lg rounded-2xl dark:bg-gray-700 ">
      <Viewer
        initialValue="### hellossafy ```const dev = 2```"
        plugins={[[codeSyntaxHighlight, { highlighter: Prism }]]}
      />
      <div> { arrLoop() } </div>

      {/* <div>{myrepo.length} </div>
      <div>{myrepo} </div> */}
    </div>
  );
}
