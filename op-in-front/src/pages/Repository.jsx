import React from "react";
import Status from "../components/Status";
import Repo from "../components/Repo";
import Sidebar from "../components/SideBar";

export default function Repository() {
  return (
    <>
      <Sidebar />

      <div className="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
        <div className="relative flex items-center w-full">
          <div className="inline-flex grid-rows-2 gap-1 w-2/3">
            <div className="col-start-1 col-end-3">
              <Repo />
              <Repo />
              <Repo />
            </div>
          </div>
          <div className="col-start-4 col-end-5 w-1/3">
            <Status />
          </div>
        </div>
      </div>
    </>
  );
}
