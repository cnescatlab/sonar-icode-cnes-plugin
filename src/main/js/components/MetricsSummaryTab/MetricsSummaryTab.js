import "./MetricsSummaryTab.css";
import MetricsSummary from '../MetricsSummary';
import MetricsBulletChart from '../MetricsBulletChart';
import React from "react";

class MetricsSummaryTab extends React.Component {

    state = {
        data: []
    };
    
    componentDidMount() {

        this.setState({
            data: [
                { name: 'Nesting', total: '-', min: 5, mean: 5.2, max: 6 },
                { name: 'Ratio Comment', total: '-', min: 18.6, mean: 29.8, max: 38.9 },
                { name: 'Complexity Simplified', total: '-', min: 26, mean: 33.6, max: 58 },
                { name: 'Line Of Code', total: 601, min: 101, mean: 110.2, max: 182 }
            ]
        });

    }

    render() {
        return (
        <div className="App">
    
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f77" value="f77" checked />
    <label role="tab-pane" className="tab-pane" id="tab-pane-f77" htmlFor="radio-tab-f77">F77</label>
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f90" value="f90" />
    <label role="tab-pane" className="tab-pane" id="tab-pane-f90" htmlFor="radio-tab-f90">F90</label>
    <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-sh" value="sh" />
    <label role="tab-pane" className="tab-pane" id="tab-pane-sh" htmlFor="radio-tab-sh">SH</label>
    
    <div data-reactroot="" role="main-content" className="main-content" id="main-content">
        <div role="panel" className="panel" id="panel-f77">
        	<h3>F77</h3>
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
        	<h3>F90</h3>
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
        <h3>SHELL</h3>
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
    }
}

export default MetricsSummaryTab;