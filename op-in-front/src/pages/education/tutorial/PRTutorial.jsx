import React, { useEffect, useState } from 'react';
import { useSpring, animated } from '@react-spring/web'
import { useNavigate } from 'react-router';
import Joyride from 'react-joyride'
import ReactPlayer from 'react-player'
import fork1 from '@assets/video/fork1.mov'
import fork2 from '@assets/video/fork2.mov'
import clone from '@assets/video/clone.mov'
import clone2 from '@assets/video/clone2.mov'
import branch from '@assets/video/branch.mov'
import contributor from '@assets/video/contributor.mov'
import add from '@assets/video/add.mov'
import commit from '@assets/video/commit.mov'
import push from '@assets/video/push.mov'
import pullReque from '@assets/video/pullReque.mov'
import cpr from '@assets/video/cpr.mov'
import complete from '@assets/video/complete.mov'
import { Progress } from 'react-sweet-progress';
import "react-sweet-progress/lib/style.css";







function PRTutorial() {
  

  const navigate =  useNavigate()
  const contentsList = [fork1, fork2, clone, clone2, branch, contributor, add, commit, push, pullReque, cpr, complete]
  const [curNum, setCurNum] = useState(0)
  const [curContent, setCurContent] = useState(contentsList[curNum])
  const joyRideSteps = [
    [
  
      {
        title:"FORK", 
        content: "first contributor가 되기 위한 첫걸음 입니다!  fork를 눌러 repository를 나의 repository에 복제해봅시다. ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"FORK", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"CLONE", 
        content: "fork를 완료했습니다! 이제 clone을 통해 컴퓨터에 repository를 설치해봅시다.",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"CLONE", 
        content: "code를 눌러 repository 주소를 복사해주세요!",
        target: "#player",
        placement:"bottom",
      },
    ],
    [
      {
        title:"CLONE", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"BRANCH", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"FORK", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"FIX", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"ADD", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"COMMIT", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"PUSH", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"PULL & REQUEST", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"PULL & REQUEST", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"COMPLETE", 
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],


  ];

  const [state, toggle] = useState(true)
  const { x } = useSpring({
    from: { x: 0 },
    x: state ? 1 : 0,
    config: { duration: 1000 },
  })
  

  const [joyride, setJoyride] = useState({});

  const nextBtn = () => {
    setCurNum(curNum => curNum + 1)
    if(!state){
      toggle(!state)
    }
    
  }

  const preBtn = () => {
    setCurNum(curNum => curNum - 1)
    if(!state){
      toggle(!state)
    }
    
  }

  useEffect(() => {
    setCurContent(contentsList[curNum])
    setJoyride({ run: false, steps: joyRideSteps[curNum] })
  },[curNum])

  
  return (
    <div className="mx-44">
      <Joyride
          {...joyride}
          disableBeacon={true}
          disableScrolling = {true}
          continuous
          hideCloseButton
          scrollToFirstStep
          showProgress
          showSkipButton
          styles={{
            options: {
              arrowColor: '#e3ffeb',
              backgroundColor: '#e3ffeb',
              overlayColor: 'rgba(79, 26, 0, 0.4)',
              primaryColor: '#7dee7b',
              textColor: '#004a14',
              width: 900,
              zIndex: 1000,
            }
          }}
          
        />
      <div className='py-8'>
      </div>
      <div className="grid justify-center shadow-md py-10 bg-white">
        <div className='grid grid-cols-4 '>
          <div></div>
          <div></div>
          <div></div>
          <div className='grid grid-cols-2'>
          <div>
          </div>
          { curNum !== contentsList.length - 1 ? (<button 
              className="flex justify-center shadow-md rounded-md border border-transparent bg-green-400 py-2 my-3  text-sm font-medium text-white hover:bg-green-300 focus:outline-none focus:ring-2 focus:ring-green-200 focus:ring-offset-2"
              onClick={() => {
                toggle(!state)
                setJoyride({ run: true, steps: joyRideSteps[curNum] })
              }}
            >
              <animated.div
                style={{
                  opacity: x.to({ range: [0, 1], output: [0.3, 1] }),
                  scale: x.to({
                    range: [0, 0.25, 0.35, 0.45, 0.55, 0.65, 0.75, 1],
                    output: [1, 0.97, 0.9, 1.1, 0.9, 1.1, 1.03, 1],
                  }),
                }}>
                HELP!!
              </animated.div>
            </button>
          ):(
            <div className='py-8 '></div>
          )}
          </div>
        </div>
        <div id="player" className='shadow-md rounded-xl border '>
          
            <ReactPlayer className='shadow-md'
              url={curContent} 
              loop={true} 
              playing={true}
              muted={true} 
              height='500px'
              width='1000px'
            />

        </div>
      </div>
      <div className=" shadow-md py-8 px-10 bg-white">
        <p className='text-3xl'>
          <animated.div
                style={{
                  opacity: x.to({ range: [0, 1], output: [0.3, 1] }),
                  scale: x.to({
                    range: [0, 0.25, 0.35, 0.45, 0.55, 0.65, 0.75, 1],
                    output: [1, 0.97, 0.9, 1.1, 0.9, 1.1, 1.03, 1],
                  }),
                }}>
                {joyRideSteps[curNum][0].title}
          </animated.div>
        </p>
        { curNum !== 0 ?(<Progress percent={(parseInt(100/contentsList.length + 1 )* curNum)+1 } />
        ):(
          <Progress percent={0} />
        )}
      </div>
        <div className='grid grid-cols-4 shadow-md bg-white '>
          <div className='grid' >
            { curNum !== contentsList.length - 1 ? (<button
              className="flex justify-center shadow-md rounded-md border border-transparent bg-yellow-400 mx-7 my-3 py-4 px-4 text-sm font-medium text-white hover:bg-yellow-300 focus:outline-none focus:ring-2 focus:ring-yellow-200 focus:ring-offset-2"
              onClick={(e) =>{
                e.preventDefault()
                navigate('/')}}
            >
              SKIP
            </button>
            ):(
              <div></div>
            )}
          </div>
          <div></div>
          <div></div>
          <div className='grid grid-cols-2'>
            { curNum ? (<button 
                className='flex justify-center shadow-md rounded-md border border-transparent bg-indigo-400 mx-3 my-3 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
                onClick={preBtn}
              >
                PREVIOUS
              </button>
              ) : (
                <div></div>
              )}

            { curNum !== contentsList.length - 1 ?(<button 
              className='flex justify-center rounded-md shadow-md border border-transparent bg-indigo-400 mx-3 my-3 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
              onClick={nextBtn}
            >
              NEXT
            </button>
            ) : (
              <button 
              className='flex justify-center rounded-md shadow-md border border-transparent bg-indigo-400 mx-3 my-3 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
              onClick={(e) =>{
                e.preventDefault()
                navigate('/')}}
            >
              
              COMPLETE
            </button>

            )}
          </div>
        </div>
    </div>

    
  );
}





export default PRTutorial;



