import React, { useState } from "react";
import RecommandRepo from "@components/repository/RecommandRepo";

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
  return (
    <div className="grid grid-cols-1 gap-4 mt-4 ml-4">
      <RecommandRepo />
    </div>
  );
}

export default RecommandIndex;
