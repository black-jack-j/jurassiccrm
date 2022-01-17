import React from "react";
import API, {findByTitle, getAllTitles} from '../API';
import {encode} from 'uint8-to-base64';
import './Wiki.css'

export default class WikiEditor extends React.Component {
    state = {
        id: "",
        title: "",
        text: "",
        image: "",
        image_bytes: [],
        relatedPages: [],
        articles: [],
    }

    loc = window.location.href;
    loc_split = this.loc.split('/');
    title = this.loc_split[this.loc_split.length - 1]

    handleTitle = event => {
        this.setState({title: event.target.value})
    }

    handleTextChange = event => {
        this.setState({text: event.target.value})
        console.log(event.target.value)
    };

    handleImageDelete = () => {
        // eslint-disable-next-line no-restricted-globals
        const result = confirm("Удалить картинку?");
        if (result) {
            this.setState({image: "", image_bytes: []})
        }
    };

    handleImageUpload = async(event) => {
        let reader = new FileReader();
        let file = event.target.files[0];
        reader.readAsArrayBuffer(file)
        reader.onload = (evt) => {
            if (evt.target.readyState === FileReader.DONE){
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
        const request = `/wiki/updateWikiPage?id=${this.state.id}&title=${this.state.title}&text=${this.state.text.replace(/\r?\n/g, '<br/>')}&image=${this.state.image_bytes}&relatedPages=${this.state.relatedPages}`
        console.log(request)
        API.post(request).then(res => {
            if (res.status === 200){
                window.location.href="/wiki/admin"
            } else {
                alert("Ошибка при создании: "+res.statusText)
            }
        })
    };

    componentDidMount() {
        findByTitle(this.title)
            .then(res => {
                this.setState({
                    id: res.data.id,
                    title: res.data.title,
                    text: res.data.text.replaceAll('<br/>', '\n'),
                    image: res.data.image,
                    relatedPages: res.data.relatedPages
                });
                if (this.state.image !== null){
                    this.setState({image_bytes: "default"})
                }

                getAllTitles().then(res1 => {
                    let receivedArticles = res1.data
                    let otherArticles = []
                    for (let i = 0; i < receivedArticles.length; i++) {
                        if (receivedArticles[i] !== this.state.title) {
                            otherArticles.push(receivedArticles[i])
                        }
                    }
                    if (otherArticles[0] === undefined){
                        this.setState({
                            articles: []
                        })
                    } else{
                        let articleMatrix = []
                        for (let i = 0; i < otherArticles.length; i++) {
                            let checked_ = "checked"
                            if (this.state.relatedPages.indexOf(otherArticles[i]) === -1) {
                                checked_ = ""
                            }
                            articleMatrix.push({
                                title: otherArticles[i], checked: checked_
                            })
                        }
                        this.setState({
                            articles: articleMatrix
                        })
                    }
                })
            })
    }

    renderImage() {
        if (this.state.image === null || this.state.image==="") {
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
        if (this.state.articles.length > 0){
            return(
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
        if (this.state.title === "") {
            return (<div><p>LOADING...</p></div>)
        } else {
            return (
                <div>
                    <br/>
                    <form onSubmit={this.handleSubmit}>
                        <input type={"text"} id={"title"} onChange={this.handleTitle} value={this.state.title}/>
                        <br/>
                        <br/>
                        <div id={"information"}>
                            {this.renderImage()}
                            <br/>
                            &nbsp;<textarea onChange={this.handleTextChange}
                                            defaultValue={this.state.text}/>
                            <br/>
                        </div>
                        {this.renderTable()}
                        <button id="submit" type="submit">
                            Submit
                        </button>
                    </form>
                </div>
            )
        }
    }
}