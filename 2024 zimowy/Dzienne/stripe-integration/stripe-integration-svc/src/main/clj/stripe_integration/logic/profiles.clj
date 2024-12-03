(ns stripe-integration.logic.profiles)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(def ^:private profile-indexes-atom
  (atom {:profile-id->profile    {}
         :profile-email->profile {}}))

(defn- find-profile*
  [profile-indexes profile-id]
  (get-in profile-indexes [:profile-id->profile profile-id]))

(defn find-profile
  [profile-id]
  (find-profile* @profile-indexes-atom profile-id))

(defn- find-profile-by-email*
  [profile-indexes email]
  (get-in profile-indexes [:profile-email->profile email]))

(defn find-profile-by-email
  [email]
  (find-profile-by-email* @profile-indexes-atom email))

(defn valid-credentials?
  [email password]
  (when-let [profile (find-profile-by-email email)]
    (= password (:password profile))))

(defn sign-up!
  [email password]
  (swap! profile-indexes-atom
         (fn [profile-indexes]
           (when (find-profile-by-email* profile-indexes email)
             (throw (ex-info "Profile already exists" {:email email})))

           (let [profile-id (random-uuid)
                 profile    {:id       profile-id
                             :email    email
                             :password password}]

             (-> profile-indexes
                 (update :profile-id->profile    assoc profile-id profile)
                 (update :profile-email->profile assoc email      profile))))))

(sign-up! "john.doe@gmail.com" "76253765")
(sign-up! "kgrzanek@san.edu.pl" "9897d9s8797sd987")
