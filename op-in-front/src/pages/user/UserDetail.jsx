import React, { useState, useEffect, useRef } from "react";
import DefaultImg from "@assets/basicprofile.png";
// import Setting from "@assets/settings.png";
import Post from "@components/Post";
import MyInfo from "@components/user/MyInfo";
import http from "@api/http";
import { useLocation } from "react-router-dom";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilState } from "recoil";
import PasswordModifyModal from "@components/modals/PasswordModifyModal";
import { Tooltip } from "react-tooltip";

const PostList = ({ posts = [] }) =>
  posts.map((post) => (
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
  ));

const followClassState =
  "py-1 px-3 bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2  focus:ring-offset-2  opacity-70 rounded-lg ";

const followingClassState =
  "py-1 px-3 bg-orange-600 hover:bg-orange-700 focus:ring-orange-500  focus:ring-offset-red-200 text-white  transition ease-in duration-200 text-center font-semibold shadow-md focus:outline-none focus:ring-2   focus:ring-offset-2  opacity-70 rounded-lg ";

const UserDetail = () => {
  const location = useLocation();
  const currentNick = location.state;
  const [user, setUser] = useRecoilState(userInfo);

  const isMe = user.nickname == currentNick;
  const [open, setOpen] = useState(false);
  const [myinfo, setMyInfo] = useState("");
  const [Image, setImage] = useState();
  const [myPosts, setMyPosts] = useState([]);
  const [followState, setFollowState] = useState({
    state: false,
    classValue: followClassState,
    value: "follow",
  });
  const fileInput = useRef(null);

  useEffect(() => {
    if (Image) {
      imageUpload(Image);
    }
  }, [Image]);

  const onFileChange = (e) => {
    if (e.target.files[0]) {
      setImage(e.target.files[0]);
    }
  };

  const imageUpload = async (file) => {
    let formData = new FormData();
    formData.append("profilePhoto", file);
    await http
      .post(`member/profilePhoto`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(({ data }) => {
        setImage(null);
        setUser((prev) => ({
          ...prev,
          img_url: data.url,
        }));
        getMyPosts();
        getMember();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getMember = async () => {
    await http
      .post(`member/mypage`, {
        nickname: currentNick,
      })
      .then(({ data }) => {
        setMyInfo(data);
      })
      .catch(() => alert("error"));
  };

  const getMyPosts = async () => {
    await http
      .get(`post/member/${currentNick}`)
      .then(({ data }) => {
        console.log(data);
        setMyPosts([...data]);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    getMember();
    getMyPosts();
    if (user.nickname != currentNick) setFollowButton();
  }, []);

  const setFollowButton = async () => {
    await http
      .post(`member/follow/check`, {
        nickname: currentNick,
      })
      .then(({ data }) => {
        console.log("팔로우 체크 : " + (data ? "following" : "nofollow"));
        if (data) {
          setFollowState({
            state: true,
            classValue: followingClassState,
            value: "following",
          });
          console.log("initial following setted");
        } else {
          setFollowState({
            state: false,
            classValue: followClassState,
            value: "follow",
          });
          console.log("initial nofollow setted");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const followStateChange = async () => {
    // 팔로우 상태라면 언팔로우
    if (followState.state) {
      await http
        .post(`member/follow/delete`, {
          nickname: currentNick,
        })
        .then(() => {
          setFollowState({
            state: false,
            classValue: followClassState,
            value: "follow",
          });
          console.log("no follow set");
        })
        .catch((error) => {
          console.log(error);
        });
    }
    // 언팔로우 상태라면 팔로우
    else {
      await http
        .post(`member/follow`, {
          nickname: currentNick,
        })
        .then(() => {
          setFollowState({
            state: true,
            classValue: followingClassState,
            value: "following",
          });
          console.log("following set");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="flex items-start justify-between mx-44">
      <div className="w-full mx-4 my-4 h-screen overflow-auto">
        <div className="grid grid-cols-3 gap-3 mb-8">
          <div
            className="flex flex-col justify-center h-full ml-44"
            id="profile_img"
            style={{
              width: "200px",
              height: "200px",
              border: "1px solid gray",
              borderRadius: "200px",
              position: "relative",
              overflow: "hidden",
            }}
            onClick={() => {
              if (isMe) fileInput.current.click();
            }}
          >
            <img
              src={user.img_url || DefaultImg}
              style={{ width: "100%", height: "100%", objectFit: "contain" }}
            />
            {isMe && (
              <>
                <input
                  type="file"
                  style={{ display: "none" }}
                  accept="image/jpg,image/png,image/jpeg"
                  name="profile_img"
                  onChange={onFileChange}
                  ref={fileInput}
                />
              </>
            )}
          </div>
          <Tooltip anchorId="profile_img" content="Click to change Image" />
          <div className="flex justify-center col-span-2 lg:text-3xl md:text-2xl sm:text-xl w-4/5">
            <div className="grid content-center">
              <div>
                <div className="grid grid-cols-2 gap-2 justify-items-between">
                  <div className="bg-prinavy self-center">
                    {" "}
                    {myinfo.nickname}
                  </div>
                  <div className="self-center">
                    {isMe ? (
                      <button
                        type="button"
                        disabled=""
                        onClick={() => setOpen(true)}
                        className={`py-1 px-3 bg-green-600 hover:bg-green-700 focus:ring-green-500
                      focus:ring-offset-red-200 text-white transition ease-in duration-200
                      text-center font-semibold shadow-md focus:outline-none focus:ring-2
                      focus:ring-offset-2 opacity-70 rounded-lg`}
                      >
                        Modify Password
                      </button>
                    ) : (
                      <button
                        type="button"
                        onClick={followStateChange}
                        className={followState.classValue}
                      >
                        {followState.value}
                      </button>
                    )}
                  </div>
                  {/* <img src={Setting} alt="setting" className="h-16 justify-self-end" /> */}
                </div>
                <div className="grid grid-cols-3 gap-4 justify-items-between mt-6">
                  <div>
                    {" "}
                    posts {myinfo.posts == null ? 0 : myinfo.posts.length}
                  </div>
                  <div> follower {myinfo.countFollower}</div>
                  <div> following {myinfo.countFollowing}</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
          <div className="pt-2 pb-24 pl-2 pr-2  md:pt-0 md:pr-0 md:pl-0">
            <div className="flex flex-col flex-wrap sm:flex-row h-full">
              <div className="w-1/3 h-full ">
                <MyInfo currentuser={myinfo} />
              </div>

              <div className=" w-2/3  h-screen overflow-auto">
                <div className="ml-4 mb-4 ">
                  <header className="z-40 items-center w-full h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
                    <div className="relative z-20 flex flex-col justify-center h-full px-3 mx-auto flex-center">
                      <div className="relative flex items-center w-full pl-1 lg:max-w-68 sm:pr-2 sm:ml-0">
                        <div className="container relative left-0 z-50 flex w-3/4 h-auto h-full">
                          <div className="relative flex items-center w-full h-full lg:w-64 group ml-3 text-2xl">
                            My Posts
                          </div>
                        </div>
                      </div>
                    </div>
                  </header>
                </div>
                <div className="grid grid-cols-1 ml-4 gap-4">
                  <PostList posts={myPosts} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      {isMe && <PasswordModifyModal open={open} setOpen={setOpen} />}
    </div>
  );
};
export default UserDetail;

