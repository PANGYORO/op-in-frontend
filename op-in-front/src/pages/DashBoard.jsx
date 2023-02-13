import React, { useCallback, useState } from "react";
import News from "@pages/dashboard/News";
import Hots from "@pages/dashboard/Hots";

const NEWS_TAB = "news";
const HOTS_TAB = "hots";

export default function DashBoard() {
  const [tab, setTab] = useState(NEWS_TAB);

  const onClick = useCallback((item) => {
    setTab(item);
  }, []);
  const selected =
    "inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active dark:bg-gray-800 dark:text-blue-500";
  const deselected =
    "inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 dark:hover:bg-gray-800 dark:hover:text-gray-300";

  return (
    <div className="w-full mt-4">
      <div className="ml-4 mb-4">
        <header className="z-40 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
          <div className="relative flex items-center w-full h-full group ml-3 text-2xl">
            DashBoard
          </div>
        </header>
        <header className="z-40 items-center w-full h-15 mt-4 pb-3 bg-white shadow-lg dark:bg-gray-700 rounded-t-2xl">
          <ul className="pt-3 pl-3 flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400">
            <li className="mr-2">
              <div
                className={tab == NEWS_TAB ? selected : deselected}
                onClick={() => onClick(NEWS_TAB)}
              >
                New
              </div>
            </li>
            <li className="mr-2">
              <div
                className={tab == HOTS_TAB ? selected : deselected}
                onClick={() => onClick(HOTS_TAB)}
              >
                Hot
              </div>
            </li>
          </ul>
        </header>
      </div>
      <div id="dashboardcontent" className="h-screen overflow-auto">
        {tab == "news" ? <News /> : <Hots />}
      </div>
    </div>
  );
}
