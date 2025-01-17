(ns build
  (:refer-clojure :exclude [compile])
  (:require
   [clojure.tools.build.api :as build-api]))

(def lib     'kongra/stripe-integration-svc)
(def basis   (build-api/create-basis {:project "deps.edn"}))
(def version (format "0.1.%s" (build-api/git-count-revs nil)))

;; SRC/RESOURCES
(def src-dirs           ["src/main/clj/"])
(def java-dirs          ["src/main/java/"])
(def resources-dirs     ["resources/"])
(def src+resources-dirs ["src/" "resources/"])

;; TARGET(S)
(def ^:private build-folder                    "target/")
(def ^:private class-dir    (str build-folder "classes/"))
(def ^:private jar-file     (format "target/%s-%s.jar" (name lib) version))
(def ^:private uberjar-file (format "target/%s-STANDALONE.jar" (name lib)))
(def ^:private main         "stripe-integration.core")

;; TASKS
(defn- prs [x] (with-out-str (pr x)))

(defn clean [_]
  (println "deleting" (prs build-folder))
  (time
    (build-api/delete {:path build-folder})))

(defn javac [_]
  (println "javac" (prs java-dirs) "into" (prs class-dir))
  (time
    (build-api/javac
      {:src-dirs   java-dirs
       :class-dir  class-dir
       :basis      basis
       :javac-opts ["--release" "21"]})))

(defn compile-clj [_]
  ;; (javac nil)
  (println "compiling" (prs src-dirs) "into" (prs class-dir))
  (time
    (build-api/compile-clj
      {:basis     basis
       :src-dirs  src-dirs
       :java-opts ["-XX:+UseStringDeduplication"
                   "-Dclojure.compiler.direct-linking=true"
                   "-Dclojure.warn.on.reflection=false"
                   "-Dclojure.assert=true"]

       :class-dir class-dir})))

(defn jar [_]
  (println "writing pom for" lib version)
  (time
    (build-api/write-pom
      {:class-dir class-dir
       :lib       lib
       :version   version
       :basis     basis
       :src-dirs  src-dirs}))

  (println "copying" (prs src+resources-dirs) "to" (prs class-dir))
  (time
    (build-api/copy-dir
      {:src-dirs   src+resources-dirs
       :target-dir class-dir}))

  (println "creating" jar-file)
  (time
    (build-api/jar
      {:class-dir class-dir
       :jar-file  jar-file})))

(defn install [_]
  (build-api/install
    {:basis     basis
     :class-dir class-dir
     :jar-file  jar-file
     :lib       lib
     :version   version})

  (println "installed" jar-file))

(defn uberjar [_]
  (clean       nil)
  (compile-clj nil)

  (println "writing pom for" lib version)
  (time
    (build-api/write-pom
      {:class-dir class-dir
       :lib       lib
       :version   version
       :basis     basis
       :src-dirs  src-dirs}))

  (println "copying" (prs src+resources-dirs) "to" (prs class-dir))
  (time
    (build-api/copy-dir
      {:src-dirs   src+resources-dirs
       :target-dir class-dir}))

  (println "creating" uberjar-file)
  (time
    (build-api/uber
      {:basis     basis
       :class-dir class-dir
       :main      main
       :uber-file uberjar-file})))
