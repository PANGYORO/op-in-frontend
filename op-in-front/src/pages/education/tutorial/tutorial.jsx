
import React, { useEffect, useState } from 'react';
import ReactPlayer from 'react-player'
import fork1 from '@assets/video/fork1.mov'
import fork2 from '@assets/video/fork2.mov'
import clone from '@assets/video/clone.mov'
import Joyride from 'react-joyride'




function PRTutorial() {

  const contentsList = [fork1, fork2, clone]
  const [curNum, setCurNum] = useState(0)
  const [curContent, setCurContent] = useState(contentsList[curNum])
  const steps = [
    [
  
      {
        title:"Topic Tag", 
        content: "1",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"Lan Tag", 
        content: "1",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"Tag Btn ", 
        content: "1",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title:"Topic Tag", 
        content: "2",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"Lan Tag", 
        content: "2",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"Tag Btn ", 
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

  const [joyride, setJoyride] = useState({});

  const nextBtn = () => {
    setCurNum(curNum => curNum + 1)
    
  }

  useEffect(() => {
    setCurContent(contentsList[curNum])
    setJoyride({ run: false, steps: steps[curNum] })
    console.log(joyride.run)
    
  },[curNum])

  
  return (
    <div className="mx-44">
      <div className='py-8'>
        <div className="text-3xl justify-center">
          progressBar
        </div>
        <button onClick={() => setJoyride({ run: true, steps: steps[curNum] })}>startguide</button>
      </div>
      <div className="grid justify-center ">
        <Joyride
          {...joyride}
          disableBeacon={true}
          continuous
          hideCloseButton
          scrollToFirstStep
          showProgress
          showSkipButton
          
        />
        <div id="player">
          <ReactPlayer
            url={curContent} 
            loop={true} 
            playing={true}
            muted={true} 
            height='500px'
            width='800px'
          />

        </div>
        <button
          onClick={nextBtn}
        >next
        </button>
      </div>
    </div>

    
  );
}

export default PRTutorial;