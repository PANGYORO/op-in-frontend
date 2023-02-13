import React from "react";
import Post from "@components/Post";

export default function Hots() {
  return (
    <div className="grid grid-cols-2 max-xl:grid-cols-1 ml-4 gap-3">
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
    </div>
  );
}
