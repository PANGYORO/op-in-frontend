import QnA from "../../../components/QnA";
// import Status from "../../../components/Status";
import React from "react";

export default function Question() {
  return (
    <div className="w-full mt-4">
      <div className="ml-4 mb-4">
        <header className="z-40 items-center w-full h-15 pb-3 bg-white shadow-lg dark:bg-gray-700 rounded-t-2xl">
          <ul className="pt-3 pl-3 flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400">
            <li className="mr-2">
              <a
                href="#"
                aria-current="page"
                className="inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active dark:bg-gray-800 dark:text-blue-500"
              >
                New
              </a>
            </li>
            <li className="mr-2">
              <a
                href="#"
                className="inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 dark:hover:bg-gray-800 dark:hover:text-gray-300"
              >
                Hot
              </a>
            </li>
          </ul>
        </header>
      </div>
      <div className="grid grid-cols-1 gap-4 w-full">
        <QnA />
        <QnA />
        <QnA />
      </div>
    </div>
    // <>
    //   <div className="flex flex-col w-full pl-0 md:p-4">
    //     <div className="flex items-top w-full h-screen pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
    //       <div className="grid grid-cols-1 gap-4 w-full">
    //         <QnA />
    //         <QnA />
    //         <QnA />
    //       </div>
    //       <div className="w-1/3">
    //         <Status />
    //       </div>
    //     </div>
    //   </div>
    // </>
  );
}
