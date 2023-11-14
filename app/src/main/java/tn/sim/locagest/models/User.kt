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
        fun setUserList(list: ArrayList<User>) {
            listUsers = list
        }

        fun getUserList(): ArrayList<User>{
            return listUsers
        }
    }
}