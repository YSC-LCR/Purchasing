'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')
//const BASE_URL = 'http://40.78.44.203:8090' //server1
//const BASE_URL = 'http://40.78.90.250:8090' //server2
//const BASE_URL = 'http://52.187.33.172:8090' //testing
const BASE_URL = 'http://localhost:8090'
module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
//BASE_API: '"http://40.78.44.203:8090"'	//server1
//BASE_API: '"http://40.78.90.250:8090"'	//server2
//BASE_API: '"http://52.187.33.172:8090"'	//testing
BASE_API: '"http://localhost:8090"'
})
