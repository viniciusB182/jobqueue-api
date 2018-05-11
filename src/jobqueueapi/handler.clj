(ns jobqueueapi.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.util.response :refer :all]
            [jobqueueapi.utils.queueManager :refer :all]))

(defroutes app-routes
  ;return a JSON with the best jobs that matchs with the agents specified into jobRequests
  (POST "/api/dequeue" {params :params body :body}

    ;define the lists for each type
    (def newJobList (findKeyValues body :new_job))
    (def newAgentList (findKeyValues body :new_agent))
    (def jobRequestList (findKeyValues body :job_request))

    ;return a new list of agents that matchs with the jobRequest list
    (def agentsFromJobRequest (findAgentByJobRequest jobRequestList newAgentList))  
    
    ;return a list of agents with your best jobs assigneds
    (def agentsJobsAssigned (assignJob newJobList agentsFromJobRequest))

    ;turns the agents list with the jobs assigned into a new list of jobs assigned
    (def jobsAssigned (formatResponse agentsJobsAssigned))

    ;return the JSON
    (response jobsAssigned))

  (route/not-found "Not Found"))

  
(def app
  (-> (handler/api app-routes)
  (json/wrap-json-response)
  (json/wrap-json-body {:keywords? true :bigdecimals? true})))
