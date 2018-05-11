(ns jobqueueapi.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [ring.middleware.json :as json]
            [jobqueueapi.handler :refer :all]
            [cheshire.core :as cheshire]
            [jobqueueapi.payloads.request :as request]
            [jobqueueapi.payloads.response :as response]))

;return the default request for the tests with the specific request payload
(defn setRequest [request]
  (app          (-> (mock/request :post "/api/dequeue")
                (mock/json-body request)
                (mock/content-type "application/json"))))
  
;turns the expected json result into string for validation
(defn stringResult [result] 
  (cheshire/generate-string result))

;return the default response for the tests with the specific response
(defn setResponse [result]
  {:status 200 
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (stringResult result)})
 
;success tests
(deftest success-jobqueueapi-test

  ;should return success with a valid job request
  (is (= (setRequest request/validJobRequest) (setResponse response/validJobResult)))

  ;should return success with the specific exercise body
  (is (= (setRequest request/exerciseRequest) (setResponse response/exerciseResult))))

;failure tests
(deftest failure-jobqueueapi-test
  ;should return job null for a single jobRequest
  (is (= (setRequest request/brokenJobRequest) (setResponse response/brokenJobResult)))
    
  ;should return empty for a single jobRequest
  (is (= (setRequest request/allBrokenRequest) (setResponse response/allBrokenResult))))