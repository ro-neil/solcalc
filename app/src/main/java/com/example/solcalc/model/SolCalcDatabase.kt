package com.example.solcalc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.solcalc.model.client.Client
import com.example.solcalc.model.client.ClientDao
import com.example.solcalc.model.estimate.Estimate
import com.example.solcalc.model.estimate.EstimateDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Client::class, Estimate::class], version=1, exportSchema=false)
public abstract class SolCalcDatabase: RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun estimateDao(): EstimateDao

    companion object{
        @Volatile
        private var INSTANCE:SolCalcDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SolCalcDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SolCalcDatabase::class.java,
                    "solcalc_db"
                ).addCallback(SolCalcDBCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class SolCalcDBCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{database ->
                scope.launch { populateDatabase(database.clientDao(), database.estimateDao()) }
            }
        }

        suspend fun populateDatabase(clientDao: ClientDao, estimateDao: EstimateDao){
            clientDao.deleteAll()
            estimateDao.deleteAll()

            val clients = arrayListOf<Client>()
            clients.add(Client(1,"Jane Doe","5 Bay Leaf Road, Kingston 19", 18097.67, 70, true))
            clients.add(Client(1,"John Doe","5 Bay Leaf Road, Kingston 19", 18097.67, 70, true))

            for(client in clients){
                clientDao.insertClient(client)
            }
        }
    }
}