(defproject spam_checker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.4"]
                 [clj-http "3.9.0"]
                 [cheshire "5.8.1"]
                 [org.jsoup/jsoup "1.9.2"]]

  :main ^:skip-aot spam-checker.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
