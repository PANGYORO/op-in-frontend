import React, { useState, useEffect } from "react";
import Post from "@components/Post";
import http from "@api/http";
import { useToast } from "@hooks/useToast";

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

const Posts = ({ value }) => {
  const [results, setResults] = useState([]);
  const [page, setPage] = useState(0);
  const { setToast } = useToast();
  const size = 10;
  useEffect(() => {
    http
      .get(`search/posts?query=${value}&size=${size}&page=${page}`)
      .then(({ data }) => {
        if (!(data?.length)) setToast({ message: "Post 검색 결과가 존재하지 않습니다." });
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [value]);
  const getPreviousData = (pageNum) => {
    http
      .get(`search/posts?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        setResults([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  const getNextData = (pageNum) => {
    http
      .get(`search/posts?query=${value}&size=${size}&page=${pageNum}`)
      .then(({ data }) => {
        console.log(data);
        if (data?.length > 0) {
          setResults([...data]);
          setPage(page + 1);
        }
        else {
          setToast({ message: "마지막 페이지입니다." });
        }
        console.log(page);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  return (
    <>
      <div className="grid justify-center">
        <div className="pb-3">
          <div onClick={() => {
            if (page != 0) {
              getPreviousData(page - 1);
              setPage(page - 1);
            } else {
              setToast({ message: "첫 페이지입니다." });
            }
          }} className="inline-flex items-center px-4 py-2 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            &lt; Previous
          </div>
          <div onClick={() => {
            getNextData(page + 1);
          }} className="inline-flex items-center px-4 py-2 ml-3 text-xl font-medium text-gray-500 bg-white border border-gray-300 rounded-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            Next	&gt;
          </div>
        </div>
      </div>
      <PostList posts={results} />

    </>
  );
};
export default Posts;
