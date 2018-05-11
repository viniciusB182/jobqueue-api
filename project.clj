(defproject jobqueueapi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.4.0"]
                 [cheshire "5.8.0"]
                 [org.clojure/data.json "0.2.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler jobqueueapi.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
