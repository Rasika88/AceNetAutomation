Feature: AceNet Automation

@AddMultipleSKUsToPlaceOrders
Scenario Outline: VerifyOrdersWithMultipleSkusAndshippment
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
Then Click Show RSC Qty check box and verify listed items with RSC Qty
And Add a new basket with shipping Method "<shippingMethod>"
And Add multiple sku in the basket sku "<Sku>"
And Checkout an order "<shipType>"

Examples:
|  SearchCategory   | shippingMethod    |  Sku  |  shipType        |
|hammer             |Cross-Dock Pick Up |20160  |Cross-Dock Pick Up|
#|hammer             |RSC Pickup        |20267  |RSC Pickup        |
#|hammer             |Ship to Customer  |20160  |Ship to Customer  |

#Stock Reserve
#Ship to Retailer
#Ship to Customer
#RSC Pickup
#Cross-Dock Pick Up

@PlaceOrderWithRSCPickupShippment
Scenario Outline: VerifyplacingRSCpickupOrderfromItemDetailPage
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method and order qty in product details page "<shippingMethod>" "<orderQty>"
And  Confirm Orders has been placed

Examples:
|  SearchCategory   |shippingMethod  |orderQty|verifySearchCategory|
|20267              |RSC Pickup		 |1       |3037405|

@PlaceOrderWithShipToRetilerShippment
Scenario Outline: Validate placing Stock Reserve order from item detail page
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method and order qty in product details page "<shippingMethod>" "<orderQty>"
And  Confirm Orders has been placed
#And  Verify placed order is present in stock reserve list "<verifySearchCategory>" 
#need to check the last line

Examples:
|  SearchCategory   |shippingMethod  |orderQty|verifySearchCategory|
|3037405            |Ship to Retailer|1       |3037405|


@PlaceOrderWithStockReserveShippment
Scenario Outline: AcenetOrderPlacementStockReserve
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method and order qty in product details page "<shippingMethod>" "<orderQty>"
And  Confirm Orders has been placed

Examples:
|  SearchCategory   |shippingMethod  |orderQty|verifySearchCategory|
|8015627            |Stock Reserve   |1       |8015627|

@PlaceOrderWithShiptoRetailerUsingDifferentRSC
Scenario Outline:placing Ship to Retailer order from the item detail page for a different RSC
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method and order qty in product details page "<shippingMethod>" "<orderQty>"
Then Checkout to place an order "<shippingMethod>"
Then Click on View shipping option from different RSC
Then Remove the selected RSC and select different RSC using recalculate approach
Then Remove the selected RSC and select different RSC using recalculate approach
Examples:
|  SearchCategory   |shippingMethod  |orderQty|verifySearchCategory|
|3037405            |Ship to Retailer|1       |3037405|

