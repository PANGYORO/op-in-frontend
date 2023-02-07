import React, { useState } from "react";
import { Tooltip } from "react-tooltip";

const [openState, setOpenState] = useState("false");

function AddTag() {
  return (
    <div className="w-full max-w-xs z-50 ">
      <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 grid justify-items-center">
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="add">
            add New
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="add"
            type="text"
            placeholder="Insert Name..."
          />
        </div>
        <button
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          type="button"
          onClick={() => {
            setOpenState("false");
          }}
        >
          Add
        </button>
        <div className="flex items-center justify-between"></div>
      </form>
      <p className="text-center text-gray-500 text-xs">
        &copy;2020 Acme Corp. All rights reserved.
      </p>
    </div>
  );
}

export default function TagInfo(props) {
  const rendering = (list) => {
    const result = [];
    for (let i = 0; i < props.titlelength; i++) {
      result.push(
        <div
          key={i}
          className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full mb-2"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="20"
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
        <span id={props.title}>{rendering(props.taglist)}</span>
        <button
          id={props.title + "-plus"}
          type="button"
          onClick={() => {
            setOpenState(true);
          }}
          className="ml-4 px-3 py-1 text-white bg-slate-700 hover:bg-slate-800 focus:ring-4 focus:outline-none focus:ring-slate-300 font-medium rounded-full text-sm p-2.5 text-center inline-flex items-center mr-2 dark:bg-slate-600 dark:hover:bg-slate-700 dark:focus:ring-slate-800"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            className="w-5 h-5"
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
          </svg>

          <span className="sr-only">Icon description</span>
        </button>
        <Tooltip
          anchorId={props.title + "-plus"}
          // closeOnEsc="true"
          // isOpen="false"
          variant="light"
          events={["click"]}
          // isOpen={openState}
          // afterShow={() => {
          //   setOpenState("true");
          // }}
        >
          <AddTag />
        </Tooltip>
      </div>
    </>
  );
}
