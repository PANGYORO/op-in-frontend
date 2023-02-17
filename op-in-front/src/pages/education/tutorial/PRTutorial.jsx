import React, { useEffect, useState } from 'react';
import { useSpring, animated, useTransition, useSpringRef } from '@react-spring/web'
import { useNavigate } from 'react-router';
import Joyride from 'react-joyride'
import ReactPlayer from 'react-player'
import fork1 from '@assets/video/fork1.mov'
import fork2 from '@assets/video/fork2.mov'
import clone from '@assets/video/clone.mov'
import clone2 from '@assets/video/clone2.mov'
import branch from '@assets/video/branch.mov'
import fix from '@assets/video/contributor.mov'
import add from '@assets/video/add.mov'
import commit from '@assets/video/commit.mov'
import push from '@assets/video/push.mov'
import pullReque from '@assets/video/pullReque.mov'
import cpr from '@assets/video/cpr.mov'
import complete from '@assets/video/complete.mov'
import { Progress } from 'react-sweet-progress';
import "react-sweet-progress/lib/style.css";
import styles from './styles.module.css';







function PRTutorial() {
  const [index, set] = useState(0);
    const onClick = () => set(state => (state + 1) % 3);
    const transRef = useSpringRef();
    const transitions = useTransition(index, {
        ref: transRef,
        keys: null,
        from: { opacity: 0, transform: 'translate3d(100%,0,0)' },
        enter: { opacity: 1, transform: 'translate3d(0%,0,0)' },
        leave: { opacity: 0, transform: 'translate3d(-50%,0,0)' },
    });
    useEffect(() => {
        transRef.start();
    }, [index]);
  

  const pages = [
    ({ style }) => React.createElement(animated.div, { style: Object.assign(Object.assign({}, style), { background: 'white' }) },
      <ReactPlayer className='shadow-md'
        url={curContent} 
        loop={true} 
        playing={true}
        muted={true} 
        height='400px'
        width='800px'
      />
    ),
    ({ style }) => React.createElement(animated.div, { style: Object.assign(Object.assign({}, style), { background: 'white' }) },
      <ReactPlayer className='shadow-md'
        url={curContent} 
        loop={true} 
        playing={true}
        muted={true} 
        height='400px'
        width='800px'
      />
    ),
    ({ style }) => React.createElement(animated.div, { style: Object.assign(Object.assign({}, style), { background: 'white' }) },
      <ReactPlayer className='shadow-md'
        url={curContent} 
        loop={true} 
        playing={true}
        muted={true} 
        height='400px'
        width='800px'
      />
    ),
  ];
  const navigate =  useNavigate()
  const contentsList = [fork1, fork2, clone, clone2, branch, fix, add, commit, push, pullReque, cpr, complete]
  const [curNum, setCurNum] = useState(0)
  const [curContent, setCurContent] = useState(contentsList[curNum])
  const joyRideSteps = [
    [
  
      {
        title: "FORK",
        disableBeacon: true,
        content: "first contributor가 되기 위한 첫걸음 입니다!  fork를 눌러 repository를 나의 repository에 복제해봅시다. ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "FORK",
        disableBeacon: true,
        content: "create fork를 눌러 복제를 완료하세요! ",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "CLONE",
        disableBeacon: true,
        content: "fork를 완료했습니다! 이제 clone을 통해 컴퓨터에 repository를 설치해봅시다.",
        target: "#player",
        placement:"top-end",
      },
      {
        title: "CLONE",
        disableBeacon: true,
        content: "code를 눌러 repository 주소를 복사해주세요!",
        target: "#player",
        placement:"bottom",
      },
    ],
    [
      {
        title: "CLONE",
        disableBeacon: true,
        content: "Visual Studio Code 를 실행하여 터미널을 실행시켜주세요! ",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"CLONE", 
        content: "터미널에 복사한 repository 주소를 clone 하여 내컴퓨터에 repository를 설치해 봅시다 ",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"CLONE", 
        content: " 터미널에 'git clone 복사한 주소' 를 입력하고 enter를 눌러주세요 ",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"CLONE", 
        content: " 축하합니다! clone을 통해 내 컴퓨터에 repository를 설치하는데 성공했습니다!  ",
        target: "#player",
        placement:"bottom",
      },

    ],
    [
      {
        title: "BRANCH",
        disableBeacon: true,
        content: "컴퓨터에 설치된 first-contribution에 접근하여 나만의 branch를 만들어 봅시다",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"BRANCH", 
        content: "터미널에 'cd first-contribution'을 입력하고 enter를 눌러주세요",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"BRANCH", 
        content: "이제 branch를 생성하여 작업환경을 나의 branch로 바꾸어 봅시다",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"BRANCH", 
        content: "'git switch -c branchName'을 입력하세요. branchName은 여러분이 사용하고 싶은 단어를 사용해도 됩니다. ",
        target: "#player",
        placement:"bottom",
      },
      {
        title:"BRANCH", 
        content: "'git switch -c branchName'을 입력하고 enter를 눌렀다면 이제 나만의 branch가 생성되어 작업환경으로 변경됐습니다 ",
        target: "#player",
        placement:"bottom",
      },
    ],
    [
      {
        title: "FIX",
        disableBeacon: true,
        content: "이제 Contributor.md 파일에 이름을 남겨 기여를 해봅시다! ",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"FIX", 
        content: "Visual Studio Code를 통해 first-contribution을 열어주세요",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"FIX", 
        content: "Contributor.md 파일을 열어 첫줄과 마지막줄을 제외하고 이름과 github주소를 남겨주세요!",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "ADD", 
        disableBeacon: true,
        content: "Contributor.md 파일을 수정하고 저장했다면 'git add Contributors.md' 를 입력하여 변경 내용을 스테이징 영역에 추가하세요",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "COMMIT",
        disableBeacon: true,
        content: "변경 내용에 대해 message를 남겨 봅시다. git commit -m 'Add your-name to Contributors list' 을 입력하세요",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "PUSH",
        disableBeacon: true,
        content: "이제 변경된 파일을 다시 웹상의 repository로 업로드 해봅시다.",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"PUSH", 
        content: "우리는 아까 우리만의 branch를 생성하고 작업했기 때문에 이 branch를 통해 웹상의 repository로 업로드 해야 합니다",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"PUSH", 
        content: "'git push -u origin your-branch-name' 를 입력하여 push를 진행하세요!",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "PULL & REQUEST",
        disableBeacon: true,
        content: "다시 fork 받은 repository로 돌아가보면 아까는 보이지 않던 compare & pull request 버튼이 활성화된걸 볼 수 있습니다",
        target: "#player",
        placement:"top-end",
      },
      {
        title:"PULL & REQUEST", 
        content: "compare & pull request 버튼을 눌러주세요!",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "PULL & REQUEST",
        disableBeacon: true,
        content: "마지막으로 Create pull request 버튼을 눌러주세요",
        target: "#player",
        placement:"top-end",
      },
    ],
    [
      {
        title: "COMPLETE",
        disableBeacon: true,
        content: "first-contriburions repository로 Pull request가 날라갔습니다! 관리자가 확인한 후 문제가 없다면 merge 시켜줄 것입니다",
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
    set(state => (state + 1) % 3);
    
  }

  const preBtn = () => {
    setCurNum(curNum => curNum - 1)
    if(!state){
      toggle(!state)
    }
    set(state => (state + 1) % 3);
    
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
      <div className='py-4'>
      </div>
      <div className="grid shadow-md pt-2 bg-white">
      <div className='grid grid-cols-4 '>
          <div></div>
          <div></div>
          <div></div>
          <div className='grid grid-cols-2'>
          { curNum !== contentsList.length ? (<button 
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

      </div>

      <div className="grid justify-center shadow-md py-2 bg-white">
  
        <div id="player" className='rounded-xl '>
            <div className={`fill ${styles.player}`} onClick={onClick}>
              {transitions((style, i) => {
                const Page = pages[i]
                return <Page style={style} />
              })}
            </div>

        </div>
      </div>
      <div className=" shadow-md py-4 px-10 bg-white">
        <p className='text-3xl'>
          {joyRideSteps[curNum][0].title}
        </p>
        { curNum !== 0 ?(<Progress percent={(parseInt(100/contentsList.length + 1 )* curNum)+1 } />
        ):(
          <Progress percent={0} />
        )}
      </div>
        <div className='grid grid-cols-4 shadow-md bg-white '>
          <div className='grid' >
            { curNum !== contentsList.length - 1 ? (<button
              className="flex justify-center shadow-md rounded-md border border-transparent bg-yellow-400 mx-7 my-3 py-2 px-4 text-sm font-medium text-white hover:bg-yellow-300 focus:outline-none focus:ring-2 focus:ring-yellow-200 focus:ring-offset-2"
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
                className='flex justify-center shadow-md rounded-md border border-transparent bg-indigo-400 mx-3 my-3 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
                onClick={preBtn}
              >
                PREVIOUS
              </button>
              ) : (
                <div></div>
              )}

            { curNum !== contentsList.length - 1 ?(<button 
              className='flex justify-center rounded-md shadow-md border border-transparent bg-indigo-400 mx-3 my-3 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
              onClick={nextBtn}
            >
              NEXT
            </button>
            ) : (
              <button 
              className='flex justify-center rounded-md shadow-md border border-transparent bg-indigo-400 mx-3 my-3 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'
              onClick={(e) =>{
                e.preventDefault()
                navigate('/tutorial/complete')}}
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



