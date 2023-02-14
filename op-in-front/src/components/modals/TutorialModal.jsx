import React from "react";
import { Fragment, useRef } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { useNavigate } from "react-router-dom";


export default function QnaModal({ open, setOpen }) {
  const cancelButtonRef = useRef(null);
  const navigate = useNavigate();

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
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-2/3">
                <div className="bg-white p-6 sm:p-6 sm:pb-4">
                  <div className="sm:flex sm:items-start">
                    <div className="mt-3 text-center sm:mt-0 sm:m-4 sm:text-left">
                      <Dialog.Title
                          as="h2"
                          className="text-2xl font-bold leading-6 text-gray-900 pt-4 pb-4"
                        >
                        Tutorial은 다음 환경에서 진행됩니다!
                      </Dialog.Title>
                      <div className="grid grid-cols-2 mt-2 items-center">
                        <div className="grid grid-rows-2  mt-2 justify-center">
                          <div className='grid grid-rows-2 items-center'>
                            <p>
                              1. git이 설치된 터미널에서 진행됩니다
                            </p>
                            <div className='flex flex-row grid grid-cols-2'>
                              <svg className='ml-10' width="70px" height="70px" viewBox="0 0 15 15" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M6.79286 1.20708L7.14642 1.56063L7.14642 1.56063L6.79286 1.20708ZM1.20708 6.79287L0.853524 6.43931H0.853524L1.20708 6.79287ZM1.20708 8.20708L1.56063 7.85352L1.56063 7.85352L1.20708 8.20708ZM6.79287 13.7929L6.43931 14.1464L6.79287 13.7929ZM8.20708 13.7929L7.85352 13.4393L8.20708 13.7929ZM13.7929 8.20708L14.1464 8.56063L13.7929 8.20708ZM13.7929 6.79286L13.4393 7.14642L13.7929 6.79286ZM8.20708 1.20708L8.56063 0.853524V0.853524L8.20708 1.20708ZM6.43931 0.853525L0.853524 6.43931L1.56063 7.14642L7.14642 1.56063L6.43931 0.853525ZM0.853525 8.56063L6.43931 14.1464L7.14642 13.4393L1.56063 7.85352L0.853525 8.56063ZM8.56063 14.1464L14.1464 8.56063L13.4393 7.85352L7.85352 13.4393L8.56063 14.1464ZM14.1464 6.43931L8.56063 0.853524L7.85352 1.56063L13.4393 7.14642L14.1464 6.43931ZM14.1464 8.56063C14.7322 7.97484 14.7322 7.0251 14.1464 6.43931L13.4393 7.14642C13.6346 7.34168 13.6346 7.65826 13.4393 7.85352L14.1464 8.56063ZM6.43931 14.1464C7.0251 14.7322 7.97485 14.7322 8.56063 14.1464L7.85352 13.4393C7.65826 13.6346 7.34168 13.6346 7.14642 13.4393L6.43931 14.1464ZM0.853524 6.43931C0.267737 7.0251 0.267739 7.97485 0.853525 8.56063L1.56063 7.85352C1.36537 7.65826 1.36537 7.34168 1.56063 7.14642L0.853524 6.43931ZM7.14642 1.56063C7.34168 1.36537 7.65826 1.36537 7.85352 1.56063L8.56063 0.853524C7.97484 0.267737 7.0251 0.267739 6.43931 0.853525L7.14642 1.56063ZM5.14642 2.85352L6.14642 3.85352L6.85352 3.14642L5.85352 2.14642L5.14642 2.85352ZM7.49997 4.99997C7.22383 4.99997 6.99997 4.77611 6.99997 4.49997H5.99997C5.99997 5.3284 6.67154 5.99997 7.49997 5.99997V4.99997ZM7.99997 4.49997C7.99997 4.77611 7.77611 4.99997 7.49997 4.99997V5.99997C8.3284 5.99997 8.99997 5.3284 8.99997 4.49997H7.99997ZM7.49997 3.99997C7.77611 3.99997 7.99997 4.22383 7.99997 4.49997H8.99997C8.99997 3.67154 8.3284 2.99997 7.49997 2.99997V3.99997ZM7.49997 2.99997C6.67154 2.99997 5.99997 3.67154 5.99997 4.49997H6.99997C6.99997 4.22383 7.22383 3.99997 7.49997 3.99997V2.99997ZM8.14642 5.85352L9.64642 7.35352L10.3535 6.64642L8.85352 5.14642L8.14642 5.85352ZM10.5 7.99997C10.2238 7.99997 9.99997 7.77611 9.99997 7.49997H8.99997C8.99997 8.3284 9.67154 8.99997 10.5 8.99997V7.99997ZM11 7.49997C11 7.77611 10.7761 7.99997 10.5 7.99997V8.99997C11.3284 8.99997 12 8.3284 12 7.49997H11ZM10.5 6.99997C10.7761 6.99997 11 7.22383 11 7.49997H12C12 6.67154 11.3284 5.99997 10.5 5.99997V6.99997ZM10.5 5.99997C9.67154 5.99997 8.99997 6.67154 8.99997 7.49997H9.99997C9.99997 7.22383 10.2238 6.99997 10.5 6.99997V5.99997ZM6.99997 5.49997V9.49997H7.99997V5.49997H6.99997ZM7.49997 11C7.22383 11 6.99997 10.7761 6.99997 10.5H5.99997C5.99997 11.3284 6.67154 12 7.49997 12V11ZM7.99997 10.5C7.99997 10.7761 7.77611 11 7.49997 11V12C8.3284 12 8.99997 11.3284 8.99997 10.5H7.99997ZM7.49997 9.99997C7.77611 9.99997 7.99997 10.2238 7.99997 10.5H8.99997C8.99997 9.67154 8.3284 8.99997 7.49997 8.99997V9.99997ZM7.49997 8.99997C6.67154 8.99997 5.99997 9.67154 5.99997 10.5H6.99997C6.99997 10.2238 7.22383 9.99997 7.49997 9.99997V8.99997Z" fill="#000000"/>
                              </svg>
                              <a
                              href='https://git-scm.com/'
                              target="_blank"
                              rel="noreferrer"
                              className="items-center mx-4 px-4 py-5 text-md font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                            >visit</a>
                            </div>
                          </div>
                          <div className='grid grid-rows-2 items-center'>
                            <p>
                              3. 에디터는 Visual Studio Code를 사용합니다
                            </p>
                            <div className='flex flex-row grid grid-cols-2'>
                              <svg className='ml-10' fill="#000000" width="70px" height="70px" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
                                <path d="M30.865 3.448l-6.583-3.167c-0.766-0.37-1.677-0.214-2.276 0.385l-12.609 11.505-5.495-4.167c-0.51-0.391-1.229-0.359-1.703 0.073l-1.76 1.604c-0.583 0.526-0.583 1.443-0.005 1.969l4.766 4.349-4.766 4.349c-0.578 0.526-0.578 1.443 0.005 1.969l1.76 1.604c0.479 0.432 1.193 0.464 1.703 0.073l5.495-4.172 12.615 11.51c0.594 0.599 1.505 0.755 2.271 0.385l6.589-3.172c0.693-0.333 1.13-1.031 1.13-1.802v-21.495c0-0.766-0.443-1.469-1.135-1.802zM24.005 23.266l-9.573-7.266 9.573-7.266z"/>
                              </svg>
                              <a
                              href='https://code.visualstudio.com/Download'
                              target="_blank"
                              rel="noreferrer"
                              className="items-center mx-4 px-4 py-5 text-md font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                            >visit</a>
                            </div>
                          </div>
                      
                        </div>
                        <div className="grid grid-rows-2 mt-2 ml-20 justify-center">
                          <div className='grid grid-rows-2 items-center'>
                            <p>
                              2. git hub 로그인을 해주세요
                            </p>
                            <div className='flex flex-row grid grid-cols-2'>
                              <svg  className='ml-10' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="70px" width="70px">
                                <path d="M400 32H48C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48zM277.3 415.7c-8.4 1.5-11.5-3.7-11.5-8 0-5.4.2-33 .2-55.3 0-15.6-5.2-25.5-11.3-30.7 37-4.1 76-9.2 76-73.1 0-18.2-6.5-27.3-17.1-39 1.7-4.3 7.4-22-1.7-45-13.9-4.3-45.7 17.9-45.7 17.9-13.2-3.7-27.5-5.6-41.6-5.6-14.1 0-28.4 1.9-41.6 5.6 0 0-31.8-22.2-45.7-17.9-9.1 22.9-3.5 40.6-1.7 45-10.6 11.7-15.6 20.8-15.6 39 0 63.6 37.3 69 74.3 73.1-4.8 4.3-9.1 11.7-10.6 22.3-9.5 4.3-33.8 11.7-48.3-13.9-9.1-15.8-25.5-17.1-25.5-17.1-16.2-.2-1.1 10.2-1.1 10.2 10.8 5 18.4 24.2 18.4 24.2 9.7 29.7 56.1 19.7 56.1 19.7 0 13.9.2 36.5.2 40.6 0 4.3-3 9.5-11.5 8-66-22.1-112.2-84.9-112.2-158.3 0-91.8 70.2-161.5 162-161.5S388 165.6 388 257.4c.1 73.4-44.7 136.3-110.7 158.3zm-98.1-61.1c-1.9.4-3.7-.4-3.9-1.7-.2-1.5 1.1-2.8 3-3.2 1.9-.2 3.7.6 3.9 1.9.3 1.3-1 2.6-3 3zm-9.5-.9c0 1.3-1.5 2.4-3.5 2.4-2.2.2-3.7-.9-3.7-2.4 0-1.3 1.5-2.4 3.5-2.4 1.9-.2 3.7.9 3.7 2.4zm-13.7-1.1c-.4 1.3-2.4 1.9-4.1 1.3-1.9-.4-3.2-1.9-2.8-3.2.4-1.3 2.4-1.9 4.1-1.5 2 .6 3.3 2.1 2.8 3.4zm-12.3-5.4c-.9 1.1-2.8.9-4.3-.6-1.5-1.3-1.9-3.2-.9-4.1.9-1.1 2.8-.9 4.3.6 1.3 1.3 1.8 3.3.9 4.1zm-9.1-9.1c-.9.6-2.6 0-3.7-1.5s-1.1-3.2 0-3.9c1.1-.9 2.8-.2 3.7 1.3 1.1 1.5 1.1 3.3 0 4.1zm-6.5-9.7c-.9.9-2.4.4-3.5-.6-1.1-1.3-1.3-2.8-.4-3.5.9-.9 2.4-.4 3.5.6 1.1 1.3 1.3 2.8.4 3.5zm-6.7-7.4c-.4.9-1.7 1.1-2.8.4-1.3-.6-1.9-1.7-1.5-2.6.4-.6 1.5-.9 2.8-.4 1.3.7 1.9 1.8 1.5 2.6z" />
                              </svg>
                              <a
                              href='https://github.com/login'
                              target="_blank"
                              rel="noreferrer"
                              className="items-center mx-4 px-4 py-5 text-md font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                            >visit</a>
                            </div>
                          </div>
                          <div className='grid grid-rows-2 items-center'>
                            <p>
                              4. first contribution git hub page를 띄어주세여
                            </p>
                            <div className='flex flex-row grid grid-cols-2'>
                              <svg className='ml-10' fill="#000000" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" viewBox="0 0 962.689 962.689">
                                <g>
                                  <g>
                                    <path d="M254.233,833.65l115.735,129.039l111.377-275.766l111.377,275.766L708.457,833.65l172.888,12.469L734.478,482.484
                                      c35.706-51.094,54.944-111.771,54.944-175.408c0-82.023-31.941-159.138-89.941-217.137C641.481,31.941,564.368,0,482.345,0
                                      S323.208,31.941,265.209,89.94c-58,57.999-89.941,135.113-89.941,217.137c0,62.88,18.792,122.864,53.685,173.573L81.345,846.119
                                      L254.233,833.65z M482.345,68c63.86,0,123.896,24.868,169.053,70.024c45.156,45.155,70.024,105.193,70.024,169.053
                                      c0,33.158-6.72,65.28-19.489,94.829c-10.885,25.191-26.171,48.508-45.473,68.99c-1.661,1.764-3.342,3.513-5.063,5.232
                                      c-30.952,30.954-68.897,52.37-110.272,62.782c-14.146,3.561-28.693,5.82-43.498,6.748c-5.067,0.316-10.16,0.494-15.282,0.494
                                      c-5.779,0-11.526-0.209-17.234-0.613c-14.914-1.057-29.555-3.488-43.784-7.217c-40.504-10.615-77.643-31.801-108.035-62.194
                                      c-2.206-2.205-4.349-4.457-6.457-6.731c-19.25-20.774-34.424-44.396-45.108-69.894c-12.103-28.885-18.459-60.167-18.459-92.428
                                      c0-63.859,24.868-123.897,70.024-169.053C358.447,92.868,418.484,68,482.345,68z M777.445,770.449l-97.351-7.021l-65.168,72.66
                                      l-90.778-224.762c59.204-8.01,114.369-32.984,159.727-72.555L777.445,770.449z M279.341,537.469
                                      c45.152,39.895,100.174,65.229,159.303,73.602l-90.881,225.02l-65.168-72.66l-97.351,7.02L279.341,537.469z"/>
                                    <polygon points="464.764,260.005 464.764,437.148 464.764,450.542 464.764,459.668 520.521,459.668 532.764,459.668 
                                      532.764,423.078 532.764,132.962 375.254,237.944 412.968,294.528 		"/>
                                  </g>
                                </g>
                              </svg>
                              <a
                              href='https://github.com/firstcontributions/first-contributions'
                              target="_blank"
                              rel="noreferrer"
                              className="items-center mx-4 px-4 py-5 text-md font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                            >visit</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                  <button
                    type="button"
                    className="inline-flex w-full justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm"
                    onClick={(e) => {
                      e.preventDefault()
                      setOpen(false)
                      navigate('/tutorial/pr')
                      }
                    }
                  >
                    Tutorial
                  </button>
                  <button
                    type="button"
                    className="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
                    onClick={() => setOpen(false)}
                    ref={cancelButtonRef}
                  >
                    Cancel
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
