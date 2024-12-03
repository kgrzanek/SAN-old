(ns stripe-integration.core
  (:require
   ["nanoid" :as nano]
   ["react" :as React]
   ["react-dom/client" :as react-client]
   [cljs-http.client :as http]
   [cljs.core.async :refer [<! go]]
   [reagent.core :as r]))

(def ^:private counter-atom (r/atom 0))

(def ^:private currency-rates-atom (r/atom []))

(defn- fetch-nbp-currency-rates! []
  (go (let [response-chan
            (http/get "https://api.nbp.pl/api/exchangerates/tables/A?format=json"
              {:with-credentials? false})

            response (<! response-chan)]

        (when (:success response)
          (let [rates (-> response
                          :body
                          first    ; Get first table
                          :rates)] ; Get rates array
            (reset! currency-rates-atom rates))))))

;; APP COMPONENTS
(defn html-currencies-table [currency-rates]
  [:div.max-w-md.mx-auto.mt-8
   [:table {:style {:border "1px solid #ccc"}}
    [:thead
     [:tr
      [:th "Currency Name"]
      [:th "Currency Code"]
      [:th "Rate (mid)"]]]
    [:tbody
     (for [{:keys [currency code mid]} currency-rates]
       ^{:key (nano/nanoid)}
       [:tr
        [:td currency]
        [:td code]
        [:td (str mid)]])]]])

(defn vspace [height]
  [:div {:style {:height (str height)}}])

(defn- html-root []
  (println "test-1")
  [:div
   [:div
    {:on-click #(r/rswap! counter-atom inc)}
    (str "You've clicked " @counter-atom " times.")]

   [vspace "15px"]
   [:button {:on-click fetch-nbp-currency-rates!} "Pobierz kursy walut"]
   [vspace "25px"]
   [html-currencies-table @currency-rates-atom]])

;; APP INSTRUMENTATION
(defn- render-root!
  [root]
  (->> [:> React/StrictMode [html-root]]
       (r/as-element)
       (.render root)))

(defonce root-atom (atom nil))

(defn after-load! []
  (render-root! @root-atom))

(defn start! []
  (->> "core"
       (js/document.getElementById)
       (react-client/createRoot)
       (reset! root-atom)
       (render-root!)))
