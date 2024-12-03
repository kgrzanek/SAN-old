(ns stripe-integration.logic.subscriptions)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(def ^:private subscriptions-atom
  (atom {:profile-id->subscriptions {}}))

(defn find-subscriptions
  [profile-id]
  (get-in @subscriptions-atom [:profile-id->subscriptions profile-id]))

(defn has-subscription?
  ([profile-id]
   (seq (find-subscriptions profile-id)))

  ([profile-id subscription]
   ((or (find-subscriptions profile-id) #{}) subscription)))

(defn add-subscription!
  [profile-id subscription]
  (swap! subscriptions-atom
         (fn [subscriptions]
           (update-in subscriptions [:profile-id->subscriptions profile-id]
             (fnil conj #{}) subscription))))

(add-subscription! 1 "subscription 2")
(has-subscription? 1 "subscription 3")
(has-subscription? 2 "subscription 3")
