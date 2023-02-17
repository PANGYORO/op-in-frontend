import React from "react";
import Event from "@components/event/event";

export default function Events() {
  return (
    <>
      <div className=" h-screen overflow-auto w-full pt-4">
        <Event />
        <Event />
        <Event />
        <Event />
        <Event />
        <Event />
        <Event />
        <Event />
      </div>
    </>
  );
}
