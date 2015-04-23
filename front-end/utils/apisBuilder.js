import request from 'superagent'

let mockRequest
/**
 * userApiInterface
 * provide all the api interface for user
 */
const apisBuilder = function(apiConfig, endpoint, mockApi) {

  //mock the api
  if (mockApi && process.env.NODE_ENV !== 'production') {
    mockRequest = require('./superagent-mock')
    mockRequest(request, mockApi)
  }

  var serviceApis =  Object.keys(apiConfig).reduce(function(apis, name) {
    const api = apiConfig[name]
    if (api) {
      apis[name] = function() {
        const args = arguments
        return new Promise(function(resolve, reject) {
          if (validMethod(api.method)) {
            request[api.method](endpoint + api.url)
              .send(args[0])
              .end(function(err, data) {
                if (err) {
                  return reject(err)
                }
                resolve(data)
              })
          } else {
            reject(new Error('invalid method!'))
          }
        })
      }
    }
    return apis
  }, {})

  // for mocking the http requests
  serviceApis._request = request;
  return serviceApis
}

export default apisBuilder

function validMethod(method) {
  if (typeof method !== 'string')
    return new Error('invalid method type!')
  return [
    'post',
    'get',
    'put',
    'delete',
    'head',
    'update'].find(item => method.toLowerCase() === item)
}
