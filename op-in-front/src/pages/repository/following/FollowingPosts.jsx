import React from "react";
import Post from "@components/repository/RepoPost";

export default function FollowingPosts() {
  return (
    <div className="grid grid-cols-2 gap-4 w-full ml-4">
      <Post />
      <Post />
      <Post />
      <Post />
      <Post />
    </div>
  );
}
