import React, { useState } from "react";
import Post from "@components/Post";
import http from "@api/http";

const Hots = () => {
  // const [results, setResults] = useState([]);
  // useEffect(() => {
  //   http
  // })
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
export default Hots;