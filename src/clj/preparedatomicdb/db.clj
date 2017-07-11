(ns preparedatomicdb.db
  (:require [clojure.pprint :as pp]
            [durable-atom.core :refer [durable-atom]]
            [datomic.api :as d :refer [db q]]))

(def uri "datomic:mem://test")
(d/create-database uri)
(def db-connection (d/connect uri))
(defn now [] (new java.util.Date))

;;; Here you have to point exactly to the path of your datastore
(def persist-atom (durable-atom "/home/appsmith/Datastore/glue27wells.dat"))

;;;
(defn getDsnList []
  (->> @persist-atom
       (:wells)
       (map #(:dsn %))
       (distinct)
       (vec)))

(defn getWellNameList []
  (->> @persist-atom
       (:wells)
       (map #(:well %))
       (vec)))

(def currentdsn :petronasdb)
(def currentwell {:field "BOKOR"
                  :lease "DP-A"
                  :well "A114"
                  :cmpl "SS"})

(defn getWellNameListOfADataSourceName [currentdsn]
  (->> @persist-atom
       (:wells)
       (filter #(= (first (keys (:dsn %))) currentdsn))
       (map #(:well %))
       (vec)))

(defn getAWell [dsn well]
  (->> @persist-atom
       (:wells)
       (filter #(and (= dsn (first (keys (:dsn %))))
                     (= well (:well %))))
       (first)
       (:welldoc)))

(defn getAllkeysAWell [dsn well]
  (->> @persist-atom
       (:wells)
       (filter #(and (= dsn (first (keys (:dsn %))))
                     (= well (:well %))))
       (first)
       (keys)
       ))

(defn getAllkeysWelldocAWell [dsn well]
  (->> @persist-atom
       (:wells)
       (filter #(and (= dsn (first (keys (:dsn %))))
                     (= well (:well %))))
       (first)
       (:welldoc)
       (keys)
       ))

(defn getAllWells
  "Get all wells in the datastore"
  []
  (->> @persist-atom
       (:wells)))

;;; Init schema for well

(defn initdb []
  (println "Get data from durable-atom to check")
  (pp/pprint (getDsnList))
  (pp/pprint (getWellNameList))
  (pp/pprint (getWellNameListOfADataSourceName currentdsn))
  (pp/pprint (getAllkeysAWell currentdsn currentwell))
  (pp/pprint (getAllkeysWelldocAWell currentdsn currentwell)))



























