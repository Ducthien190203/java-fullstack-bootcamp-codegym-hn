import React, { Component } from "react";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      list: [],
      item: "",
    };
  }

  handleChange = (event) => {
    this.setState({ item: event.target.value });
  };

  handleAddItem = () => {
    const { item, list } = this.state;
    if (item.trim() !== "") {
      this.setState({
        list: [...list, item],
        item: "", // clear input sau khi thêm
      });
    }
  };

  render() {
    return (
      <div style={{ textAlign: "center", marginTop: "50px" }}>
        <h1>Todo List</h1>

        <input
          type="text"
          value={this.state.item}
          onChange={this.handleChange}
          placeholder="Nhập công việc..."
          style={{ padding: "8px", width: "200px" }}
        />
        <button
          onClick={this.handleAddItem}
          style={{
            marginLeft: "10px",
            padding: "8px 16px",
            cursor: "pointer",
          }}
        >
          Add
        </button>

        <ul style={{ listStyle: "none", marginTop: "20px", padding: 0 }}>
          {this.state.list.map((todo, index) => (
            <li key={index} style={{ margin: "5px 0", fontSize: "18px" }}>
              {todo}
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default App;
