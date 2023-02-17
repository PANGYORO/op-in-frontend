import React from "react";
import Repo from "@components/repository/RecommandRepo";

function RecommandIndex() {
  return (
    <div className="grid grid-cols-1 gap-4 mt-4 ml-4">
      <Repo />
      <Repo />
      <Repo />
      <Repo />
      <Repo />
      <Repo />
    </div>
  );
}

export default RecommandIndex;
