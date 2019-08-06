(defproject clojure-imdb-scrapper "0.1.0-SNAPSHOT"
  :description "A simple example of IMDB scrapper"
  :url "https://github.com/jmsstudio/clojure-imdb-scrapper"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.jsoup/jsoup "1.11.3"]
                 [ring "1.7.1"]
                 [cheshire "5.8.1"]]
  :ring {:handler clojure-imdb-scrapper.api/app}
  :plugins [[lein-ring "0.12.5"]])

