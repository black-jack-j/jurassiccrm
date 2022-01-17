import React from "react";
import API, {getAllTitles} from '../API';
import {encode} from 'uint8-to-base64';
import './Wiki.css'

export default class WikiCreate extends React.Component {
    state = {
        id: "",
        title: "",
        text: "",
        image: "",
        image_bytes: [],
        relatedPages: [],
        articles: [],
        articlesNames: [],
        textLength: "",
        titleStyle: "",
        textStyle: ""
    }

    handleTitle = event => {
        this.setState({title: event.target.value})
        console.log(this.state.articlesNames)
        console.log(event.target.value)
        if (this.state.articlesNames.indexOf(event.target.value) > -1){
            console.log(this.state.articlesNames.indexOf(event.target.value))
            document.getElementById("submit").disabled='true'
            this.setState({titleStyle: "invalid"})
        } else {
            document.getElementById("submit").disabled=''
            this.setState({titleStyle: ""})
        }
    }

    handleTextChange = event => {
        this.setState({
            text: event.target.value,
            textLength: event.target.value.length
        })
        if (event.target.value.length > 10000){
            document.getElementById("submit").disabled='true'
            this.setState({textStyle: "invalid"})
        } else {
            document.getElementById("submit").disabled=''
            this.setState({textStyle: ""})
        }
    };

    handleImageDelete = () => {
        // eslint-disable-next-line no-restricted-globals
        const result = confirm("Удалить картинку?");
        if (result) {
            this.setState({image: "", image_bytes: []})
        }
    };

    handleImageUpload = async (event) => {
        let reader = new FileReader();
        let file = event.target.files[0];
        reader.readAsArrayBuffer(file)
        reader.onload = (evt) => {
            if (evt.target.readyState === FileReader.DONE) {
                const arrayBuffer = evt.target.result,
                    array_ = new Uint8Array(arrayBuffer),
                    array_int = new Int8Array(arrayBuffer);
                const encoded = encode(array_);
                this.setState({image: encoded})
                this.setState({image_bytes: array_int})
            }
        }
    }

    handleCheckBoxChange = event => {
        let currentState = this.state.relatedPages
        if (event.target.checked) {
            if (currentState.indexOf(event.target.id) === -1) {
                currentState.push(event.target.id)
                this.setState({relatedPages: currentState})
            }
        } else {
            let index = currentState.indexOf(event.target.id)
            if (index !== -1) {
                currentState.splice(index, 1)
                this.setState({relatedPages: currentState})
            }
        }
    }

    handleSubmit = event => {
        event.preventDefault();
        const request = `/wiki/${this.state.id}&title=${this.state.title}&text=${this.state.text.replace(/\r?\n/g, '<br/>')}&image=${this.state.image_bytes}&relatedPages=${this.state.relatedPages}`
        API.put(request).then(res => {
            if (res.status === 200){
                window.location.href="/wiki"
            } else {
                alert("Ошибка при создании: "+res.statusText)
            }
        })
    };

    componentDidMount() {
        getAllTitles().then(res => {
            let receivedArticles = res.data
            let articleMatrix = []
            for (let i = 0; i < receivedArticles.length; i++) {
                articleMatrix.push({
                    title: receivedArticles[i], checked: ""
                })
            }
            this.setState({
                articles: articleMatrix,
                articlesNames: res.data,
                textLength: 0
            })
            console.log(articleMatrix)
        })
    }

    renderImage() {
        if (this.state.image === null || this.state.image === "") {
            return (
                <div>
                    <input type="file" name="myImage" accept="image/png, image/gif, image/jpeg"
                           onInput={this.handleImageUpload}/>
                </div>

            )
        } else {
            return (
                <div>
                    &nbsp;<img alt={'dinosaur pic'} src={`data:image/png;base64,${this.state.image}`}/>
                    <br/>
                    &nbsp; <input type={"button"} onClick={this.handleImageDelete} value={"Удалить картинку"}/>
                </div>
            )
        }
    }

    renderTable() {
        if (this.state.articles.length > 0) {
            return (
                <div className="scroll-table">
                    <table>
                        <thead>
                        <tr>
                            <th>Название статьи</th>
                            <th>Связанность</th>
                        </tr>
                        </thead>
                    </table>
                    <div className="scroll-table-body">
                        <table>
                            <tbody>

                            {this.state.articles.map(article =>
                                <tr>
                                    <td>{article.title}</td>
                                    <td><input type={"checkbox"} id={article.title}
                                               defaultChecked={article.checked}
                                               onChange={this.handleCheckBoxChange}/></td>
                                </tr>
                            )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            )
        }
    }

    render() {
        if (this.state.articles === []) {
            return (<div><p>LOADING...</p></div>)
        } else {
            return (
                <div>
                    <br/>
                    <form onSubmit={this.handleSubmit}>
                        Название статьи:
                        <br/>
                        <input type={"text"} id={"title"} className={this.state.titleStyle} onChange={this.handleTitle} value={this.state.title}/>
                        <br/>
                        <p>Имя должно быть уникальным</p>
                        <br/>
                        <br/>
                        <div id={"information"}>
                            {this.renderImage()}
                            <br/>
                            &nbsp;<textarea className={this.state.textStyle} onChange={this.handleTextChange}
                                            defaultValue={this.state.text}/>
                            <br/>
                        </div>
                        <p className={this.state.textStyle}>Размер введённого текста: {this.state.textLength}/10000</p>
                        <br/>
                        {this.renderTable()}
                        <button id={"submit"} type="submit">
                            Submit
                        </button>
                    </form>
                </div>
            )
        }
    }
}