(ns swarm.go
  (:use [clojure.pprint :only [pprint]]))

(def board (atom nil))

(defn make-board [size]
  (vec (repeat size (vec (repeat size '+)))))

(defn start-game []
  (reset! board  (make-board 19)))

(defn pass? [[x y _]]
  (every? nil? [x y]))

(defn valid-placement?
  "This function determines whether the proposed move would clobber an existing stone."
  [board [x y _ :as move]]
  (or (pass? move)
      (and (< -1 x (count (first board)))
           (< -1 y (count (first board)))
           (= '+ (get-in board [x y] '+)))))

(defn neighbor-coords [x y]
  [[(dec x) y] [(inc x) y]
   [x (dec y)] [x (inc y)]])

(defn neighbors [board x y]
  (map (partial get-in board) (neighbor-coords x y)))

(defn group [board x y visited-set]
  ;; (if-not (visited-set [x y])
  ;; get the neighbors of the neighbors that are the same color
  ;; merge them into a set
)

(defn alive-group? [group]
  "do any of the stones in the group have any empy neighbors"
  ;; search for a stone in the group with an empty neighbor
  )

(defn place-stone [board [x y color :as move]]
  (when-not (valid-placement? board move)
    (throw (IllegalArgumentException. (pr-str move))))
  (if (and x y)
    (assoc-in board [x y] color)
    board))

(def inputs (map conj
                 [[1 1] [3 3] [1 2] [3 2] [nil nil] [nil nil]]
                 (cycle ['& 'O])))

(defn play [size inputs]
  (reduce place-stone (make-board size) inputs))

;; (pprint (play 10 inputs))