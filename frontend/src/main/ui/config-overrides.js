const webpack = require('webpack');

module.exports = function override(config, env) {

    const PATH = process.env.ASSET_PATH || '/';

    config.output = {
        ...config.output,
        ...{
            filename: 'jurassiccrm-fe.js',
                publicPath: PATH
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
        })]
    return config
}