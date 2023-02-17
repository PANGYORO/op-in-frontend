import React from "react";
import { useNavigate } from "react-router-dom";

export default function post() {
  const navigate = useNavigate();

  return (
    <>
      <div className="ml-4 mb-4">
        <div
          className=" w-full p-4 bg-white shadow-lg rounded-2xl dark:bg-gray-700 items-center justify-center group sm:flex space-x-6 "
          onClick={() => navigate(`/postview`)}
        >
          <div className="sm:w-8/12 pl-0 p-5">
            <div className="space-y-2">
              <div className="space-y-4 ">
                <h3 className="text-3xl font-semibold text-cyan-900 text-justify">
                  How to Manage Project
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
                  <span className="text-md">
                    It is a daunting task to manage a project from start to
                    finish – or any number of tasks for that matter. Usually,
                    such problems arise as a result of a person not being
                    equipped with project management certifications...
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
                  <p className="text-xs">2 hours ago</p>
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
                    <span className="text-md mx-1">80</span>
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
                    <span className="text-md mx-1">80</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <img
            className="mx-auto w-full block w-4/12 h-40 rounded-lg"
            alt="art cover"
            loading="lazy"
            src="https://picsum.photos/seed/2/2000/1000"
          />
        </div>
      </div>

      {/* <div className="ml-4 mb-4">
      <div className="w-full p-4 bg-white shadow-lg rounded-2xl dark:bg-gray-700">
        <div className="flex items-center justify-between mb-6">
          <div className="flex items-center">
            <span className="relative p-2 bg-blue-100 rounded-xl">
              <svg
                width="25"
                height="25"
                viewBox="0 0 2447.6 2452.5"
                xmlns="http://www.w3.org/2000/svg"
              >
                <g clipRule="evenodd" fillRule="evenodd">
                  <path
                    d="m897.4 0c-135.3.1-244.8 109.9-244.7 245.2-.1 135.3 109.5 245.1 244.8 245.2h244.8v-245.1c.1-135.3-109.5-245.1-244.9-245.3.1 0 .1 0 0 0m0 654h-652.6c-135.3.1-244.9 109.9-244.8 245.2-.2 135.3 109.4 245.1 244.7 245.3h652.7c135.3-.1 244.9-109.9 244.8-245.2.1-135.4-109.5-245.2-244.8-245.3z"
                    fill="#36c5f0"
                  ></path>
                  <path
                    d="m2447.6 899.2c.1-135.3-109.5-245.1-244.8-245.2-135.3.1-244.9 109.9-244.8 245.2v245.3h244.8c135.3-.1 244.9-109.9 244.8-245.3zm-652.7 0v-654c.1-135.2-109.4-245-244.7-245.2-135.3.1-244.9 109.9-244.8 245.2v654c-.2 135.3 109.4 245.1 244.7 245.3 135.3-.1 244.9-109.9 244.8-245.3z"
                    fill="#2eb67d"
                  ></path>
                  <path
                    d="m1550.1 2452.5c135.3-.1 244.9-109.9 244.8-245.2.1-135.3-109.5-245.1-244.8-245.2h-244.8v245.2c-.1 135.2 109.5 245 244.8 245.2zm0-654.1h652.7c135.3-.1 244.9-109.9 244.8-245.2.2-135.3-109.4-245.1-244.7-245.3h-652.7c-135.3.1-244.9 109.9-244.8 245.2-.1 135.4 109.4 245.2 244.7 245.3z"
                    fill="#ecb22e"
                  ></path>
                  <path
                    d="m0 1553.2c-.1 135.3 109.5 245.1 244.8 245.2 135.3-.1 244.9-109.9 244.8-245.2v-245.2h-244.8c-135.3.1-244.9 109.9-244.8 245.2zm652.7 0v654c-.2 135.3 109.4 245.1 244.7 245.3 135.3-.1 244.9-109.9 244.8-245.2v-653.9c.2-135.3-109.4-245.1-244.7-245.3-135.4 0-244.9 109.8-244.8 245.1 0 0 0 .1 0 0"
                    fill="#e01e5a"
                  ></path>
                </g>
              </svg>
            </span>
            <div className="flex flex-col">
              <span className="ml-2 font-bold text-black text-md dark:text-white">Slack</span>
              <span className="ml-2 text-sm text-gray-500 dark:text-white">Slack corporation</span>
            </div>
          </div>
          <div className="flex items-center">
            <button className="p-1 border border-gray-200 rounded-full">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="20"
                height="20"
                className="w-4 h-4 text-yellow-500"
                fill="currentColor"
                viewBox="0 0 1792 1792"
              >
                <path d="M1728 647q0 22-26 48l-363 354 86 500q1 7 1 20 0 21-10.5 35.5t-30.5 14.5q-19 0-40-12l-449-236-449 236q-22 12-40 12-21 0-31.5-14.5t-10.5-35.5q0-6 2-20l86-500-364-354q-25-27-25-48 0-37 56-46l502-73 225-455q19-41 49-41t49 41l225 455 502 73q56 9 56 46z"></path>
              </svg>
            </button>
            <button className="text-gray-200">
              <svg
                width="25"
                height="25"
                fill="currentColor"
                viewBox="0 0 1792 1792"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M1088 1248v192q0 40-28 68t-68 28h-192q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h192q40 0 68 28t28 68zm0-512v192q0 40-28 68t-68 28h-192q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h192q40 0 68 28t28 68zm0-512v192q0 40-28 68t-68 28h-192q-40 0-68-28t-28-68v-192q0-40 28-68t68-28h192q40 0 68 28t28 68z"></path>
              </svg>
            </button>
          </div>
        </div>
        <div className="flex items-center justify-between mb-4 space-x-12">
          <span className="flex items-center px-2 py-1 text-xs font-semibold text-green-700 rounded-md bg-green-50">
            COMPLETED
          </span>
          <span className="flex items-center px-2 py-1 text-xs font-semibold text-green-600 bg-white border border-green-600 rounded-md">
            MEDIUM PRIORITY
          </span>
        </div>
        <div className="block m-auto">
          <div>
            <span className="inline-block text-sm text-gray-500 dark:text-gray-100">
              Task done :<span className="font-bold text-gray-700 dark:text-white">50</span>
              /50
            </span>
          </div>
          <div className="w-full h-2 mt-2 bg-gray-200 rounded-full">
            <div className="w-full h-full text-xs text-center text-white bg-pink-400 rounded-full"></div>
          </div>
        </div>
        <div className="flex items-center justify-start my-4 space-x-4">
          <span className="flex items-center px-2 py-1 text-xs font-semibold text-green-500 rounded-md bg-green-50">
            IOS APP
          </span>
          <span className="flex items-center px-2 py-1 text-xs font-semibold text-yellow-600 bg-yellow-200 rounded-md">
            ANDROID
          </span>
        </div>
        <div className="flex -space-x-2">
          <a href="#" className="">
            <img
              className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
              src="/images/person/1.jpg"
              alt="Guy"
            />
          </a>
          <a href="#" className="">
            <img
              className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
              src="/images/person/2.jpeg"
              alt="Max"
            />
          </a>
          <a href="#" className="">
            <img
              className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
              src="/images/person/3.jpg"
              alt="Charles"
            />
          </a>
          <a href="#" className="">
            <img
              className="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white"
              src="/images/person/4.jpg"
              alt="Jade"
            />
          </a>
        </div>
        <span className="flex items-center px-2 py-1 mt-4 text-xs font-semibold text-yellow-500 bg-yellow-100 rounded-md w-36">
          DUE DATE : 18 JUN
        </span>
      </div>
    </div> */}
    </>
  );
}
