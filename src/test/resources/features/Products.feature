Feature: Products Page Interaction

  Scenario: Open Cart Page
    Given the user is logged in with username "standard_user" and password "secret_sauce"
    When the user is redirected to products page
    Then the user can open the cart page by clicking on the cart icon

