(ns clojure-imdb-scrapper.api
  (:require [clojure-imdb-scrapper.core :as scrapper]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [cheshire.core :as json]))


(defn handler
  "reads the query-param limit and show only limit results"
  [request]

  (def limit (or (get (:query-params request) "limit") 10))
  (println limit)
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string (scrapper/list-top-movies (Integer. limit)))})

(def app
  (-> handler
      wrap-params
      wrap-multipart-params))

