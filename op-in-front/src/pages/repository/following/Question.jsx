import QnA from "../../../components/QnA";
import Status from "../../../components/Status";
import React from 'react';

export default function Question() {
  return (
    <>
      <div className="flex flex-col w-full pl-0 md:p-4">
        <div className="flex items-top w-full h-screen pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
          <div className="grid grid-cols-1 gap-4 w-full">
            <QnA />
            <QnA />
            <QnA />
          </div>
          <div className="w-1/3">
            <Status />
          </div>
        </div>
      </div>
    </>
  );
}
