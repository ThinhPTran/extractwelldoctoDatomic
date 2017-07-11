(ns preparedatomicdb.core
  (:require [preparedatomicdb.db :as db]))

(defn -main [& args]
  (println "Starting!!!")
  (db/initdb))


