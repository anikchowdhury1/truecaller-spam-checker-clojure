(ns spam-checker.core
  (:gen-class)
  (:require [clj-http.client :as http]
            [clojure.string :as str]
            [clojure.inspector :as inspect_data]
            [clj-http.util :as utility]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io])

  (:use clojure.pprint))

(def AUTH_HEADER "******")
(def USER_AGENT "Truecaller/9.14.8 (Android;7.1.2)")
(def ACCEPT_ENCD "gzip, deflate")
(def url_primary "******")
(def other_part_url "*****************")

(defn csv_read_func []
  (with-open [f (clojure.java.io/reader "in-file.csv")]
    (let [rows (csv/read-csv f)]
      (doseq [row-chunks (partition-all 100 rows)]
        (loop [data row-chunks]
          (let [data_list (first data)]
            (when (seq data)
              (let [phone (first data_list)
                    encoded-phone (utility/url-encode phone)
                    url (str url_primary encoded-phone other_part_url)
                    result (http/get url
                                     {:headers {:Authorization   AUTH_HEADER
                                                :User-Agent      USER_AGENT
                                                :accept-encoding ACCEPT_ENCD}
                                      ;:debug?  true?
                                      :as      :json-strict-string-keys})]

                (let [score (get-in result ["data" 0 "phones" 0 "spamScore"] "N/A")]
                  (println (format "phone: %s, spam-score: %s" phone score))))
              (recur (rest data)))))
        (Thread/sleep 200000)))))


































(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (csv_read_func))

