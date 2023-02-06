import React, { useState, useEffect } from "react";
import DefaultImg from "@assets/basicprofile.png";
import Setting from "@assets/settings.png";
import Post from "@components/Post";
import MyInfo from "@components/user/MyInfo";
import http from "@api/http";

export default function Detail() {
  const [myinfo, setMyInfo] = useState("");

  useEffect(() => {
    async () => {
      await http
        .get(`auth/getMember`, {
          email: "swany0509@naver.com",
        })
        .then((data) => {
          alert(data);
          console.log(data);
          setMyInfo(data);
        })
        .catch(() => alert("error"));
    };
    console.log(myinfo);
  }, []);
  return (
    <div className="flex items-start justify-between mx-44">
      <div className="w-full mx-4 my-4 h-screen overflow-auto">
        <div className="grid grid-cols-3 gap-3 mb-8">
          <div className="flex justify-center h-full ">
            <img src={DefaultImg} alt="none" />
          </div>
          <div className="flex justify-center col-span-2 lg:text-3xl md:text-2xl sm:text-xl w-4/5">
            <div className="grid content-center">
              <div>
                <div className="grid grid-cols-2 gap-2 justify-items-between">
                  <div className="bg-prinavy self-center"> NickName</div>
                  <div className="grid grid-cols-2 gap-2 justify-items-center">
                    <button
                      type="button"
                      disabled=""
                      className="py-1 px-3 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500
                    focus:ring-offset-red-200 text-white  transition ease-in duration-200
                    text-center font-semibold shadow-md focus:outline-none focus:ring-2
                    focus:ring-offset-2  opacity-70 rounded-lg "
                    >
                      Follow
                    </button>

                    <img
                      src={Setting}
                      alt="setting"
                      className="h-16 justify-self-end"
                    />
                  </div>
                </div>
                <div className="grid grid-cols-3 gap-4 justify-items-between mt-6">
                  <div> posts 0</div>
                  <div> follower 5</div>
                  <div> following 10</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
          <div className="pt-2 pb-24 pl-2 pr-2  md:pt-0 md:pr-0 md:pl-0">
            <div className="flex flex-col flex-wrap sm:flex-row h-full">
              <div className="w-1/3 h-full ">
                <MyInfo />
              </div>

              <div className=" w-2/3  h-screen overflow-auto">
                <div className="ml-4 mb-4 ">
                  <header className="z-40 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
                    <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
                      <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
                        <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full">
                          <div className="relative flex items-center w-full h-full lg:w-64 group">
                            <div className="absolute z-50 flex items-center justify-center block w-auto h-10 p-3 pr-2 text-sm text-gray-500 uppercase cursor-pointer sm:hidden">
                              <svg
                                fill="none"
                                className="relative w-5 h-5"
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                strokeWidth="2"
                                stroke="currentColor"
                                viewBox="0 0 24 24"
                              >
                                <path d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                              </svg>
                            </div>
                            <svg
                              className="absolute left-0 z-20 hidden w-4 h-4 ml-4 text-gray-500 pointer-events-none fill-current group-hover:text-gray-400 sm:block"
                              xmlns="http://www.w3.org/2000/svg"
                              viewBox="0 0 20 20"
                            >
                              <path d="M12.9 14.32a8 8 0 1 1 1.41-1.41l5.35 5.33-1.42 1.42-5.33-5.34zM8 14A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"></path>
                            </svg>
                            <input
                              type="text"
                              className="block w-full py-1.5 pl-10 pr-4 leading-normal rounded-2xl focus:border-transparent focus:outline-none focus:ring-2 focus:ring-blue-500 ring-opacity-90 bg-gray-100 dark:bg-gray-800 text-gray-400 aa-input"
                              placeholder="Search"
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                  </header>
                </div>
                <div className="grid grid-rows-1 ">
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                  <Post />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
