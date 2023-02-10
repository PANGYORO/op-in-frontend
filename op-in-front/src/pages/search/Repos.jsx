import React from "react";
import Repo from "@components/repository/Repo";

const Repos = ({ value }) => {
  return (
    <>
      <div className="grid grid-cols-2 ml-4 gap-4">
        <Repo />
        <Repo />
        <Repo />
        <Repo />
        <Repo />
        <Repo />
        <Repo />
        <Repo />
      </div>
    </>
  );
};
export default Repos;
