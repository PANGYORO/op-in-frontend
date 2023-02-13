import React from "react";
import Post from "@components/Post";

const News = () => {
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
export default News;