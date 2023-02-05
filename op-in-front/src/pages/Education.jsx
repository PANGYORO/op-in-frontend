import React from "react";

export default function Education() {
  return (
    <>
      <div className="flex flex-col w-full h-full pl-0 md:p-4 md:space-y-4">
        <div className="grid grid-cols-2 gap-4 h-1/2">
          {/* <header className="z-40 items-center w-full h-96 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
            <div className="p-5">
              <h3 className="text-3xl font-bold">Tutorial</h3>
            </div>
            <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
              <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
                <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full"></div>
              </div>
            </div>
          </header> */}
          <header className="z-40 items-center w-full h-full bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
            <div className="p-5">
              <h3 className="text-3xl font-bold">Totorials</h3>
            </div>
            <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
              <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0 h-full">
                <div className="container relative left-0 z-50 flex w-3/4 h-96"></div>
              </div>
            </div>
          </header>
          <header className="z-40 items-center w-full h-full bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
            <div className="p-5">
              <h3 className="text-3xl font-bold">OpenSouce Guide</h3>
            </div>
            <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
              <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0 h-full">
                <div className="container relative left-0 z-50 flex w-3/4 h-full"></div>
              </div>
            </div>
          </header>
        </div>
        <header className="z-40 items-center w-full h-full bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="p-5">
            <h3 className="text-3xl font-bold">Documentations</h3>
          </div>
          <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
            <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0 h-full">
              <div className="container relative left-0 z-50 flex w-3/4 h-96"></div>
            </div>
          </div>
        </header>
      </div>
    </>
  );
}
