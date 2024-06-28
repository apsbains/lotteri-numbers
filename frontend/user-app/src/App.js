import React, { useState, useEffect } from 'react';
import axios from 'axios';
import UserForm from './components/UserForm';
import NumberGenerator from './components/NumberGenerator';
import NumberList from './components/NumberList';

const App = () => {
  const [user, setUser] = useState(null);
  const [numbers, setNumbers] = useState([]);

  const fetchNumbers = async (name) => {
    const response = await axios.get(`http://localhost:8080/api/users/${name.trim()}/numbers`);
    setNumbers(response.data);
  };

  useEffect(() => {
    if (user) {
      fetchNumbers(user.name.trim());
    }
  }, [user]);

  return (
    <div>
      <h1>Number Generator</h1>
      <UserForm setUser={setUser} fetchNumbers={fetchNumbers} />
      {user && (
        <>
          <NumberGenerator user={user} fetchNumbers={fetchNumbers} />
          <NumberList numbers={numbers} />
        </>
      )}
    </div>
  );
};

export default App;
