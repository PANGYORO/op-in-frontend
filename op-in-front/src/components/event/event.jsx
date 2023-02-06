import React from "react";
import OpenSource from "@assets/opensource.png";

export default function post() {
  return (
    <>
      <div className="ml-4 mb-4">
        <div className=" w-full p-4 bg-white shadow-lg rounded-2xl dark:bg-gray-700 items-center justify-center group sm:flex space-x-6 ">
          <div className="sm:w-8/12 pl-0 p-5">
            <div className="space-y-2">
              <div className="space-y-4 ">
                <h3 className="text-3xl font-semibold text-cyan-900 text-justify">
                  OpenSource Event
                </h3>
              </div>
              <div>
                <div className="text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    strokeWidth="2"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    className="feather feather-activity mr-2"
                  >
                    <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline>
                  </svg>
                  facebook-initail-user
                </div>
              </div>
              <div className="flex items-center space-x-4 justify-between">
                <div className="flex gap-3 space-y-1">
                  <span className="text-md">Competition details...</span>
                </div>
              </div>
              <div className="flex items-center space-x-4 justify-between">
                <div className="text-grey-500 flex flex-row space-x-1  my-4">
                  <svg
                    stroke="currentColor"
                    fill="none"
                    strokeWidth="0"
                    viewBox="0 0 24 24"
                    height="1em"
                    width="1em"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"
                    ></path>
                  </svg>
                  <p className="text-xs">2 hours ago</p>
                </div>
              </div>
            </div>
          </div>
          <img
            className="mx-auto w-full block w-4/12 h-40 rounded-lg"
            alt="art cover"
            loading="lazy"
            src={OpenSource}
          />
        </div>
      </div>
    </>
  );
}
