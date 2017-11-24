import React from "react";
import "./MetricsSummaryTab.css";
import MetricsSummaryPanel from '../MetricsSummaryPanel';

class MetricsSummaryTab extends React.Component {

    state = {
        dataF77: [],
        dataF90: [],
        dataSH: []
    };

    componentDidMount() {

        this.setState({
            dataF77: [
                { name: 'Nesting', total: '-', min: 5, mean: 5.2, max: 6 },
                { name: 'Ratio Comment', total: '-', min: 18.6, mean: 29.8, max: 38.9 },
                { name: 'Complexity Simplified', total: '-', min: 26, mean: 33.6, max: 58 },
                { name: 'Line Of Code', total: 601, min: 101, mean: 110.2, max: 182 }
            ],
            dataF90: [
                { name: 'Nesting', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Ratio Comment', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Complexity Simplified', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Line Of Code', total: '-', min: '-', mean: '-', max: '-' }
            ],
            dataSH: [
                { name: 'Nesting', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Ratio Comment', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Complexity Simplified', total: '-', min: '-', mean: '-', max: '-' },
                { name: 'Line Of Code', total: '-', min: '-', mean: '-', max: '-' }
            ]
        });
    }

    render() {
        return (
            <div className = "MetricsSummaryTab" >

                <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f77" value="f77" checked />
                <label role="tab-pane" className="tab-pane" id="tab-pane-f77" htmlFor="radio-tab-f77">F77</label>
                <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-f90" value="f90" />
                <label role="tab-pane" className="tab-pane" id="tab-pane-f90" htmlFor="radio-tab-f90">F90</label>
                <input type="radio" name="radio-tab-chart" className="radio-tab" id="radio-tab-sh" value="sh" />
                <label role="tab-pane" className="tab-pane" id="tab-pane-sh" htmlFor="radio-tab-sh">SH</label>
                
                <div data-reactroot="" role="main-content" className="main-content" id="main-content">
                    <div role="panel" className="panel" id="panel-f77">
                        <MetricsSummaryPanel label='F77' data={this.state.dataF77}/>
                    </div>
                    <div role="panel" className="panel" id="panel-f90">
                        <MetricsSummaryPanel label='F90' data={this.state.dataF90}/>
                    </div>
                    <div role="panel" className="panel" id="panel-sh">
                        <MetricsSummaryPanel label='SH' data={this.state.dataSH}/>
                    </div>
                </div>
            </div>
        );
    };
}

export default MetricsSummaryTab;