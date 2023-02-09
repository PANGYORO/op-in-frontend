import React, { useState, useEffect } from "react";
import RecommandRepo from "@components/repository/RecommandRepo";
import http from "@api/http";
import { useLocation } from "react-router-dom";

//axios 사용해서 정보 전달
// const arr = ['배열 요소1', '배열 요소2', '배열 요소3'];
// arr.map((elem, index) => {
//   console.log(elem);
//   console.log(index);
// });

// repos.map((elem, index) => {
//   console.log(elem);
//   console.log(index);
// })

function RecommandIndex() {
  const [myinfo, setMyInfo] = useState("");
  const location = useLocation();
  const currentEmail = location.state;

  async function getRepo() {
    await http
      .post("post/")
      .then((response) => {
        console.log(myinfo);
        setMyInfo(response.data);
      })
      .catch(() => console.log("error"));
    console.log("error");
  }

  // async function RepoData() {
  //   // await http
  //   //   .post(`repo/member`, {
  //   //     email: currentEmail,
  //   //   })
  //   //   .then((response) => {
  //   //     // alert(currentEmail);
  //   //     // alert(response.data.nickname);
  //   //     console.log(response.data);
  //   //     setMyInfo(response.data);
  //   //   })
  //   //   .catch(() => alert("error"));
  //   console.log(myinfo);
  // }

  useEffect(() => {
    getRepo();
  }, []);

  return (
    <div className="z-40 items-center w-full h-15 pb-3 dark:bg-gray-700 rounded-t-2xl">
      <div className="grid grid-cols-1 gap-4 mt-4 ml-4">
        {/* {myrepo.map((data) => (
          <RecommandRepo
            key={data.id}
            _id={data.id}
            _title={data._title}
            _content={data._content}
            _techLangs={data._techLangs}
          />
        ))} */}
        <RecommandRepo />
      </div>
    </div>
  );
}

export default RecommandIndex;
