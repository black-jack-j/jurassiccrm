import React from 'react';
import API from '../api';


export default class WikiAdmin extends React.Component {
    state = {
        pages: [],
        filteredPages: []
    }


    deleteWiki = event => {
        // eslint-disable-next-line no-restricted-globals
        const deleteWiki = confirm("Вы точно хотите удалить статью "+event.target.value+"?")
        if (deleteWiki){
            API.delete(`wiki/deleteByTitle?title=${event.target.value}`).then(res => {
                if (res.status === 200){
                    alert("Удалено успешно")
                    let pages = this.state.pages
                    let index = this.state.pages.indexOf(event.target.value)
                    if (index !== -1) {
                        pages.splice(index, 1)
                        this.setState({pages: pages, filteredPages: pages})
                    }
                    document.getElementById("search").value = ""
                } else {
                    alert("Ошибка при создании: "+res.statusText)
                }
            })
        }
    }

    redirectToEditPage = event => {
        window.location.href = `/wiki/edit/${event.target.value}`
    }

    handleFilter = event => {
        const filtered = this.state.pages.filter(title => title.toLowerCase().includes(event.target.value.toLowerCase()))
        this.setState({filteredPages: filtered})
    }

    renderTable() {
        if (this.state.pages.length > 0) {
            return (
                <div className="scroll-table">
                    <table>
                        <thead>
                        <tr>
                            <th style={{ fontSize: 23 }}>Название статьи</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                    <div className="scroll-table-body">
                        <table>
                            <tbody>
                            {
                                this.state.filteredPages.map(title =>
                                    <tr>
                                        <td style={{ fontSize: 23 }}>
                                            <a href={`/wiki/page/${title}`}>{title}</a>
                                        </td>
                                        <td>
                                            <button value={title} style={{ backgroundColor: "lightcyan", fontSize: 18}} onClick={this.redirectToEditPage}>&#128394;</button>
                                        </td>
                                        <td>
                                            <button value={title} style={{ backgroundColor: "#ff4010", fontSize: 18}} onClick={this.deleteWiki}>&#128465;</button>
                                        </td>
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

    componentDidMount() {
        API.get(`/wiki/getAllTitles`)
            .then(res => {
                const pages = res.data;
                this.setState({pages: pages,
                                    filteredPages: pages
                });
            })
    }

    render() {
        return (
            <div>
                <h2>Welcome to Jurassic Park Wiki</h2>
                Здесь Вы можете администрировать википедию (удалять и редактировать страницы, создавать новые)
                <br/>
                <br/>
                Поиск по названию: <input type={"text"} id={"search"} onChange={this.handleFilter}/>
                <br/>
                <br/>
                <div>
                    {this.renderTable()}
                </div>
            </div>
        );
    }
};