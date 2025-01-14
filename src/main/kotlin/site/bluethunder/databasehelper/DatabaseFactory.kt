package site.bluethunder.databasehelper

import site.bluethunder.entities.category.CategoryTable
import site.bluethunder.entities.category.SubCategoryTable
import site.bluethunder.entities.product.*
import site.bluethunder.entities.product.defaultproductcategory.ProductCategoryTable
import site.bluethunder.entities.product.defaultproductcategory.ProductSubCategoryTable
import site.bluethunder.entities.product.defaultvariant.ProductColorTable
import site.bluethunder.entities.product.defaultvariant.ProductSizeTable
import site.bluethunder.entities.shop.ShopCategoryTable
import site.bluethunder.entities.shop.ShopTable
import site.bluethunder.entities.user.UserHasTypeTable
import site.bluethunder.entities.user.UserTypeTable
import site.bluethunder.entities.user.UserProfileTable
import site.bluethunder.entities.user.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.slf4j.LoggerFactory
import java.net.URI
import javax.sql.DataSource

object DatabaseFactory {
    private val log = LoggerFactory.getLogger(this::class.java)
    fun init() {
        initDB()
        //Database.connect(hikari())
        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)
            create(UserTable, UserProfileTable, UserTypeTable, UserHasTypeTable,ShopTable, ShopCategoryTable, ProductCategoryTable, ProductSubCategoryTable, ProductTable, ProductSizeTable, ProductColorTable)
            create(CategoryTable, SubCategoryTable, BrandTable)
        }
    }

    private fun initDB() {
        // database connection is handled from hikari properties
        val config = HikariConfig("/hikari.properties")
        val dataSource = HikariDataSource(config)
        runFlyway(dataSource)
        Database.connect(dataSource)
    }
    private fun hikari():HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("HEROKU_POSTGRESQL_NAVY_URL")
        config.maximumPoolSize = 3
        config.isAutoCommit = true
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    // For heroku deployement
    private fun hikariForHeroku(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"

        val uri = URI(System.getenv("DATABASE_URL"))
        val username = uri.userInfo.split(":").toTypedArray()[0]
        val password = uri.userInfo.split(":").toTypedArray()[1]

        config.jdbcUrl =
            "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require" + "&user=$username&password=$password"

        config.validate()
        return HikariDataSource(config)
    }


    // database connection for h2
    private fun hikariForH2(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:file:~/documents/db/h2db"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    private fun runFlyway(datasource: DataSource) {
        val flyway = Flyway.configure().dataSource(datasource).load()
        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            log.error("Exception running flyway migration", e)
            throw e
        }
        log.info("Flyway migration has finished")
    }
}