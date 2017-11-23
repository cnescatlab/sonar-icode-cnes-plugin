import "./MetricsSummary.css";
import React    from "react";

class MetricsSummary extends React.Component {
  render() {
    return (
    	    <tr>
    	      <td>{this.props.item.name}</td>
    	      <td>{this.props.item.total}</td>
    	      <td>{this.props.item.min}</td>
    	      <td>{this.props.item.mean}</td>
    	      <td>{this.props.item.max}</td>
    	    </tr>
    		);
  }
}

export default MetricsSummary;
