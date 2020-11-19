Feature: AceNet Automation

@LoginToAStore
Scenario: AcenetLoginToStore
Given user is on AceNet Home Page
Then User Navigates to the store

@PlaceStockReserveOrder
Scenario: AcenetOrderPlacementStockReserve
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the search key "8015627"
Then the user is navigated to the Article Details page
Then User enters the details and place order
And Confirm Orders
    


@ItemAndInventorySearch
Scenario: AcenetArticleDetails
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the search Category "hammer"
Then Click Show RSC Qty check box and verify listed items with RSC Qty


@AcenetItempageValidation
Scenario: ValidateItemDetailpage
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the search Category "10017"
Then the user is navigated to the Article Details page
Then Click on productView tab and verify product view
Then Click on specfication tab and verify specification
Then Click on Hazard badge and verify HAZMAT text 

@ItemAndInventorySearch
Scenario: ValidateItemAndInventorySearch
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the search Category "hammer"
Then Click Show RSC Qty check box and verify listed items with RSC Qty
Then the user is navigated to the Article Details page

@AddingMultipleSKUsToPlaceOrders
Scenario: ValidateItemAndInventorySearch
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the search Category "hammer"
Then Click Show RSC Qty check box and verify listed items with RSC Qty
And Add a new basket with shipping Method as "Stock Reserve"
And Add multiple sku in the basket "20160"
And Checkout an order

