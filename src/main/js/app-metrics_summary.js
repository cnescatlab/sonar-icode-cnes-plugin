/*
 * Copyright (C) 2017-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
import React from 'react';
import { render, unmountComponentAtNode } from 'react-dom';
import './style.css';
import MetricsSummaryTab from './components/MetricsSummaryTab';

window.registerExtension('icode/icode_metrics_summary', options => {

  const { el } = options;

  render(
          <MetricsSummaryTab project={options.component}/>, el
  );

  return () => unmountComponentAtNode(el);
});
