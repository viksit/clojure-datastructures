(ns viksit-trie)

(defn map-filter [f coll]
  (map f (filter f (coll))))

(defn add-to-trie [trie x]
  (assoc-in trie x (merge (get-in trie x) {:val x :terminal true})))

(defn in-trie? [trie x]
  "Returns true if the value x exists in the specified trie."
  (:terminal (get-in trie x) false))

(defn prefix-matches [trie prefix]
  "Returns a list of matches with the prefix specified in the trie specified."
  (map-filter :val (tree-seq map? vals (get-in trie prefix))))

(defn build-trie [coll]
  "Builds a trie over the values in the specified seq coll."
  (reduce add-to-trie {} coll))

;; Example
(def inTree
 '((1 2)
   (1 2 3)
   (1 2 4 5 9)
   (1 2 4 10 15)
   (1 2 4 20 25)))

(def mytrie (build-trie inTree))

(get-in mytrie [1 2 4 10])

