(ns clojure-imdb-scrapper.api
  (:require [clojure-imdb-scrapper.core :as scrapper]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [cheshire.core :as json]))


(defn handler "reads the query-param limit and show only limit results"
  [request]

  ;(println (or (get (:query-params request) "limit") 10))
  (def limit (Integer. (get (:query-params request) "limit")))
  (println limit)
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string (scrapper/list-top-movies limit))})

(def app
  (-> handler
      wrap-params
      wrap-multipart-params))

;(defn handler [request]
;  {:status 200
;   :headers {"Content-Type" "text/html"}
;   :body (scrapper/list-top-movies 5)})

;(jetty/run-jetty handler {:port 3000})

;(scrapper/list-top-movies)