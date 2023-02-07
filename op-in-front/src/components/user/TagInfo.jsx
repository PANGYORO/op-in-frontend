import React from "react";

export default function TagInfo(props) {
  const rendering = (list) => {
    const result = [];
    for (let i = 0; i < props.titlelength; i++) {
      result.push(
        <div
          key={i}
          className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full"
        >
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
          {list[i]}
        </div>
      );
    }
    return result;
  };
  return (
    <>
      <p className="p-4 font-bold text-black text-md dark:text-white">
        {props.title}
        <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
          ({props.titlelength})
        </span>
      </p>
      <div className="mb-3">
        <div>{rendering(props.taglist)}</div>
      </div>
    </>
  );
}
