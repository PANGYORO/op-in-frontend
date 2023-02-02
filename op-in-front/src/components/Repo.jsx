import React from "react";
import axios from "axios";

// const Reopitem = ({ repos }) => {
//     const { img, name, contributor, star } = repos;
//     return (

//     )
//   }
export default function Repo() {
    return (
        <>
        
        <div className="flex flex-col grid grid-row-3 bg-white dark:bg-gray-800 w-full shadow-lg rounded-xl p-4">
            <div>
                <span class='text-lg font-bold'>참여레포</span>
            </div>
            <div className="grid grid-cols-1 place-contnet-start">
                <p class="text-gray-600 dark:text-white my-5 pl-2">
                    <span class="text-lg font-bold text-indigo-500 ">
                        “
                    </span>
                    내용칸 입니다.
                    내용칸 입니다.
                    <span class="text-lg font-bold text-indigo-500">
                        ”
                    </span>
                </p>
            </div>
            <div className='grid grid-col place-content-bottom'>
                <div class="inline-flex items-center bg-white leading-none ${props.textColor} rounded-full p-2 shadow text-teal text-sm">
                    <div class="flex flex-wrap items-center gap-4">
                        <span class="px-4 py-1  text-base rounded-full text-red-600  bg-red-200 ">
                            java
                        </span>
                        <span class="px-4 py-1  text-base rounded-full text-yellow-600  bg-yellow-200 ">
                            jupiter notebook
                        </span>
                        <span class="px-4 py-1  text-base rounded-full text-green-600  bg-green-200 ">
                            python
                        </span>
                    </div>
                </div>
            </div>

            </div>
        </>
    )
}