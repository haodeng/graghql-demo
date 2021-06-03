# GraghQL Demo
## Data Fetching with REST vs GraphQL
With a REST API, you would typically gather the data by accessing multiple endpoints. 
In the example, 
* these could be /users/<id> endpoint to fetch the initial user data. 
* Secondly, there’s likely to be a /users/<id>/posts endpoint that returns all the posts for a user. 
* The third endpoint will then be the /users/<id>/followers that returns a list of followers per user.

In GraphQL on the other hand, you’d simply send a single query to the GraphQL server that includes the concrete data requirements. The server then responds with a JSON object where these requirements are fulfilled.

## No more Over- and Underfetching
One of the most common problems with REST is that of over- and underfetching. This happens because the only way for a client to download data is by hitting endpoints that return fixed data structures.

### Overfetching: Downloading superfluous data
Overfetching means that a client downloads more information than is actually required in the app.

### Underfetching and the n+1 problem
Another issue is underfetching and the n+1-requests problem. 
Underfetching generally means that a specific endpoint doesn’t provide enough of the required information. 
The client will have to make additional requests to fetch everything it needs. 
This can escalate to a situation where a client needs to first download a list of elements, but then needs to make one additional request per element to fetch the required data.

As an example, the app will have to make one request to the /users endpoint and then hit the /users/<user-id>/followers endpoint for each user.

## Benefits of a Schema & Type System
GraphQL uses a strong type system to define the capabilities of an API. All the types that are exposed in an API are written down in a schema using the GraphQL Schema Definition Language (SDL). This schema serves as the contract between the client and the server to define how a client can access the data.
