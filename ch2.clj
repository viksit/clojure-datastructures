(ns viksit.cljfsd)

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
  (