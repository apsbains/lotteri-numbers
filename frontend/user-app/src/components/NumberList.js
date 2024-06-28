import React from 'react';

const NumberList = ({ numbers }) => {
  return (
    <ul>
      {numbers.map((number) => (
        <li key={number.id}>{number.numbers.join(', ')} (generated at {new Date(number.generatedAt).toLocaleString()})</li>
      ))}
    </ul>
  );
};

export default NumberList;