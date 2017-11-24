import "./MetricsSummaryTab.css";
import '../../App.css';
import MetricsSummary from '../MetricsSummary';
import MetricsBulletChart from '../MetricsBulletChart';
import React from "react";

function template() {
  return (
    <div className="App">
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/bullet.js"></script>


    <table>
      <tr>
      <th></th>
      <th>Total</th>
      <th>Min</th>
      <th>Mean</th>
      <th>Max</th>
      </tr>
      <tbody>
        {this.state.data.map(
            (item) =>
            <MetricsSummary
                item={item}
              />
          )
        }
        </tbody>
    </table>
    <MetricsBulletChart/>
        </div>
  );
};

export default template;
