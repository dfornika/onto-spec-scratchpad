(ns onto-spec.core
  (:require [ontodev.owlapi :as owl]
            [clojure.spec.alpha :as s]
            [clojure.data.json :as json]))


(owl/add-prefix "GENEPIO" "http://purl.obolibrary.org/obo/GENEPIO_")
(owl/add-prefix "OBI" "http://purl.obolibrary.org/obo/OBI_")

(def genepio-ontology
  (owl/load-ontology "resources/ontologies/genepio.owl"))

(def obi-ontology
  (owl/load-ontology "resources/ontologies/obi.owl"))

(def ncbi-tax-ontology
  (owl/load-ontology "resources/ontologies/NCBItax.owl"))

(def genepio-ncbi-sra-standard
  (json/read (clojure.java.io/reader "resources/GENEPIO_0000147.json")
             :key-fn clojure.core/keyword))


(map #(select-keys % [:uiLabel :datatype :id :choices]) (get-in genepio-ncbi-sra-standard [:specifications :GENEPIO:0000147 :components]))

(s/def cardinality-one? (complement coll?))

(s/def ::GENEPIO:0000071 #{:OBI:0000697 :OBI:0000706 :ERO:0001642
                           :GENEPIO:0001924 :GENEPIO:0001923 :GENEPIO:0001927
                           :GENEPIO:0001926})

(s/def ::GENEPIO:0001965 #{:GENEPIO:0001966 :GENEPIO:0001967 :GENEPIO:0001968
                           :GENEPIO:0001969 :GENEPIO:0001970 :GENEPIO:0001971
                           :GENEPIO:0001972})

(s/def ::GENEPIO:0001973 #{:GENEPIO:0001975 :GENEPIO:0001978 :GENEPIO:0001979
                           :GENEPIO:0001980 :GENEPIO:0001981 :GENEPIO:0001983
                           :GENEPIO:0001984 :GENEPIO:0001985 :GENEPIO:0001986
                           :GENEPIO:0001987 :GENEPIO:0001990 :GENEPIO:0001991
                           :GENEPIO:0001992 :GENEPIO:0001985 :GENEPIO:0001993
                           :GENEPIO:0001974 :GENEPIO:0001976 :GENEPIO:0001976
                           :GENEPIO:0001977 :GENEPIO:0001982 :GENEPIO:0001988
                           :GENEPIO:0001989 })

(s/def ::GENEPIO:0001995 string?)

(defn validate-sequencing-platform [platform]
  (s/valid? ::GENEPIO:0001965 platform))

(defn validate-library-name [name]
  (s/valid? ::GENEPIO:0001995 name))
