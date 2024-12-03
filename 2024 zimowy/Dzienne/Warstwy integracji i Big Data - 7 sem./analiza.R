library(data.table)
setwd("~/Devel/Projects/SAN/2024 zimowy/Dzienne/Warstwy integracji i Big Data - 7 sem.")

dt1 <- fread("nazwiska_męskie-osoby_żyjące.csv")
summary(dt1)
dt1[`Nazwisko aktualne` == 'GRZANEK']

dt2 <- fread("nazwiska_żeńskie-osoby_żyjące.csv")
summary(dt2)
dt2[`Nazwisko aktualne` == 'GRZANEK']
