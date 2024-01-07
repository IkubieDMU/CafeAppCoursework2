package com.example.cafeappcoursework.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "cafeData.db"
        private const val DATABASE_VERSION = 1

        //Table Names
        private const val TABLE_USERS = "Users"
        private const val TABLE_ORDER_DETAILS = "OrderDetails"
        private const val TABLE_PRODUCTS = "Products"

        //User Table Column Names
        private const val COLUMN_UserID = "UserID"
        private const val COLUMN_FullName = "FullName"
        private const val COLUMN_PhoneNo = "PhoneNo"
        private const val COLUMN_Email = "Email"
        private const val COLUMN_Username = "UserName"
        private const val COLUMN_Password = "Password"

        //OrderDetails Column Names
        private const val COLUMN_OrderID = "OrderID"
        private const val COLUMN_CustomerName = "CustomerName"
        private const val COLUMN_CustPhoneNo = "PhoneNo"
        private const val COLUMN_OrderDesc = "OrderDesc"
        private const val COLUMN_TableNo = "TableNo"
        private const val COLUMN_OrderPrice = "OrderPrice"

        //Products Column Names
        private const val COLUMN_ProductID = "ProductID"
        private const val COLUMN_ProductName = "ProductName"
        private const val COLUMN_ProductType = "ProductType"
        private const val COLUMN_ProductPrice = "ProductPrice"
    }

    private val createUsersTableQuery = """
        CREATE TABLE $TABLE_USERS (
            $COLUMN_UserID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_FullName TEXT,
            $COLUMN_PhoneNo TEXT,
            $COLUMN_Email TEXT,
            $COLUMN_Username TEXT,
            $COLUMN_Password TEXT
        )
    """.trimIndent()

    private val createOrderDetailsTableQuery = """
        CREATE TABLE $TABLE_ORDER_DETAILS (
            $COLUMN_OrderID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_CustomerName TEXT,
            $COLUMN_CustPhoneNo TEXT,
            $COLUMN_OrderDesc TEXT,
            $COLUMN_TableNo TEXT,
            $COLUMN_OrderPrice INTEGER
        )
    """.trimIndent()

    private val createProductsTableQuery = """
        CREATE TABLE $TABLE_PRODUCTS (
            $COLUMN_ProductID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_ProductName TEXT,
            $COLUMN_ProductType TEXT,
            $COLUMN_ProductPrice INTEGER
        )
    """.trimIndent()




    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUsersTableQuery)
        db?.execSQL(createOrderDetailsTableQuery)
        db?.execSQL(createProductsTableQuery)

        //Product Table Data


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(user: User): Long {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FullName,user.fullname)
            put(COLUMN_PhoneNo,user.phoneNo)
            put(COLUMN_Email,user.email)
            put(COLUMN_Username,user.usersUsername)
            put(COLUMN_Password,user.password)
        }
        val rowID = db.insert(TABLE_USERS,null,values)
        db.close()
        return rowID
    }

    fun addOrderDetails(order: Order): Long {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CustomerName,order.customerName)
            put(COLUMN_CustPhoneNo,order.custPhoneNo)
            put(COLUMN_OrderDesc,order.orderDesc)
            put(COLUMN_TableNo,order.tableNo)
            put(COLUMN_OrderPrice,order.price)
        }
        val rowID = db.insert(TABLE_ORDER_DETAILS,null,values)
        db.close()
        return rowID
    }

    fun addProduct(product: Products): Long {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ProductName,product.productName)
            put(COLUMN_ProductType,product.type)
            put(COLUMN_ProductPrice,product.price)
        }
        val rowID = db.insert(TABLE_PRODUCTS,null,values)
        db.close()
        return rowID
    }

    private fun isProductExists(product: Products): Boolean {
        val db = readableDatabase

        val selection =
            "$COLUMN_ProductName = ? AND $COLUMN_ProductType = ? AND $COLUMN_ProductPrice = ?"
        val selectionArgs = arrayOf(
            product.productName,
            product.type,
            product.price.toString()
        )

        val cursor = db.query(TABLE_PRODUCTS, null, selection, selectionArgs, null, null, null)

        val productExists = cursor.count > 0

        cursor.close()
        db.close()

        return productExists
    }

    fun deleteAllProducts() {
        val db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_PRODUCTS, null, null)
        db.close()
    }


    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_Username = ? OR $COLUMN_Password = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)

        val userExists = cursor.count > 0

        cursor.close()
        db.close()

        return userExists
    }

    fun getAllProducts(): List<Products> {
        val productList = mutableListOf<Products>()
        val db = this.readableDatabase

        val query = "SELECT * FROM $TABLE_PRODUCTS"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val prodNameIndex = cursor.getColumnIndexOrThrow(COLUMN_ProductName)
                val prodTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_ProductType)
                val prodPriceIndex = cursor.getColumnIndexOrThrow(COLUMN_ProductPrice)

                val prodName = cursor.getString(prodNameIndex)
                val prodType = cursor.getString(prodTypeIndex)
                val prodPrice = cursor.getDouble(prodPriceIndex)

                val product = Products(prodName, prodType, prodPrice)
                productList.add(product)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return productList
    }

}
