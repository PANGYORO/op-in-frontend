import React,{useState, useEffect} from "react";
import Event from "@components/event/event";
import axios from "axios";
// import http from "@api/http";


// const Events = () => {
//   // const location = useLocation();
//   const [detail, setEvents] = useState({});

//   useEffect(() => {
//     http
//       .get('http://i8c211.p.ssafy.io:5001/event')
//       .then(({ data }) => {
//         setEvents(data);
//       })
//       .catch((error) => console.log(error));
//   }, []);

//   return (
//     <div className=" h-screen overflow-auto w-full pt-4">
//       <Event details={detail} />
//     </div>
//   );
// };
// export default Events;

// export default function Events() {
//   return (
//     <>
//       <div className=" h-screen overflow-auto w-full pt-4">
//         <Event />
//         <Event />
//       </div>
//     </>
//   );
// }

///////////////////////// chat gpt

// const Events = () => {
//   const [events, setEvents] = useState([]);

//   useEffect(() => {
//     const fetchData = async () => {
//       setLoading(true);
//       try {
//         const response = await fetch("http://i8c211.p.ssafy.io:5001/event", {
//           method: "GET",
//         });
//         const Eventdata = await response.data;
//         setEvents = Eventdata
//         setLoading(false);
//       } catch (error) {
//         setLoading(false);
//       }
//     };
//     fetchData();
//   }, []);

//   return (
//     <div className=" h-screen overflow-auto w-full pt-4">
//       {events.map((event) => (
//         // <li key={event.id}>
//         <div >
//           <Event
//             key={event.id}
//             id={event.id}
//             title={event.title}
//             content={event.content}
//             repoDetails={event}
//           />
//         </div>
//       ))}
//     </div>
//   );
// };
// export default Events;

const EventList = ({ events }) => {
  // console.log(events);
  return (
    <div className="h-screen overflow-auto w-full pt-4">
      {events.map((event) => {
        return (
          <Event
            key={event.id}
            event={event}
          />
        );
      })}
    </div>
  );
};

const Eventselect = () => {
  const [EventData, setEventData] = useState([]);

  useEffect(() => {
    getEventData();
  }, []);

  const getEventData = async () => {
    await axios
    .get('http://i8c211.p.ssafy.io:5001/event')
    .then(({ data }) => {
      // console.log(data);
      setEventData([...data]);
      // console.log(data,'호호')
    })
    .catch((error) => {
      console.log(error);
    });
  }

  return (
    <>
      <EventList events={EventData}/>
    </>
  );
};
export default Eventselect;

