(ns viksit.cljfsd)

; Basic stack functions from Chapter 2 (Okasaki)

(defn make-stack []
  "Empty stack"
  nil)

(defn stack-push [stack item]
  "Return new stack with item pushed in"
  (cons item stack))

(defn stack-top [stack]
  "Top item on the stack"
  (first stack))

(defn stack-empty? [stack]
  "Check whether the stack is empty"
  (empty? stack))

(defn stack-size [stack]
  "Find no of items on the stack"
  (count stack))

(defn map-stack [fun stack]
  (map fun stack))

(defn stack-from-list [lst]
  lst)

;; Exercise 2.1
;; Take a list and return a list of all suffixes in decreasing order of length
(defn suf [coll]
  (loop [c coll
        ret []]
        (if (empty? c)
    ret
    (recur (next c)
           (conj ret c)))))


;; 2.2 Binary Search Trees



