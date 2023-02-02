import React from "react";
import News from "./dashboard/news";
import Hots from "./dashboard/Hots";

export default function DashBoard() {
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
      <News />
    </div>
  );
}
