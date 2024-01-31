
import { useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../app/hooks";
import ChartList from "../../components/report-chart";
import Spinner from "../../components/spinner";
import Alert from "../../components/alert";
import { clearError, fetchSensorData } from "../../features/sensor/sensorSlice";

function Reports() {

  const [refresh, setRefresh] = useState(false);
  const sensorData = useAppSelector((state) => state.sensor.sensorData);
  const status = useAppSelector((state) => state.sensor.status);
  const error = useAppSelector((state) => state.sensor.error);

  const dispatch = useAppDispatch();

  const closeModal = () => {
    dispatch(clearError());
  }

  useEffect(() => {
    // Fetch sensor data
    dispatch(fetchSensorData());
  }, []);

  useEffect(() => {
    dispatch(fetchSensorData());
  }, [refresh]);


  const handleRefresh = () => {
    setRefresh(!refresh);
  }

  return (
    <div>
      <h2>Reports</h2>
      <button onClick={handleRefresh}>Refresh</button>
      {
        status === 'loading' && <Spinner />
      }
      {
        error && <Alert error={error} closeModal={closeModal} />
      }
      {
        (sensorData && sensorData.length > 0) && <ChartList data={sensorData} refreshed={refresh} />
      }
    </div>
  );
}

export default Reports;