import React, { useState, useCallback, useRef, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useTransition, animated } from '@react-spring/web'
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from 'recoil'
import styles from './styles.module.css'
import TutorialModal from '@components/modals/TutorialModal'

function ChooseTutorial() {
  const user = useRecoilValue(userInfo)
  const [open, setOpen] = useState(false);
  const navigate = useNavigate()
  const ref = useRef([]);
  const [_items, _set] = useState([]);
  function toggleModal() {
    setOpen((prev) => !prev);
  }
  const _transitions = useTransition(_items, {
    from: {
      opacity: 0,
      height: 0,
      innerHeight: 0,
      transform: 'perspective(600px) rotateX(0deg)',
      color: '#8fa5b6',
    },
    enter: [
      { opacity: 1, height: 80, innerHeight: 80 },
      { transform: 'perspective(600px) rotateX(180deg)', color: '#28d79f' },
      { transform: 'perspective(600px) rotateX(0deg)' },
    ],
    leave: [{ color: '#c23369' }, { innerHeight: 0 }, { opacity: 0, height: 0 }],
    update: { color: '#28b4d7' },
  });

  const reset = useCallback(() => {
    ref.current.forEach(clearTimeout);
    ref.current = [];
    _set([]);
    ref.current.push(setTimeout(() => _set(['welcome', 'to op-in', user.nickname]), 2000));
    ref.current.push(setTimeout(() => _set(['welcome', user.nickname]), 5000));
    ref.current.push(setTimeout(() => _set(['welcome', 'to op-in', user.nickname]), 8000));
  }, []);

  useEffect(() => {
    reset();
    return () => ref.current.forEach(clearTimeout);
  }, []);
  return (
    <div className="mx-44">
      <div className='py-8'>
      </div>
      <div className='py-4'></div>
      <div className={styles.container}>
        <div className={styles.main}>
          {_transitions(({ innerHeight, ...rest }, _items) => (
            <animated.div className={styles.transitionsItem} style={rest} onClick={reset}>
              <animated.div style={{ overflow: 'hidden', height: innerHeight }}>{_items}</animated.div>
            </animated.div>
          ))}
        </div>
      </div>
      <div className='grid grid-cols-2 px-20 py-20'>
        <button
          type="button"
          className="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300  shadow-lg shadow-green-500/50   font-medium rounded-lg text-sm px-5 py-5 text-center mx-10 mb-2"
          onClick={(e) => {
            e.preventDefault();
            toggleModal();
          }}
        >
          TUTORIAL
        </button>
        <button
          type="button"
          className="text-white bg-gradient-to-r from-cyan-400 via-cyan-500 to-cyan-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-cyan-300  shadow-lg shadow-cyan-500/50   font-medium rounded-lg text-sm px-5 py-5 text-center mx-10 mb-2"
          onClick={(e) => {
            e.preventDefault()
            navigate('/signin')
          }}
        >
          SIGN-IN
        </button>
      </div>
      <TutorialModal open={open} setOpen={setOpen} />
    </div>



  );
}

export default ChooseTutorial