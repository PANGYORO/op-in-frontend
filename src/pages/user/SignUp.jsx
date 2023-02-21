import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { debounce } from "lodash";
import { useToast } from "@hooks/useToast";

import Logo from "@components/Logo";
import http from "@api/http";
import { useSetRecoilState } from "recoil";
import { userInfo } from "@recoil/user/atoms";

function Button({ onClick = () => {}, loading = false, children }) {
  return (
    <button
      type="submit"
      onClick={onClick}
      disabled={loading}
      className="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
    >
      {loading && (
        <svg
          className="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
        >
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          ></circle>
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
          ></path>
        </svg>
      )}
      {children}
    </button>
  );
}
function Nickname({ register, error, name }) {
  const [check, checkState] = useState(false);
  const [available, availableState] = useState(false);
  const [overlap, overlapState] = useState(false);

  const callApi = debounce(async (value) => {
    await http
      .post("member/nickname/check", {
        nickname: value,
      })
      .then((res) => {
        checkState(false);

        if (!res.data) {
          availableState(true);
          overlapState(false);
        } else if (res.data) {
          overlapState(true);
          availableState(false);
        }
      })
      .catch((err) => {
        console.debug(err);
      });
  }, 2000);

  return (
    <div className="col-span-6 sm:col-span-3">
      <label htmlFor={name} className="block text-sm font-medium text-gray-700">
        Nickname
      </label>
      <input
        id={name}
        name={name}
        type="text"
        required
        autoComplete="username"
        className="mt-1 block w-full rounded-md border-gray-00 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        {...register(name, {
          required: {
            value: true,
            message: "Nickname을 입력해주세요.",
          },
          maxLength: {
            value: 40,
            message: "Nickname은 최대 40자까지 가능합니다.",
          },
          validate: (value) => {
            if (!error) {
              checkState(true);
              callApi(value);
            }
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
      {!error && check && (
        <div className="flex justify-start">
          <p className="text-indigo-500 text-xs ">Nickname check..</p>
          <svg
            className="animate-spin -ml-1 mr-3 h-4 w-8 text-white"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
          >
            <circle
              className="opacity-25"
              cx="12"
              cy="12"
              r="10"
              stroke="indigo"
              strokeWidth="4"
            ></circle>
            <path
              className="opacity-75"
              fill="indigo"
              d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
            ></path>
          </svg>
        </div>
      )}
      {!error && !check && available && (
        <p className="text-indigo-700 text-xs">사용가능한 Nickname 입니다!</p>
      )}
      {!error && !check && overlap && (
        <p className="text-red-700 text-xs">중복된 Nickname이 존재합니다!</p>
      )}
    </div>
  );
}
function Password({ register, error, name, label }) {
  return (
    <div className="col-span-6 sm:col-span-3">
      <label htmlFor={name} className="block text-sm font-medium text-gray-700">
        {label}
      </label>
      <input
        id={name}
        type="password"
        name={name}
        autoComplete="new-password"
        aria-invalid={error ? "true" : "false"}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        {...register(name, {
          required: {
            value: true,
            message: "비밀번호를 입력해주세요.",
          },
          pattern: {
            value:
              /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/,
            message:
              "비밀번호는 대문자, 소문자, 특수문자, 숫자를 포함해야합니다 (8~16 글자)",
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}

function PasswordConfirm({ register, error, name, label, password }) {
  return (
    <div className="col-span-6 sm:col-span-3">
      <label htmlFor={name} className="block text-sm font-medium text-gray-700">
        {label}
      </label>
      <input
        id={name}
        type="password"
        name={name}
        autoComplete="new-password"
        aria-invalid={error ? "true" : "false"}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        {...register(name, {
          required: {
            value: true,
            message: "비밀번호를 입력해주세요.",
          },
          // pattern: {
          //   value: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/,
          //   message: "비밀번호는 대문자, 소문자, 특수문자, 숫자를 포함해야합니다 (8~16 글자)",
          // },
          validate: (value) => {
            if (value != password) {
              return "비밀번호가 일치하지 않습니다.";
            }
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}
function EmailInput({ register, error, name }) {
  const [check, checkState] = useState(false);
  const [available, availableState] = useState(false);
  const [overlap, overlapState] = useState(false);

  const callApi = debounce(async (value) => {
    await http
      .post("member/email/check", {
        email: value,
      })
      .then((res) => {
        checkState(false);

        if (!res.data) {
          availableState(true);
          overlapState(false);
        } else if (res.data) {
          overlapState(true);
          availableState(false);
        }
      })
      .catch((err) => {
        console.debug(err);
      });
  }, 2000);

  return (
    <div className="col-span-6 sm:col-span-3">
      <label
        htmlFor="email"
        className="block text-sm font-medium text-gray-700"
      >
        Email Address
      </label>
      <input
        type="email"
        name={name}
        id={name}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        aria-invalid={error ? "true" : "false"}
        {...register(name, {
          required: {
            value: true,
            message: "Email을 입력해주세요.",
          },
          pattern: {
            value: /\w+@\w+\.\w+/,
            message: "Email 형식이 맞지 않습니다.",
          },

          validate: (value) => {
            if (!error) {
              checkState(true);
              callApi(value);
            }
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
      {!error && check && (
        <div className="flex justify-start">
          <p className="text-indigo-500 text-xs ">Email check..</p>
          <svg
            className="animate-spin -ml-1 mr-3 h-4 w-8 text-white"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
          >
            <circle
              className="opacity-25"
              cx="12"
              cy="12"
              r="10"
              stroke="indigo"
              strokeWidth="4"
            ></circle>
            <path
              className="opacity-75"
              fill="indigo"
              d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
            ></path>
          </svg>
        </div>
      )}
      {!error && !check && available && (
        <p className="text-indigo-700 text-xs">사용가능한 Email입니다!</p>
      )}
      {!error && !check && overlap && (
        <p className="text-red-700 text-xs">중복된 Email이 존재합니다!</p>
      )}
    </div>
  );
}
function SignUpForm({ setToast }) {
  const {
    register,
    handleSubmit,
    watch,
    formState: { isSubmitting, errors },
  } = useForm({ mode: "onChange" });

  const setUser = useSetRecoilState(userInfo);

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    await http
      .post("auth/signup", {
        email: data.email,
        password: data.password,
        nickname: data.nickname,
      })
      .then((res) => {
        setUser((before) => ({
          ...before,
          nickname: data.nickname,
          email: res.data,
        }));
        setToast({ message: "회원가입 성공! 관심있는 tag를 골라주세요!!" });
        navigate("/selecttag");
      })
      .catch((err) => {
        console.debug(err);
      });
  };

  return (
    <div className="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8 bg-gray-50 p-5">
        <div>
          {/* <button
            onClick={() => {
              setToast({ message: "hi" });
            }}
          >
            123213
          </button> */}
          <Logo className="mx-auto h-20 w-auto" />
          <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">
            Sign <span className="text-blue-600">up</span> to your account
          </h2>
        </div>
        <div className="shadow-md p-5 mb-4 bg-white">
          <form className="mt-8 space-y-6" onSubmit={handleSubmit(onSubmit)}>
            <Nickname
              register={register}
              error={errors?.nickname}
              name="nickname"
              label="Nickname"
            />
            <EmailInput
              register={register}
              error={errors?.email}
              name="email"
              check={false}
            />
            <Password
              register={register}
              error={errors?.password}
              name="password"
              label="Password"
            />
            <PasswordConfirm
              register={register}
              error={errors?.["password-confirm"]}
              name="password-confirm"
              label="Password Confirm"
              password={watch("password")}
            />
            <div>
              <Button onClick={handleSubmit(onSubmit)} loading={isSubmitting}>
                Sign up
              </Button>
            </div>
            <div>
              <a
                href={`${import.meta.env.VITE_API_URL}/oauth/redirect/github`}
                className="group relative flex w-full justify-center rounded-md border py-2 px-4 text-sm font-medium hover:bg-gray-100"
                disabled={isSubmitting}
              >
                <span className="absolute inset-y-0 left-0 flex items-center pl-3">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                  >
                    <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-4.466 19.59c-.405.078-.534-.171-.534-.384v-2.195c0-.747-.262-1.233-.55-1.481 1.782-.198 3.654-.875 3.654-3.947 0-.874-.312-1.588-.823-2.147.082-.202.356-1.016-.079-2.117 0 0-.671-.215-2.198.82-.64-.18-1.324-.267-2.004-.271-.68.003-1.364.091-2.003.269-1.528-1.035-2.2-.82-2.2-.82-.434 1.102-.16 1.915-.077 2.118-.512.56-.824 1.273-.824 2.147 0 3.064 1.867 3.751 3.645 3.954-.229.2-.436.552-.508 1.07-.457.204-1.614.557-2.328-.666 0 0-.423-.768-1.227-.825 0 0-.78-.01-.055.487 0 0 .525.246.889 1.17 0 0 .463 1.428 2.688.944v1.489c0 .211-.129.459-.528.385-3.18-1.057-5.472-4.056-5.472-7.59 0-4.419 3.582-8 8-8s8 3.581 8 8c0 3.533-2.289 6.531-5.466 7.59z" />
                  </svg>
                </span>
                Sign up with Github
              </a>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
function SignUp() {
  const { setToast } = useToast();
  return <SignUpForm setToast={setToast} />;
}

export default SignUp;
