import React, { useEffect, useState } from 'react';
import ReactPlayer from 'react-player'
import fork1 from '@assets/video/fork1.mov'
import fork2 from '@assets/video/fork2.mov'
import clone from '@assets/video/clone.mov'




function PRTutorial() {

  const contentsList = [fork1, fork2, clone]
  const [curNum, setCurNum] = useState(0)
  const [curContent, setCurContent] = useState(contentsList[curNum])

  const nextBtn = () => {
    setCurNum( curNum => curNum + 1)
    console.log(curNum)
    setCurContent(contentsList[curNum])
    
  }
  
  return (
    <div className='player-wrapper'>
      <ReactPlayer 
        url={curContent} 
        loop={true} 
        playing={true}
        muted={true} 
        height='500px'
        width='800px'
      />
      <button
        onClick={nextBtn}
      >next
      </button>
    </div>

    
  );
}

export default PRTutorial