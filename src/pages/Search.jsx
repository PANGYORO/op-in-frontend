import React, { useCallback, useState } from "react";
import Posts from "@pages/search/Posts";
import Repos from "@pages/search/Repos";
import Users from "@pages/search/Users";
import { useLocation } from "react-router-dom";

const POSTS_TAB = "posts";
const REPOS_TAB = "repos";
const USERS_TAB = "users";

export default function DashBoard() {
  const location = useLocation();
  const searchValue = location.state;
  const [tab, setTab] = useState(POSTS_TAB);

  const onClick = useCallback((item) => {
    setTab(item);
  }, []);

  const selected =
    "inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active  ";
  const deselected =
    "inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50  ";

  return (
    <div className="flex items-start justify-between mx-44">
      <div className="w-full mt-4">
        <div className="ml-4 mb-4">
          <header className="z-40 items-center w-full h-15 pb-3 bg-white shadow-lg  rounded-t-2xl">
            <div className="pt-3 pl-3 text-2xl">
              <span className="font-semibold"> Current Search :&nbsp;</span>
              <span className="font-bold">{searchValue == "" ? "nodata" : searchValue}</span>
            </div>
            <ul className="pt-3 pl-3 flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200  ">
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
                  className={tab == REPOS_TAB ? selected : deselected}
                  onClick={() => onClick(REPOS_TAB)}
                >
                  Repo
                </div>
              </li>
              <li className="mr-2">
                <div
                  className={tab == USERS_TAB ? selected : deselected}
                  onClick={() => onClick(USERS_TAB)}
                >
                  User
                </div>
              </li>
            </ul>
          </header>
        </div>
        <div id="dashboardcontent">
          {tab == "posts" ? (
            <Posts value={searchValue} />
          ) : tab == "repos" ? (
            <Repos value={searchValue} />
          ) : (
            <Users value={searchValue} />
          )}
        </div>
      </div>
    </div>
  );
}
