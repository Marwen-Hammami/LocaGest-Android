package tn.sim.locagest.models

data class User(
    val _id: String?,
    val username: String,
    val email: String,
    val password: String,
    val firstName: String? ,
    val lastName: String? ,
    val creditCardNumber: Long? ,
    val rate: String = "GOOD" ,
    val specialization: String? ,
    val experience: Int? ,
    val roles: String = "client",
    val online: Boolean = false,
    val image: String?
) {
    companion object {
        lateinit var listUsers: ArrayList<User>
        var currentUser: User = User("654be79dd52a6532621b0220","Mer","marwen@gmail.com", "12345678", "Marwen", "Hammami", 123456789, "GOOD", "Client", 3, "client", true, "path/to/img.png")
//        var currentUser: User = User("655367c3c2687b023c32e357","Sami","samir.tounsi@gmail.com", "12345678", "Samir", "Tounsi", 123456789, "GOOD", "Client", 3, "client", true, "path/to/img.png")
        fun setUserList(list: ArrayList<User>) {
            listUsers = list
        }

        fun getUserList(): ArrayList<User>{
            return listUsers
        }
    }
}