(defn perm [word idx]
  (if (== (.length word) idx)
    (word)
    (for [i (range idx (.length word))]
      (perm (str-swap word i idx) (inc idx)))))

(perm "123" 0)

(defn str-swap [s index-a index-b]
  (let [v (vec s)]
    (apply str (assoc v index-a
		      (get v index-b)
		      index-b (get v index-a)))))

(str-swap "123" 0 1)

(defn part [coll]
  (cons [() coll]
	(when (seq coll)
	  (map (fn [[left right]]
		 [(cons (first coll) left) right])
	       (part (rest coll))))))
(defn perm [coll]
  (if (seq coll)
    (for [p (perm (rest coll))
	  [l r] (part p)]
      (concat l [(first coll)] r))
    [()]))

(perm "123") 
(part "v")
[() v]
  [(v) ()] <-[() ()]


(part "vi")
[[() "vi"] [[(v) (i)] [() () nil]


    
(defn p [c]
  (cons [() c] (rest c)))

(p [1 2])

([() "vik"] [(\v) (\i \k)] [(\v \i) (\k)] [(\v \i \k) ()])

[() vik]
vik
  ik [(ik) nil]
    k [() k] -> [(k) nil]
     ()
    