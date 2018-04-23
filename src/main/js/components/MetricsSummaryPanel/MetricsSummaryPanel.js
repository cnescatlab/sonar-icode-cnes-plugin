import React from "react";
import template from "./MetricsSummaryPanel.css";
import MetricsSummary from '../MetricsSummary';
import MetricsBulletChart from '../MetricsBulletChart';

class MetricsSummaryPanel extends React.Component {
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
            <div className="MetricsSummaryPanel" >
                <h3>{ this.props.label }</h3>
                <table>
                    <tr>
                        <th id="empty-cell"></th>
                        <th>Total</th>
                        <th>Min</th>
                        <th>Mean</th>
                        <th>Max</th>
                    </tr>
                    <tbody>
                    {
                        this.props.data.map(
                            (item) =>
                            <MetricsSummary item = { item }/>
                        )
                    }
                    </tbody>
                </table>
                {
                    this.props.data.map(
                        (item) =>
                        <MetricsBulletChart item = { item } />
                    )
                }
            </div>
        );
    };
}

export default MetricsSummaryPanel;