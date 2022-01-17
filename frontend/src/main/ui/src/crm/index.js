import React from "react";
import ReactDOM from "react-dom";
import axios from "axios";
import 'semantic-ui-css/semantic.min.css'
import {Container, Header} from "semantic-ui-react";
import SignupForm from "./SignupForm";


class App extends React.Component{

    state = {
        possibleAssignees: [],
        taskTypes: []
    }

    componentDidMount() {
        this.getAssignees();
        this.getTaskTypes();
    }

    getAssignees() {
        axios.get('/task/assignee')
            .then(possibleAssignees => this.setState({possibleAssignees: possibleAssignees.data}))
    }

    getTaskTypes() {
        axios.get('/task/type')
            .then(taskTypes => this.setState({taskTypes: taskTypes.data}))
    }

    render() {
        return (
            <Container>
                <Header as="h3" content="New Task"/>
                <SignupForm possibleAssignees={this.state.possibleAssignees} taskTypes={this.state.taskTypes}/>
            </Container>
        )
    }
}

const rootElement = document.getElementById("create-task");
ReactDOM.render(<App />, rootElement);
