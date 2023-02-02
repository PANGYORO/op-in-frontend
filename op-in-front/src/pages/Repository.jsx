import React from "react";
import Status from "../components/Status";
import Repo from "../components/Repo";
import Sidebar from "../components/SideBar";
import QnA from "../components/QnA"
import TestRR from "../components/TestRR"






export default function Repository({
  // repos = [{
  //   title: '우리레포는요...',
  //   content: '내용이 어쩌구 저쩌고',
  //   language: 'python',
  //   contributor: [{ nickname: '짱짱맨' },{id:''}, {profileimg:''}],
  //   star: 341110,
  //   forknum : 321,
  //   topic: 'react',
  //   updatedate: '2021-01-02'
  // }]

}) {
  return (
    <>
      <Sidebar />
      
      <div className="flex flex-col w-full pl-0 md:p-4">
        

        <div className="flex items-top w-full">
          <div class="grid grid-cols-2  gap-4 w-full">
            <Repo />
            <QnA />
            <QnA />
            <Repo />
            <Repo />
            <TestRR />
       
          </div>
          
          <div class="w-1/3">
            <Status />
          </div>

        </div>
      </div>
    </>
  );
}
