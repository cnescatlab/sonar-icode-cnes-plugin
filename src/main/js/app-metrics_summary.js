/*
 * Copyright (C) 2017-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
import React from 'react';
import { render, unmountComponentAtNode } from 'react-dom';
import App from './App';
import './style.css';

window.registerExtension('icode/icode_metrics_summary', options => {

  const { el } = options;

  render(
          <App/>, el
  );

  return () => unmountComponentAtNode(el);
});
