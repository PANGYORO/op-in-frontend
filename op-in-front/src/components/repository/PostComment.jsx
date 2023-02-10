import React from "react";
import DefaultImg from "@assets/basicprofile.png";

export default function PostComment({ _name, _text }) {
  return (
    <div className="flex flex-col inline-flex place-item-start mt-4">
      <div className="flex flex-row mr-4 items-center">
        <div className="w-20">
          <img alt="profile" src={DefaultImg} className="mx-auto object-cover h-10 ml-4" />
        </div>
        <div>네임입니다.{_name}</div>
      </div>
      <div className="flex items-center p-3">
        <p>호로로롤로로로로로{_text} </p>
      </div>
      <hr />
    </div>
  );
}
