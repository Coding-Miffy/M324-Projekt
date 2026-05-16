import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [todos, setTodos] = useState([]);
  const [taskdescription, setTaskdescription] = useState("");
  const [color, setColor] = useState("#ffffff");
  const [priority, setPriority] = useState("LOW");

  /** Is called when the html form is submitted. It sends a POST request to the API endpoint '/tasks' and updates the component's state with the new todo.
   ** In this case a new taskdecription is added to the actual list on the server.
   */
  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(
      "Sending task description to Spring-Server: " + taskdescription,
    );
    fetch("http://localhost:8080/tasks", {
      // API endpoint (the complete URL!) to save a taskdescription
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        taskdescription: taskdescription,
        color: color,
        priority: priority,
      }), // both 'taskdescription' are identical to Task-Class attribute in Spring
    })
      .then((response) => {
        console.log("Receiving answer after sending to Spring-Server: ");
        console.log(response);
        window.location.href = "/";
        setTaskdescription(""); // clear input field, preparing it for the next input
      })
      .catch((error) => console.log(error));
  };

  /** Is called when ever the html input field value below changes to update the component's state.
   ** This is, because the submit should not take the field value directly.
   ** The task property in the state is used to store the current value of the input field as the user types into it.
   ** This is necessary because React operates on the principle of state and props, which means that a component's state
   ** determines the component's behavior and render.
   ** If we used the value directly from the HTML form field, we wouldn't be able to update the component's state and react to changes in the input field.
   */
  const handleChange = (event) => {
    setTaskdescription(event.target.value);
  };

  /** Is called when the component is mounted (after any refresh or F5).
   ** It updates the component's state with the fetched todos from the API Endpoint '/'.
   */
  useEffect(() => {
    fetch("http://localhost:8080/")
      .then((response) => response.json())
      .then((data) => {
        setTodos(data);
      });
  }, []);

  /** Is called when the Done-Button is pressed. It sends a POST request to the API endpoint '/delete' and updates the component's state with the new todo.
   ** In this case if the task with the unique taskdescription is found on the server, it will be removed from the list.
   */
  const handleDelete = (event, taskdescription) => {
    console.log(
      "Sending task description to delete on Spring-Server: " + taskdescription,
    );
    fetch(`http://localhost:8080/delete`, {
      // API endpoint (the complete URL!) to delete an existing taskdescription in the list
      method: "POST",
      body: JSON.stringify({ taskdescription: taskdescription }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        console.log("Receiving answer after deleting on Spring-Server: ");
        console.log(response);
        window.location.href = "/";
      })
      .catch((error) => console.log(error));
  };

  /**
   * render all task lines
   * @param {*} todos : Task list
   * @returns html code snippet
   */

  const renderTasks = (todos) => {

    const priorityToColor = (priority) => {
      switch (priority) {
        case "LOW": return "#00ff00";
        case "MEDIUM": return "#ffff00";
        case "HIGH": return "#ff0000";
        default: return "#ffffff";
      }
    };

    const priorityToLabel = (priority) => {
      switch (priority) {
        case "LOW": return "Tief";
        case "MEDIUM": return "Mittel";
        case "HIGH": return "Hoch";
        default: return priority;
      }
    };


    return (
      <ul className="todo-list">
        {todos.map((todo, index) => (
          <li
            key={todo.taskdescription}
            priority={todo.priority}
            style={{
              borderLeft: `10px solid ${todo.color || "#ffffff"}`,
              paddingLeft: "10px",
              background: priorityToColor(todo.priority),
            }}
          >
            <span>{"Task " + (index + 1) + ": " + todo.taskdescription}</span>
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
        <h1>ToDo Liste</h1>
        <form onSubmit={handleSubmit} className="todo-form">
          <label htmlFor="taskdescription">Neues Todo anlegen:</label>
          <input type="text" value={taskdescription} onChange={handleChange} />
          <label htmlFor="color">Farbe auswählen:</label>
          <input
            id="color"
            type="color"
            value={color}
            onChange={(event) => setColor(event.target.value)}
          />

          <label htmlFor="priority">Priorität zuweisen</label>
          <select
              id="priority"
              name="priority"
              value={priority}
              onChange={(event) => setPriority(event.target.value)}
          >
            <option value="LOW">Niedrig</option>
            <option value="MEDIUM">Mittel</option>
            <option value="HIGH">Hoch</option>
          </select>
          <button type="submit">Absenden</button>
        </form>
        <div>{renderTasks(todos)}</div>
      </header>
    </div>
  );
}

export default App;
