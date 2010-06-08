(ns viksit-stack
  (:refer-clojure :exclude [pop]))

(defprotocol PStack
  "A stack protocol"
  (push [this val] "Push element in")
  (pop [this] "Pop element from stack")
  (top [this] "Get top element from stack"))

; A stack record that implements the PStack protocol
; This uses mutable semantics within the record itself
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

; A functional stack record that uses immutable semantics
; It returns a copy of the datastructure while ensuring the original
; is not affected.
(defrecord FStack [coll]
  PStack
  (push [_ val]
	"Return the stack with the new element inserted"
	(FStack. (conj coll val)))
  (pop [_]
       "Return the stack without the top element"
	 (FStack. (rest coll)))
  (top [_]
       "Return the top value of the stack"
       (first coll)))

; The funtional stack can be used in conjunction with a ref or atom
(def s2 (atom (FStack. '())))
s2
(swap! s2 push 10)
s2
(swap! s2 push 20)
s2
(swap! s2 pop)
s2
(top @s2)

(def s3 (atom (FStack. '[])))
(swap! s3 push 10)
(swap! s3 push 20)