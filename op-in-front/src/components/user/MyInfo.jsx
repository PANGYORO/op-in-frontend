import React from "react";
import BasicBadge from "../../assets/basicbadge.png";
import { Tooltip } from "react-tooltip";
import "react-tooltip/dist/react-tooltip.css";

export default function MyInfo() {
  return (
    <div className="mx-0 mb-4 h-full ">
      <div className="w-full h-full bg-white shadow-lg rounded-2xl dark:bg-gray-700">
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Badges
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            (??)
          </span>
        </p>
        <div className="grid grid-cols-8 gap-4 px-3 mb-3">
          <img id="badge-1" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-1" content="normal badge" />
          <img id="badge-2" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-2" content="normal badge" />
          <img id="badge-3" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-3" content="normal badge" />
          <img id="badge-4" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-4" content="normal badge" />
          <img id="badge-5" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-5" content="normal badge" />
          <img id="badge-6" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-6" content="normal badge" />
          <img id="badge-7" src={BasicBadge} alt="badge" />
          <Tooltip anchorId="badge-7" content="normal badge" />
        </div>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Languages
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            (??)
          </span>
        </p>
        <div className="mb-3">
          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full">
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
              className="feather feather-arrow-right mr-2"
            >
              <line x1="5" y1="12" x2="19" y2="12"></line>
              <polyline points="12 5 19 12 12 19"></polyline>
            </svg>
            java
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-orange-200 text-orange-700 rounded-full">
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
            javascript
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-red-200 text-red-700 rounded-full">
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
              className="feather feather-archive mr-2"
            >
              <polyline points="21 8 21 21 3 21 3 8"></polyline>
              <rect x="1" y="3" width="22" height="5"></rect>
              <line x1="10" y1="12" x2="14" y2="12"></line>
            </svg>
            HTML&CSS
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 rounded-full bg-white text-gray-700 border">
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
              className="feather feather-hard-drive mr-2"
            >
              <line x1="22" y1="12" x2="2" y2="12"></line>
              <path d="M5.45 5.11L2 12v6a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-6l-3.45-6.89A2 2 0 0 0 16.76 4H7.24a2 2 0 0 0-1.79 1.11z"></path>
              <line x1="6" y1="16" x2="6.01" y2="16"></line>
              <line x1="10" y1="16" x2="10.01" y2="16"></line>
            </svg>
            Python
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-blue-200 text-blue-700 rounded-full">
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
              className="feather feather-bell-off mr-2"
            >
              <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
              <path d="M18.63 13A17.89 17.89 0 0 1 18 8"></path>
              <path d="M6.26 6.26A5.86 5.86 0 0 0 6 8c0 7-3 9-3 9h14"></path>
              <path d="M18 8a6 6 0 0 0-9.33-5"></path>
              <line x1="1" y1="1" x2="23" y2="23"></line>
            </svg>
            React
          </div>
        </div>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Interests
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            (??)
          </span>
        </p>
        <div className="mb-3">
          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-blue-200 text-blue-700 rounded-full">
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
              className="feather feather-bell-off mr-2"
            >
              <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
              <path d="M18.63 13A17.89 17.89 0 0 1 18 8"></path>
              <path d="M6.26 6.26A5.86 5.86 0 0 0 6 8c0 7-3 9-3 9h14"></path>
              <path d="M18 8a6 6 0 0 0-9.33-5"></path>
              <line x1="1" y1="1" x2="23" y2="23"></line>
            </svg>
            JPA
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full">
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
              className="feather feather-arrow-right mr-2"
            >
              <line x1="5" y1="12" x2="19" y2="12"></line>
              <polyline points="12 5 19 12 12 19"></polyline>
            </svg>
            springboot
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-orange-200 text-orange-700 rounded-full">
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
            cd/ci
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-red-200 text-red-700 rounded-full">
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
              className="feather feather-archive mr-2"
            >
              <polyline points="21 8 21 21 3 21 3 8"></polyline>
              <rect x="1" y="3" width="22" height="5"></rect>
              <line x1="10" y1="12" x2="14" y2="12"></line>
            </svg>
            server
          </div>

          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 rounded-full bg-white text-gray-700 border">
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
              className="feather feather-hard-drive mr-2"
            >
              <line x1="22" y1="12" x2="2" y2="12"></line>
              <path d="M5.45 5.11L2 12v6a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-6l-3.45-6.89A2 2 0 0 0 16.76 4H7.24a2 2 0 0 0-1.79 1.11z"></path>
              <line x1="6" y1="16" x2="6.01" y2="16"></line>
              <line x1="10" y1="16" x2="10.01" y2="16"></line>
            </svg>
            UI
          </div>
        </div>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          Completed Contributes
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            (??)
          </span>
        </p>
        <ul>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">01</span>
              <span>Facebook</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">02</span>
              <span>Git</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">03</span>
              <span>naver</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">04</span>
              <span>google</span>
            </div>
          </li>
        </ul>
        <hr />
        <p className="p-4 font-bold text-black text-md dark:text-white">
          following Respsitories
          <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
            (??)
          </span>
        </p>
        <ul>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">01</span>
              <span>Facebook</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">02</span>
              <span>Git</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">03</span>
              <span>naver</span>
            </div>
          </li>
          <li className="flex items-center justify-between py-3 text-gray-600 border-b-2 border-gray-100 dark:text-gray-200 dark:border-gray-800">
            <div className="flex items-center justify-start text-sm">
              <span className="mx-4">04</span>
              <span>google</span>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
}
