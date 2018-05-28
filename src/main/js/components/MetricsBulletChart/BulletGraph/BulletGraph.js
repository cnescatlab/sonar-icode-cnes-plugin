import React from 'react';
import HorizontalBulletGraph from './HorizontalBulletGraph';

// PropTypes is a separate package now:
import PropTypes from 'prop-types';

class BulletGraph extends React.Component {

    // Use static properties for propTypes/defaultProps
    static propTypes = {
        title: PropTypes.string.isRequired,
        textLabel: PropTypes.string.isRequired,
        scaleMin: PropTypes.number.isRequired,
        scaleMax: PropTypes.number.isRequired,
        performanceVal: PropTypes.number,
        symbolMarker: PropTypes.number,
        badVal: PropTypes.number,
        satisfactoryVal: PropTypes.number,
        unitsPrefix: PropTypes.string,
        unitsSuffix: PropTypes.string,
        titleStyle: PropTypes.string,
        textFont: PropTypes.string,
        badColor: PropTypes.string,
        satisfactoryColor: PropTypes.string,
        goodColor: PropTypes.string,
        height: PropTypes.number,
        width: PropTypes.number
    }

    static defaultProps = {
        badColor: "#999999",
        satisfactoryColor: "#bbbbbb",
        goodColor: "#dddddd",
        width: 500,
        height: 38
    }

    render() {
        return ( 
            <div className = "BulletGraph" >
                <HorizontalBulletGraph title = { this.props.title }
                    textLabel = { this.props.textLabel }
                    scaleMin = { this.props.scaleMin }
                    scaleMax = { this.props.scaleMax }
                    performanceVal = { this.props.performanceVal }
                    symbolMarker = { this.props.symbolMarker }
                    badVal = { this.props.badVal }
                    satisfactoryVal = { this.props.satisfactoryVal }
                    unitsSuffix = { this.props.unitsSuffix }
                    unitsPrefix = { this.props.unitsPrefix }
                    titleStyle = { this.props.titleStyle }
                    textFont = { this.props.textFont }
                    badColor = { this.props.badColor }
                    satisfactoryColor = { this.props.satisfactoryColor }
                    goodColor = { this.props.goodColor }
                    height = { this.props.height }
                    width = { this.props.width } />
                </div>
        );
    }
};

export default BulletGraph;