import React, { useState } from "react";
import QnA from "@components/repository/QnA";
import QnaModal from "@components/modals/QnaModal";

export default function FollowingQnas() {
  const [open, setOpen] = useState(false);

  function toggleModal() {
    setOpen((prev) => !prev);
  }
  return (
    <>
      <header className="z-20 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl ml-4 mb-4 mr-4">
        <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
          <div className="relative grid grid-cols-2 items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
            <div className="container relative left-0 z-20 flex w-3/4 h-auto h-full">
              <div className="relative flex items-center w-full h-full lg:w-64 group">
                <div className="absolute z-30 flex items-center justify-center block w-auto h-10 p-3 pr-2 text-sm text-gray-500 uppercase cursor-pointer sm:hidden">
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
                  placeholder="Qna Search"
                />
              </div>
            </div>
            <div className="w-auto h-auto h-full justify-self-end">
              <button
                type="button"
                disabled=""
                onClick={toggleModal}
                class="py-2 px-4 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-blue-200 text-white 
                w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none 
                focus:ring-2 focus:ring-offset-2  opacity-70 rounded-lg flex justify-center items-center"
              >
                <svg
                  width="20"
                  height="20"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  class="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
                  />
                </svg>
                Qna Write
              </button>
            </div>
          </div>
        </div>
      </header>

      <div className="ml-4 w-full h-screen overflow-auto">
        <QnA />
        <QnA />
        <QnA />
        <QnA />
        <QnA />
      </div>
      <QnaModal open={open} setOpen={setOpen} />
    </>
  );
}
