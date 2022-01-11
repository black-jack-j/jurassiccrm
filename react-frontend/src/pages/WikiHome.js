import React from 'react';
import API from '../api';


export default class WikiHome extends React.Component {
    state = {
            pages: [],
            filteredPages: []
            }


    componentDidMount() {
        API.get(`/wiki/getAllTitles`)
            .then(res => {
                const pages = res.data;
                this.setState({pages: pages, filteredPages: pages});
            })
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
                            <th>Название статьи</th>
                        </tr>
                        </thead>
                    </table>
                    <div className="scroll-table-body">
                        <table>
                            <tbody>
                            {
                                this.state.filteredPages.map(title =>
                                    <tr>
                                        <td>
                                            <a href={`/wiki/page/${title}`}>{title}</a>
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

    render() {
        return (
            <div>
                <h2>Welcome to Jurassic Park Wiki</h2>
                Welcome to Jurassic Park Wiki here you can find additional information about our dinosaurs. Chose one of
                the
                links below and click it to see the corresponding page.
                <br/>
                <br/>
                Поиск по названию: <input type={"text"} onChange={this.handleFilter}/>
                <br/>
                <br/>
                <div>
                    {this.renderTable()}
                </div>
                <br/>
            </div>
        );
    }
};