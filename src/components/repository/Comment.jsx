import React from "react";
import DefaultImg from "@assets/basicprofile.png";
import { useNavigate } from "react-router-dom";

const Comment = ({ member, comment }) => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col inline-flex place-item-start">
      <div className="flex flex-row mr-4 items-center">
        <div className="w-20">
          <span onClick={() => navigate(`/userdetail`, { state: member.nickname })}>
            <img
              alt="profile"
              src={member?.user_img || DefaultImg}
              className="mx-auto object-cover  rounded-full h-10 w-10 ml-4"
            />
          </span>
        </div>
        <div>{member?.nickname}</div>
      </div>
      <div className="flex items-center p-3">
        <p>{comment} </p>
      </div>
      <hr />
    </div>
  );
};
export default Comment;
