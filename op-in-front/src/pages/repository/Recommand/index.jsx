import React, { useState, useEffect } from "react";
import http from "@api/http";
import { useLocation } from "react-router-dom";
import Repo from "@components/repository/Repo";
//axios 사용해서 정보 전달
// const arr = ['배열 요소1', '배열 요소2', '배열 요소3'];
// arr.map((elem, index) => {
//   console.debug(elem);
//   console.debug(index);
// });

// repos.map((elem, index) => {
//   console.debug(elem);
//   console.debug(index);
// })

function RecommandIndex() {
  const [myinfo, setMyInfo] = useState("");
  // const location = useLocation();
  // const currentEmail = location.state;

  async function getRepo() {
    await http
      .post("post/")
      .then((response) => {
        console.debug(myinfo);
        setMyInfo(response.data);
      })
      .catch(() => console.debug("error"));
    console.debug("error");
  }

  // async function RepoData() {
  //   // await http
  //   //   .post(`repo/member`, {
  //   //     email: currentEmail,
  //   //   })
  //   //   .then((response) => {
  //   //     // alert(currentEmail);
  //   //     // alert(response.data.nickname);
  //   //     console.debug(response.data);
  //   //     setMyInfo(response.data);
  //   //   })
  //   //   .catch(() => alert("error"));
  //   console.debug(myinfo);
  // }

  useEffect(() => {
    getRepo();
  }, []);

  return (
    <div className="z-40 items-center w-full h-15 pb-3 dark:bg-gray-700 rounded-t-2xl">
      <div className="grid grid-cols-1 gap-4 mt-4 ml-4">
        <header className="z-40 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative flex items-center w-full h-full lg:w-64 group ml-3 text-2xl">
            Recommand Repos
          </div>
        </header>
        <Repo />
      </div>
    </div>
  );
}

export default RecommandIndex;
