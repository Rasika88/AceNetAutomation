Feature: AceNet Automation

@AddMultipleSKUsToPlaceOrders
Scenario Outline: To validate placing orders from Batch ordering tab
Given user is on AceNet Home Page
Then User Navigates to the store
Then User enters the searchCategory "<SearchCategory>" 
And  Add a new basket with shipping Method "<shippingMethod>"
Then Click addToCart
And  Add multiple sku in the basket sku "<Sku>"
And Checkout multiple Skus order "<shipType>"

Examples:
|  SearchCategory   | shippingMethod    |  Sku  |  shipType        |
#|hammer             |Cross-Dock Pick Up |20160  |Cross-Dock Pick Up|
#|hammer             |RSC Pickup        |20267  |RSC Pickup        |
#|hammer             |Ship to Retailer  |20160  |Ship to Retailer |
#|hammer             |Stock Reserve  |20160  |Stock Reserve  |
|hammer             |Ship to Customer  |20160  |Ship to Customer  |

@PlaceOrderWithStockReserveShippment
Scenario Outline: To validate placing Stock Reserve order from item detail page
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>" 
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And  Confirm Orders has been placed
#working perfect

Examples:
|StoreNumber|  SearchCategory   |shippingMethod  |orderQty|shipType|
|297        |8015627            |Stock Reserve   |1       |Stock Reserve|

@PlaceOrderWithRSCPickupShippment 
Scenario Outline: To validate placing RSC pickup order from item detail page
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>"
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And provide RSC pickup details "<rSCPickupName>" "<rSCPickupDate>"
And  Confirm Orders has been placed
#working perfect
Examples:
|StoreNumber|  SearchCategory  |shippingMethod  |orderQty|shipType    |rSCPickupName|rSCPickupDate|
|297        |8015627           |RSC Pickup		|1       |RSC Pickup  |John         |12/23/2020|


@PlaceOrderWithShipToRetilerShippment
Scenario Outline: To validate placing Ship to Retailer order from item detail page
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>"
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And  Confirm Orders has been placed
#working perfect
Examples:
|StoreNumber|  SearchCategory  |shippingMethod  |orderQty|shipType|
#|297       |20267             |RSC Pickup		|1       |RSC Pickup	|
#|297       |8015627           |Stock Reserve   |1       |Stock Reserve|
 |297       | 3037405         |Ship to Retailer|1       |Ship to Retailer|

@PlaceOrderWithShiptoRetailerUsingDifferentRSC
Scenario Outline: placing Ship to Retailer order from the item detail page for a different RSC
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>"
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
Then Checkout to place an order "<shippingMethod>"
Then Remove the selected RSC and select different RSC using recalculate approach "<NewRSCValue>"
And  Confirm Orders has been placed
#working perfect
Examples:
|StoreNumber|  SearchCategory   |shippingMethod  |orderQty|NewRSCValue|
 |297       |20267             |Ship to Retailer|1       |1		  |

@PlaceOrderWithShiptoCustomer
Scenario Outline: placing Ship to Customer order from the item detail page
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>"
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And  Confirm Orders has been placed
#Ship to Customer phone number field is not entering values need to check
Examples:
|StoreNumber|  SearchCategory   |shippingMethod  |orderQty|shipType|
|297        |2012250            |Ship to Customer|1       |Ship to Customer|

@PlaceDropshipOrder
Scenario Outline: placing DropShip order
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>"
Then Click on manage product tab
Then Select Browse Product and Vendors
Then Select vendors catagory 
Then Apply vendors filter
Then Choose the dropship serach in the listed vendor
Then Verify you are landing in to search page
And  Click any sku and verify you are landing into item detail page "<sku>"
Then the user is navigated to the Article Details page
And  User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And continue checkout
#working perfect
Examples:
|StoreNumber|sku    |shippingMethod|orderQty|shipType|
|297        |1182237|Dropship      |6       |Dropship|


@PlaceDiscoveryOrder
Scenario Outline: To validate discovery order creation in Acenet
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>"
Then Click on manage product tab
Then Navigate to discovery assortment planner page
Then Select By Merchandise Class
Then Select any Department and MC enter the quantity "<merchOrderQty>"
Then Click addToCart
And  Click on Discovery shopping cart
And  Select Discovery basket
And  Verify the added item is present in discovery cart
And  Select the added the item and checkout
#working as expected
Examples:
|StoreNumber|  merchOrderQty   |
|297  |1|

