(ns hack-project.core
  (:use [clojure.pprint :only [pprint]]))

(def board (atom nil))

(defn make-board [size]
  (vec (repeat size (vec (repeat size '+)))))

(defn start-game []
  (reset! board  (make-board 19)))

(defn place-stone [board [x y color]]
  (if (and x y)
    (assoc-in board [x y] color)
    board))

(def inputs (map conj
                 [[1 1] [3 3] [1 2] [3 2] [nil nil] [nil nil]]
                 (cycle ['w 'b])))

(defn play [size inputs]
  (reduce place-stone (make-board size) inputs))

;; (play 5 inputs)