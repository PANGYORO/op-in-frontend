import React from "react";
import DefaultImg from "@assets/basicprofile.png";
import { useNavigate } from "react-router-dom";

const PostComment = ({
  memberName = "david",
  memberAvatarUrl = DefaultImg,
  commentContent = "안녕하세요",
  date = new Date(),
}) => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col inline-flex place-item-start">
      <div className="flex flex-row mr-4 items-center">
        <div className="w-20">
          <img
            alt="profile"
            src={memberAvatarUrl || DefaultImg}
            onClick={() => navigate(`/userdetail`, { state: memberName })}
            className="mx-auto object-cover rounded-full h-10 w-10 ml-4"
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
};
export default PostComment;
