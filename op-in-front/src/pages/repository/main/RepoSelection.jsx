import React, { useEffect, useState } from "react";
import Repo from "@components/repository/Repo";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import http from "@api/http";
// import axios from "axios";

const RepoList = ({ repos }) => {
  console.log(repos);
  return (
    <div className="grid grid-cols-3 gap-4 ml-4">
      <Repo />
      {repos.map((repo) => {
        return (
          <Repo
            key={repo.id}
            id={repo.id}
            title={repo.title}
            content={repo.content}
            techLangs={repo.techLangs}
          />
        );
      })}
    </div>
  );
};

const RepoSelection = () => {
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();
  const [repoDatas, setRepoData] = useState([]);
  console.log(user);
  useEffect(() => {
    if (!user.logined) navigate(`/repo/recommand`);
    else
      http
        .post(`repo/member`, {
          email: user.email,
        })
        .then(({ data }) => {
          console.log(data);
          setRepoData([...data]);
        })
        .catch((error) => {
          console.log(error);
        });
  }, []);

  return (
    <div className="flex flex-col w-full pl-0 md:p-4 h-screen">
      <div className="flex items-top w-full  pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        <RepoList repos={repoDatas} />
      </div>
    </div>
  );
};
export default RepoSelection;

