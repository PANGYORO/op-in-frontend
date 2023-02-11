import React, { useEffect, useState, useRef } from "react";
import { Tooltip } from "react-tooltip";
import { useToast } from "@hooks/useToast";
import http from "@api/http";

const TagInfo = ({ title, taglist = [], ismine }) => {
  // console.log(taglist);
  // console.log(taglist.length);
  const [openState, setOpenState] = useState(false);
  const [curlength, setCurlength] = useState(0);
  const [amount, setAmount] = useState(0);
  const inputRef = useRef();

  useEffect(() => {
    setCurlength(taglist.length);
    setAmount(taglist.length);
  });

  // console.log(curlength + " " + amount);
  const { setToast } = useToast();

  useEffect(() => {
    if (openState && inputRef.current) {
      inputRef.current.focus();
    }
  }, [openState, inputRef]);

  const rendering = (list) => {
    const result = [];
    for (let i = 0; i < curlength; i++) {
      let tempid = title + "-minus-" + i;
      result.push(
        <span id={tempid} key={i}>
          <div
            id={tempid + "-value"}
            className="ml-4 text-xs inline-flex items-center font-bold leading-sm uppercase px-3 py-1 bg-green-200 text-green-700 rounded-full mb-2"
          >
            {list[i].title}
          </div>
          {ismine ? (
            <Tooltip anchorId={tempid} clickable variant="light" events={["hover"]}>
              <button
                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                type="button"
                onClick={() => {
                  MinusTag(list[i].title, document.getElementById(tempid));
                  //document.getElementById(tempid).remove();
                  //setAmount(amount - 1);
                }}
              >
                delete
              </button>
            </Tooltip>
          ) : (
            <span></span>
          )}
        </span>
      );
    }
    return result;
  };
  const MinusTag = async (data, e) => {
    if (title == "Language") {
      await http
        .post(`member/language/delete`, {
          title: data,
        })
        .then(() => {
          e.remove();
          setAmount(amount - 1);
          setToast({ message: data + " 언어 태그가 삭제되었습니다." });
        })
        .catch(() => {
          setToast({ message: "삭제 중 에러가 발생했습니다." });
        });
    } else if (title == "Topic") {
      await http
        .post(`member/topic/delete`, {
          title: data,
        })
        .then(() => {
          e.remove();
          setAmount(amount - 1);
          setToast({ message: data + " 토픽 태그가 삭제되었습니다." });
        })
        .catch(() => {
          setToast({ message: "삭제 중 에러가 발생했습니다." });
        });
    } else {
      setToast({ message: "잘못된 접근입니다." });
    }
  };
  const PlusTag = async (data) => {
    if (title == "Language") {
      await http
        .post(`member/language/put`, {
          title: data,
        })
        .then(() => {
          taglist.push({
            id: "",
            title: data,
          });
          setCurlength(Number(curlength) + 1);
          setAmount(Number(amount) + 1);
          setToast({ message: data + " 언어 태그가 추가되었습니다." });
        })
        .catch(() => {
          setToast({ message: "삭제 중 에러가 발생했습니다." });
        });
    } else if (title == "Topic") {
      await http
        .post(`member/topic/put`, {
          title: data,
        })
        .then(() => {
          taglist.push({
            id: "",
            title: data,
          });
          setCurlength(Number(curlength) + 1);
          setAmount(Number(amount) + 1);
          setToast({ message: data + " 토픽 태그가 추가되었습니다." });
        })
        .catch(() => {
          setToast({ message: "삭제 중 에러가 발생했습니다." });
        });
    } else {
      setToast({ message: "잘못된 접근입니다." });
    }
  };
  const AddTag = () => {
    return (
      <div className="w-full max-w-xs z-50 ">
        <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 grid justify-items-center">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor={"add" + title}>
              add New
            </label>
            <input
              ref={inputRef}
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id={"add" + title}
              type="text"
              placeholder="Insert Name..."
              onKeyUp={(e) => {
                if (e.key == "Enter") {
                  PlusTag(document.getElementById("add" + title).value);
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
                PlusTag(document.getElementById("add" + title).value);
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
        {title}
        <span className="ml-2 text-sm text-gray-500 dark:text-gray-300 dark:text-white">
          ({amount})
        </span>
      </p>
      <div className="mb-3">
        <span id={title}>{rendering(taglist)}</span>
        {ismine ? (
          <>
            <button
              id={title + "-plus"}
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
              anchorId={title + "-plus"}
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
          </>
        ) : (
          <div></div>
        )}
      </div>
    </>
  );
};
export default TagInfo;
