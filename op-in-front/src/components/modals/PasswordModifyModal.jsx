import { Fragment, useRef } from "react";
import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { useForm } from "react-hook-form";
import http from "@api/http";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";
import { useToast } from "@hooks/useToast";

function Button({ onClick = () => { }, loading = false, children }) {
  return (
    <button
      type="submit"
      onClick={onClick}
      disabled={loading}
      className="group relative flex w-full justify-center rounded-md border border-transparent bg-green-600 py-2 px-4 text-sm font-medium text-white hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
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
    <div className="col-span-6 sm:col-span-3 sm:mt-0 sm:mx-6 ">
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
            value: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/,
            message: "비밀번호는 대문자, 소문자, 특수문자, 숫자를 포함해야합니다 (8~16 글자)",
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}

function PasswordConfirm({ register, error, name, label, password }) {
  return (
    <div className="col-span-6 sm:col-span-3 sm:mt-0 sm:mx-6 ">
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
export default function PasswordModifyModal({ open, setOpen }) {
  const user = useRecoilValue(userInfo);
  const { setToast } = useToast();

  const cancelButtonRef = useRef(null);
  const {
    register,
    handleSubmit,
    watch,
    formState: { isSubmitting, errors },
  } = useForm({ mode: "onChange" });

  const onSubmit = async (data) => {
    await http
      .post("member/password/put", {
        email: user.email,
        password: data.password,
      })
      .then(() => {
        setOpen(false);
        setToast({ message: "비밀번호가 변경되었습니다." });
      })
      .catch((err) => {
        console.debug(err);
      });
  };
  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog as="div" className="relative z-40" initialFocus={cancelButtonRef} onClose={setOpen}>
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </Transition.Child>

        <div className="fixed inset-0 z-40 overflow-y-auto">
          <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              enterTo="opacity-100 translate-y-0 sm:scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 translate-y-0 sm:scale-100"
              leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            >
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-1/2">
                <div className="bg-white pt-6">
                  <div className="w-full sm:items-start">
                    <div className=" text-center sm:text-left">
                      <Dialog.Title
                        as="h2"
                        className="text-3xl sm:mt-0 sm:mx-6  font-bold leading-6 text-gray-900 pt-4 pb-4"
                      >
                        Modify Password
                      </Dialog.Title>
                      <div className="mt-2 ">
                        <form className=" mt-8 space-y-6" onSubmit={handleSubmit(onSubmit)}>
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

                          <div className="bg-gray-50 p-4 sm:flex sm:flex-row-reverse sm:px-6">
                            <button
                              type="button"
                              className="mt-3 inline-flex w-full justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-base font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
                              onClick={() => setOpen(false)}
                              ref={cancelButtonRef}
                            >
                              Cancel
                            </button>
                            <Button onClick={handleSubmit(onSubmit)} loading={isSubmitting}>
                              Modify
                            </Button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition.Root>
  );
}
