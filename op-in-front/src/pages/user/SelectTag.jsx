import { Transition } from '@headlessui/react'
import { useNavigate } from "react-router-dom";
import React, { Fragment, useState} from 'react'
import { useTimeoutFn } from 'react-use'
import http from '@api/http'
import { useRecoilValue } from "recoil";
import { userInfo } from "@recoil/user/atoms";
import { useToast } from '@hooks/useToast';
import Joyride from 'react-joyride'




function TopicTag(props) {
  let [isShowing, setIsShowing] = useState(false)
  useTimeoutFn(() => setIsShowing(true), 500)

  return (
    <div className="flex flex-col items-center p-4">
      <div className="h-24 w-32">
        <Transition
          as={Fragment}
          show={isShowing}
          enter="transform transition duration-[400ms]"
          enterFrom="opacity-0 rotate-[-120deg] scale-50"
          enterTo="opacity-100 rotate-0 scale-100"
          leave="transform duration-200 transition ease-in-out"
          leaveFrom="opacity-100 rotate-0 scale-100 "
          leaveTo="opacity-0 scale-95 "
        >
          <div>
            <input onClick={(event)=>{props.handleTopic(event.target.value)}} type="checkbox" id={props.name} value={props.name} className="hidden peer" required="" />
            <label htmlFor={props.name} className="inline-flex items-center justify-between w-full p-5 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-200 peer-checked:bg-blue-300 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">                           
                <div className="block flex items-center justify-center">
                  {props.svg}
                  <div className="w-full font-semibold mx-2">{props.name}</div>
                </div>
            </label>
          </div>
        </Transition>
      </div>

      
    </div>
  )
}

function LanTag(props) {
  let [isShowing, setIsShowing] = useState(false)
  useTimeoutFn(() => setIsShowing(true), 500)

  return (
    <div className="flex flex-col items-center p-4">
      <div className="h-24 w-32">
        <Transition
          as={Fragment}
          show={isShowing}
          enter="transform transition duration-[400ms]"
          enterFrom="opacity-0 rotate-[-120deg] scale-50"
          enterTo="opacity-100 rotate-0 scale-100"
          leave="transform duration-200 transition ease-in-out"
          leaveFrom="opacity-100 rotate-0 scale-100 "
          leaveTo="opacity-0 scale-95 "
        >
          <div>
            <input onClick={(event)=>{props.handleLan(event.target.value)}} type="checkbox" id={props.name} value={props.name} className="hidden peer" required="" />
            <label htmlFor={props.name} className="inline-flex items-center justify-between w-full p-5 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-200 peer-checked:bg-blue-300 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">                           
                <div className="block flex items-center justify-center">
                  {props.svg}
                  <div className="w-full font-semibold mx-2">{props.name}</div>
                </div>
            </label>
          </div>
        </Transition>
      </div>

      
    </div>
  )
}

function Button({ topic, lan }) {
  const user = useRecoilValue(userInfo);
  const { setToast } = useToast()
  const navigate = useNavigate();
  

  const onSubmit = async () => {
    
    try {
      await http.post("member/topic/language/put", {
        email: user.email,
        topic,
        lan
      });
      
      setToast({message: '관심tag를 저장했습니다! 로그인 후 op-in을 사용해보세요'})
      navigate('/signin')

    } catch (error) {
      console.log(user.email)
      console.log(error)
    }
    
  };

  return (
    <div className="flex flex-col items-center p-4">
      <button
        className="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-400 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        onClick={onSubmit}
      >
        Send
      </button>
    </div>
      
  );
}




