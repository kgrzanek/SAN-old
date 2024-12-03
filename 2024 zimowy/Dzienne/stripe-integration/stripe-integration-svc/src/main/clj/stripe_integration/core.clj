(ns stripe-integration.core
  (:require
   [clojure.tools.logging :as log]
   [stripe-integration.http :as http]
   [stripe-integration.routes :refer [routes]])
  (:gen-class))

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defonce jetty
  (delay (http/jetty-start!
           (-> routes http/reitit-router http/reitit-handler)
           {:port                 8080
            :join?                false
            :use-virtual-threads? false})))

;; NS FINALIZATION (for kongra/telsos-sysload)
(defn ns-finalize []
  (when (realized? jetty)
    (.close ^java.io.Closeable @jetty)))

(defn -main [& args]
  (log/info "stripe-integration.core/main running with args" args)
  (force jetty))

(force jetty)
