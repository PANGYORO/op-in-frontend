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

  async function RepoData() {
    await http
      .post(`repo/member`, {
        email: currentEmail,
      })
      .then((response) => {
        // alert(currentEmail);
        alert(response.data.nickname);
        console.log(response.data);
        setMyInfo(response.data);
      })
      .catch(() => alert("error"));
    console.log(myinfo);
  }

  useEffect(() => {
    RepoData();
  }, []);

  return (
    <div className="grid grid-cols-1 gap-4 mt-4 ml-4">
      <RecommandRepo />
    </div>
  );
}

export default RecommandIndex;
