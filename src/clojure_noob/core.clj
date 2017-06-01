(ns clojure-noob.core
  (:gen-class))


;; Press C-c C-k to compile
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))
(println "Cleanliness is next to godliness")
(defn train
  []
  (println "Choo choo!"))


(defn codger-communication [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn favorite-things
  [name & things]
  (str "hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

;; Return the first element of a collection
(defn my-firsty
  [[first-thing]] ;; this element is within a vector
  first-thing  )

(defn chooser
  [[first-choice second-choice & other-choices]]
  (println (str "your first choice is:" first-choice))
  (println (str "your other choices are" (clojure.string/join ", " other-choices)))
  )

;; break up some keys yo
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "lat: " lat))
  (println (str "lng: " lng)))

;; break up some keys yo
(defn announce-treasure-location-original-map
  [{:keys [lat lng] :as treasure-location}]
  (println (str "lat: " lat))
  (println (str "lng: " lng)))


;; anonymous functions can either look like this (fn [name] (str "hi, " name)) or like this
;; #(str "Hi, " %)

(def anonymous-func #(str "hi, " %))

(def multi-param #(str "hi, " %1 %2 %3))

(def rest-multi #(str "hi, " %1 %&))


;; let functions do neat things and can allow you to use the rest parameters inside of them
(def dalmation-list ["Pupper 1" "Pupper 2" "Pupper 3" "Pupper 4" "Pupper 5"])
(defn get-dalmations
  [] ;; don't forget these when defining a function!
  (let [[pupper-1 & dalmations] dalmation-list]
    (println(str pupper-1 (clojure.string/join "," dalmations)))))

(get-dalmations)


;; loop function, specify loop param and will loop until done

(defn loop-until
  [times name]
  (loop [iteration 1]
    (println (str name " " iteration))
    (if (>= iteration times)
      (println "Goodbye!")
      (recur (inc iteration)))))


;; regex

(re-find #"^left-" "left-eye")


(defn add-hundred
  [number]
  (+ number 100))


(defn dec-maker
  "takes a number and subtracts "
  [number]
  (fn [new-num] (- new-num number)))


(defn create-title
  [topic]
  (str topic " for the Brave and True"))

(defn map-works-with-anything
  [anything]
  (println (clojure.string/join (map create-title anything))))

(map-works-with-anything ["It's a " "vector!"])
(map-works-with-anything '("It's a " "list!"))
(map-works-with-anything #{"it's a" " set???"})
;; maps are a little bit weird to call this on so I'll figure this out
(map-works-with-anything (second {:uncomfortable-thing "winking"}) )


;; Map applies a function to a list by iterating over said list and running the function on each
;; item in it until it runs out of things to run
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
;; Map can apply functions over a list of numbers like this.

;; If you have a map data structure, the map function makes it very easy to get keywords out

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-man" :real "Peter Parker"}
   {:alias "Super-man" :real "Clark Kent"}])

(defn get-real-identities
  [itentities]
  (map :real identities))

;; take returns first n elements of seq
;; drop returns sequence with first n elements removed

;; both of these have another function called func-while which takes until a falsy value is evaluted
;; as their second parameter

;; filter returns all elements of a sequence that test true for predicate function

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(defn filter-by-day
  " takes a day and a list and will return anything that falls on that day"
  [day ary]
  (filter #(= (:day %) day) ary ))

(defn take-while-mth
  "takes a month and a list and returns anything before and including that month"
  [mth ary]
  (take-while #(<= (:month %) mth) ary))

(defn drop-while-mth
  "take a month and returns anything falling after that month"
  [mth ary]
  (drop-while #(<= (:month %) mth) ary))
