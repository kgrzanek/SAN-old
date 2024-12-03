;; {}

[:create-aggregate :email "test@gmail.com"]

;; {:aggregate-id 1 :email "test1@gmail.com"}

[:set-email :aggregate-id 1 :email "test2@gmail.com"]

;; {:aggregate-id 1 :email "test2@gmail.com"}

[:set-email :aggregate-id 1 :email "test3@gmail.com"]

{:aggregate-id 1 :email "test3@gmail.com"}
