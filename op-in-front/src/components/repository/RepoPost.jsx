import React from "react";
import { useNavigate } from "react-router-dom";

export default function RepoPost({
  _postId = 1,
  _createTime = "2022-02-09",
  post_content = "basic title",
  _likeCount = 0,
  _commentCount = 0,
}) {
  const navigate = useNavigate();

  return (
    <>
      <div className="mb-4">
        <div
          className=" w-full p-4 bg-white shadow-lg rounded-2xl dark:bg-gray-700 items-center justify-center group sm:flex space-x-6 "
          onClick={() => navigate(`/repo/postview`, { state: post_content })}
        >
          <div className="grid grid-row gap-3">
            <img
              className="mx-auto w-full block w-full h-40 rounded-lg"
              alt="art cover"
              loading="lazy"
              src="https://picsum.photos/seed/2/2000/1000"
            />
            <div className="sm:w-full pl-0 p-5">
              <div className="space-y-2">
                <div className="space-y-4 ">
                  <h3 className="text-3xl font-semibold text-cyan-900 text-justify">{_postId}</h3>
                </div>
                {/* <div>
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
                </div> */}
                <div className="flex items-center space-x-4 justify-between">
                  <div className="flex gap-3 space-y-1">
                    <span className="text-md">
                      {post_content == null ? "nodtata" : post_content}
                    </span>
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
                    <p className="text-xs">{_createTime}</p>
                  </div>
                  <div className=" px-3 py-1 rounded-lg flex space-x-2 flex-row">
                    <div className="cursor-pointer text-center text-md justify-center items-center flex">
                      <svg
                        stroke="currentColor"
                        fill="currentColor"
                        strokeWidth="0"
                        viewBox="0 0 1024 1024"
                        height="1em"
                        width="1em"
                        xmlns="http://www.w3.org/2000/svg"
                        className="text-md"
                      >
                        <path d="M923 283.6a260.04 260.04 0 0 0-56.9-82.8 264.4 264.4 0 0 0-84-55.5A265.34 265.34 0 0 0 679.7 125c-49.3 0-97.4 13.5-139.2 39-10 6.1-19.5 12.8-28.5 20.1-9-7.3-18.5-14-28.5-20.1-41.8-25.5-89.9-39-139.2-39-35.5 0-69.9 6.8-102.4 20.3-31.4 13-59.7 31.7-84 55.5a258.44 258.44 0 0 0-56.9 82.8c-13.9 32.3-21 66.6-21 101.9 0 33.3 6.8 68 20.3 103.3 11.3 29.5 27.5 60.1 48.2 91 32.8 48.9 77.9 99.9 133.9 151.6 92.8 85.7 184.7 144.9 188.6 147.3l23.7 15.2c10.5 6.7 24 6.7 34.5 0l23.7-15.2c3.9-2.5 95.7-61.6 188.6-147.3 56-51.7 101.1-102.7 133.9-151.6 20.7-30.9 37-61.5 48.2-91 13.5-35.3 20.3-70 20.3-103.3.1-35.3-7-69.6-20.9-101.9zM512 814.8S156 586.7 156 385.5C156 283.6 240.3 201 344.3 201c73.1 0 136.5 40.8 167.7 100.4C543.2 241.8 606.6 201 679.7 201c104 0 188.3 82.6 188.3 184.5 0 201.2-356 429.3-356 429.3z"></path>
                      </svg>
                      <span className="text-md mx-1">{_likeCount}</span>
                    </div>
                    <div className="cursor-pointer text-center text-md justify-center items-center flex">
                      <svg
                        stroke="currentColor"
                        fill="currentColor"
                        strokeWidth="0"
                        viewBox="0 0 24 24"
                        height="1em"
                        width="1em"
                        xmlns="http://www.w3.org/2000/svg"
                        className="text-md"
                      >
                        <path d="M20 2H4c-1.103 0-2 .897-2 2v18l5.333-4H20c1.103 0 2-.897 2-2V4c0-1.103-.897-2-2-2zm0 14H6.667L4 18V4h16v12z"></path>
                        <circle cx="15" cy="10" r="2"></circle>
                        <circle cx="9" cy="10" r="2"></circle>
                      </svg>
                      <span className="text-md mx-1">{_commentCount}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
