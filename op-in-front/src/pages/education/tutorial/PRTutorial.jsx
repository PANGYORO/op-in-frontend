import React from 'react';
import ReactPlayer from 'react-player'
import fork1 from '@assets/video/fork1.mov'
import fork2 from '@assets/video/fork2.mov'
import clone from '@assets/video/clone.mov'



function PRTutorial() {
  return (
    <div className='player-wrapper'>
      <ReactPlayer 
        url={fork1} 
        loop={true} 
        playing={true}
        muted={true} 
        height='500px'
        width='800px'
      />
    </div>
  );
}

export default PRTutorial