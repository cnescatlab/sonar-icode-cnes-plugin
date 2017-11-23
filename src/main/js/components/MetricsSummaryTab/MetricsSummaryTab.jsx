import "./MetricsSummaryTab.css";
import '../../App.css';
import MetricsSummary from '../MetricsSummary';
import MetricsBulletChart from '../MetricsBulletChart';
import React from "react";

function template() {
	
	
	return (
    <div classNameName="App">
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/bullet.js"></script>
    
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f77" value="f77" checked />
    <label role="tab-pane" className="tab-pane" id="tab-pane-f77" htmlFor="radio-tab-f77">F77</label>
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f90" value="f90" />
    <label role="tab-pane" className="tab-pane" id="tab-pane-f90" htmlFor="radio-tab-f90">F90</label>
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-sh" value="sh" />
    <label role="tab-pane" className="tab-pane" id="tab-pane-sh" htmlFor="radio-tab-sh">SH</label>
    
    <div data-reactroot="" role="main-content" className="main-content" id="main-content">
        <div role="panel" className="panel" id="panel-f77">
            <table>
                <tr>
                    <th id="empty-cell"></th>
                    <th>Total</th>
                    <th>Min</th>
                    <th>Mean</th>
                    <th>Max</th>
                </tr>
				{this.state.data.map(
					(item) => 
						<MetricsSummary
							item={item}
                        />
                    )
				}
            </table>
            <MetricsBulletChart/>
        </div>
        <div role="panel" className="panel" id="panel-f90">
            <table>
                <tr>
                    <th id="empty-cell"></th>
                    <th>Total</th>
                    <th>Min</th>
                    <th>Mean</th>
                    <th>Max</th>
                </tr>
				{this.state.data.map(
					(item) => 
						<MetricsSummary
							item={item}
                        />
                    )
				}
            </table>
            <MetricsBulletChart/>
        </div>
        <div role="panel" className="panel" id="panel-sh">
            <table>
                <tr>
                    <th id="empty-cell"></th>
                    <th>Total</th>
                    <th>Min</th>
                    <th>Mean</th>
                    <th>Max</th>
                </tr>
				{this.state.data.map(
					(item) => 
						<MetricsSummary
							item={item}
                        />
                    )
				}
            </table>
            <MetricsBulletChart/>
        </div>
    </div>
</div>

  );
};

export default template;
