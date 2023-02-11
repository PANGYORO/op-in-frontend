import React, { useState, useEffect } from "react";
import Repo from "@components/repository/Repo";
import http from "@api/http";

const RepoList = ({ repos }) => {
  console.log(repos);
  return (
    <div className="grid grid-cols-3 gap-4 ml-4">
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

const repos = ({ value }) => {
  const [results, setResults] = useState([]);
  useEffect(() => {
    http
      .get(`search/repos?query=${value}`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <>
      <RepoList repos={results} />
    </>
  );
};
export default repos;
