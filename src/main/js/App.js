import React from 'react';
import MetricsSummaryTab from './components/MetricsSummaryTab';

class App extends React.Component {
	
	state = {
	    project: ''
	};

	componentDidMount() {
		console.log('App:'+this.props.project);
		this.setState({project:this.props.project});
	}
	
	render(){
		return (<MetricsSummaryTab project={this.state.project}/>);
	}
	
}

export default App;