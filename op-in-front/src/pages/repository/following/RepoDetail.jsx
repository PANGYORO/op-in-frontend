import React, { useState, useCallback } from "react";
import FollowingPosts from "./FollowingPosts";
import FollowingQnas from "./FollowingQnas";

const POSTS_TAB = "posts";
const QNAS_TAB = "qnas";

export default function Question() {
  const [tab, setTab] = useState(POSTS_TAB);

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
        <header className="z-40 items-center w-full h-15 pb-3 bg-white shadow-lg dark:bg-gray-700 rounded-t-2xl">
          <ul className="pt-3 pl-3 flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400">
            <li className="mr-2">
              <div
                className={tab == POSTS_TAB ? selected : deselected}
                onClick={() => onClick(POSTS_TAB)}
              >
                Post
              </div>
            </li>
            <li className="mr-2">
              <div
                className={tab == QNAS_TAB ? selected : deselected}
                onClick={() => onClick(QNAS_TAB)}
              >
                Qna
              </div>
            </li>
          </ul>
        </header>
      </div>
      <div className="h-screen overflow-auto">
        {tab == POSTS_TAB ? <FollowingPosts /> : <FollowingQnas />}
      </div>
    </div>
  );
}
