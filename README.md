# jobqueueapi

The jobqueueapi can return the best Jobs for your Agents from your jobRequests. 

1. After receive the JSON body containing Jobs, Agents and JobRequest, the API will verify the keys and get the values for each one and turns into three lists. 

2. Doing this, they match the agents id specified in the JobRequest objects list with the id of the Agents list objects, getting a new list that contains just the Agents that have a JobRequest. 

3. Now, they just receive the new list of agents and will filter the Jobs list. The filter have some decisions and prioritys that involves the primary key, secondary key and the urgent flag in the objects of Jobs list and will return a new list of agents with a new key called job that contains the best job for it! 

4. This result will be formatted for a simples response and the API finally returns a JSON with the expected result.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

If your environment is the popular double Visual Code + Windows, you can see how to prepair everything in my article "Starting with Clojure + Windows + Visual Code":

https://medium.com/@vini.raanch/starting-with-clojure-windows-visual-code-2ac11add749c

It's your choice the tool to use for consume the API. My suggestion is Postman.

## Running

Before starting the web server, run:

    lein deps

This will install all the dependencies needed for the project to run. The dependencies are listed on project.clj

To start a web server for the application, run:

    lein ring server

## First Try

With the tool choosed by you:

1. Select the type of the request as POST
2. In the header "content-type" as "application/json"
3. Set the route as http://localhost:port/api/dequeue
4. Specify the JSON body
5. Send the Request

And you see the best Jobs for you Agents!

## Tests

And you can run the application tests with the command:

    lein test

