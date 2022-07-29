Feature: Login User
  Scenario: Logging in to site
    Given i am on "https://kpfu.ru"
    When i open "//*[@id=\"top-panel\"]/div[1]/div[1]/a" page
    And type text: "//*[@id=\"eu_enter\"]/input[1]" to input with name "AIKhusaenov"
    And type text: "//*[@id=\"eu_enter\"]/input[2]" to input with password "hpzwlauq"
    And press button with id "//*[@id=\"eu_enter\"]/input[3]"
    And I visit "//*[@id=\"main-blocks\"]/div[1]/div[6]/a" page
    And I visit "//*[@id=\"under-slider-menu\"]/div/a[2]/i" portfolio
    Then I should see the find "Хусаенов Амир Ильдусович" on the page
#  Scenario: dowlaud image and document
#    Given "//*[@id=\"under-slider-menu\"]/div/a[2]/i" portfolio
#    When delete image and loud
