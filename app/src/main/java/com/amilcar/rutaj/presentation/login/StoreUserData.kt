package com.amilcar.rutaj.presentation.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserData (private val context: Context){

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userName")

        val USER_NAME = stringPreferencesKey("userName")
        val USER_EMAIL = stringPreferencesKey("userEmail")
        val USER_PASS = stringPreferencesKey("userPass")
        val USER_TELEFONO = stringPreferencesKey("userTelefono")
        val USER_STATE = booleanPreferencesKey("userState")
        val USER_ID = intPreferencesKey("userId")
    }

    // obtengo el usuario y pasword guardado en datastore

    fun getProferences() : Flow<UserData> {
        return context.dataStore.data
            .map { preferences ->
                UserData(
                    name = preferences[USER_NAME] ?: "",
                    pass = preferences[USER_PASS] ?: "",
                    email = preferences[USER_EMAIL] ?: "",
                    telefono = preferences[USER_TELEFONO] ?: "",
                    state = preferences[USER_STATE] ?: true,
                    id = (preferences[USER_ID] ?: 0)
                )
            }
    }

    //get the saved email
/*    val getEmail: Flow<String?> = context.dataStoree.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: "FirstLast@gmail.com"
        }*/

    //save email into datastore
    suspend fun setPreferences(name:String, email:String, pass:String,telefono :String,state:Boolean,id:Int){
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
            preferences[USER_EMAIL] = email
            preferences[USER_PASS] = pass
            preferences[USER_TELEFONO] = telefono
            preferences[USER_STATE] = state
            preferences[USER_ID] = id
        }
    }
}