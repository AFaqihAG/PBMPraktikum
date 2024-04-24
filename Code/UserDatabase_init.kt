@Database(entities = [/* TODO : Add The Table */], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    /* TODO :  Add The DAO */

    companion object {
        @Volatile
        private var Instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "my_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
