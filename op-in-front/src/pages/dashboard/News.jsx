import React, { useEffect, useState } from "react";
import Post from "@components/Post";
import http from "@api/http";

const PostList = ({ posts }) => {
  // console.log(posts);
  return (
    <div className="grid grid-cols-2 gap-4 ml-4">
      {posts.map((post) => {
        // console.log(post.id);
        return (
          <Post
            key={post.id}
            id={post.id}
            createTime={post.date}
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

const News = () => {
  const [results, setResults] = useState([]);
  useEffect(() => {
    http
      .get(`post/news?page=0&size=10`)
      .then(({ data }) => {
        setResults([...data.repoPostSimpleResponseList]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [])
  return (
    <>
      <PostList posts={results} />
    </>
  );
}
export default News;