import { Fragment, useRef } from "react";
import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import User from "@components/user/User";
import http from "@api/http";
import { userInfo } from "@recoil/user/atoms";

export default function ContributorsModal({ open, setOpen, contributors }) {
  const cancelButtonRef = useRef(null);

  const userRender = (list) => {
    const result = [];
    if (list != null)
      for (let i = 0; i < list.length; i++) {
        result.push(
          <>
            <User key={i} profileImg={list[i].profileImg} nickname={list[i].nickname} />
          </>
        );
      }
    return result;
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
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-1/2">
                <div className="bg-white p-6 sm:p-6 sm:pb-4">
                  <div className="w-full sm:items-start">
                    <div className="mt-3 text-center sm:mt-0 sm:m-4 sm:text-left">
                      <Dialog.Title
                        as="h2"
                        className="text-3xl font-bold leading-6 text-gray-900 pt-4 pb-4"
                      >
                        Contributors
                      </Dialog.Title>
                      <div className="mt-2 grid grid-cols-2 gap-2">{userRender(contributors)}</div>
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
                    close
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
