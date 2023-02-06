import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import React, { useEffect }from "react";
import jwt_decode from "jwt-decode";

import Logo from "@components/Logo";
import http from "@api/http";
import { useSetRecoilState, useRecoilValue } from "recoil";
import { userInfo } from "@recoil/user/atoms";

import useToken from "@hooks/useToken";

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
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}

function EmailInput({ register, error }) {
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
        name="email"
        id="email"
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
        aria-invalid={error ? "true" : "false"}
        {...register("email", {
          required: {
            value: true,
            message: "이메일을 입력해주세요.",
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}

  

function LoginForm({ saveToken }) {
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, errors },
  } = useForm();

  const setUser = useSetRecoilState(userInfo);
  const user = useRecoilValue(userInfo);
  const navigate = useNavigate();

  useEffect(() => {
    
  }, [user])
  const onSubmit = async (data) => {
    try {
      let res = await http.post("auth/login", {
        email: data.email,
        password: data.password,
      });
      const decodedUserInfo = jwt_decode(res.data.accessToken);
      setUser((before) => ({
        ...before,
        ...decodedUserInfo,
        logined: true,
      }))
      saveToken(res.data)
      navigate('/')
      
      
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8 bg-gray-50 p-5">
        <div>
          <Logo className="mx-auto h-20 w-auto" />
          <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">
            Sign in to your account
          </h2>
          <p className="mt-2 text-center text-sm text-gray-600">
            Or{" "}
            <Link
              to="/signup"
              className="font-medium text-indigo-600 hover:text-indigo-500"
            >
              Sign up
            </Link>
          </p>
        </div>
        <form className="mt-8 space-y-6" onSubmit={handleSubmit(onSubmit)}>
          <EmailInput register={register} error={errors?.email} />
          <Password
            register={register}
            error={errors?.password}
            name="password"
            label="Password"
          />
          <div className="flex items-center justify-between">
            <div className="flex items-center">
              <input
                id="remember-me"
                name="remember-me"
                type="checkbox"
                className="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"
              />
              <label
                htmlFor="remember-me"
                className="ml-2 block text-sm text-gray-900"
              >
                Remember me
              </label>
            </div>

            <div className="text-sm">
              <Link
                to="/userfind"
                className="font-medium text-indigo-600 hover:text-indigo-500"
              >
                Forgot your password?
              </Link>
            </div>
          </div>

          <div>
            <Button onClick={handleSubmit(onSubmit)} loading={isSubmitting}>
              Sign In
            </Button>
          </div>
          <div>
            <Link to="/auth/github">
              <button className="group relative flex w-full justify-center rounded-md border py-2 px-4 text-sm font-medium hover:bg-gray-100">
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
                Sign in with Github
              </button>
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
}

function SignIn() {
  const {  saveToken } = useToken();

  return <LoginForm  saveToken={saveToken} />;
}

export default SignIn;
