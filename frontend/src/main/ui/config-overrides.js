const webpack = require('webpack');
const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = function override(config, env) {

    const PATH = process.env.ASSET_PATH || '/';



    config.entry = {
        main: path.resolve(__dirname, 'src/crm/index'),
        wiki: path.resolve(__dirname, 'src/wiki/index')
    }

    config.output = {
        ...config.output,
        ...{
            filename: 'jurassiccrm-fe-[name].js',
            publicPath: PATH,
            path: path.resolve(__dirname, 'build')
        }
    }
    config.optimization = {
        ...config.optimization,
        ...{
            minimize: false,
            runtimeChunk: false,
            splitChunks: {
                cacheGroups: {
                    default: false
                }
            }
        }
    }
    config.plugins = [...config.plugins,
        new webpack.DefinePlugin({
            'process.env.ASSET_PATH': JSON.stringify(PATH)
        }),
        new HtmlWebpackPlugin({
            inject: true,
            template: 'public/wiki.html',
            filename: 'wiki.html',
            chunks: ['wiki']
        }),
        new HtmlWebpackPlugin({
            inject: true,
            template: 'public/index.html',
            filename: 'index.html',
            chunks: ['main'],
        })
    ]

    return config
}