import React from "react";
import Status from "../components/Status";
import Repo from "../components/Repo";
import QnA from "../components/QnA";
import TestRR from "../components/TestRR";

export default function Repository() {
  return (
    <>
      <div className="flex flex-col w-full pl-0 md:p-4">
        <div className="flex items-top w-full">
          <div className="grid grid-cols-2  gap-4 w-full">
            <Repo />
            <QnA />
            <QnA />
            <Repo />
            <Repo />
            <TestRR />
          </div>

          <div className="w-1/3">
            <Status />
          </div>
        </div>
      </div>
    </>
  );
}

