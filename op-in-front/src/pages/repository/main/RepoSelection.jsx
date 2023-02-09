import React, { useEffect, useState } from "react";
import Repo from "@components/repository/Repo";
// import RepoPost from "@components/repository/RepoPost";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import http from "@api/http";

// axios 사용하기
// for or map 사용해서 여러가지 repo 하기

const repoData = {
  id: 2,
  title: "test title",
  content: "test content",
  techLangs: ["java", "C++", "HTML", "a", "b"],
  contributors: [
    {
      nickname: "박소망",
      id: "1",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "2",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "3",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "4",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "5",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "6",
      profileImg: null,
    },
    {
      nickname: "swany0509",
      id: "7",
      profileImg: null,
    },
  ],
  gitContributors: null,
  star: "12334",
  forkNum: "1232",
  topicList: ["JPA", "Security", "Django"],
  updateDate: null,
};
export default function RepoSelection() {
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();
  const [repoDatas, setRepoData] = useState("");
  useEffect(() => {
    if (!user.logined) navigate(`/repo/recommand`);
    else getData();
  }, []);

  const getData = async () => {
    await http
      .post(`/repo/member`, {
        email: user.email,
      })
      .then((response) => {
        setRepoData(response.data);
        console.log("success");
      })
      .catch((error) => {
        console.log("fail");
        console.log(error);
      });
  };

  const repoRendering = (list) => {
    console.log(list);
    const result = [];
    if (list != null)
      for (let i = 0; i < list.length; i++) {
        result.push(
          <Repo
            key={i}
            _id={list[i].id}
            _title={list[i].title}
            _content={list[i].content}
            _techLangs={list[i].techLangs}
            _repoDetails={list[i]}
          />
        );
      }
    return result;
  };

  return (
    <div className="flex flex-col w-full pl-0 md:p-4 h-screen">
      <div className="flex items-top w-full  pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        <div className="grid grid-cols-2 gap-4 w-full">
          <Repo
            _id={repoData.id}
            _title={repoData.title}
            _content={repoData.content}
            _techLangs={repoData.techLangs}
            _repoDetails={repoData}
          />
          {repoRendering(repoDatas)}
        </div>
      </div>
    </div>
  );
}
