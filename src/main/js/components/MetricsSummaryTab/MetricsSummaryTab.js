import React from "react";
import "./MetricsSummaryTab.css";
import MetricsSummaryPanel from '../MetricsSummaryPanel';
import {findMetricsSummaryMeasures} from '../../api.js';
import {getJSON} from 'sonar-request';

const NESTING = 0;
const RATIO_COMMENT = 1;
const COMPLEXITY_SIMPLIFIED = 2;
const LOC = 3;

const metricKeysTabF77 = [
	'icode-f77-nesting-min',
	'icode-f77-nesting-mean',
	'icode-f77-nesting-max',
	'icode-f77-ratio-comment-min',
	'icode-f77-ratio-comment-mean',
	'icode-f77-ratio-comment-max',
	'icode-f77-cyclomatic-complexity-min',
	'icode-f77-cyclomatic-complexity-mean',
	'icode-f77-cyclomatic-complexity-max',
	'icode-f77-loc',
	'icode-f77-loc-min',
	'icode-f77-loc-mean',
	'icode-f77-loc-max'];

const metricKeysTabF90 = [
	'icode-f90-nesting-min',
	'icode-f90-nesting-mean',
	'icode-f90-nesting-max',
	'icode-f90-ratio-comment-min',
	'icode-f90-ratio-comment-mean',
	'icode-f90-ratio-comment-max',
	'icode-f90-cyclomatic-complexity-min',
	'icode-f90-cyclomatic-complexity-mean',
	'icode-f90-cyclomatic-complexity-max',
	'icode-f90-loc',
	'icode-f90-loc-min',
	'icode-f90-loc-mean',
	'icode-f90-loc-max'];

const metricKeysTabSH = [
	'icode-shell-nesting-min',
	'icode-shell-nesting-mean',
	'icode-shell-nesting-max',
	'icode-shell-ratio-comment-min',
	'icode-shell-ratio-comment-mean',
	'icode-shell-ratio-comment-max',
	'icode-shell-cyclomatic-complexity-min',
	'icode-shell-cyclomatic-complexity-mean',
	'icode-shell-cyclomatic-complexity-max',
	'icode-shell-loc',
	'icode-shell-loc-min',
	'icode-shell-loc-mean',
	'icode-shell-loc-max'];

function findMeasuresF77(componentName) {
	return new Promise(function(resolve, reject) {

	let metricKeys = metricKeysTabF77.reduce((accumulator, currentValue) => accumulator + ',' + currentValue);

	let measure_component = getJSON('/api/measures/component', {
		metricKeys:metricKeys,
		component:componentName
	}).then(function (measure_component) {
		console.log(measure_component);
		
		// Build map 'metric:value'
		let allMetrics = new Map();
		measure_component.component.measures.forEach(element => {
		    for (let index = 0; index < metricKeysTabF77.length; index++) {
		      const key = metricKeysTabF77[index];
		      if(element.metric === key){
		    	  allMetrics.set(element.metric, element.value);
		      }      
		    }
		});
		console.log(allMetrics);
		
		// Build result table
		let res=[
			{name:'Nesting', 
				total: '-', 
				min: allMetrics.get('icode-f77-nesting-min'), 
				mean: allMetrics.get('icode-f77-nesting-mean'), 
				max: allMetrics.get('icode-f77-nesting-max')
			},
			{name:'Ratio Comment', 
				total: '-', 
				min: allMetrics.get('icode-f77-ratio-comment-min'), 
				mean: allMetrics.get('icode-f77-ratio-comment-mean'), 
				max: allMetrics.get('icode-f77-ratio-comment-max')
			},
			{name:'Complexity Simplified', 
				total: '-', 
				min: allMetrics.get('icode-f77-cyclomatic-complexity-min'), 
				mean: allMetrics.get('icode-f77-cyclomatic-complexity-mean'), 
				max: allMetrics.get('icode-f77-cyclomatic-complexity-max')
			},
			{name:'Line Of Code', 
				total: allMetrics.get('icode-f77-loc'),
				min: allMetrics.get('icode-f77-loc-min'), 
				mean: allMetrics.get('icode-f77-loc-mean'), 
				max: allMetrics.get('icode-f77-loc-max')
			}
		];	
		console.log(res);

		resolve(res);
	}).catch(function(error) {
	    console.log('No measures found: ' + error.message); 
	    reject(null);
	});
});
}

class MetricsSummaryTab extends React.Component {

    state = {
        dataF77: [],
        dataF90: [],
        dataSH: [],
        data:[]
    };

    componentDidMount() {
    	
    	findMeasuresF77(this.props.project.key).then((item) => {
    	    this.setState({
    	      dataF77: item
    	    });
    	});
  }

  constructor(){
    super();
    this.findMeasures = this.findMeasures.bind(this);
  }

    componentDidMount() {
        
  this.findMeasures().then((valuesReturnedByAPI) => {
    this.setState({
      data: valuesReturnedByAPI
    });
  });
        
        this.setState({
/*            dataF77: [
                { name: 'Nesting', total: '-', min: 5, mean: 5.2, max: 6 },
                { name: 'Ratio Comment', total: '-', min: 18.6, mean: 29.8, max: 38.9 },
                { name: 'Complexity Simplified', total: '-', min: 26, mean: 33.6, max: 58 },
                { name: 'Line Of Code', total: 601, min: 101, mean: 110.2, max: 182 }
            ],*/
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
