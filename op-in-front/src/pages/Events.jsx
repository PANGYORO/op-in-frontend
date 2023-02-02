import React from "react";
import Event from "../components/event/event";

export default function Events() {
  return (
    <>
      <div className="overflow-scroll w-full">
        <div className="grid grid-rows-1 pt-4">
          <Event />
          <Event />
          <Event />
          <Event />
          <Event />
          <Event />
          <Event />
          <Event />
        </div>
      </div>
      {/* <div className="flex flex-col w-full pl-0 md:p-4 md:space-y-4 overflow-auto">
        <header className="z-40 items-center w-full h-96 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
              <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full"></div>
            </div>
          </div>
        </header>
        <header className="z-40 items-center w-full h-96 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
              <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full"></div>
            </div>
          </div>
        </header>
        <header className="z-40 items-center w-full h-96 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
              <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full"></div>
            </div>
          </div>
        </header>
        <header className="z-40 items-center w-full h-96 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
              <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full"></div>
            </div>
          </div>
        </header>
      </div> */}
    </>
  );
}
