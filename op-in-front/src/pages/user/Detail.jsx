import DefaultImg from "../../assets/basicprofile.png";

export default function Detail() {
  return (
    <div className="bg-primary-blue mx-24 my-24" >
      <div className="grid grid-cols-3 gap-3 mb-8">
        <div className="flex justify-center h-full">
          <img src={DefaultImg} alt="none" />
        </div>
        <div className="flex justify-center col-span-2 bg-primary-navy">
            <div className='grid content-center'>
            <div>
              <div className='grid grid-cols-2 gap-3 justify-items-center'>
                <div> NickName</div>
                <div> button</div>
              </div>
              <div className='grid grid-cols-3 gap-3 justify-items-center'>
                <div> posts</div>
                <div> follower</div>
                <div> following</div>
              </div>
            </div>
            </div>
        </div>
      </div>
      <div className="grid grid-rows-3 grid-flow-col gap-4">
        <div className="row-span-3 bg-slate-600">01</div>
        <div className="col-span-2 bg-red-300">02</div>
        <div className="row-span-2 col-span-2 bg-blue-400">03</div>
      </div>

    </div>
    
  )
}
