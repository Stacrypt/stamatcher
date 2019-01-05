# StaMatcher

Simple order matcher.

## Stack
* Kotlin
* Kafka

## commands

#### Input:
* add
* change
* cancel

#### Output:
* added
* changed
* canceled
* matched


## Protocol
### Topics
Topics are "market"s. A market is usually made by two symbols, separated with `/` character.
Sample: 
* `USD/EUR`
* `BTC/ETH`

### Order
`uid:type:price:quantity(:expiresAt)`


