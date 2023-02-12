import React, { useEffect, useState } from 'react';
import { useSpring, animated } from '@react-spring/web'
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






function PRTutorial() {

  const contentsList = [fork1, fork2, clone, clone2, branch, contributor, add, commit, push, pullReque, cpr, complete]
  const [curNum, setCurNum] = useState(0)
  const [curContent, setCurContent] = useState(contentsList[curNum])
  const steps = [
    [
  
      {
        title:"fork", 
        content: "first contributor가 되기 위한 첫걸음 입니다!  fork를 눌러 repository를 나의 repository에 복제해봅시다. ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"ddd", 
        content: "2",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"Landdd", 
        content: "2",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"Tagddd", 
        content: "2",
        target: "#player",
        placement:"top",
      },
    ],
    [
      {
        title:"Topic Tag", 
        content: "3",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"Lan Tag", 
        content: "3",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"Tag Btn ", 
        content: "3",
        target: "#player",
        placement:"top",
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
    toggle(!state)
    
  }

  useEffect(() => {
    setCurContent(contentsList[curNum])
    setJoyride({ run: false, steps: steps[curNum] })
  },[curNum])

  
  return (
    <div className="mx-44">
      <Joyride
          {...joyride}
          disableBeacon={true}
          continuous
          hideCloseButton
          scrollToFirstStep
          showProgress
          showSkipButton
          
        />
      <div className='py-8'>
        <div className="text-3xl justify-center " onClick={() => toggle(!state)}>
        <animated.div
          className='flex justify-center'
          style={{
            opacity: x.to({ range: [0, 1], output: [0.3, 1] }),
            scale: x.to({
              range: [0, 0.25, 0.35, 0.45, 0.55, 0.65, 0.75, 1],
              output: [1, 0.97, 0.9, 1.1, 0.9, 1.1, 1.03, 1],
            }),
          }}>
          click
        </animated.div>
        </div>
      </div>
      <div className="grid justify-center shadow-md p-5 mb-4 bg-white">
        <div id="player">
          <ReactPlayer className='shadow-md'
            url={curContent} 
            loop={true} 
            playing={true}
            muted={true} 
            height='500px'
            width='900px'
          />

        </div>
        <div className='grid grid-cols-4 mt-4'>
          <div>
            <button onClick={() => setJoyride({ run: true, steps: steps[curNum] })}>startguide</button>
          </div>
          <div></div>
          <div></div>
          <button 
            className=' flex justify-center rounded-md border border-transparent bg-indigo-400 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
            onClick={nextBtn}
          >
            next
          </button>
        </div>
      </div>
    </div>

    
  );
}





export default PRTutorial;



