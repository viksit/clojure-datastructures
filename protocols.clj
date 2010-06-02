(ns stack
  (:refer-clojure :exclude [pop]))

(defprotocol PStack
  "A stack protocol"
  (push [this val] "Push element in")
  (pop [this] "Pop element from stack")
  (top [this] "Get top element from stack"))

(defrecord Stack [coll]
  PStack
  (push [_ val]
	(swap! coll conj val))
  (pop [_]
       (let [ret (first @coll)]
	 (swap! coll rest)
	 ret))
  (top [_]
       (first @coll)))

(def s (Stack. (atom '())))
(push s 10)
(push s 20)
(top s)
(pop s)