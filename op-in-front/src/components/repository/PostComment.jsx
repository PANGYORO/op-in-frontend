import React from "react";
import DefaultImg from "@assets/basicprofile.png";

export default function PostComment({
  memberName = "david",
  memberAvatarUrl = DefaultImg,
  commentContent = "안녕하세요",
  date = new Date(),
}) {
  return (
    <div className="flex flex-col inline-flex place-item-start">
      <div className="flex flex-row mr-4 items-center">
        <div className="w-20">
          <img
            alt="profile"
            src={memberAvatarUrl || DefaultImg}
            className="mx-auto object-cover h-10 ml-4"
          />
        </div>
        <div>
          <p>{memberName}</p>
          <p className="text-slate-400">{date.toLocaleString()}</p>
        </div>
      </div>
      <div className="p-3">
        <p>{commentContent} </p>
      </div>
      <hr />
    </div>
  );
}

