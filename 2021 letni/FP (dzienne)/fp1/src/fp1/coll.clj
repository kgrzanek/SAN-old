(ns fp1.core)

;; Czy jest możliwe przedstawienie w pamięci komputera zbioru
;; N = 0, 1, 2, 3, 4, ...

(defn foo [x]
  (println "Trwają bardzo długie obliczenia...")
  (Thread/sleep 1000) ;; 1s
  (println "...done.")
  (+ x 3))

;; (def y (foo 4))

(def y (delay (foo 4)))

(deref y)
@y

;; ODROCZONA (PÓŹNA/LENIWA) EWALUACJA WYRAŻEŃ (ang. lazy evaluation)
;; Haskell

;; (first (range 1000000000000000000000000000000))
;; 0

;; (iterate <f> <start>)
;; (<start> (<f> <start>) (<f> (<f> <start>)) ... (<f> ... (<f> <start>)) ...)

;; (iterate inc 0)
;; (0 (inc 0) (inc (inc 0)) (inc (inc (inc 0))) ... )
;; (0 1 2 3 4 ...)

;; POJEMNIKI ASOCJACYJNE (MAPY)

(def m1
  {"0" "aaa"
   "1" "bbb"
   "2" 3.14
   "3" [1 2 3 4]
   "4" #{6 7}})

;; fp1.core=> m1
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}}
;; fp1.core=> (get m1 "4")
;; #{7 6}
;; fp1.core=> (get m1 67)
;; nil
;; fp1.core=> (get m1 67 :nic-nie-ma)
;; :nic-nie-ma
;; fp1.core=> (m1 "4")
;; #{7 6}
;; fp1.core=> (m1 67)
;; nil
;; fp1.core=> (class m1)
;; clojure.lang.PersistentArrayMap
;; fp1.core=> (hash-map)
;; {}
;; fp1.core=> (class (hash-map))
;; clojure.lang.PersistentArrayMap
;; fp1.core=> (class (hash-map :a 1))
;; clojure.lang.PersistentHashMap
;; fp1.core=> (assoc m1 "6" 123)
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}, "6" 123}
;; fp1.core=> m1
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}}
;; fp1.core=> (assoc m1 "6" 123 "7" 432)
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}, "6" 123, "7" 432}
;; fp1.core=> m1
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}}
;; fp1.core=> (dissoc m1 "0")
;; {"1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}}
;; fp1.core=> m1
;; {"0" "aaa", "1" "bbb", "2" 3.14, "3" [1 2 3 4], "4" #{7 6}}
;; fp1.core=> (class m1)
;; clojure.lang.PersistentArrayMap
;; fp1.core=> (instance? java.util.Map m1)
;; true
;; fp1.core=> (.get m1 "0")
;; "aaa"

;; SETS IN CLOJURE
;; fp1.core=> #{1 2 3 4 5}
;; #{1 4 3 2 5}
;; fp1.core=> (class #{1 2 3 4 5})
;; clojure.lang.PersistentHashSet
;; fp1.core=> (conj #{1 2 3 4 5} "abc")
;; #{1 4 "abc" 3 2 5}
;; fp1.core=> (contains? #{1 2 3 4 5} 4)
;; true
;; fp1.core=> (contains? #{1 2 3 4 5} 44)
;; false
;; fp1.core=> (#{1 2 3 4 5} 4)
;; 4
;; fp1.core=> (#{1 2 3 4 5} 44)
;; nil
;; fp1.core=> (disj #{1 2 3 4 5} 4)
;; #{1 3 2 5}
;; fp1.core=>

;; fp1.core=> (set [1 2 3 4 5 5 5 5 6 6 6 7 8 8 8 9])
;; #{7 1 4 6 3 2 9 5 8}
;; fp1.core=> (distinct [1 2 3 4 5 5 5 5 6 6 6 7 8 8 8 9])
;; (1 2 3 4 5 6 7 8 9)
;; fp1.core=> (class (distinct [1 2 3 4 5 5 5 5 6 6 6 7 8 8 8 9]))
;; clojure.lang.LazySeq

;; Zadanie 9. Napisz procedurę zwracającą zbiór potęgowy danego zbioru wejściowego
;;            (powerset #{1 2 3}) =>
;;            #{#{}
;;              #{1}
;;              #{2}
;;              #{3}
;;              #{1 2}
;;              #{1 3}
;;              #{2 3}
;;              #{1 2 3}}
;;            Jeżeli s1 ma n elementów, to (powerset s1) ma 2^n elementów
