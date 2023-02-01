import React from "react";
// import Status from "../components/Status";
import Repo from "../components/Repo";
import Sidebar from "../components/SideBar";

export default function Repository() {
  return (
    <>
      <Sidebar />

      <div className="flex flex-col w-full pl-0 md:p-4">
        <div className="flex items-top w-full">
          <div className="grid grid-cols-2  gap-4 w-full">
            <Repo />
            <Repo />
            <Repo />
            <Repo />
            <Repo />
            <Repo />
            <Repo />
            <Repo />
            <Repo />
          </div>

          {/* <div class="w-1/3">
            <Status />
          </div> */}
        </div>
      </div>
    </>
  );
}
