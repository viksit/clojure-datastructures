(ns viksit-trees)

(comment

; tree representation  
;    1 
;   / \
;  2   3
; /   / \
;4   5   6

)

; Tree node
(defstruct node :value :left :right)

(def nval (accessor node :value))
(def lft (accessor node :left))
(def rt (accessor node :right))

; Convenience macro for quick tree creation
(defmacro tn
 ([v]
  `(struct node ~v nil nil))
 ([v l r]
  `(struct node ~v ~l ~r)))

(macroexpand '(tn 1 nil nil))
  
(def mytree
     (tn 1
	 (tn 2
	     (tn 4) nil)
	 (tn 3
	     (tn 5) (tn 6))))


(defn tree-search [bt e]
  "Test if element e is in binary tree bt"
  (let [curval (bt :value) lft (bt :left) rt (bt :right)]
    (if (nil? curval)
      false
      (do
	(println (str "Value: " curval))
	(or
	 (== curval e)
	 (and (not (nil? lft)) (tree-search lft e))
	 (and (not (nil? rt)) (tree-search rt e)))))))


(tree-search mytree 1)
(tree-search mytree 2)
(tree-search mytree 3)
(tree-search mytree 4)
(tree-search mytree 5)

(tree-search mytree 6)
(tree-search mytree 11)
(tree-search mytree 10)

; Test harness
(map #(tree-search mytree %) (range 1 11))

(for [i (range 0 10)]
  (tree-search mytree i))

(doseq [i (range 0 10)]
  (tree-search mytree i))

(loop [i 0]
  (when-not (== i 10)
    (tree-search mytree i) (recur (inc i))))

(loop [i 0 r []]
  (if-not (== i 10)
    (recur (inc i) (conj r (tree-search mytree i))) r))


; Traversal
; pass a function and call that
(defn pre-order [bt]
  (let [curval (bt :value) lft (bt :left) rt (bt :right) r []]
    (if (nil? curval)
      false
      (or
       (println (str "Visited " curval))
       (and (not (nil? lft)) (pre-order lft))
       (and (not (nil? rt)) (pre-order rt))))))
      
(pre-order mytree)

(defn post-order [bt]
  (let [curval (bt :value) lft (bt :left) rt (bt :right) r []]
    (if (nil? curval)
      false
    	(or
	 (and (not (nil? lft)) (post-order lft))
	 (and (not (nil? rt)) (post-order rt))
	 (println (str "Visited " curval))))))

(post-order mytree)

(defn in-order [bt]
  (let [curval (bt :value) lft (bt :left) rt (bt :right) r []]
    (if (nil? curval)
      false
   	(or
	 (and (not (nil? lft)) (in-order lft))
	 (println (str "Visited " curval))
	 (and (not (nil? rt)) (in-order rt))))))		  

(in-order mytree)

(defn queue [& xs]
  (when (seq xs)
   (apply conj clojure.lang.PersistentQueue/EMPTY xs)))

(defn level-order [rootnode]
  (loop [curlevel (queue rootnode)]
    (when-not (empty? curlevel)
      (if-let [n (first curlevel)]
	(do
	  (prn (seq curlevel))
	  (prn (n :value))
	  (recur (conj (pop curlevel) (n :left) (n :right))))
	(recur (pop curlevel))))))
		       

(level-order mytree)