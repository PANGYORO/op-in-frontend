import React from "react";
import DefaultImg from "@assets/basicprofile.png";

export default function Comment({ member, comment }) {
  return (
    <div className="flex flex-col inline-flex place-item-start">
      <div className="flex flex-row mr-4 items-center">
        <div className="w-20">
          <img
            alt="profile"
            src={member?.user_img || DefaultImg}
            className="mx-auto object-cover h-10 ml-4"
          />
        </div>
        <div>{member?.nickname}</div>
      </div>
      <div className="flex items-center p-3">
        <p>{comment} </p>
      </div>
      <hr />
    </div>
  );
}
