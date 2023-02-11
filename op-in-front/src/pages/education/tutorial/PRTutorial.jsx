import React, { useEffect, useState } from 'react';
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
  


  const nextBtn = () => {
    setCurNum(curNum => curNum + 1)
    
  }

  useEffect(() => {
    setCurContent(contentsList[curNum])
  },[curNum])

  
  return (
    <div className="mx-44">
      <div className='py-8'>
        <div className="text-3xl justify-center">
          progressBar
        </div>
      </div>
      <div className="shadow-md p-5 mb-4 bg-white">

      </div>
      <div className="grid justify-center shadow-md p-5 mb-4 bg-white">
        <div id="player">
          <ReactPlayer className='shadow-md'
            url={curContent} 
            loop={true} 
            playing={true}
            muted={true} 
            height='600px'
            width='900px'
          />

        </div>
        <button 
          className='group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-400 py-4 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
          onClick={nextBtn}
        >next
        </button>
      </div>
    </div>

    
  );
}

export default PRTutorial