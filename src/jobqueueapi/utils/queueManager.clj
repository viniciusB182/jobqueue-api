(ns jobqueueapi.utils.queueManager)

;find jobs that match with the Agent
(defn findjobsFromAgent [agent jobs]
    ;verify if the job have a primary skill or a secundary skill that matchs with the agent 
    (remove nil? 
        (for [job jobs] 
            (if (contains? (set (get-in agent [:primary_skillset])) (get-in job [:type])) job
                (if (contains? (set (get-in agent [:secondary_skillset])) (get-in job [:type])) job)))))       

;verify the first with the flag urgent
(defn findFirstUrgent [jobs]
    (first 
        (remove nil? 
            (for [job jobs]
                (if (= (get-in job [:urgent]) true) job)))))

;verify the best job for the Agent
(defn findBestJob [jobs bestJobs]
    ;remove the jobs already assigned
    (def filteredJobs
        (remove nil? 
            (for [job jobs]
                (if (contains? (set (map :id bestJobs)) (get-in job [:id])) nil job))))

    ;find the first that matchs with the job or the first urgent
    (if (contains? (set (map :urgent filteredJobs)) true) (findFirstUrgent filteredJobs) (first filteredJobs)))

;get the properties values of the specific key
(defn findKeyValues [body key] 
    (remove nil?
        (for [element body] 
            (get-in element [key]))))

;get the properties values of the agent that have the same id of the job requests
(defn findAgentByJobRequest [jobRequests agents]
    (def requestAgentsId (set (map :agent_id jobRequests)))

    (remove nil? 
        (for [agent agents]        
            (if (contains? requestAgentsId (get-in agent [:id])) agent))))

(defn assignJob [jobs agents]
    ;create atom element that can be changed in a for loop
    (def bestJobs (atom []))

    (for [agent agents]
        (assoc agent :job 
                (last 
                    (swap! bestJobs conj
                        (findBestJob 
                            (findjobsFromAgent agent jobs) @bestJobs))))))  

;get job id and agent id for the response
(defn formatResponse [response]
    (for [agent response]
        (assoc {} :job_assigned 
            (assoc {} :job_id (get-in agent [:job :id]) :agent_id (get-in agent [:id])))))