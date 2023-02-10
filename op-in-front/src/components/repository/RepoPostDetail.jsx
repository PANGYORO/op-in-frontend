import React from "react";
import DefaultImg from "@assets/basicprofile.png";
import PostComment from "./PostComment";

export default function RepoPostDetail() {
  return (
    <>
      {/* 제목 */}
      <div className="dark:bg-gray-800 border-4">
        <div className="lg:flex lg:items-center lg:justify-between w-full mx-auto py-12 px-4 sm:px-6 lg:py-5 lg:px-8 z-20">
          <h1 className="text-3xl font-extrabold text-black dark:text-white sm:text-4xl">
            <span className="block flex felx-col place-items-center">
              {/* <div>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="w-16 h-16 px-3"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
                  />
                </svg>
              </div> */}
              Repo Name
            </span>
          </h1>
          {/* <div> 여기 오른쪽 끝에 붙여주는 칸</div> */}
        </div>

        {/* 내용칸 */}
        <div className="m-4 p-4 mb-6 rounded-lg shadow from-blue-500 to-blue-600">
          <div className="flex items-start text-left">
            <div className="flex-shrink-0">
              <div className="relative inline-block">
                <a href="#" className="relative block">
                  <img
                    alt="profil"
                    src={DefaultImg}
                    className="mx-auto object-cover rounded-full h-16 w-16 "
                  />
                </a>
              </div>
            </div>
            <div className="ml-6 mt-4 grid grid-col">
              <span className="ml-2 font-bold text-gray-600 dark:text-gray-200"><h2>nickname</h2></span>
              <span className="ml-2 text-sm text-gray-500 dark:text-gray-300">2 months ago</span>
            </div>
          </div>

          <div className="mt-3">
            <p className="mt-1 mx-4 font-light">
              My first job of scanning photos at the Memories 2 Digital Photo Scanning was My first
              job of scanning photos at the Memories 2 Digital Photo Scanning was My first job of
              scanning photos at the Memories 2 Digital Photo Scanning was fantastic. She completed
              the work quickly while I was waiting. Thanks for a great ``` ```
            </p>
          </div>
          <div className="flex items-start mt-6 text-gray-100">
            {/* 하트 */}
            <button className="mr-4 hover:text-white">
              <svg
                width="25"
                height="25"
                fill="currentColor"
                viewBox="0 0 1792 1792"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M1664 596q0-81-21.5-143t-55-98.5-81.5-59.5-94-31-98-8-112 25.5-110.5 64-86.5 72-60 61.5q-18 22-49 22t-49-22q-24-28-60-61.5t-86.5-72-110.5-64-112-25.5-98 8-94 31-81.5 59.5-55 98.5-21.5 143q0 168 187 355l581 560 580-559q188-188 188-356zm128 0q0 221-229 450l-623 600q-18 18-44 18t-44-18l-624-602q-10-8-27.5-26t-55.5-65.5-68-97.5-53.5-121-23.5-138q0-220 127-344t351-124q62 0 126.5 21.5t120 58 95.5 68.5 76 68q36-36 76-68t95.5-68.5 120-58 126.5-21.5q224 0 351 124t127 344z"></path>
              </svg>
            </button>
            {/* 포크 */}
            <button className="hover:text-white">
              <svg
                width="25"
                height="25"
                fill="currentColor"
                viewBox="0 0 1792 1792"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M1344 1024q133 0 226.5 93.5t93.5 226.5-93.5 226.5-226.5 93.5-226.5-93.5-93.5-226.5q0-12 2-34l-360-180q-92 86-218 86-133 0-226.5-93.5t-93.5-226.5 93.5-226.5 226.5-93.5q126 0 218 86l360-180q-2-22-2-34 0-133 93.5-226.5t226.5-93.5 226.5 93.5 93.5 226.5-93.5 226.5-226.5 93.5q-126 0-218-86l-360 180q2 22 2 34t-2 34l360 180q92-86 218-86z"></path>
              </svg>
            </button>
          </div>
        </div>
        {/* 댓글 적는칸 */}
        <div className="my-3">
          <label htmlFor="chat" className="sr-only">
            Leave a Comment...
          </label>
          <div className="flex items-center px-3 py-2 rounded-lg bg-gray-50 dark:bg-gray-700">
            <textarea
              id="chat"
              rows="1"
              // value={text}
              // onChange={(e) => {
              //   setText(e.target.value);
              // }}
              className="block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Leave a Comment..."
            ></textarea>
            <button
              // type="submit"
              onClick={() => {
                // createComment(text);
                // setText("");
              }}
              className="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600"
            >
              <svg
                aria-hidden="true"
                className="w-6 h-6 rotate-90"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M10.894 2.553a1 1 0 00-1.788 0l-7 14a1 1 0 001.169 1.409l5-1.429A1 1 0 009 15.571V11a1 1 0 112 0v4.571a1 1 0 00.725.962l5 1.428a1 1 0 001.17-1.408l-7-14z"></path>
              </svg>
              <span className="sr-only">Send message</span>
            </button>
          </div>
        </div>
        {/* 댓글달리는 곳 */}
        <div className="m-4 p-4 grid grid-row">
          <PostComment />
          <PostComment />
          <PostComment />
          <PostComment />
        </div>
      </div>
    </>
  );
}
