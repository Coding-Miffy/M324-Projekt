import { useEffect, useState, useMemo } from "react";
import logo from "./assets/react.svg";
import "./App.css";
import { priorityToColor, priorityToLabel } from "./utils/priorityUtils";
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
  const [priority, setPriority] = useState("LOW");

  const fetchTodos = (selectedTag) => {
    const url = selectedTag
      ? `http://localhost:8080/api/v1/tasks/filter?tag=${selectedTag}`
<<<<<<< HEAD
      : "http://localhost:8080/api/v1/";
=======
      : "http://localhost:8080/api/v1/tasks";
>>>>>>> 8461a2fef90ac0b31941fdbf6b8e8b7742b20064
    fetch(url)
      .then((response) => response.json())
      .then((data) => setTodos(data))
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    fetchTodos(filterTag);
  }, [filterTag]);

  const sortedTodos = useMemo(() => {
    const priorityOrder = { HIGH: 0, MEDIUM: 1, LOW: 2 };
    return [...todos].sort(
      (a, b) => priorityOrder[a.priority] - priorityOrder[b.priority],
    );
  }, [todos]);

  /** Is called when the html form is submitted. It sends a POST request to the API endpoint '/tasks' and updates the component's state with the new todo.
   ** In this case a new taskdecription is added to the actual list on the server.
   */

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch("http://localhost:8080/api/v1/tasks", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        taskdescription: taskdescription,
        color: color,
        tag: tag || null,
        priority: priority,
      }), // both 'taskdescription' are identical to Task-Class attribute in Spring
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
<<<<<<< HEAD
    fetch("http://localhost:8080/api/v1/delete", {
=======
    fetch("http://localhost:8080/api/v1/tasks/delete", {
>>>>>>> 8461a2fef90ac0b31941fdbf6b8e8b7742b20064
      method: "POST",
      body: JSON.stringify({ taskdescription: taskdescription }),
      headers: { "Content-Type": "application/json" },
    })
      .then(() => fetchTodos(filterTag))
      .catch((error) => console.log(error));
  };

  const renderTasks = (todos) => {
    const priorityOrder = { HIGH: 0, MEDIUM: 1, LOW: 2 };
    const sorted = [...todos].sort(
      (a, b) => priorityOrder[a.priority] - priorityOrder[b.priority],
    );
    return (
      <ul className="todo-list">
        {sorted.map((todo, index) => (
          <li
            key={todo.taskdescription}
            style={{
              borderLeft: `10px solid ${todo.color || "#ffffff"}`,
              paddingLeft: "10px",
              background: priorityToColor(todo.priority),
            }}
          >
            <span>{"Task " + (index + 1) + ": " + todo.taskdescription}</span>
            {todo.tag && (
              <span className="todo-tag">
                {TAG_LABELS[todo.tag] ?? todo.tag}
              </span>
            )}
            <span>{priorityToLabel(todo.priority)}</span>

            <button
              onClick={(event) => handleDelete(event, todo.taskdescription)}
            >
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
              <option key={value} value={value}>
                {label}
              </option>
            ))}
          </select>
          <label htmlFor="priority">Priorität zuweisen</label>
          <select
            id="priority"
            name="priority"
            onChange={(event) => setPriority(event.target.value)}
          >
            <option value="LOW">Niedrig</option>
            <option value="MEDIUM">Mittel</option>
            <option value="HIGH">Hoch</option>
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
              <option key={value} value={value}>
                {label}
              </option>
            ))}
          </select>
        </div>
        <div>{renderTasks(todos)}</div>
      </header>
    </div>
  );
}

export default App;
