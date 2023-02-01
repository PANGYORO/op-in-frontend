import React from "react";
import DefaultImg from "../assets/basicprofile.png";

export default function Comment() {
  return (
    <div class='flex flex-row inline-flex place-item-start'>
      <div className='mr-4'>
        <img alt="profile" src={DefaultImg} class="mx-auto object-cover h-10 " />
      </div>
      <div className='flex items-center'>
        <p> 댓글입니다~ </p>
      </div>
    </div>
  )
}
