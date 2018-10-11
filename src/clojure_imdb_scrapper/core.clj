(ns clojure-imdb-scrapper.core
  (:require [clojure.string :as str])
  (:require [clojure.inspector])
  (:import [org.jsoup Jsoup]))

(defn get-page "loads the html code of an url" [url]
  (.get (Jsoup/connect url)))

(defn select-element [html-data element]
  (.select html-data element))

(let [page (get-page "https://www.imdb.com/chart/top")]
  (def rows (select-element page "table.chart tr")))

(def cols (for [tr rows]
            (vec (for [td (select-element tr "td")] (.text td)))))

(def movies (for [row (filter not-empty cols)]
              (let [info (str/split (get row 1) #"\s")]
                {:ranking (Integer. (str/escape (first info) {\. ""}))
                 :title (str/join " " (subvec info 1 (dec (count info))))
                 :published (Integer. (str/escape (last info) {\( "" \) ""}))
                 :rating (Double. (get row 2))})))

(take 10 movies)


;movies with rating greater than 9
(filter #(> (:rating %) 9) movies)

;Piratas do Cabie movies
(filter #(str/includes? (:title %) "Piratas do Caribe") movies)

;print table
(clojure.pprint/print-table movies)

;print table in a clojure inspector
(clojure.inspector/inspect-table movies)



;(let [conn (Jsoup/connect "https://www.imdb.com/chart/top")]
;  (.get conn))

; does the same as the above, but doesn't use vars
;(.get (Jsoup/connect "http://localhost:8000"))
