package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductJsonFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ResumeRepository(
    private val sessionManager: SessionManager,
    private val productDAO: ProductDAO
) {

    private fun saveUserPassQuestBackup() {
        sessionManager.saveUserPassQuestBackup()
    }

    fun passedByQuestBackup(): Boolean = sessionManager.passedByQuestBackup()

    fun getSaveUserEmailBackup(): String = sessionManager.getSaveUserEmailBackup()

    suspend fun returnListProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            productDAO.returnListProduct()
        }
    }

    suspend fun acceptBackupData(): String {
        saveUserPassQuestBackup()
        return withContext(Dispatchers.IO) {
            var response = ""
            Firebase
            Firebase.database.reference.child(PATH_USERS).child(getSaveUserEmailBackup())
                .child(PATH_PRODUCT).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChildren()) {
                            response = snapshot.childrenCount.toString()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            delay(2000L)
            response
        }
    }

//            Firebase.database.reference.child(PATH_USERS).child(getSaveUserEmailBackup()).child(PATH_PRODUCT).get().addOnSuccessListener {
//                response = it.value.toString()
//            }
//            delay(2000L)
//            response
//            //mapJsonForDataClass(response)!!.toString()

    private fun mapJsonForDataClass(response: String): Any? {
        return Gson().fromJson(response, ProductJsonFirebase::class.java)
    }

    fun deniedBackupData() {
        saveUserPassQuestBackup()
    }

    companion object {
        const val PATH_USERS = "users"
        const val PATH_PRODUCT = "products"
        const val PATH_EXTRACT = "extracts"
    }

}
