import React, { useEffect, useState } from 'react';
import Chart from 'chart.js/auto'; // Import Chart.js
import 'chartjs-adapter-date-fns';
import { useAppSelector } from '../app/hooks';

const ChartList = ({data, refreshed}) => {
  
  const sensorData= useAppSelector((state) => state.sensor.sensorData);

  
  useEffect(() => {
    // Create line graphs for each sensor data
    createLineGraph('Light Density', 'lightDensity', 'rgba(255, 99, 132, 1)');
    createLineGraph('Water Level', 'waterLevel', 'rgba(54, 162, 235, 1)');
    createLineGraph('Temperature', 'temperature', 'rgba(255, 206, 86, 1)');
    createLineGraph('Humidity Soil', 'humiditySoil', 'rgba(75, 192, 192, 1)');
    createLineGraph('Humidity Weather', 'humidityWeather', 'rgba(153, 102, 255, 1)');


  }, [sensorData]); // Re-run when sensorData changes



// ...

const createLineGraph = (title, dataKey, color) => {
  const ctx = document.getElementById(`${dataKey}Chart`);
  if (ctx) {
    // Destroy existing chart associated with the canvas
    Chart.getChart(ctx)?.destroy();
  }

  const chartData = {
    labels: sensorData.map(sensor => {
      const date = new Date(sensor.createdDate);
      const month = (date.getMonth() + 1)
      return date.getDate() + '/' + ( month<10? "0"+month : month)+ " " + date.getHours() + ':' + date.getMinutes();
    }).reverse(), // Parse dates
    datasets: [{
      label: title,
      data: sensorData.map(sensor => sensor[dataKey]).reverse(),
      borderColor: color,
      borderWidth: 1,
      fill: false,
    }],
  };

  new Chart(ctx, {
    type: 'line',
    data: chartData,
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        x: {
          // type: 'time',
          // time: {
          //     displayFormats: {
          //         unit: 'day',
          //         displayFormats: {
          //             day: 'MMM d'
          //         } 
          //     }
          // }
      },
        y: {
          beginAtZero: true,
        },
      },
    },
  });
};


// ...


  return (
    <div>
      <h2>Sensor Data</h2>
      <div>
        <canvas id="lightDensityChart" width="400" height="200"></canvas>
      </div>
      <div>
        <canvas id="waterLevelChart" width="400" height="200"></canvas>
      </div>
      <div>
        <canvas id="temperatureChart" width="400" height="200"></canvas>
      </div>
      <div>
        <canvas id="humiditySoilChart" width="400" height="200"></canvas>
      </div>
      <div>
        <canvas id="humidityWeatherChart" width="400" height="200"></canvas>
      </div>
    </div>
  );
};

export default ChartList;