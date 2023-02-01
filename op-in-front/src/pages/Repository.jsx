import React from "react";
import Status from "../components/Status"
import Repo from "../components/Repo"

export default function Repository() {
  return (
    <div class="bg-white dark:bg-gray-800 w-100 shadow-lg mx-auto rounded-xl p-4">
      <div class="inline-flex grid-cols-5 gap-1 ">
        <div class="col-start-1 col-end-3">
    <Repo />
    <Repo />
    <Repo />
        </div>
        <div class="col-start-4 col-end-5">
    <Status />
        </div>
    </div>
    </div>
  )
}