function SelectTag() {

  const [topic, setTopic] = useState([])
  const [lan, setLan] = useState([])

  const steps = [
    {
      title:"Topic Tag", 
      content: "인기있는 topic 모음입니다",
      target: "#topicTag",
      placement:"top-end",
    },
    {
      title:"Lan Tag", 
      content: "인기있는 언어 모음입니다",
      target: "#lanTag",
      placement:"bottom",
    },
    {
      title:"Tag Btn ", 
      content: "관심있는 tag들을 모두 골랐다면 버튼을 눌러 제출해주세요",
      target: "#tagButton",
      placement:"top",
    },
  ];
  const [joyride, setJoyride] = useState({
    run: false,
    steps: steps,
  });

  
  

  const handleTopic = (newTopic) => {
    if (topic.includes(newTopic)) {
      const newTopicList = topic.filter((it) => it !== newTopic)
      setTopic(newTopicList)
      console.log(topic)
    }
    else {
      setTopic([newTopic, ...topic])
      console.log(topic)
    }
    
  }
  
  const handleLan = (newLan) => {
    if (lan.includes(newLan)) {
      const newLanList = lan.filter((it) => it !== newLan)
      setLan(newLanList)
      console.log(lan)
    }
    else {
      setLan([newLan, ...lan])
      console.log(lan)
      console.log(topic)
    }
  }



  const reactSvg = <svg fill="#000000" width="50" height="50" viewBox="0 0 32 32" id="Camada_1" version="1.1"  xmlns="http://www.w3.org/2000/svg"><g><path d="M29.7,16c0-1.9-2.2-3.5-5.7-4.5C24.9,8,24.6,5.3,23,4.3C22.6,4.1,22.2,4,21.7,4c-1.5,0-3.5,1.1-5.5,3c-2-1.9-3.9-3-5.5-3   c-0.5,0-0.9,0.1-1.3,0.3C7.9,5.3,7.6,8,8.4,11.5c-3.4,1-5.7,2.7-5.7,4.5c0,1.9,2.2,3.5,5.7,4.5c-0.8,3.5-0.5,6.3,1.1,7.2   c0.4,0.2,0.8,0.3,1.3,0.3c1.5,0,3.5-1.1,5.5-3c2,1.9,3.9,3,5.5,3c0.5,0,0.9-0.1,1.3-0.3c1.6-0.9,1.9-3.7,1.1-7.2   C27.5,19.5,29.7,17.8,29.7,16z M21.7,5.3c0.3,0,0.5,0,0.6,0.2c0.8,0.4,1.1,2,0.8,4.1c-0.1,0.5-0.2,1.1-0.3,1.6   c-1.1-0.3-2.2-0.4-3.5-0.6c-0.7-1-1.4-1.9-2.2-2.7C18.9,6.2,20.6,5.3,21.7,5.3z M19.9,18.1c-0.4,0.7-0.8,1.4-1.3,2   c-0.8,0.1-1.6,0.1-2.4,0.1c-0.8,0-1.6,0-2.4-0.1c-0.4-0.6-0.8-1.3-1.3-2c-0.4-0.7-0.8-1.4-1.1-2.1c0.3-0.7,0.7-1.4,1.1-2.1   c0.4-0.7,0.9-1.4,1.3-2.1c0.8-0.1,1.6-0.1,2.4-0.1c0.8,0,1.6,0,2.4,0.1c0.4,0.6,0.9,1.3,1.3,2.1c0.4,0.7,0.8,1.4,1.1,2.1   C20.7,16.7,20.3,17.4,19.9,18.1z M21.7,17.5c0.3,0.7,0.5,1.4,0.7,2c-0.7,0.2-1.4,0.3-2.1,0.4c0.2-0.4,0.5-0.8,0.7-1.2   C21.3,18.4,21.5,17.9,21.7,17.5L21.7,17.5z M16.2,23.2c-0.5-0.5-1-1.1-1.4-1.7c0.5,0,1,0,1.4,0c0.5,0,1,0,1.4,0   C17.2,22.1,16.7,22.7,16.2,23.2z M12.1,20c-0.7-0.1-1.4-0.2-2.1-0.4c0.2-0.7,0.5-1.4,0.7-2c0.2,0.4,0.4,0.8,0.7,1.2   C11.7,19.2,11.9,19.6,12.1,20L12.1,20z M10.7,14.5c-0.3-0.7-0.5-1.4-0.8-2.1c0.7-0.2,1.4-0.3,2.2-0.4c-0.3,0.4-0.5,0.8-0.8,1.2   C11.2,13.7,11,14.1,10.7,14.5z M16.2,8.8c0.5,0.5,0.9,1.1,1.4,1.6c-0.5,0-0.9,0-1.4,0c-0.5,0-0.9,0-1.4,0   C15.3,9.9,15.8,9.3,16.2,8.8z M21,13.2c-0.3-0.4-0.5-0.8-0.8-1.2c0.8,0.1,1.5,0.2,2.2,0.4c-0.2,0.7-0.5,1.4-0.8,2.1   C21.5,14.1,21.3,13.7,21,13.2z M9.4,9.5c-0.3-2.1,0-3.6,0.8-4.1c0.2-0.1,0.4-0.2,0.6-0.2c1.1,0,2.7,0.9,4.5,2.6   c-0.7,0.8-1.5,1.7-2.2,2.7c-1.2,0.1-2.4,0.3-3.5,0.6C9.5,10.6,9.4,10,9.4,9.5z M7.2,18.7C5.3,17.9,4,16.8,4,16   c0-0.9,1.2-1.9,3.1-2.7c0.5-0.2,1-0.4,1.6-0.5c0.3,1.1,0.7,2.2,1.3,3.3c-0.5,1.1-0.9,2.2-1.2,3.2C8.2,19.1,7.7,18.9,7.2,18.7   L7.2,18.7z M10.1,26.6c-0.8-0.4-1.1-2-0.8-4.1c0.1-0.5,0.2-1.1,0.3-1.7c1.1,0.2,2.2,0.4,3.4,0.5c0.7,1,1.5,1.9,2.2,2.7   c-1.8,1.7-3.4,2.6-4.5,2.6C10.5,26.7,10.3,26.6,10.1,26.6L10.1,26.6z M23.1,22.5c0.3,2.1,0,3.6-0.8,4.1c-0.2,0.1-0.4,0.2-0.6,0.2   c-1.1,0-2.7-0.9-4.5-2.6c0.8-0.8,1.5-1.7,2.2-2.7c1.2-0.1,2.4-0.3,3.4-0.5C22.9,21.4,23,21.9,23.1,22.5L23.1,22.5z M25.3,18.7   c-0.5,0.2-1,0.4-1.6,0.5c-0.3-1-0.7-2.1-1.2-3.2c0.5-1.1,0.9-2.2,1.3-3.3c0.5,0.2,1.1,0.3,1.6,0.5c1.9,0.8,3.1,1.8,3.1,2.7   C28.4,16.8,27.2,17.9,25.3,18.7L25.3,18.7z"/><path d="M16.3,18.5c1.4,0,2.5-1.1,2.5-2.5s-1.1-2.5-2.5-2.5s-2.5,1.1-2.5,2.5S14.9,18.5,16.3,18.5z"/></g></svg>
  const nodeSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M224 508c-6.7 0-13.5-1.8-19.4-5.2l-61.7-36.5c-9.2-5.2-4.7-7-1.7-8 12.3-4.3 14.8-5.2 27.9-12.7 1.4-.8 3.2-.5 4.6.4l47.4 28.1c1.7 1 4.1 1 5.7 0l184.7-106.6c1.7-1 2.8-3 2.8-5V149.3c0-2.1-1.1-4-2.9-5.1L226.8 37.7c-1.7-1-4-1-5.7 0L36.6 144.3c-1.8 1-2.9 3-2.9 5.1v213.1c0 2 1.1 4 2.9 4.9l50.6 29.2c27.5 13.7 44.3-2.4 44.3-18.7V167.5c0-3 2.4-5.3 5.4-5.3h23.4c2.9 0 5.4 2.3 5.4 5.3V378c0 36.6-20 57.6-54.7 57.6-10.7 0-19.1 0-42.5-11.6l-48.4-27.9C8.1 389.2.7 376.3.7 362.4V149.3c0-13.8 7.4-26.8 19.4-33.7L204.6 9c11.7-6.6 27.2-6.6 38.8 0l184.7 106.7c12 6.9 19.4 19.8 19.4 33.7v213.1c0 13.8-7.4 26.7-19.4 33.7L243.4 502.8c-5.9 3.4-12.6 5.2-19.4 5.2zm149.1-210.1c0-39.9-27-50.5-83.7-58-57.4-7.6-63.2-11.5-63.2-24.9 0-11.1 4.9-25.9 47.4-25.9 37.9 0 51.9 8.2 57.7 33.8.5 2.4 2.7 4.2 5.2 4.2h24c1.5 0 2.9-.6 3.9-1.7s1.5-2.6 1.4-4.1c-3.7-44.1-33-64.6-92.2-64.6-52.7 0-84.1 22.2-84.1 59.5 0 40.4 31.3 51.6 81.8 56.6 60.5 5.9 65.2 14.8 65.2 26.7 0 20.6-16.6 29.4-55.5 29.4-48.9 0-59.6-12.3-63.2-36.6-.4-2.6-2.6-4.5-5.3-4.5h-23.9c-3 0-5.3 2.4-5.3 5.3 0 31.1 16.9 68.2 97.8 68.2 58.4-.1 92-23.2 92-63.4z"/></svg>
  const vueSvg = <svg  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M356.9 64.3H280l-56 88.6-48-88.6H0L224 448 448 64.3h-91.1zm-301.2 32h53.8L224 294.5 338.4 96.3h53.8L224 384.5 55.7 96.3z"/></svg>
  const gitSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M400 32H48C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48zM277.3 415.7c-8.4 1.5-11.5-3.7-11.5-8 0-5.4.2-33 .2-55.3 0-15.6-5.2-25.5-11.3-30.7 37-4.1 76-9.2 76-73.1 0-18.2-6.5-27.3-17.1-39 1.7-4.3 7.4-22-1.7-45-13.9-4.3-45.7 17.9-45.7 17.9-13.2-3.7-27.5-5.6-41.6-5.6-14.1 0-28.4 1.9-41.6 5.6 0 0-31.8-22.2-45.7-17.9-9.1 22.9-3.5 40.6-1.7 45-10.6 11.7-15.6 20.8-15.6 39 0 63.6 37.3 69 74.3 73.1-4.8 4.3-9.1 11.7-10.6 22.3-9.5 4.3-33.8 11.7-48.3-13.9-9.1-15.8-25.5-17.1-25.5-17.1-16.2-.2-1.1 10.2-1.1 10.2 10.8 5 18.4 24.2 18.4 24.2 9.7 29.7 56.1 19.7 56.1 19.7 0 13.9.2 36.5.2 40.6 0 4.3-3 9.5-11.5 8-66-22.1-112.2-84.9-112.2-158.3 0-91.8 70.2-161.5 162-161.5S388 165.6 388 257.4c.1 73.4-44.7 136.3-110.7 158.3zm-98.1-61.1c-1.9.4-3.7-.4-3.9-1.7-.2-1.5 1.1-2.8 3-3.2 1.9-.2 3.7.6 3.9 1.9.3 1.3-1 2.6-3 3zm-9.5-.9c0 1.3-1.5 2.4-3.5 2.4-2.2.2-3.7-.9-3.7-2.4 0-1.3 1.5-2.4 3.5-2.4 1.9-.2 3.7.9 3.7 2.4zm-13.7-1.1c-.4 1.3-2.4 1.9-4.1 1.3-1.9-.4-3.2-1.9-2.8-3.2.4-1.3 2.4-1.9 4.1-1.5 2 .6 3.3 2.1 2.8 3.4zm-12.3-5.4c-.9 1.1-2.8.9-4.3-.6-1.5-1.3-1.9-3.2-.9-4.1.9-1.1 2.8-.9 4.3.6 1.3 1.3 1.8 3.3.9 4.1zm-9.1-9.1c-.9.6-2.6 0-3.7-1.5s-1.1-3.2 0-3.9c1.1-.9 2.8-.2 3.7 1.3 1.1 1.5 1.1 3.3 0 4.1zm-6.5-9.7c-.9.9-2.4.4-3.5-.6-1.1-1.3-1.3-2.8-.4-3.5.9-.9 2.4-.4 3.5.6 1.1 1.3 1.3 2.8.4 3.5zm-6.7-7.4c-.4.9-1.7 1.1-2.8.4-1.3-.6-1.9-1.7-1.5-2.6.4-.6 1.5-.9 2.8-.4 1.3.7 1.9 1.8 1.5 2.6z"/></svg> 
  const springSvg = <svg role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" height="50" width="50"><path d="M21.8537 1.4158a10.4504 10.4504 0 0 1-1.284 2.2471A11.9666 11.9666 0 1 0 3.8518 20.7757l.4445.3951a11.9543 11.9543 0 0 0 19.6316-8.2971c.3457-3.0126-.568-6.8649-2.0743-11.458zM5.5805 20.8745a1.0174 1.0174 0 1 1-.1482-1.4323 1.0396 1.0396 0 0 1 .1482 1.4323zm16.1991-3.5806c-2.9385 3.9263-9.2601 2.5928-13.2852 2.7904 0 0-.7161.0494-1.4323.1481 0 0 .2717-.1234.6174-.2469 2.8398-.9877 4.1732-1.1853 5.9018-2.0743 3.2349-1.6545 6.4698-5.2844 7.1118-9.0379-1.2347 3.6053-4.9881 6.7167-8.3959 7.9761-2.3459.8643-6.5685 1.7039-6.5685 1.7039l-.1729-.0988c-2.8645-1.4076-2.9632-7.6304 2.2718-9.6306 2.2966-.889 4.4696-.395 6.9637-.9877 2.6422-.6174 5.7043-2.5929 6.939-5.1857 1.3828 4.1732 3.062 10.643.0493 14.6434z"/></svg>
  const angularSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M185.7 268.1h76.2l-38.1-91.6-38.1 91.6zM223.8 32L16 106.4l31.8 275.7 176 97.9 176-97.9 31.8-275.7zM354 373.8h-48.6l-26.2-65.4H168.6l-26.2 65.4H93.7L223.8 81.5z"/></svg>
  const metaSvg = <svg xmlns="http://www.w3.org/2000/svg" height="50" width="50" className="bi bi-meta" viewBox="0 0 16 16"> <path d="M8.217 5.243C9.145 3.988 10.171 3 11.483 3 13.96 3 16 6.153 16.001 9.907c0 2.29-.986 3.725-2.757 3.725-1.543 0-2.395-.866-3.924-3.424l-.667-1.123-.118-.197a54.944 54.944 0 0 0-.53-.877l-1.178 2.08c-1.673 2.925-2.615 3.541-3.923 3.541C1.086 13.632 0 12.217 0 9.973 0 6.388 1.995 3 4.598 3c.319 0 .625.039.924.122.31.086.611.22.913.407.577.359 1.154.915 1.782 1.714Zm1.516 2.224c-.252-.41-.494-.787-.727-1.133L9 6.326c.845-1.305 1.543-1.954 2.372-1.954 1.723 0 3.102 2.537 3.102 5.653 0 1.188-.39 1.877-1.195 1.877-.773 0-1.142-.51-2.61-2.87l-.937-1.565ZM4.846 4.756c.725.1 1.385.634 2.34 2.001A212.13 212.13 0 0 0 5.551 9.3c-1.357 2.126-1.826 2.603-2.581 2.603-.777 0-1.24-.682-1.24-1.9 0-2.602 1.298-5.264 2.846-5.264.091 0 .181.006.27.018Z"/> </svg>
  const pytorchSvg = <svg role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" height="50" width="50"><path d="M12.005 0L4.952 7.053a9.865 9.865 0 000 14.022 9.866 9.866 0 0014.022 0c3.984-3.9 3.986-10.205.085-14.023l-1.744 1.743c2.904 2.905 2.904 7.634 0 10.538s-7.634 2.904-10.538 0-2.904-7.634 0-10.538l4.647-4.646.582-.665zm3.568 3.899a1.327 1.327 0 00-1.327 1.327 1.327 1.327 0 001.327 1.328A1.327 1.327 0 0016.9 5.226 1.327 1.327 0 0015.573 3.9z"/></svg>
  const tensorFlowSvg = <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 32 32"><path d="M26.135 16l0.13 6.266-4.13-2.401v8.938l-5.469 3.198v-32l13.599 7.865v7.068l-8.13-4.797v3.599zM1.734 7.865l13.599-7.865v32l-5.469-3.198v-18.667l-8.13 4.797z"/></svg>
  const djangoSvg = <svg viewBox="0 0 24 24" height="50" width="50" xmlns="http://www.w3.org/2000/svg"><path d="M7.533 12.249c-.011 1.985 1.445 3.168 3.768 2.63V9.618c-2.352-.716-3.758.733-3.768 2.631m3.839-10.238h3.199v15.143c-3.066.501-6.004.819-8.104-.355-2.705-1.513-2.923-6.319-.782-8.46 1.085-1.085 3.271-1.85 5.616-1.351V2.225c-.006-.101-.012-.202.071-.214m8.389 3.342h-3.199V2.011h3.199v3.342z"/><path d="M19.761 7.044c-.003 2.356-.003 4.048-.003 6.911-.136 2.813-.104 5.052-1.135 6.398-.203.266-.634.652-.995.924-.303.228-.881.691-1.208.711-.331.021-1.18-.459-1.564-.64-.505-.237-.971-.553-1.493-.71 1.218-.754 2.372-1.32 2.844-2.844.41-1.326.355-3.247.355-5.119 0-1.849.009-3.998.009-5.63l3.19-.001z"/></svg>

  const jsSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M400 32H48C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48zM243.8 381.4c0 43.6-25.6 63.5-62.9 63.5-33.7 0-53.2-17.4-63.2-38.5l34.3-20.7c6.6 11.7 12.6 21.6 27.1 21.6 13.8 0 22.6-5.4 22.6-26.5V237.7h42.1v143.7zm99.6 63.5c-39.1 0-64.4-18.6-76.7-43l34.3-19.8c9 14.7 20.8 25.6 41.5 25.6 17.4 0 28.6-8.7 28.6-20.8 0-14.4-11.4-19.5-30.7-28l-10.5-4.5c-30.4-12.9-50.5-29.2-50.5-63.5 0-31.6 24.1-55.6 61.6-55.6 26.8 0 46 9.3 59.8 33.7L368 290c-7.2-12.9-15-18-27.1-18-12.3 0-20.1 7.8-20.1 18 0 12.6 7.8 17.7 25.9 25.6l10.5 4.5c35.8 15.3 55.9 31 55.9 66.2 0 37.8-29.8 58.6-69.7 58.6z" /></svg>
  const pythonSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" height="50" width="50"><path d="M439.8 200.5c-7.7-30.9-22.3-54.2-53.4-54.2h-40.1v47.4c0 36.8-31.2 67.8-66.8 67.8H172.7c-29.2 0-53.4 25-53.4 54.3v101.8c0 29 25.2 46 53.4 54.3 33.8 9.9 66.3 11.7 106.8 0 26.9-7.8 53.4-23.5 53.4-54.3v-40.7H226.2v-13.6h160.2c31.1 0 42.6-21.7 53.4-54.2 11.2-33.5 10.7-65.7 0-108.6zM286.2 404c11.1 0 20.1 9.1 20.1 20.3 0 11.3-9 20.4-20.1 20.4-11 0-20.1-9.2-20.1-20.4.1-11.3 9.1-20.3 20.1-20.3zM167.8 248.1h106.8c29.7 0 53.4-24.5 53.4-54.3V91.9c0-29-24.4-50.7-53.4-55.6-35.8-5.9-74.7-5.6-106.8.1-45.2 8-53.4 24.7-53.4 55.6v40.7h106.9v13.6h-147c-31.1 0-58.3 18.7-66.8 54.2-9.8 40.7-10.2 66.1 0 108.6 7.6 31.6 25.7 54.2 56.8 54.2H101v-48.8c0-35.3 30.5-66.4 66.8-66.4zm-6.7-142.6c-11.1 0-20.1-9.1-20.1-20.3.1-11.3 9-20.4 20.1-20.4 11 0 20.1 9.2 20.1 20.4s-9 20.3-20.1 20.3z"/></svg>
  const javaSvg = <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" height="50" width="50"><path d="M277.74 312.9c9.8-6.7 23.4-12.5 23.4-12.5s-38.7 7-77.2 10.2c-47.1 3.9-97.7 4.7-123.1 1.3-60.1-8 33-30.1 33-30.1s-36.1-2.4-80.6 19c-52.5 25.4 130 37 224.5 12.1zm-85.4-32.1c-19-42.7-83.1-80.2 0-145.8C296 53.2 242.84 0 242.84 0c21.5 84.5-75.6 110.1-110.7 162.6-23.9 35.9 11.7 74.4 60.2 118.2zm114.6-176.2c.1 0-175.2 43.8-91.5 140.2 24.7 28.4-6.5 54-6.5 54s62.7-32.4 33.9-72.9c-26.9-37.8-47.5-56.6 64.1-121.3zm-6.1 270.5a12.19 12.19 0 0 1-2 2.6c128.3-33.7 81.1-118.9 19.8-97.3a17.33 17.33 0 0 0-8.2 6.3 70.45 70.45 0 0 1 11-3c31-6.5 75.5 41.5-20.6 91.4zM348 437.4s14.5 11.9-15.9 21.2c-57.9 17.5-240.8 22.8-291.6.7-18.3-7.9 16-19 26.8-21.3 11.2-2.4 17.7-2 17.7-2-20.3-14.3-131.3 28.1-56.4 40.2C232.84 509.4 401 461.3 348 437.4zM124.44 396c-78.7 22 47.9 67.4 148.1 24.5a185.89 185.89 0 0 1-28.2-13.8c-44.7 8.5-65.4 9.1-106 4.5-33.5-3.8-13.9-15.2-13.9-15.2zm179.8 97.2c-78.7 14.8-175.8 13.1-233.3 3.6 0-.1 11.8 9.7 72.4 13.6 92.2 5.9 233.8-3.3 237.1-46.9 0 0-6.4 16.5-76.2 29.7zM260.64 353c-59.2 11.4-93.5 11.1-136.8 6.6-33.5-3.5-11.6-19.7-11.6-19.7-86.8 28.8 48.2 61.4 169.5 25.9a60.37 60.37 0 0 1-21.1-12.8z"/></svg>
  const tsSvg = <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" height="50" width="50"><path d="M1.125 0C.502 0 0 .502 0 1.125v21.75C0 23.498.502 24 1.125 24h21.75c.623 0 1.125-.502 1.125-1.125V1.125C24 .502 23.498 0 22.875 0zm17.363 9.75c.612 0 1.154.037 1.627.111a6.38 6.38 0 0 1 1.306.34v2.458a3.95 3.95 0 0 0-.643-.361 5.093 5.093 0 0 0-.717-.26 5.453 5.453 0 0 0-1.426-.2c-.3 0-.573.028-.819.086a2.1 2.1 0 0 0-.623.242c-.17.104-.3.229-.393.374a.888.888 0 0 0-.14.49c0 .196.053.373.156.529.104.156.252.304.443.444s.423.276.696.41c.273.135.582.274.926.416.47.197.892.407 1.266.628.374.222.695.473.963.753.268.279.472.598.614.957.142.359.214.776.214 1.253 0 .657-.125 1.21-.373 1.656a3.033 3.033 0 0 1-1.012 1.085 4.38 4.38 0 0 1-1.487.596c-.566.12-1.163.18-1.79.18a9.916 9.916 0 0 1-1.84-.164 5.544 5.544 0 0 1-1.512-.493v-2.63a5.033 5.033 0 0 0 3.237 1.2c.333 0 .624-.03.872-.09.249-.06.456-.144.623-.25.166-.108.29-.234.373-.38a1.023 1.023 0 0 0-.074-1.089 2.12 2.12 0 0 0-.537-.5 5.597 5.597 0 0 0-.807-.444 27.72 27.72 0 0 0-1.007-.436c-.918-.383-1.602-.852-2.053-1.405-.45-.553-.676-1.222-.676-2.005 0-.614.123-1.141.369-1.582.246-.441.58-.804 1.004-1.089a4.494 4.494 0 0 1 1.47-.629 7.536 7.536 0 0 1 1.77-.201zm-15.113.188h9.563v2.166H9.506v9.646H6.789v-9.646H3.375z"/></svg>
  const cppSvg = <svg  viewBox="0 0 24 24" height="50" width="50" xmlns="http://www.w3.org/2000/svg"><path d="M20.66 7a1.51 1.51 0 0 0-.55-.57l-7.34-4.24a1.67 1.67 0 0 0-1.54 0L3.89 6.43a1.68 1.68 0 0 0-.77 1.33v8.48a1.57 1.57 0 0 0 .22.76 1.51 1.51 0 0 0 .55.57l7.34 4.24a1.67 1.67 0 0 0 1.54 0l7.34-4.24a1.51 1.51 0 0 0 .55-.57 1.57 1.57 0 0 0 .22-.76V7.76a1.57 1.57 0 0 0-.22-.76zM12 17.92A5.92 5.92 0 1 1 17.13 9L16 9.71l-.36.2-1 .61A3 3 0 0 0 9 12a2.88 2.88 0 0 0 .4 1.48 3 3 0 0 0 5.13 0l2.6 1.52A5.94 5.94 0 0 1 12 17.92zm5.92-5.59h-.66V13h-.65v-.66H16v-.66h.66V11h.65v.66h.66zm2.47 0h-.66V13h-.66v-.66h-.65v-.66h.65V11h.66v.66h.66z"/></svg>
  
  return (

    
    <div className='mx-44'>
      <Joyride
        {...joyride}
        continuous
        hideCloseButton
        scrollToFirstStep
        showProgress
        showSkipButton
      />
      <div className='py-8'>
        <div className="text-3xl">
          HOT Topic
        </div>
      </div>
      <div id='topicTag' className='grid grid-cols-5 justify-center gap-4'>
        <TopicTag name='Git' svg={gitSvg} handleTopic={handleTopic} />
        <TopicTag name='Vue' svg={vueSvg } handleTopic={handleTopic} />
        <TopicTag name='Angular' svg={angularSvg} handleTopic={handleTopic}/>
        <TopicTag name='Spring' svg={springSvg} handleTopic={handleTopic}/>
        <TopicTag name='Node.js' svg={nodeSvg} handleTopic={handleTopic}/>
        <TopicTag name='Meta' svg={metaSvg} handleTopic={handleTopic}/>
        <TopicTag name='Pytorch' svg={pytorchSvg} handleTopic={handleTopic}/>
        <TopicTag name='React' svg={reactSvg} handleTopic={handleTopic}/>
        <TopicTag name='Tensor Flow' svg={tensorFlowSvg} handleTopic={handleTopic}/>
        <TopicTag name='django' svg={djangoSvg} handleTopic={handleTopic}/>

      </div>
      <div className='py-8'>
        <div className="text-3xl">
          HOT Language
        </div>
      </div>
      <div id='lanTag' className='grid grid-cols-5 justify-center gap-4'>
        <LanTag name='python' svg={pythonSvg} handleLan={handleLan} />
        <LanTag name='Java' svg={javaSvg} handleLan={handleLan}/>
        <LanTag name='Java script' svg={jsSvg} handleLan={handleLan}/>
        <LanTag name='C++' svg={cppSvg} handleLan={handleLan}/>
        <LanTag name='Type script' svg={tsSvg} handleLan={handleLan}/>
      </div>
      <div className='py-8'>
      </div>
      <div  id="tagButton" className='grid grid-cols-4 justify-center gap-4'>
        <div>
        <button onClick={() => setJoyride({ run: true, steps: steps })}>
            Start Tour
          </button>
        </div>
        <div></div>
        <div></div>
        <Button topic={topic} lan={lan}/>
      </div>

    </div>

    
    
    
  );
}

export default SelectTag;