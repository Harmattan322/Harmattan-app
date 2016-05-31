Feature: Набор заданий 1

  @TravelInsurance
  Scenario: Тест 1
    Given Go to link
    Given Check Polis Fields
    Given Check Registration and Confirm
    Given Check Total Amount Min
    Given Select Enough
    Given Check Total Amount Enough
    Given Pick Sport and Check Total Amount
    Given Check Text Sport
    Given Pick also Prudent and Check Total Amount
    Given Pick also Baggage protection, but no Sport

  @PrivateCustomers
  Scenario: Тест 2
    Given Go to link
    Given Check Date
    Given Check Converter
    Given Set on Converter RUB and EUR
    Given check value of RUB to EUR
    Given Set on Converter USD and EUR
    Given check value of USD to EUR
    Given Set on Converter USD and USD
    Given check value of USD to USD

  @Atm
  Scenario: Тест 3
    Given Go to link
    Given check Green Icons
    Given check Minimal count Location
    Given check Order Locations
    Given pick checkbox Payment Device
    Given push button Show More
    Given remove checkbox Offices

  @DNS
  Scenario: Тест ДНС
    Given Go to link
    Given pick Criteria
    Given push Button
    Given check Name
    Given check Desc
    Given find Average Good
    Given find Cheap Goods with Waiting

