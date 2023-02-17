import { Fragment, useRef, useState } from "react";
import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { Editor } from "@toast-ui/react-editor";
import http from "@api/http";
import { useToast } from "@hooks/useToast";

const PostModifyModal = ({
  id,
  title,
  previousValue,
  open,
  setOpen,
  propFunction,
}) => {
  const cancelButtonRef = useRef(null);

  const toastuiEditor = useRef();
  const [curTitle, setTitle] = useState(title);
  const [data, setData] = useState(previousValue);
  const { setToast } = useToast();

  const titleChangeHandler = (e) => {
    setTitle(e.currentTarget.value);
  };

  const onChange = () => {
    setData(toastuiEditor.current.getInstance().getMarkdown());
  };

  const modifyPost = async () => {
    // 서버에 데이터 보내는 로직
    await http
      .put(`post`, { postTitle: curTitle, postContent: data, postId: id })
      .then(() => {
        propFunction({ postTitle: curTitle, postContent: data });
      })
      .catch((error) => {
        console.debug(error);
      });
  };

  const uploadImage = async (file, callback) => {
    const formData = new FormData();
    formData.append("uploadImage", file);

    http
      .post("post/upload/image", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(({ data }) => {
        callback(data.url, "");
        setToast("이미지 등록이 완료됐습니다.");
      })
      .catch((e) => {
        console.error(e);
      });
  };

  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog
        as="div"
        className="relative z-40"
        initialFocus={cancelButtonRef}
        onClose={setOpen}
      >
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
                <div className="bg-white p-6 sm:p-6 sm:pb-4">
                  <div className="w-full sm:items-start">
                    <div className="mt-3 text-center sm:mt-0 sm:m-4 sm:text-left">
                      <Dialog.Title
                        as="h2"
                        className="text-3xl font-bold leading-6 text-gray-900 pt-4 pb-4"
                      >
                        Post Modify
                      </Dialog.Title>

                      <div className=" relative  p-4">
                        <input
                          type="text"
                          id="name-with-label"
                          className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-400 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                          name="Title"
                          placeholder="Insert Title..."
                          value={curTitle}
                          onChange={titleChangeHandler}
                        />
                      </div>

                      <div className="mt-2">
                        <Editor
                          height="500px"
                          initialValue={previousValue}
                          previewStyle="vertical"
                          ref={toastuiEditor}
                          onChange={onChange}
                          language="ko-KR"
                          hooks={{
                            addImageBlobHook: uploadImage,
                          }}
                          toolbarItems={[
                            // 툴바 옵션 설정
                            ["heading", "bold", "italic", "strike"],
                            ["hr", "quote"],
                            ["ul", "ol", "task", "indent", "outdent"],
                            ["table", "image", "link"],
                            ["code", "codeblock"],
                          ]}
                        />
                      </div>
                    </div>
                  </div>
                </div>
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
                      modifyPost();
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
};
export default PostModifyModal;
