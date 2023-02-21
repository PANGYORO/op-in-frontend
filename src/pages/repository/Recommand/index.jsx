import React, { useState, useEffect } from "react";
import http from "@api/http";
import Repo from "@components/repository/Repo";


const RepoList = ({ repos }) => {
  console.debug(repos);
  return (
    <div className="grid grid-cols-3 gap-4">
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

function RecommandIndex() {
  const [repoDatas, setRepoData] = useState([]);

  const getRepo = () => {
    http
      .get("member/repo/recommend")
      .then(({ data }) => {
        console.log(data);
        setRepoData([...data]);
      })
      .catch(() => console.debug("error"));
  }

  useEffect(() => {
    getRepo();
  }, []);

  return (
    <div className="flex flex-col w-full pl-0 md:p-4 ">
      <header className="z-40 items-center w-full h-16 bg-white shadow-lg  rounded-2xl">
        <div className="relative flex items-center w-full h-full group ml-3 text-2xl">
          Recommand Repos
        </div>
      </header>
      <div className="flex items-top w-full mt-4 pt-2 pb-24 pl-2 pr-2 md:pt-0 md:pr-0 md:pl-0">
        <RepoList repos={repoDatas} />
      </div>
    </div>
  );
}

export default RecommandIndex;
