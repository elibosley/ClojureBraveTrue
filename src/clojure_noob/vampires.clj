;; File used for identifying vampires given this database, ensures that the person both makes blood
;; puns as well as has no pulse before returning
;; Uses the lazy properties of map to speed up the time it takes to perform this call


(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})


(defn vampire-related-details
  [idx]
  (Thread/sleep 1000)
  (get vampire-database idx))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [idx]
  (first (filter vampire?
                 (map vampire-related-details idx))))

(defn identify-vampire-timed
  [idx]
  (time (identify-vampire idx)))



(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                 :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "convert a csv into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "returns a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def filename "/Users/elibosley/Documents/clojure-noob/src/clojure_noob/suspects.csv")

(def get-da-glitter
  (glitter-filter 3 (mapify (parse (slurp (java.io.FileReader. filename))

                                   ))))
