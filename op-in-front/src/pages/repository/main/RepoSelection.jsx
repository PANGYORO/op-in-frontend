import React, { useEffect } from "react";
import Repo from "@components/repository/Repo";
// import RepoPost from "@components/repository/RepoPost";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";

// axios 사용하기
// for or map 사용해서 여러가지 repo 하기

const repoData = [
  {
    id: 1,
    title: "테스트1",
    content: "내용내용내용내용1",
    techLangs: ["java", "jupiter notebook", "Python", "React"],
  },
  {
    id: 2,
    title: "테스트2",
    content: "내용내용내용내용2",
    techLangs: ["java", "jupiter notebook"],
  },
];

export default function RepoSelection() {
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();
  useEffect(() => {
    if (!user.logined) navigate(`/repo/recommand`);
  }, []);

  return (
    <div className="flex flex-col w-full pl-0 md:p-4 h-screen">
      <div className="flex items-top w-full  pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        <div className="grid grid-cols-2 gap-4 w-full">
          {repoData.map((data) => (
            <Repo
              key={data.id}
              _id={data.id}
              _title={data.title}
              _content={data.content}
              _techLangs={data.techLangs}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
