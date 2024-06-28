import React, { useState } from 'react';
import axios from 'axios';

const UserForm = ({ setUser, fetchNumbers }) => {
  const [name, setName] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await axios.post('http://localhost:8080/api/users', { name: name.trim() });
    setUser(response.data);
    fetchNumbers(response.data.name);
    setName('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Enter your name"
        required
      />
      <button type="submit">Submit</button>
    </form>
  );
};

export default UserForm;