import DefaultImg from "../assets/basicprofile.png";


export default function Status() {
    return (
        <div class="relative max-w-xs p-4 overflow-hidden bg-white shadow-lg rounded-xl dark:bg-gray-800 my-2">
            <div class='my-2'>
                <span class='font-bold'>Contributors</span>
                <div class="relative max-w-xs overflow-hidden my-2">
                    <a href="#" class="block w-full h-full">
                        <div class="w-full">
                            <div class="flex items-center">
                                <div class="flex -space-x-2">
                                    <a href="#" class="">
                                        <img class="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white" src={DefaultImg} alt="Guy" />
                                    </a>
                                    <a href="#" class="">
                                        <img class="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white" src={DefaultImg} alt="Max" />
                                    </a>
                                    <a href="#" class="">
                                        <img class="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white" src={DefaultImg} alt="Max" />
                                    </a>
                                    <a href="#" class="">
                                        <img class="inline-block h-10 w-10 rounded-full object-cover ring-2 ring-white" src={DefaultImg} alt="Max" />
                                    </a>
                                </div>
                                <span class="ml-2 text-blue-500 dark:text-gray-300">
                                    + 4 more
                                </span>
                            </div>
                        </div>
                    </a>
                </div>

            </div>
            <div class='my-2'>이게 뭐지?</div>
            <div class='my-2 text-gray-400 dark:text-white-300'>2 months ago</div>
            <div class='my-2 font-bold'>About</div>
            <div class='inline-flex items-center w-auto my-2'>
                {/* 태그 양식 */}
                <button type="button" class="flex items-center w-auto px-3 py-1 text-base text-blue-600 bg-blue-200 rounded-full hover:bg-red-300 mx-1">
                    Starter
                </button>
                <button type="button" class="flex items-center w-auto px-3 py-1 text-base text-blue-600 bg-blue-200 rounded-full hover:bg-red-300 mx-1">
                    Starter
                </button>
                <button type="button" class="flex items-center w-auto px-3 py-1 text-base text-blue-600 bg-blue-200 rounded-full hover:bg-red-300 mx-1">
                    Starter
                </button>


            </div>
            <div class='m-4'>
                <div>
                    <div class="inline-flex">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 016-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0018 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25" />
                        </svg>
                        Readme
                    </div>
                </div>

                <div class="inline-flex">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z" />
                    </svg>
                    2.2k star</div>

                <div>

                    <div class="inline-flex"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z" />
                        <path strokeLinecap="round" strokeLinejoin="round" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                    </svg>
                        31watching</div>
                </div>
                <div>
                    <div class="inline-flex">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M7.217 10.907a2.25 2.25 0 100 2.186m0-2.186c.18.324.283.696.283 1.093s-.103.77-.283 1.093m0-2.186l9.566-5.314m-9.566 7.5l9.566 5.314m0 0a2.25 2.25 0 103.935 2.186 2.25 2.25 0 00-3.935-2.186zm0-12.814a2.25 2.25 0 103.933-2.185 2.25 2.25 0 00-3.933 2.185z" />
                        </svg>

                        366 forks</div>
                </div>
            </div>
        </div>
    )
}