import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";

import Logo from "../../components/Logo";


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
          pattern: {
            value: /\S+@\S+\.\S+/,
            message: "이메일 형식이 맞지 않습니다.",
          },
        })}
      />
      {error && <p className="text-red-500 text-xs">{error.message}</p>}
    </div>
  );
}
function SignUpForm() {
  
  const {
    register,
    handleSubmit,
    watch,
    formState: { isSubmitting, errors },
  } = useForm();

  const onSubmit = async (data, e) => {
    const result = await new Promise((res) => {
      setTimeout(() => {
        res(data);
      }, 3000);

    });
    
  
    window.alert(JSON.stringify(result));

  };

  return (
    <div className="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8 bg-gray-50 p-5">
        <div>
          <Logo className="mx-auto h-20 w-auto" />
          <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">
            Forgot Your Password?
          </h2>
        </div>
        <div className="shadow-md p-5 mb-4 bg-white">
          <form className="mt-8 space-y-6" onSubmit={handleSubmit(onSubmit)}>
            <EmailInput register={register} error={errors?.email} />
            <div>
              <Button onClick={handleSubmit(onSubmit)} loading={isSubmitting}>
                Send email
              </Button>
            </div>
    
          </form>
        </div>
      </div>
    </div>
  );
}
function UserFind() {
  return (
    
    <SignUpForm />

  );
}

export default UserFind;

