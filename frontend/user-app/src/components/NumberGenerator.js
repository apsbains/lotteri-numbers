import React from 'react';
import axios from 'axios';

const NumberGenerator = ({ user, fetchNumbers }) => {
  const generateNumbers = async () => {
    await axios.post(`http://localhost:8080/api/users/${user.name.trim()}/numbers`);
    fetchNumbers(user.name.trim());
  };

  return (
    <div>
      <button onClick={generateNumbers}>Generate Numbers</button>
    </div>
  );
};

export default NumberGenerator;