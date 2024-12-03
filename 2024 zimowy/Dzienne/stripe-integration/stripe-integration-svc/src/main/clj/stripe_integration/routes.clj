(ns stripe-integration.routes
  (:require
   [jsonista.core :as json]
   [ring.util.response :as ring-response]
   [stripe-integration.commit-hash :as commit-hash]
   [stripe-integration.logic.subscriptions :as subscriptions]
   [stripe-integration.validation-exception])
  (:import
   (java.util UUID)
   (stripe-integration ValidationException)))

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defmacro validating [& body]
  `(try ~@body
        (catch Exception e# nil)))

(defn- handle-has-subscription
  [request]
  (let [profile-id
        (validating (-> request (get-in [:body "profile-id"]) UUID/fromString))]

    (if-not profile-id
      (ring-response/bad-request {:reason "invalid profile-id"})

      (-> {:has-subscription? (boolean (subscriptions/has-subscription? profile-id))
           :commit-hash       commit-hash/value}

          json/write-value-as-string
          ring-response/response))))

(def routes
  [["/has-subscription" {:get {:handler #'handle-has-subscription}}]])

(defn generate-sequence-with-history [coll]
  (map-indexed
    (fn [idx item]
      [item (vec (reverse (take idx coll)))])

    coll))

(generate-sequence-with-history (range 5))
(use 'criterium.core)
(quick-bench (last (generate-sequence-with-history (range 5))))

(quick-bench (last (generate-sequence-with-history' (range 5) '())))

(defn generate-sequence-with-history' [coll previous-elements]
  (lazy-seq
    (when (seq coll)
      (let [e (first coll)]
        (cons [e previous-elements]
              (generate-sequence-with-history' (rest coll) (conj previous-elements e)))))))

(time (last (generate-sequence-with-history' (range 30000) '())))

(defn generate-sequence-with-history'' [coll]
  (let [pairs (map vector coll (reductions conj [] coll))]
    (map (fn [[item history]]
           [item (vec (rest (reverse history)))])
         pairs)))

;; (require '[criterium.core :refer [quick-bench]])
;; (quick-bench (Object.))
;; (quick-bench (RuntimeException.))

;; (println (ex-info "No pasaran" {:reason "unknown"}))

;; (quick-bench (ValidationException. "No pasaran" {}))
;; (quick-bench (vector 1 2 3 4))

#_(throw
    (ValidationException. "No pasaran" {:reason "unknown"}))

;; (defn foo [x]
;;   (throw
;;     (ValidationException. "No pasaran" {:reason "unknown"})
;;     #_(ex-info "No pasaran" {:x x}))

;;   #_(+ (long x) 10))

;; (defn goo [x]
;;   (try
;;     (foo x)

;;     (catch clojure.lang.ExceptionInfo _ :exception-info)
;;     (catch ValidationException        _ :validation-exception)
;;     (catch Exception                  _ :exception)))

;; (quick-bench (goo 123))
