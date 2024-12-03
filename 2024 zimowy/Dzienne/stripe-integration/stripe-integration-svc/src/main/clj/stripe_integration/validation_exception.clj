(ns stripe-integration.validation-exception)

(gen-class
  :name "stripe-integration.ValidationException"
  :extends java.lang.Throwable
  :constructors
  {[String Object] [String Throwable boolean boolean]}
  :init init
  :state state
  :main false)

(defn -init [^String message obj]
  [[(str message) nil true false] obj])
