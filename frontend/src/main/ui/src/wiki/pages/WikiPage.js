import React from "react";
import API from '../API';

export default class WikiPage extends React.Component {
    state = {
        title: "",
        text: "",
        image: "",
        relatedPages: []
    }

    loc = window.location.href;
    loc_split = this.loc.split('/');
    title = this.loc_split[this.loc_split.length - 1]

    componentDidMount() {
        API.get(`/wiki?title=${this.title}`)
            .then(res => {
                this.setState({title: res.data.title,
                                    text: res.data.text.replaceAll('<br/>', '\n'),
                                    image: res.data.image,
                                    relatedPages: res.data.relatedPages.sort()
                });
            })
    }

    renderImage(){
        if (this.state.image !== null) {
            return(
                <div>
                    <img alt={'dinosaur pic'} src={`data:image/png;base64,${this.state.image}`}/>
                </div>
            )
        }
    }

    renderRelated(){
        if (this.state.relatedPages.length !== 0){
            return(
                <div>
                    <p>Связанные страницы:</p>
                    <ul>
                        {this.state.relatedPages.map(title =>
                            <li>
                                <a href={`/wiki/page/${title}`}>
                                    {title}
                                </a>
                            </li>
                        )}
                    </ul>
                </div>
            )
        }
    }



    render() {
        if (this.state.title === ""){
            return (<div><p>LOADING...</p></div>)
        }
        else {
            console.log(this.state)
        return (
            <div style={{margin: "20px"}}>
                <h1>{this.state.title} &nbsp; <br/></h1>
                {this.renderImage()}
                <br/>
                <pre>
                    {this.state.text}
                </pre>
                <br/>
                {this.renderRelated()}
            </div>
        )
        }
    }
}