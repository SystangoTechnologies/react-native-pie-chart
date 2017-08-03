import React, { Component } from 'react';
import { Platform, View, NativeModules,requireNativeComponent} from 'react-native'
import Pie from './Pie.js';

var RNPieChart = null;

class PieChart extends Component {

  handleAngle(){
    const series = this.props.series;
    const sum = series.reduce((previous, current) => {return previous + current;}, 0);
    const angle = series.reduce((previous, current, index) => {
      if (index == (series.length - 1)) {
        return previous.concat(360);
      } else {
        return previous.concat(previous[previous.length - 1] + Math.round(360 * current/sum));
      }
    }, [0]);
    return angle;
  }

  render() {
    if (Platform.OS == 'ios') {

      return (
        <Pie
         {...this.props}
         angle={this.handleAngle()}
       />
       );

    }
    else {
      var size = {height: this.props.chart_wh, width: this.props.chart_wh};

      return (
        <View>
       <RNPieChart
        series={this.props.series}
        sliceColor={this.props.sliceColor}
        chart_wh={this.props.chart_wh}
        style={[size, this.props.style]}
       />
       </View>
      );
    }
  }
}

PieChart.propTypes = {
  chart_wh: React.PropTypes.number,
  coverFill: React.PropTypes.string,
  type: React.PropTypes.string,
  coverRadius: React.PropTypes.number,
  doughnut: React.PropTypes.bool,
  series: React.PropTypes.array,
  sliceColor: React.PropTypes.array,
  style: React.PropTypes.object,
  testID:React.PropTypes.string,
  accessibilityComponentType:React.PropTypes.string,
  accessibilityLabel:React.PropTypes.string,
  accessibilityLiveRegion:React.PropTypes.string,
  renderToHardwareTextureAndroid:React.PropTypes.bool,
  importantForAccessibility:React.PropTypes.string,
  onLayout:React.PropTypes.func,
};

PieChart.defaultProps = {
  coverFill: '#FFF',
  coverRadius: 0.6,
  doughnut: false,
  style: {},
  size:300
};


NativeModules.RNPieChart;

// Native component
RNPieChart = requireNativeComponent('PieChartModule', PieChart);


module.exports = PieChart;
