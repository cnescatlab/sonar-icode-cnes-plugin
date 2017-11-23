import React from "react";
import BulletGraph from './BulletGraph/BulletGraph';

// PropTypes is a separate package now:
import PropTypes from 'prop-types';

class MetricsBulletChart extends React.Component {
    // Use static properties for propTypes/defaultProps
    static propTypes = {
        mocks: PropTypes.array
    }

    static defaultProps = {
        mocks: []
    }

    // Initialize state right in the class body,
    // with a property initializer:
    state = {
        mocks: [{
            title: "Complexity Simp. ",
            textLabel: "values",
            scaleMin: 0,
            scaleMax: 58,
            performanceVal: 33.6,
            symbolMarker: 33.6,
            badVal: 26,
            satisfactoryVal: 58,
            width: 600
        }, ]
    }

    render() {
        return ( <
            div className = "App" >
            <
            div className = "Container" >

            {
                this.state.mocks.map((graph, i) => {
                    return ( <
                        BulletGraph className = "BulletGraph"
                        key = { i }
                        title = { graph.title }
                        textLabel = { graph.textLabel }
                        scaleMin = { graph.scaleMin }
                        scaleMax = { graph.scaleMax }
                        performanceVal = { graph.performanceVal }
                        symbolMarker = { graph.symbolMarker }
                        badVal = { graph.badVal }
                        satisfactoryVal = { graph.satisfactoryVal }
                        unitsSuffix = { graph.unitsSuffix }
                        unitsPrefix = { graph.unitsPrefix }
                        height = { graph.height }
                        width = { graph.width }
                        />
                    )
                })
            }

            <
            /div>

            <
            /div>
        );
    }

}

export default MetricsBulletChart;