import React from "react";

export default function Recommend() {
  return (
    <div>
      <div className="m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-80">
        <a href="#" className="block w-full h-full">
          <img alt="blog photo" src="/images/blog/1.jpg" className="object-cover w-full max-h-40" />
          <div className="w-full p-4 bg-white dark:bg-gray-800">
            <p className="font-medium text-indigo-500 text-md">Article</p>
            <p className="mb-2 text-xl font-medium text-gray-800 dark:text-white">Supercharged !</p>
            <p className="font-light text-gray-400 dark:text-gray-300 text-md">
              The new supercar is here, 543 cv and 140 000$. This is best racing GT about 7 years
              on...
            </p>
            <div className="flex items-center mt-4">
              <a href="#" className="relative block">
                <img
                  alt="profil"
                  src="/images/person/6.jpg"
                  className="mx-auto object-cover rounded-full h-10 w-10 "
                />
              </a>
              <div className="flex flex-col justify-between ml-4 text-sm">
                <p className="text-gray-800 dark:text-white">Jean Jacques</p>
                <p className="text-gray-400 dark:text-gray-300">20 mars 2029 - 6 min read</p>
              </div>
            </div>
          </div>
        </a>
      </div>
    </div>
  );
}
