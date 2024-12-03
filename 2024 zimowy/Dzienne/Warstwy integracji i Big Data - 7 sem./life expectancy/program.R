library(data.table)
library(purrr)

setwd("~/Devel/Projects/SAN, ITI/SAN/2024 zimowy/Dzienne/Warstwy integracji i Big Data - 7 sem./life expectancy")

dt1 <- fread("data-origin/archive/annual-death-rates-in-different-age-groups-by-sex.csv")
View(dt1)
summary(dt1)
str(dt1)

typeof(dt1$`Central death rate - Type: period - Sex: female - Age: 0`)
typeof(dt1[, Entity])
median(dt1[, `Central death rate - Type: period - Sex: female - Age: 0`])

dt1[, Entity] %>% unique() %>% sort()

sort(unique(dt1$Entity))

# Jaki jest współczynnik dla female, wiek 0, dla poszczególnych krajów
dt1[Year >= 2010, mean(`Central death rate - Type: period - Sex: female - Age: 0`), by=Entity] %>% View

dt2 <- dt1[Year >= 2011,
      .(Female0=median(`Central death rate - Type: period - Sex: female - Age: 0`),
        Male0=median(`Central death rate - Type: period - Sex: male - Age: 0`),
        Female45=median(`Central death rate - Type: period - Sex: female - Age: 45`),
        Male45=median(`Central death rate - Type: period - Sex: male - Age: 45`),
        Female80=median(`Central death rate - Type: period - Sex: female - Age: 80`),
        Male80=median(`Central death rate - Type: period - Sex: male - Age: 80`)), by=Entity]

library(ggplot2)
library(tidyr)
library(dplyr)

# First, let's reshape the data to long format
dt_long <- dt2 %>%
  pivot_longer(
    cols = c(starts_with("Female"), starts_with("Male")),
    names_to = c("Gender", "Age"),
    names_pattern = "(Female|Male)(\\d+)",
    values_to = "Mortality_Rate"
  )

# Create the plot
ggplot(dt_long, aes(x = reorder(Entity, Mortality_Rate), y = Mortality_Rate,
                    color = Gender, shape = Age)) +
  geom_point(alpha = 0.6) +
  coord_flip() + # Flip coordinates for better country name readability
  facet_wrap(~Age, scales = "free_x") + # Separate plots by age group
  theme_minimal() +
  theme(
    axis.text.y = element_text(size = 7),
    legend.position = "bottom",
    plot.title = element_text(size = 12, face = "bold"),
    strip.text = element_text(size = 10)
  ) +
  labs(
    title = "Mortality Rates by Country, Gender, and Age Group",
    x = "Country/Region",
    y = "Mortality Rate",
    color = "Gender"
  )

# Alternative visualization focusing on top 20 countries for each age group
top_20_by_age <- dt_long %>%
  group_by(Age) %>%
  arrange(desc(Mortality_Rate)) %>%
  slice_head(n = 20) %>%
  ungroup()

ggplot(top_20_by_age, aes(x = reorder(Entity, Mortality_Rate),
                          y = Mortality_Rate,
                          fill = Gender)) +
  geom_bar(stat = "identity", position = "dodge") +
  coord_flip() +
  facet_wrap(~Age, scales = "free") +
  theme_minimal() +
  theme(
    axis.text.y = element_text(size = 8),
    legend.position = "bottom",
    plot.title = element_text(size = 12, face = "bold"),
    strip.text = element_text(size = 10)
  ) +
  labs(
    title = "Top 20 Countries by Mortality Rate for Each Age Group",
    x = "Country/Region",
    y = "Mortality Rate",
    fill = "Gender"
  )

# Define European countries
european_countries <- c(
  "Albania", "Austria", "Belarus", "Belgium", "Bosnia and Herzegovina",
  "Bulgaria", "Croatia", "Czech Republic", "Denmark", "Estonia",
  "Finland", "France", "Germany", "Greece", "Hungary", "Iceland",
  "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta",
  "Moldova", "Montenegro", "Netherlands", "North Macedonia", "Norway",
  "Poland", "Portugal", "Romania", "Russia", "Serbia", "Slovakia",
  "Slovenia", "Spain", "Sweden", "Switzerland", "Ukraine", "United Kingdom"
)

# Filter and reshape the data
dt_europe <- dt2 %>%
  filter(Entity %in% european_countries) %>%
  pivot_longer(
    cols = c(starts_with("Female"), starts_with("Male")),
    names_to = c("Gender", "Age"),
    names_pattern = "(Female|Male)(\\d+)",
    values_to = "Mortality_Rate"
  )

# Create the main plot
ggplot(dt_europe, aes(x = reorder(Entity, Mortality_Rate),
                      y = Mortality_Rate,
                      fill = Gender)) +
  geom_bar(stat = "identity", position = "dodge") +
  coord_flip() +
  facet_wrap(~Age, scales = "free_x",
             labeller = labeller(Age = function(x) paste0("Age ", x))) +
  scale_fill_manual(values = c("Female" = "#FF9999", "Male" = "#6699CC")) +
  theme_minimal() +
  theme(
    axis.text.y = element_text(size = 9),
    axis.text.x = element_text(angle = 45, hjust = 1),
    legend.position = "bottom",
    plot.title = element_text(size = 12, face = "bold"),
    strip.text = element_text(size = 10),
    panel.grid.major.y = element_blank()
  ) +
  labs(
    title = "Mortality Rates in European Countries by Age Group and Gender",
    subtitle = "Comparing mortality rates at ages 0, 45, and 80",
    x = "Country",
    y = "Mortality Rate",
    fill = "Gender"
  )

# Create a plot showing the gender gap
dt_europe_gap <- dt_europe %>%
  pivot_wider(
    names_from = Gender,
    values_from = Mortality_Rate
  ) %>%
  mutate(gender_gap = Male - Female)

ggplot(dt_europe_gap, aes(x = reorder(Entity, gender_gap), y = gender_gap)) +
  geom_col(aes(fill = gender_gap > 0)) +
  coord_flip() +
  facet_wrap(~Age, scales = "free_x",
             labeller = labeller(Age = function(x) paste0("Age ", x))) +
  scale_fill_manual(values = c("#FF9999", "#6699CC"), guide = "none") +
  theme_minimal() +
  theme(
    axis.text.y = element_text(size = 9),
    axis.text.x = element_text(angle = 45, hjust = 1),
    plot.title = element_text(size = 12, face = "bold"),
    strip.text = element_text(size = 10),
    panel.grid.major.y = element_blank()
  ) +
  labs(
    title = "Gender Gap in Mortality Rates Across European Countries",
    subtitle = "Positive values indicate higher male mortality",
    x = "Country",
    y = "Mortality Rate Difference (Male - Female)",
    fill = "Gender Gap"
  )
