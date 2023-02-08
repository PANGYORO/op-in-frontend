import React from "react";
import Repo from "@components/repository/Repo";

export default function Repos() {
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
}
