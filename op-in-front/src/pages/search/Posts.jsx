import React, { useState, useEffect } from "react";
import Post from "@components/Post";
import http from "@api/http";

const PostList = ({ posts }) => {
  console.log(posts);
  return (
    <div className="grid grid-cols-2 gap-4 ml-4">
      {posts.map((post) => {
        return (
          <Post
            key={post.id}
            postId={post.id}
            createTime={post.createTime}
            title={post.title}
            likeCount={post.likeCount}
            commentCount={post.commentCount}
            pageContent={post.pageContent}
            authorMemberAvatar={post.authorMemberAvatar}
            authorMemberName={post.authorMemberName}
          />
        );
      })}
    </div>
  );
};

const Posts = ({ value }) => {
  const [results, setResults] = useState([]);
  useEffect(() => {
    console.log("value:" + value);
    http
      .get(`search/repos?query=${value}&size=10&page=0`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  return (
    <>
      <PostList posts={results} />
    </>
  );
};
export default Posts;
