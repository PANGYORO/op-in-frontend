import React, { useState, useEffect } from "react";
import Event from "@components/event/event";
import axios from "axios";

const EventList = ({ events }) => {
  // console.debug(events);
  return (
    <div className="w-full pt-4">
      <header className="z-40 items-center w-full mx-4 mb-4 h-16 bg-white shadow-lg dark:bg-gray-700 rounded-2xl">
        <div className="relative flex items-center w-full h-full group ml-3 text-2xl">
          Events
        </div>
      </header>
      {events.map((event) => {
        return <Event key={event.id} event={event} />;
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
      .get("http://i8c211.p.ssafy.io:5001/event")
      .then(({ data }) => {
        // console.debug(data);
        setEventData([...data]);
        // console.debug(data,'호호')
      })
      .catch((error) => {
        console.debug(error);
      });
  };

  return (
    <>
      <EventList events={EventData} />
    </>
  );
};
export default Eventselect;
