import React, { useEffect } from "react";
import Repo from "@components/Repo";
// import RepoPost from "@components/repository/RepoPost";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";

export default function RepoSelection() {
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();
  useEffect(() => {
    if (!user.logined) navigate(`/repo/recommand`);
  }, []);

  return (
    <div className="flex flex-col w-full pl-0 md:p-4">
      <div className="flex items-top w-full h-screen pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        <div className="grid grid-cols-2 gap-4 w-full">
          <Repo />
          <Repo />
          <Repo />
          <Repo />
          <Repo />
          <Repo />
          <Repo />
          <Repo />
        </div>
      </div>
    </div>
  );
}
