import React, { useState } from "react";
import { Tooltip } from "react-tooltip";

export default function TagInfo(props) {
  const [openState, setOpenState] = useState(false);
  const [curlength, setCurlength] = useState(Number(props.titlelength));
  const [amount, setAmount] = useState(Number(props.titlelength));
  const rendering = (list) => {
    const result = [];
    for (let i = 0; i < curlength; i++) {
      let tempid = props.title + "-minus-" + i;
      result.push(
        <span id={tempid} key={i}>
          <div className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full mb-2">
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
          <Tooltip anchorId={tempid} clickable variant="light" events={["hover"]}>
            <button
              className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="button"
              onClick={() => {
                document.getElementById(tempid).remove();
                setAmount(amount - 1);
              }}
            >
              delete
            </button>
          </Tooltip>
        </span>
      );
    }
    return result;
  };
  const PlusTag = (data) => {
    props.taglist.push(data);
    setCurlength(Number(curlength) + 1);
    setAmount(Number(amount) + 1);
  };
  const AddTag = () => {
    return (
      <div className="w-full max-w-xs z-50 ">
        <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 grid justify-items-center">
          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-bold mb-2"
              htmlFor={"add" + props.title}
            >
              add New
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id={"add" + props.title}
              type="text"
              placeholder="Insert Name..."
              onKeyUp={(e) => {
                if (e.key == "Enter") {
                  PlusTag(document.getElementById("add" + props.title).value);
                  setOpenState(false);
                }
              }}
            />
          </div>
          <div className="flex flex-row justify-items-center gap-3">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="button"
              onClick={() => {
                PlusTag(document.getElementById("add" + props.title).value);
                setOpenState(false);
              }}
            >
              Add
            </button>
            <button
              className="bg-slate-200 outline-slate-500 hover:bg-slate-700 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="button"
              onClick={() => {
                setOpenState(false);
              }}
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    );
  };

  return (
    <>
      <p className="p-4 font-bold text-black text-md dark:text-white">
        {props.title}
        <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
          ({amount})
        </span>
      </p>
      <div className="mb-3">
        <span id={props.title}>{rendering(props.taglist)}</span>
        <button
          id={props.title + "-plus"}
          type="button"
          onClick={() => {
            // document.getElementById("add" + props.title).focus();
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
          clickable
          positionStrategy="fixed"
          variant="light"
          events={["click"]}
          isOpen={openState}
          afterShow={() => {
            setOpenState(true);
          }}
        >
          <AddTag />
        </Tooltip>
      </div>
    </>
  );
}
