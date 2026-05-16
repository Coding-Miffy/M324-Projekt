import { useEffect, useState } from "react";
import logo from "./assets/react.svg";
import "./App.css";

const TAG_LABELS = {
  ARBEIT: "Arbeit",
  SCHULE: "Schule",
  PRIVAT: "Privat",
  EINKAUFEN: "Einkaufen",
};

function App() {
  const [todos, setTodos] = useState([]);
  const [taskdescription, setTaskdescription] = useState("");
  const [color, setColor] = useState("#ffffff");
  const [tag, setTag] = useState("");
  const [filterTag, setFilterTag] = useState("");

  const fetchTodos = (selectedTag) => {
    const url = selectedTag
      ? `http://localhost:8080/tasks/filter?tag=${selectedTag}`
      : "http://localhost:8080/";
    fetch(url)
      .then((response) => response.json())
      .then((data) => setTodos(data))
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    fetchTodos(filterTag);
  }, [filterTag]);

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch("http://localhost:8080/tasks", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        taskdescription: taskdescription,
        color: color,
        tag: tag || null,
      }),
    })
      .then(() => {
        setTaskdescription("");
        setColor("#ffffff");
        setTag("");
        fetchTodos(filterTag);
      })
      .catch((error) => console.log(error));
  };

  const handleDelete = (event, taskdescription) => {
    fetch("http://localhost:8080/delete", {
      method: "POST",
      body: JSON.stringify({ taskdescription: taskdescription }),
      headers: { "Content-Type": "application/json" },
    })
      .then(() => fetchTodos(filterTag))
      .catch((error) => console.log(error));
  };

  const renderTasks = (todos) => {
    return (
      <ul className="todo-list">
        {todos.map((todo, index) => (
          <li
            key={todo.taskdescription}
            style={{
              borderLeft: `10px solid ${todo.color || "#ffffff"}`,
              paddingLeft: "10px",
            }}
          >
            <span>{"Task " + (index + 1) + ": " + todo.taskdescription}</span>
            {todo.tag && (
              <span className="todo-tag">{TAG_LABELS[todo.tag] ?? todo.tag}</span>
            )}
            <button onClick={(event) => handleDelete(event, todo.taskdescription)}>
              &#10004;
            </button>
          </li>
        ))}
      </ul>
    );
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>ToDo Liste</h1>
        <form onSubmit={handleSubmit} className="todo-form">
          <label htmlFor="taskdescription">Neues Todo anlegen:</label>
          <input
            id="taskdescription"
            type="text"
            value={taskdescription}
            onChange={(e) => setTaskdescription(e.target.value)}
          />
          <label htmlFor="color">Farbe auswaehlen:</label>
          <input
            id="color"
            type="color"
            value={color}
            onChange={(e) => setColor(e.target.value)}
          />
          <label htmlFor="tag">Tag:</label>
          <select id="tag" value={tag} onChange={(e) => setTag(e.target.value)}>
            <option value="">Kein Tag</option>
            {Object.entries(TAG_LABELS).map(([value, label]) => (
              <option key={value} value={value}>{label}</option>
            ))}
          </select>
          <button type="submit">Absenden</button>
        </form>
        <div className="filter-bar">
          <label htmlFor="filterTag">Nach Tag filtern:</label>
          <select
            id="filterTag"
            value={filterTag}
            onChange={(e) => setFilterTag(e.target.value)}
          >
            <option value="">Alle</option>
            {Object.entries(TAG_LABELS).map(([value, label]) => (
              <option key={value} value={value}>{label}</option>
            ))}
          </select>
        </div>
        <div>{renderTasks(todos)}</div>
      </header>
    </div>
  );
}

export default App;