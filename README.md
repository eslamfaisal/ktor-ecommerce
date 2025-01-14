# ktor-E-Commerce Backend for Api
[![Ktor](https://img.shields.io/badge/ktor-2.3.1-blue.svg)](https://github.com/ktorio/ktor)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
<a href="https://github.com/piashcse"><img alt="License" src="https://img.shields.io/static/v1?label=GitHub&message=piashcse&color=C51162"/></a>

Ktor-E-Commerce built with [ktor](https://ktor.io/docs/welcome.html) for e-commerce api development.


## Swagger Test URL
https://free.bluethunder.site/swagger-ui/index.html?url=/openapi.json#/

## Swagger View

![Screenshot 2023-06-26 at 5 31 01 PM](https://github.com/piashcse/ktor-E-Commerce/assets/33801510/34cee19d-33c0-401d-97fe-3d89ffe769bc)


# Main Features
- Role Management(Admin, Seller, User)
- Login
- Registration
- Shop Registration
- Product Category
- Product Subcategory
- Brand
- Cart
- Order

## Architecture
MVC (Model - View - Controller)

## Built With 🛠
- [Ktor](https://ktor.io/docs/welcome.html) - Ktor is a framework to easily build connected applications – web applications, HTTP services, mobile and browser applications. Modern connected applications need to be asynchronous to provide the best experience to users, and Kotlin coroutines provide awesome facilities to do it in an easy and straightforward way.
- [Exposed](https://github.com/JetBrains/Exposed) - Exposed is a lightweight SQL library on top of JDBC driver for Kotlin language. Exposed has two flavors of database access: typesafe SQL wrapping DSL and lightweight Data Access Objects (DAO).
- [MySQL](https://www.postgresql.org/) - MySQl is a powerful, open source object-relational database system .
- [Kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) - A multiplatform Kotlin library for working with date and time.
- [Bcrypt](https://github.com/patrickfav/bcrypt) - A Java standalone implementation of the bcrypt password hash function. Based on the Blowfish cipher it is the default password hash algorithm for OpenBSD and other systems including some Linux distributions.
- [Apache Commons Email](https://github.com/apache/commons-email) - Apache Commons Email aims to provide an API for sending email. It is built on top of the JavaMail API, which it aims to simplify.
- [Ktor OpenAPI/Swagger](https://github.com/LukasForst/ktor-openapi-generator) - The Ktor OpenAPI Generator is a library to automatically generate the descriptor as you route your ktor application.
- [Valiktor](https://github.com/valiktor/valiktor) - Valiktor is a type-safe, powerful and extensible fluent DSL to validate objects in Kotlin

## Requirements

- [JAVA 11](https://jdk.java.net/11/) (or latest)
- [MySQL](https://www.mysql.com/) (latest)

## How to run

- `git clone git@github.com:piashcse/ktor-E-Commerce.git`
-  `Create a db in postgreSQL`
-  Replace you db name in `dataSource.databaseName=ktor-1.0.0` instread of ktor-1.0.0 in hikari.properties from resource folder
- `run fun main()` from application class

## Maintain and deploy by
- [**Eslam faisal**](https://github.com/eslamfaisal)
[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/eslam-faisal-46aa83114/)

## Thanks to
- [**Mehedi Hassan Piash**](https://github.com/piashcse)
- [**Original Repo**](https://github.com/piashcse/ktor-E-Commerce)

# License
```
Copyright 2023 piashcse (Mehedi Hassan Piash)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
