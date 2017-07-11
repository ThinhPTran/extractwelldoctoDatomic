(ns preparedatomicdb.db
  (:require [clojure.pprint :as pp]
            [durable-atom.core :refer [durable-atom]]
            [datomic.api :as d :refer [db q]]))

(def uri "datomic:mem://test")
(d/create-database uri)
(def db-connection (d/connect uri))
(defn now [] (new java.util.Date))

;;; Here you have to point exactly to the path of your datastore
(def persist-atom (durable-atom "/home/debtao/Datastore/glue27wells.dat"))

;;;
(defn getAllWells
  "Get all wells in the datastore"
  []
  (->> @persist-atom
       (:wells)))


(defn initdb []
  (println "Get data from durable-atom to check")
  (pp/pprint (getAllWells)))



























