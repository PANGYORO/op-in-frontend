import React, { useState } from "react";
import { Fragment, useRef } from "react";
import { Dialog, Transition } from "@headlessui/react";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/react-editor";
import Prism from "prismjs";
import "prismjs/themes/prism.css";
import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import fontSize from "tui-editor-plugin-font-size";
import http from "@api/http";

export default function PostModal({ open, setOpen, propFunction }) {
  const cancelButtonRef = useRef(null);

  const toastuiEditor = useRef();
  const [data, setData] = useState("");

  const [title, setText] = useState("");

  const textChangeHandler = (e) => {
    setText(e.currentTarget.value);
  };

  const onChange = () => {
    setData(toastuiEditor.current.getInstance().getMarkdown());
    // setData(toastuiEditor.current.getInstance().getHTML());
  };

  const createPost = async () => {
    // 서버에 데이터 보내는 로직
    await http
      .post(`post`, {
        repositoryId: 1,
        title: title,
        content: data,
      })
      .then(() => {
        console.log("success");
      })
      .catch((error) => {
        console.log(error);
      });

    propFunction({
      title: title,
      content: data,
    });
    setText("");
  };

  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog as="div" className="relative z-40" initialFocus={cancelButtonRef} onClose={setOpen}>
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </Transition.Child>

        <div className="fixed inset-0 z-40 overflow-y-auto">
          <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              enterTo="opacity-100 translate-y-0 sm:scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 translate-y-0 sm:scale-100"
              leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            >
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-4/5">
                <Dialog.Title as="h2" className="text-3xl font-bold leading-6 text-gray-900 p-4">
                  New Post
                </Dialog.Title>
                <div className=" relative  p-4">
                  <input
                    type="text"
                    id="name-with-label"
                    className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-400 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                    name="Title"
                    placeholder="Insert Title..."
                    value={title}
                    onChange={textChangeHandler}
                  />
                </div>

                <Editor
                  height="500px"
                  initialValue=" "
                  previewStyle="vertical"
                  ref={toastuiEditor}
                  onChange={onChange}
                  language="ko-KR"
                  toolbarItems={[
                    // 툴바 옵션 설정
                    ["heading", "bold", "italic", "strike"],
                    ["hr", "quote"],
                    ["ul", "ol", "task", "indent", "outdent"],
                    ["table", "image", "link"],
                    ["code", "codeblock"],
                  ]}
                  plugins={[[codeSyntaxHighlight, { highlighter: Prism }, colorSyntax, fontSize]]}
                />
                <div className="bg-gray-50 px-4 p-4 sm:flex sm:flex-row-reverse sm:px-6">
                  <button
                    type="button"
                    className="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
                    onClick={() => setOpen(false)}
                    ref={cancelButtonRef}
                  >
                    Cancel
                  </button>
                  <button
                    type="button"
                    className="inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm"
                    onClick={() => {
                      setOpen(false);
                      createPost();
                    }}
                  >
                    Write
                  </button>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition.Root>
  );
}