@PlaceCustomerPriorityOrder
Scenario Outline: To validate placing Customer Priority order from item detail page
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>" 
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<shippingMethod>"
Then User select the order qty in product details page "<orderQty>"
And  Checkout an order "<shipType>"
And  Confirm Orders has been placed
#working perfect
Examples:
|StoreNumber|  SearchCategory   |shippingMethod         |orderQty|shipType|
|388        |8009848            |Customer Priority Order|1       |Customer Priority Order|


@PlaceRSCPickupOrdersOnceSkusAddedToBasket
Scenario Outline: To validate placing RSC Pick up order once SKUs added to Basket
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>" 
Then Click addToCart
Then Click createNewBasket button from Cart page
Then create a new basket with all the details "<BasketName>" "<ShippingMethod>" "<CustName>" "<CustPh>" "<CustEmail>"
Then Click AddSkuToBasket "<Sku>"
And provide RSC pickup detail "<rSCPickupName>" "<rSCPickupDate>"
#working perfect
Examples:
|StoreNumber|  BasketName  |ShippingMethod  |CustName|CustPh    |CustEmail      |Sku  |shipType   |rSCPickupName|rSCPickupDate|
|297        |Automat12     |RSC Pick Up     |Johny   |1231231234|Johny@gmail.com|20160|RSC Pick Up|John         |12/23/2020|

@PlaceBulletinOrder
Scenario Outline: To validate that Retailer able to place Bulletin order
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>" 
Then Click on manage product tab
Then Select Event planner
Then Select Event catagory 
Then provide sku qty "<skuQty>"
Then Click addToCart
And  Click on EventPlanner shopping cart
And  Select EventPlanner basket
And  Verify the added item is present in EventPlanner cart
And  Select the item added and checkout
And  Verify Order submitted
#working but test data issue, As per functionality not an automation candidate
Examples:
|StoreNumber|skuQty|
|388        |3     |

@PlaceShipToRetailerOnceSkusAddedToBasket
Scenario Outline: To validate placing Ship to Retailer order once SKUs added to BasketPrerequisite
Given user is on AceNet Home Page
#Then User Navigates to the store
Then User Select the store "<StoreNumber>" 
Then Click addToCart
Then Click createNewBasket button from Cart page
Then create a new basket with all the details "<BasketName>" "<ShippingMethod>" "<CustName>" "<CustPh>" "<CustEmail>"
Then Click AddSkuToBasket "<Sku>"
And Checkout multiple Skus order "<shipType>"
#working perfect
Examples:
|StoreNumber|  BasketName  |ShippingMethod  |CustName|CustPh    |CustEmail      |Sku   |shipType|
|297        |Automat12     |Ship to Retailer|John    |1233271234|Johny@gmail.com|20160 |Ship to Retailer|

@PlaceShipToCustomerOnceSkusAddedToBasket
Scenario Outline: To validate placing Ship to Customer order  once SKUs added to Basket
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>"
Then Click addToCart
Then Click createNewBasket button from Cart page
Then create a new basket with all the details "<BasketName>" "<ShippingMethod>" "<CustName>" "<CustPh>" "<CustEmail>"
Then Click AddSkuToBasket "<Sku>"
And Checkout multiple Skus order "<shipType>"
#phone no field issue
Examples:
|StoreNumber|  BasketName  |ShippingMethod  |CustName|CustPh    |CustEmail      |Sku   |shipType|
|297        |Automat12     |Ship to Customer|John    |1233271234|Johny@gmail.com|20160 |Ship to Customer|

@PlaceStockReserveOrderWithNoInventorInRSC
Scenario Outline: To validate placing Stock Reserve order from item detail page
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>" 
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then User enters the shipping method "<ShippingMethod>"
Then User select the order qty in product details page "<OrderQty>"
And  Checkout an order "<ShipType>"
#working perfect

Examples:
|StoreNumber|  SearchCategory  |ShippingMethod            |OrderQty|ShipType|
|388        |2068765           |Stock Reserve Backorder	  |1       |Stock Reserve Backorder	|

@AcenetItempageValidation
Scenario Outline: ValidateItemDetailpage
Given user is on AceNet Home Page
Then User Select the store "<StoreNumber>" 
Then User enters the searchCategory "<SearchCategory>" 
Then the user is navigated to the Article Details page
Then Click on productView tab and verify product view
Then Click on specfication tab and verify specification
Then Click on Hazard badge and verify HAZMAT text 

Examples:
|StoreNumber|  SearchCategory  |
|297        |2068765           |
